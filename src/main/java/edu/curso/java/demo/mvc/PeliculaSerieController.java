package edu.curso.java.demo.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.curso.java.demo.Service.PeliculaSerieException;
import edu.curso.java.demo.Service.PeliculaSerieService;
import edu.curso.java.demo.Service.PersonajeException;
import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.bo.Personaje;
import edu.curso.java.demo.mvc.form.PeliculaSerieForm;
import edu.curso.java.demo.mvc.form.PersonajeForm;


@Controller
@RequestMapping("/peliculas")
public class PeliculaSerieController {
	private static  Logger log = LoggerFactory.getLogger(PersonajeController.class);
	@Autowired
	private PeliculaSerieService peliculaService;
	
	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		PeliculaSerie pelicula = peliculaService.buscarPeliculaSeriePorId(id);
		model.addAttribute("pelicula", pelicula);
		return "/peliculas/ver";
	}
	
	@GetMapping("/{id}/borrar")
	public String borrar(Model model, @PathVariable Long id) {
		peliculaService.eliminarPeliculaSeriePorId(id);
		return "redirect:/peliculas";
	}
	@GetMapping
	public String listar(Model model) {
		List<PeliculaSerie> pelicula = peliculaService.recuperarPeliculaSerie();
		model.addAttribute("peliculas", pelicula);
		return "/peliculas/listar";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		
		model.addAttribute("peliculaForm", new PeliculaSerieForm());
		return "/peliculas/form";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		PeliculaSerie pelicula = peliculaService.buscarPeliculaSeriePorId(id);
		
		PeliculaSerieForm peliculaForm = new PeliculaSerieForm();
		peliculaForm.setTitulo(pelicula.getTitulo());
		peliculaForm.setCalificacion(pelicula.getCalificacion());
		peliculaForm.setFechaCreacion(pelicula.getFechaCreacion());
		peliculaForm.setPersonajeAsociados(pelicula.getPersonajeAsociados());
		
	
		model.addAttribute("peliculaForm", peliculaForm);
		return "/peliculas/form";
	}
	
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "peliculaForm") PeliculaSerieForm peliculaForm, BindingResult bindingResult, Model model) {

		log.info("Ejecutando el guardar: " + bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("peliculaForm", peliculaForm);
			return "/peliculas/form";
		}
		
		PeliculaSerie pelicula = null;
		Long idPelicula = peliculaForm.getIdPelicula();
		
		if(idPelicula == null) {
			pelicula = new PeliculaSerie();
		} else {
			pelicula = peliculaService.buscarPeliculaSeriePorId(idPelicula);
		}
		
		pelicula.setTitulo(peliculaForm.getTitulo());
		pelicula.setCalificacion(pelicula.getCalificacion());
		pelicula.setFechaCreacion(pelicula.getFechaCreacion());
		pelicula.setPersonajeAsociados(pelicula.getPersonajeAsociados());
		
		System.out.println(peliculaForm.getFotoPeliculaSerie().getOriginalFilename() + " " + peliculaForm.getFotoPeliculaSerie().getSize());
		
		
		if(idPelicula == null) {
			try {
				peliculaService.agregarPeliculaSerie(pelicula);
			} catch (PeliculaSerieException e) {
				log.error("Error al gurdar un nuevo personaje", e.getMessage());
				return "redirect:/error";
			}

		} else {
			peliculaService.actualizarPeliculaSerie(pelicula);
		}
		
		
		File archivoImagen = new File("C:/Users/alche/imagenes-templ-peliculas/foto-" + pelicula.getIdPelicula() +pelicula.getTitulo()+ ".jpg");

		try(FileOutputStream out = new FileOutputStream(archivoImagen)) {
			out.write(peliculaForm.getFotoPeliculaSerie().getBytes());
			
		} catch (FileNotFoundException e) {
			log.error("Archivo no encontrado", e);
		} catch (IOException e) {
			log.error("Error al guardar el archivo", e);
		}

		return "redirect:/peliculas";
	}
	
	@GetMapping(value = "/recuperar-foto-pelicula/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] recuperarFotoPelicula(@PathVariable Long id) {
		
		PeliculaSerie pelicula = peliculaService.buscarPeliculaSeriePorId(id);

		if(pelicula != null) {
			File archivoImagen = new File("C:/Users/alche/imagenes-templ-peliculas/foto-" + pelicula.getIdPelicula() + pelicula.getTitulo() + ".jpg");
			if(archivoImagen.exists()) {
				try(FileInputStream in = new FileInputStream(archivoImagen)) {
					return in.readAllBytes();					
				} catch (FileNotFoundException e) {
					log.error("Archivo no encontrado", e);
				} catch (IOException e) {
					log.error("Error al leer el archivo", e);
				}
			}
		}
		return null;
	}
}
