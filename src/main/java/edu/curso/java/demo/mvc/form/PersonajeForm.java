package edu.curso.java.demo.mvc.form;


import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import edu.curso.java.demo.bo.PeliculaSerie;

public class PersonajeForm {


	private Long idPersonaje;
	@NotBlank
	private String nombre;
	
	@NotNull
	@Positive
	private Long edad;
	
	@Positive
	private Long peso;
	@NotNull
	private String Historia;
	@NotNull
	private List<PeliculaSerie> peliculasAsociadas = new ArrayList<PeliculaSerie>();	
	
	private MultipartFile fotoPersonaje;
		
		public MultipartFile getFotoPersonaje() {
		return fotoPersonaje;
	}
	public void setFotoPersonaje(MultipartFile fotoPersonaje) {
		this.fotoPersonaje = fotoPersonaje;
	}
		public Long getIdPersonaje() {
			return idPersonaje;
		}
		public void setIdPersonaje(Long idPersonaje) {
			this.idPersonaje = idPersonaje;
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
			return Historia;
		}
		public void setHistoria(String historia) {
			Historia = historia;
		}
		public List<PeliculaSerie> getPeliculasAsociadas() {
			return peliculasAsociadas;
		}
		public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
			this.peliculasAsociadas = peliculasAsociadas;
		}


		

	}

