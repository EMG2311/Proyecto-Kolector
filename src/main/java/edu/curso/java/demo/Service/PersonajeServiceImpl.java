package edu.curso.java.demo.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.demo.Repository.PersonajeRepository;
import edu.curso.java.demo.bo.Personaje;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonajeServiceImpl implements PersonajeService{

	@Autowired
	private PersonajeRepository personajeRepository;
	
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Long guardarNuevoPersonaje(Personaje personaje) throws PersonajeException {

		personajeRepository.save(personaje);
		return personaje.getId();
	}

	@Override
	public Personaje buscarPersonajePorId(Long id) {
		Optional<Personaje> personajeOptional = personajeRepository.findById(id);
		return personajeOptional.get();
	}

	@Override
	public List<Personaje> recuperarPersonaje() {
		// TODO Auto-generated method stub
		return personajeRepository.findAll();
	}

	@Override
	public List<Personaje> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		
		List<Personaje> personajeEncontrado = personajeRepository.buscarPorNombre(nombre);
		if(personajeEncontrado==null) {
			 throw new Error("No se encontró personaje con ese nombre.");
		}
		else {
			return personajeEncontrado;
		}
	}
	
	public List<Personaje> filtrarPorEdad(Long edad){
		List<Personaje> personajeEncontrado = personajeRepository.filtrarPorEdad(edad);
		if(personajeEncontrado==null) {
			throw new Error("No se encontro personaje con esa edad");
		}
		else {
			return personajeEncontrado;
		}
	}

	public List<Personaje> filtrarPorPeso(Long peso){
		List<Personaje> personajeEncontrado=personajeRepository.filtrarPorPeso(peso);
		if(personajeEncontrado==null) {
			throw new Error("No se encontro personaje con  ese peso");
		}
		else {
			return personajeEncontrado;
		}
	}

	@Override
	public void borrarPersonajePorId(Long id) {
		personajeRepository.deleteById(id);
		
	}

	@Override
	public void actualizarPersonaje(Personaje personaje) {
		// TODO Auto-generated method stub
		personajeRepository.save(personaje);
		}

}
