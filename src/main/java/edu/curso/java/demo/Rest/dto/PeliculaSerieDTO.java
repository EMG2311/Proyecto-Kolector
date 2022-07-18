package edu.curso.java.demo.Rest.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.bo.Personaje;

public class PeliculaSerieDTO {

	private Long idPelicula;
	@NotBlank
	private String titulo;
	private Date fechaCreacion;
	private Long calificacion;
	private List<Personaje> PersonajeAsociados;
	
	public PeliculaSerieDTO(){
	}
	public PeliculaSerieDTO(PeliculaSerie pelicula){
	{
		this.calificacion=pelicula.getCalificacion();
		this.fechaCreacion=pelicula.getFechaCreacion();
		this.idPelicula=pelicula.getIdPelicula();
		this.PersonajeAsociados=pelicula.getPersonajeAsociados();
		this.titulo=pelicula.getTitulo();
	}
	}
	public Long getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Long getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}
	
	public List<Personaje> getPersonajeAsociados() {
		return PersonajeAsociados;
	}
	public void setPersonajeAsociados(List<Personaje> personajeAsociados) {
		PersonajeAsociados = personajeAsociados;
	}
}
