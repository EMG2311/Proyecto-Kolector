package edu.curso.java.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import edu.curso.java.demo.bo.Genero;


public interface GeneroService {

	Long guardarNuevoGenero(Genero genero) throws GeneroException;
	void actualizarGenero(Genero genero);
	void borrarGeneroporId(Long id);
	List<Genero> recuperarGenero();
	Genero buscarGeneroPorId(Long id);
}
