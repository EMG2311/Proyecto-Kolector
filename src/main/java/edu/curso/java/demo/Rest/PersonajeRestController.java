package edu.curso.java.demo.Rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import edu.curso.java.demo.Service.PersonajeException;
import edu.curso.java.demo.Service.PersonajeService;
import edu.curso.java.demo.bo.Personaje;

import edu.curso.java.demo.Rest.dto.*;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonajeRestController {
	
	private static  Logger log = LoggerFactory.getLogger(PersonajeRestController.class);
	
	@Autowired
	private PersonajeService personajeService;
	
	@GetMapping("/personajes/buscar/")
	public List<PersonajeDTO> buscarPersonaje(@RequestParam String nombre) {
		
		log.info("Param nombre: " + nombre);
		List<Personaje> personajes = null;
			personajes = personajeService.buscarPorNombre(nombre);		
	
			
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		for (Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		return personajesDTO;
	}
	
	@GetMapping("/personajes")
	public List<PersonajeDTO> recuperarTodosLosPersonajes() {
		List<Personaje> personajes = personajeService.recuperarPersonaje();
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		for (Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		return personajesDTO;
	}
	@GetMapping("/personajes/{id}")
	public ResponseEntity<PersonajeDTO> recuperarPersonajePorId(@PathVariable Long id) {
		Personaje personaje = personajeService.buscarPersonajePorId(id);
		PersonajeDTO personajeDTO = new PersonajeDTO(personaje);
		return ResponseEntity.ok(personajeDTO);
	}
	
	@PostMapping("/personajes")
	public ResponseEntity<PersonajeDTO> altaDeNuevoPersonaje(@Valid @RequestBody PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEdad(personajeDTO.getEdad());
		personaje.setHistoria(personajeDTO.getHistoria());
		//personaje.setPeliculasAsociadas(personajeDTO.getPeliculasAsociadas());
		personaje.setPeso(personajeDTO.getPeso());
		Long idGenerado;
		try {
			idGenerado = personajeService.guardarNuevoPersonaje(personaje);
			personajeDTO.setId(idGenerado);

		} catch (PersonajeException e) {
			log.error("Error al dar de alta nuevo personaje", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(personajeDTO);
	}
	
	@PutMapping("/personajes/{id}")
	public void actualizarPersonajePorId(@PathVariable Long id, @Valid @RequestBody PersonajeDTO personajeDTO) {
		Personaje personaje =personajeService.buscarPersonajePorId(id);
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEdad(personajeDTO.getEdad());
		personaje.setHistoria(personajeDTO.getHistoria());
	//	personaje.setPeliculasAsociadas(personajeDTO.getPeliculasAsociadas());
		personaje.setPeso(personajeDTO.getPeso());
		personajeService.actualizarPersonaje(personaje);
	}
	
	@DeleteMapping("/personajes/{id}")
	public ResponseEntity<?> borrarPersonajePorId(@PathVariable Long id) {
		personajeService.borrarPersonajePorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	
	

}
