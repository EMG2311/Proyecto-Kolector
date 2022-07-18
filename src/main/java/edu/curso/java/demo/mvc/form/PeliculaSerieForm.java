package edu.curso.java.demo.mvc.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.bo.Personaje;

public class PeliculaSerieForm {
	private Long idPelicula;
	private String titulo;
	private Date fechaCreacion;
	private Long calificacion;
	private List<Personaje> PersonajeAsociados=new ArrayList<Personaje>();
	private MultipartFile fotoPeliculaSerie;
	
	
	public MultipartFile getFotoPeliculaSerie() {
		return fotoPeliculaSerie;
	}
	public void setFotoPeliculaSerie(MultipartFile fotoPeliculaSerie) {
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
	public List<Personaje> getPersonajeAsociados() {
		return PersonajeAsociados;
	}
	public void setPersonajeAsociados(List<Personaje> personajeAsociados) {
		PersonajeAsociados = personajeAsociados;
	}
}
