package edu.curso.java.demo.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class PeliculaSerie {
	
	
	
	
	public PeliculaSerie() {
		
	}
	public PeliculaSerie(Long idPelicula, String titulo, Date fechaCreacion, Long calificacion,List<Personaje> personajeAsociados) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
		this.calificacion = calificacion;
		this.personajeAsociados = personajeAsociados;
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
		return personajeAsociados;
	}
	public void setPersonajeAsociados(List<Personaje> personajeAsociados) {
		this.personajeAsociados = personajeAsociados;
	}
	@Id
	@GeneratedValue
	private Long idPelicula;
	private String titulo;
	private Date fechaCreacion;
	private Long calificacion;
	
	@ManyToMany
	private List<Personaje> personajeAsociados = new ArrayList<Personaje>();
	
}
