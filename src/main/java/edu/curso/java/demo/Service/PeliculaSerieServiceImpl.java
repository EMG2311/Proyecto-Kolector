package edu.curso.java.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.curso.java.demo.Repository.GeneroRepository;
import edu.curso.java.demo.Repository.PeliculaSerieRepository;
import edu.curso.java.demo.bo.Genero;
import edu.curso.java.demo.bo.PeliculaSerie;

@Service
@Transactional(rollbackFor = Exception.class)
public class PeliculaSerieServiceImpl implements PeliculaSerieService{
	
	@Autowired
	private PeliculaSerieRepository peliculaSerieRepository;
	@Autowired
	private GeneroRepository generoRepository;
	
	@Override
	public Long agregarPeliculaSerie(PeliculaSerie peliculaSerie) {
		peliculaSerieRepository.save(peliculaSerie);
		return peliculaSerie.getIdPelicula();
	}

	@Override
	public void actualizarPeliculaSerie(PeliculaSerie pelicula) {
		peliculaSerieRepository.save(pelicula);
		
	}

	@Override
	public void eliminarPeliculaSeriePorId(Long id) {
		peliculaSerieRepository.deleteById(id);
		
	}

	@Override
	public PeliculaSerie buscarPeliculaSeriePorId(Long id) {
		Optional<PeliculaSerie> peliculaSerieOptional = peliculaSerieRepository.findById(id);
		return peliculaSerieOptional.get();
	}

	@Override
	public List<PeliculaSerie> buscarPeliculaSeriePorNombre(String nombre) {
		List<PeliculaSerie> PeliculaSerieEncontrada = peliculaSerieRepository.buscarPorNombre(nombre);
		if(PeliculaSerieEncontrada==null) {
			 throw new Error("No se encontró personaje con ese nombre.");
		}
		else {
			return PeliculaSerieEncontrada;
		}
	}

	/*@Override
	public List<PeliculaSerie> filtrarPeliculaSeriePorGenero(Long idGenero) {
        Optional<Genero> genero = generoRepository.findById(idGenero);
        if (genero.isPresent()) {
            return genero.get().getPeliculasAsociadas();
        } else{
            throw new Error("No se pueden cargar las peliculas para este genero");
        }
	}*/
	

	@Override
	public List<PeliculaSerie> Ordenar(String orden) {
		 if (orden.equalsIgnoreCase("ASC")) {
	            return peliculaSerieRepository.ordenAscendente();
	        } else if (orden.equalsIgnoreCase("DESC")) {
	            return peliculaSerieRepository.ordenDescendente();
	        } else {
	            throw new Error("No se pueden ordenar las peliculas");
	        }
	}

	@Override
	public List<PeliculaSerie> recuperarPeliculaSerie() {
		 List<PeliculaSerie> peliculaSerieRecuperada = peliculaSerieRepository.findAll();

	        if (peliculaSerieRecuperada != null) {
	            return peliculaSerieRecuperada;
	        } else {
	            throw new Error("No se encontraron peliculas para presentar");
	        }
	}



}
