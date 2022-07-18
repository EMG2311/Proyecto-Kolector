package edu.curso.java.demo.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Genero {
	@Id
	@GeneratedValue
	private Long idGenero;
	private String nombre;
	
	
	//private List<PeliculaSerie> peliculasAsociadas = new ArrayList<PeliculaSerie>();
	
	
	
	public Genero() {
		
	}
	
	public Genero(Long idGenero, String nombre) {
		super();
		this.idGenero = idGenero;
		this.nombre = nombre;
		//this.peliculasAsociadas = peliculasAsociadas;
	}

	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*public List<PeliculaSerie> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}
	public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	}
	*/

}
