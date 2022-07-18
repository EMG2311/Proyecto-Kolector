package edu.curso.java.demo.mvc.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.enumeraciones.tituloGeneroEnumeracion;

public class GeneroForm {
	@NotBlank
	private tituloGeneroEnumeracion nombre;
	private List<PeliculaSerie> PeliculasAsociadas = new ArrayList<PeliculaSerie>();
	private Long idGenero;
	private MultipartFile fotoGenero;
	
	
	public MultipartFile getFoto() {
		return fotoGenero;
	}
	public void setFoto(MultipartFile fotoGenero) {
		this.fotoGenero = fotoGenero;
	}
	
	
	public tituloGeneroEnumeracion getNombre() {
		return nombre;
	}
	public void setNombre(tituloGeneroEnumeracion nombre) {
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
