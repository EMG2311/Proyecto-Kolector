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

import edu.curso.java.demo.Service.PersonajeException;
import edu.curso.java.demo.Service.PersonajeService;
import edu.curso.java.demo.bo.Personaje;
import edu.curso.java.demo.mvc.form.PersonajeForm;




@Controller
@RequestMapping("/personajes")
public class PersonajeController {
	private static  Logger log = LoggerFactory.getLogger(PersonajeController.class);

	@Autowired
	private PersonajeService personajeService;
	
	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		Personaje personaje = personajeService.buscarPersonajePorId(id);
		model.addAttribute("personaje", personaje);
		return "/personajes/ver";
	}
	
	@GetMapping("/{id}/borrar")
	public String borrar(Model model, @PathVariable Long id) {
		personajeService.borrarPersonajePorId(id);
		return "redirect:/personajes";
	}
	@GetMapping
	public String listar(Model model) {
		List<Personaje> personaje = personajeService.recuperarPersonaje();
		model.addAttribute("personajes", personaje);
		return "/personajes/listar";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		
		model.addAttribute("personajeForm", new PersonajeForm());
		return "/personajes/form";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		Personaje personaje = personajeService.buscarPersonajePorId(id);
		
		PersonajeForm personajeForm = new PersonajeForm();
		personajeForm.setIdPersonaje(personaje.getId());
		personajeForm.setNombre(personaje.getNombre());
		personajeForm.setEdad(personaje.getEdad());
		personajeForm.setHistoria(personaje.getHistoria());
		personajeForm.setPeliculasAsociadas(personaje.getPeliculasAsociadas());
		personaje.setPeso(personaje.getPeso());
		
	
		model.addAttribute("personajeForm", personajeForm);
		return "/personajes/form";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "personajeForm") PersonajeForm personajeForm, BindingResult bindingResult, Model model) {

		log.info("Ejecutando el guardar: " + bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("personajeForm", personajeForm);
			return "/personajes/form";
		}
		
		Personaje personaje = null;
		Long idPersonaje = personajeForm.getIdPersonaje();
		
		if(idPersonaje == null) {
			personaje = new Personaje();
		} else {
			personaje = personajeService.buscarPersonajePorId(idPersonaje);
		}
		
		personaje.setNombre(personajeForm.getNombre());
		personaje.setEdad(personajeForm.getEdad());
		personaje.setHistoria(personajeForm.getHistoria());
		personaje.setPeliculasAsociadas(personajeForm.getPeliculasAsociadas());
		personaje.setPeso(personajeForm.getPeso());

		
		System.out.println(personajeForm.getFotoPersonaje().getOriginalFilename() + " " + personajeForm.getFotoPersonaje().getSize());
		
		
		if(idPersonaje == null) {
			try {
				personajeService.guardarNuevoPersonaje(personaje);
			} catch (PersonajeException e) {
				log.error("Error al gurdar un nuevo personaje", e.getMessage());
				return "redirect:/error";
			}

		} else {
			personajeService.actualizarPersonaje(personaje);
		}
		
		
		File archivoImagen = new File("C:/Users/alche/imagenes-templ/foto-" + personaje.getId() +personaje.getNombre()+ ".jpg");

		try(FileOutputStream out = new FileOutputStream(archivoImagen)) {
			out.write(personajeForm.getFotoPersonaje().getBytes());
			
		} catch (FileNotFoundException e) {
			log.error("Archivo no encontrado", e);
		} catch (IOException e) {
			log.error("Error al guardar el archivo", e);
		}

		return "redirect:/personajes";
	}
	
	@GetMapping(value = "/recuperar-foto-personaje/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] recuperarFotoPersonaje(@PathVariable Long id) {
		
		Personaje personaje = personajeService.buscarPersonajePorId(id);

		if(personaje != null) {
			File archivoImagen = new File("C:/Users/alche/imagenes-templ/foto-" + personaje.getId() + personaje.getNombre() + ".jpg");
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
