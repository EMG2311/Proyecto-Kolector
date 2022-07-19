package edu.curso.java.demo.Rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.curso.java.demo.Rest.dto.GeneroDTO;
import edu.curso.java.demo.Service.GeneroException;
import edu.curso.java.demo.Service.GeneroService;
import edu.curso.java.demo.bo.Genero;


@RestController
@RequestMapping("/api")
public class GeneroRestController {
	private static  Logger log = LoggerFactory.getLogger(GeneroRestController.class);
	@Autowired
	private GeneroService generoService;
	
	
	@GetMapping("/generos/{id}")
	public ResponseEntity<GeneroDTO> recuperarGeneroPorID(@PathVariable Long id) {
		Genero genero = generoService.buscarGeneroPorId(id);
		GeneroDTO generoDTO = new GeneroDTO(genero);
		return ResponseEntity.ok(generoDTO);
	
}
	@GetMapping("/generos")
	public List<GeneroDTO> recuperarTodosLosGeneros() {
		List<Genero> generos = generoService.recuperarGenero();
		List<GeneroDTO> generosDTO = new ArrayList<GeneroDTO>();
		for (Genero genero : generos) {
			generosDTO.add(new GeneroDTO(genero));
		}
		return generosDTO;
}
	@PostMapping("/generos")
	public ResponseEntity<GeneroDTO> altaNuevoGenero(@Valid @RequestBody GeneroDTO generoDTO) {
		Genero genero = new Genero();
		genero.setNombre(generoDTO.getNombre());
		genero.setPeliculasAsociadas(generoDTO.getPeliculasAsociadas());
		
		Long idGenerado;
		try {
			idGenerado = generoService.guardarNuevoGenero(genero);
			generoDTO.setIdGenero(idGenerado);

		} catch (GeneroException e) {
			log.error("Error al dar de alta nuevo genero", e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(generoDTO);
	}
	
	@PutMapping("/generos/{id}")
	public void actualizarGeneroPorId(@PathVariable Long id, @Valid @RequestBody GeneroDTO generoDTO) {
		Genero genero =generoService.buscarGeneroPorId(id);
		genero.setNombre(generoDTO.getNombre());
		genero.setPeliculasAsociadas(generoDTO.getPeliculasAsociadas());
		
		generoService.actualizarGenero(genero);
	}
	
	@DeleteMapping("/generos/{id}")
	public ResponseEntity<?> borrarGeneroPorId(@PathVariable Long id) {
		generoService.borrarGeneroporId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	
}
	
