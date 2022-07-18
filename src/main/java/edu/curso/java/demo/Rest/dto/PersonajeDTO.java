package edu.curso.java.demo.Rest.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.bo.Personaje;

public class PersonajeDTO {
	
	
	public PersonajeDTO(){}
	public PersonajeDTO(Personaje personaje) {
		this.idPersonaje=personaje.getId();
		this.edad=personaje.getEdad();
		this.historia=personaje.getHistoria();
		this.nombre=personaje.getNombre();
		this.peliculasAsociadas=personaje.getPeliculasAsociadas();
		this.peso=personaje.getPeso();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getEdad() {
		return edad;
	}
	public void setEdad(Long edad) {
		this.edad = edad;
	}
	public Long getPeso() {
		return peso;
	}
	public void setPeso(Long peso) {
		this.peso = peso;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public List<PeliculaSerie> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}
	public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	}

	private Long idPersonaje;
	public Long getId() {
		return idPersonaje;
	}
	public void setId(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	
	@NotBlank
	private String nombre;
	
	@Positive
	private Long edad;
	@Positive
	private Long peso;
	private String historia;
	private List<PeliculaSerie> peliculasAsociadas;
}
