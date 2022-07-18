package edu.curso.java.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import edu.curso.java.demo.Repository.GeneroRepository;
import edu.curso.java.demo.bo.Genero;

@Service
@Transactional(rollbackFor = Exception.class)
public class GeneroServiceImpl implements GeneroService{

	@Autowired
	private GeneroRepository generoRepository; 
	@Override
	public Long guardarNuevoGenero(Genero genero) {
		generoRepository.save(genero);
		return genero.getIdGenero();
	}

	@Override
	@Transactional
	public void actualizarGenero(Genero genero) {

			if(genero!=null) {
	            generoRepository.save(genero);

	        } 
		else {
	            throw new Error("No se encontró el genero solicitado");
	        }
	}

	@Override
	@Transactional
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
	@Transactional
	public List<Genero> recuperarGenero() {
		return generoRepository.findAll();
	}

	@Override
	@Transactional
	public Genero buscarGeneroPorId(Long id) {
        Optional<Genero> respuesta = generoRepository.findById(id);
        if (respuesta.isPresent()) {
            return respuesta.get();
        } else {
            throw new Error("No se encontró el genero solicitado.");
        }
	}

}
