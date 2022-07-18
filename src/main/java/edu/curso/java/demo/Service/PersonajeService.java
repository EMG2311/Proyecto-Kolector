package edu.curso.java.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import edu.curso.java.demo.bo.Personaje;


public interface PersonajeService{
	Long guardarNuevoPersonaje(Personaje personaje)throws PersonajeException;

	Personaje buscarPersonajePorId(Long id);
	

	List<Personaje> recuperarPersonaje();

	List<Personaje>  buscarPorNombre(String nombre);
	List<Personaje> filtrarPorEdad(Long edad);
	List<Personaje> filtrarPorPeso(Long peso);

	void borrarPersonajePorId(Long id);
	
	void actualizarPersonaje(Personaje personaje);
}
