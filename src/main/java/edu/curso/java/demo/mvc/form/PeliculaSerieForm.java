package edu.curso.java.demo.mvc.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.curso.java.demo.bo.PeliculaSerie;

public class PeliculaSerieForm {
	private Long idPelicula;
	private String titulo;
	private Date fechaCreacion;
	private Long calificacion;
	private List<PeliculaSerie> PersonajeAsociados;
	private MultipartFile fotoPeliculaSerie;
	
	
	public MultipartFile getFoto() {
		return fotoPeliculaSerie;
	}
	public void setFoto(MultipartFile fotoPeliculaSerie) {
		this.fotoPeliculaSerie = fotoPeliculaSerie;
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
	public List<PeliculaSerie> getPersonajeAsociados() {
		return PersonajeAsociados;
	}
	public void setPersonajeAsociados(List<PeliculaSerie> personajeAsociados) {
		PersonajeAsociados = personajeAsociados;
	}
}
