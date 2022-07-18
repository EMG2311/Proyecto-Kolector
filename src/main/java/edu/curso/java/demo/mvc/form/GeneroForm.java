package edu.curso.java.demo.mvc.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.curso.java.demo.bo.PeliculaSerie;

public class GeneroForm {
	private String nombre;
	private List<PeliculaSerie> PeliculasAsociadas;
	private Long idGenero;
	private MultipartFile fotoGenero;
	
	
	public MultipartFile getFoto() {
		return fotoGenero;
	}
	public void setFoto(MultipartFile fotoGenero) {
		this.fotoGenero = fotoGenero;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<PeliculaSerie> getPeliculasAsociadas() {
		return PeliculasAsociadas;
	}
	public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
		PeliculasAsociadas = peliculasAsociadas;
	}
	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
}
