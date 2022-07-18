package edu.curso.java.demo.Rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.curso.java.demo.Rest.dto.PeliculaSerieDTO;
import edu.curso.java.demo.Rest.dto.PersonajeDTO;
import edu.curso.java.demo.Service.GeneroService;
import edu.curso.java.demo.Service.PeliculaSerieException;
import edu.curso.java.demo.Service.PeliculaSerieService;
import edu.curso.java.demo.Service.PersonajeException;
import edu.curso.java.demo.Service.PersonajeService;
import edu.curso.java.demo.bo.Genero;
import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.bo.Personaje;


@RestController
@RequestMapping("/api")
public class PeliculaSerieRestController {
	private static  Logger log = LoggerFactory.getLogger(PeliculaSerieRestController.class);
	
	
	@Autowired
	private PeliculaSerieService peliculaSerieService;
	
	
	@GetMapping("/peliculas/buscar")
	public List<PeliculaSerieDTO> buscarPeliculaSerie(@RequestParam String nombre) {
		
		log.info("Param nombre: " + nombre);
		List<PeliculaSerie> peliculas = null;
			peliculas = peliculaSerieService.buscarPeliculaSeriePorNombre(nombre);		
	
			
		List<PeliculaSerieDTO> peliculasDTO = new ArrayList<PeliculaSerieDTO>();
		for (PeliculaSerie pelicula : peliculas) {
			peliculasDTO.add(new PeliculaSerieDTO(pelicula));
		}
		
		return peliculasDTO;
	}
	
	@GetMapping("/peliculas")
	public List<PeliculaSerieDTO> recuperarTodasLasPeliculasSeries() {
		List<PeliculaSerie> peliculas = peliculaSerieService.recuperarPeliculaSerie();
		List<PeliculaSerieDTO> peliculasDTO = new ArrayList<PeliculaSerieDTO>();
		for (PeliculaSerie pelicula : peliculas) {
			peliculasDTO.add(new PeliculaSerieDTO(pelicula));
		}
		return peliculasDTO;
}
	@GetMapping("/peliculas/{id}")
	public ResponseEntity<PeliculaSerieDTO> recuperarPeliculaSeriePorId(@PathVariable Long id) {
		PeliculaSerie pelicula = peliculaSerieService.buscarPeliculaSeriePorId(id);
		PeliculaSerieDTO peliculaDTO = new PeliculaSerieDTO(pelicula);
		return ResponseEntity.ok(peliculaDTO);
	}
	
		
	@PostMapping("/peliculas")
	public ResponseEntity<PeliculaSerieDTO> altaDeNuevoPersonaje(@Valid @RequestBody PeliculaSerieDTO peliculaSerieDTO) {
		PeliculaSerie pelicula = new PeliculaSerie();
		pelicula.setTitulo(peliculaSerieDTO.getTitulo());
		pelicula.setCalificacion(peliculaSerieDTO.getCalificacion());
		pelicula.setFechaCreacion(peliculaSerieDTO.getFechaCreacion());
		pelicula.setPersonajeAsociados(peliculaSerieDTO.getPersonajeAsociados());
		
		Long idGenerado;
		try {
			idGenerado = peliculaSerieService.agregarPeliculaSerie(pelicula);
			peliculaSerieDTO.setIdPelicula(idGenerado);

		} catch (PeliculaSerieException e) {
			log.error("Error al dar de alta nueva pelicula/serie", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSerieDTO);
	}
	
	@PutMapping("/peliculas/{id}")
	public void actualizarPeliculaSeriePorId(@PathVariable Long id, @Valid @RequestBody PeliculaSerieDTO peliculaDTO) {
		PeliculaSerie pelicula =peliculaSerieService.buscarPeliculaSeriePorId(id);
		pelicula.setCalificacion(peliculaDTO.getCalificacion());
		pelicula.setFechaCreacion(peliculaDTO.getFechaCreacion());
		pelicula.setPersonajeAsociados(peliculaDTO.getPersonajeAsociados());
		pelicula.setTitulo(peliculaDTO.getTitulo());
		
		peliculaSerieService.actualizarPeliculaSerie(pelicula);
	}
	
	@DeleteMapping("/peliculas/{id}")
	public ResponseEntity<?> borrarPeliculaSeriePorId(@PathVariable Long id) {
		peliculaSerieService.eliminarPeliculaSeriePorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
}
