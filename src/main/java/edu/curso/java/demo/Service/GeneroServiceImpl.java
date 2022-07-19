package edu.curso.java.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import edu.curso.java.demo.Repository.GeneroRepository;
import edu.curso.java.demo.bo.Genero;

@Service
@Transactional(rollbackFor = Exception.class)
public class GeneroServiceImpl implements GeneroService{

	@Autowired
	private GeneroRepository generoRepository; 
	
	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public Long guardarNuevoGenero(Genero genero)throws GeneroException {
		generoRepository.save(genero);
		return genero.getIdGenero();
	}

	@Override
	public void actualizarGenero(Genero genero) {

			if(genero!=null) {
	            generoRepository.save(genero);

	        } 
		else {
	            throw new Error("No se encontró el genero solicitado");
	        }
	}

	@Override
	public void borrarGeneroporId(Long id) {
        Optional<Genero> respuesta = generoRepository.findById(id);
        if (respuesta.isPresent()) {
            Genero genero = respuesta.get();
            generoRepository.delete(genero);
        } else {
            throw new Error("No se encontró el genero solicitado");
        }

    }
	

	@Override
	public List<Genero> recuperarGenero() {
		return generoRepository.findAll();
	}

	@Override
	public Genero buscarGeneroPorId(Long id) {
        Optional<Genero> respuesta = generoRepository.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new Error("No se encontró el genero solicitado.");
        }
	}

}
