package edu.curso.java.demo.Rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import edu.curso.java.demo.bo.Genero;
import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.enumeraciones.tituloGeneroEnumeracion;

public class GeneroDTO {
	private Long idGenero;
	
	@NotBlank
	private tituloGeneroEnumeracion nombre;
	private List<PeliculaSerie> peliculasAsociadas = new ArrayList<PeliculaSerie>();
	
	
	
	public GeneroDTO() {
		
	}
	
	public GeneroDTO(Genero genero) {
		super();
		this.idGenero = genero.getIdGenero();
		this.nombre = genero.getNombre();
		this.peliculasAsociadas = genero.getPeliculasAsociadas();
	}

	public Long getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}
	public tituloGeneroEnumeracion getNombre() {
		return nombre;
	}
	public void setNombre(tituloGeneroEnumeracion nombre) {
		this.nombre = nombre;
	}
	public List<PeliculaSerie> getPeliculasAsociadas() {
		return peliculasAsociadas;
	}
	public void setPeliculasAsociadas(List<PeliculaSerie> peliculasAsociadas) {
		this.peliculasAsociadas = peliculasAsociadas;
	}
	
}
