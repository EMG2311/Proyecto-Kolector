package edu.curso.java.demo.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Personaje {
	
	@Id
	@GeneratedValue
	private Long idPersonaje;
	private String nombre;
	private Long edad;
	private Long peso;
	private String historia;
	
	//private List<PeliculaSerie> peliculasAsociadas = new ArrayList<PeliculaSerie>();
	
	
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
	/*public List<PeliculaSerie> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}
	public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	*/

	public Long getId() {
		return idPersonaje;
	}
	public void setId(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public Long getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	
}
