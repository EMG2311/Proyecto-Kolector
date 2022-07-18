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

import edu.curso.java.demo.Service.GeneroException;
import edu.curso.java.demo.Service.GeneroService;
import edu.curso.java.demo.Service.PeliculaSerieException;
import edu.curso.java.demo.bo.Genero;
import edu.curso.java.demo.bo.PeliculaSerie;
import edu.curso.java.demo.mvc.form.GeneroForm;
import edu.curso.java.demo.mvc.form.PeliculaSerieForm;

@Controller
@RequestMapping("/generos")
public class GeneroController {
	private static  Logger log = LoggerFactory.getLogger(PersonajeController.class);
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		Genero genero = generoService.buscarGeneroPorId(id);
		model.addAttribute("generos", genero);
		return "/generos/ver";
	}
	
	@GetMapping("/{id}/borrar")
	public String borrar(Model model, @PathVariable Long id) {
		generoService.borrarGeneroporId(id);
		return "redirect:/generos";
	}
	@GetMapping
	public String listar(Model model) {
		List<Genero> genero = generoService.recuperarGenero();
		model.addAttribute("generos", genero);
		return "/generos/listar";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		
		model.addAttribute("generoForm", new GeneroForm());
		return "/generos/form";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		Genero genero = generoService.buscarGeneroPorId(id);
		
		GeneroForm generoForm = new GeneroForm();
		generoForm.setNombre(genero.getNombre());
		generoForm.setPeliculasAsociadas(genero.getPeliculasAsociadas());
		
	
		model.addAttribute("generoForm", generoForm);
		return "/generos/form";
	}
	
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "generoForm") GeneroForm generoForm, BindingResult bindingResult, Model model) {

		log.info("Ejecutando el guardar: " + bindingResult.hasErrors());
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("generoForm", generoForm);
			return "/generos/form";
		}
		
		Genero genero = null;
		Long idGenero = generoForm.getIdGenero();
		
		if(idGenero == null) {
			genero = new Genero();
		} else {
			genero = generoService.buscarGeneroPorId(idGenero);
		}
		
		genero.setNombre(generoForm.getNombre());
		genero.setPeliculasAsociadas(generoForm.getPeliculasAsociadas());
		
		System.out.println(generoForm.getFoto().getOriginalFilename() + " " + generoForm.getFoto().getSize());
		
		
		if(idGenero == null) {
			try {
				generoService.guardarNuevoGenero(genero);
			} catch (GeneroException e) {
				log.error("Error al gurdar un nuevo genero", e.getMessage());
				return "redirect:/error";
			}

		} else {
			generoService.actualizarGenero(genero);
		}
		
		
		File archivoImagen = new File("C:/Users/alche/imagenes-templ-generos/foto-" + genero.getIdGenero() +genero.getNombre()+ ".jpg");

		try(FileOutputStream out = new FileOutputStream(archivoImagen)) {
			out.write(generoForm.getFoto().getBytes());
			
		} catch (FileNotFoundException e) {
			log.error("Archivo no encontrado", e);
		} catch (IOException e) {
			log.error("Error al guardar el archivo", e);
		}

		return "redirect:/generos";
	}
	
	@GetMapping(value = "/recuperar-foto-genero/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] recuperarFotoGenero(@PathVariable Long id) {
		
		Genero genero = generoService.buscarGeneroPorId(id);

		if(genero != null) {
			File archivoImagen = new File("C:/Users/alche/imagenes-templ-generos/foto-" + genero.getIdGenero() + genero.getNombre() + ".jpg");
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
