package edu.curso.java.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.curso.java.demo.bo.PeliculaSerie;


@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long>{
	
    @Query("SELECT p FROM PeliculaSerie p WHERE p.titulo LIKE :nombre")
    public List<PeliculaSerie> buscarPorNombre(@Param("nombre") String nombre);

    
    @Query("SELECT p FROM PeliculaSerie p ORDER BY p.titulo ASC")
    public List<PeliculaSerie> ordenAscendente();

    @Query("SELECT p FROM PeliculaSerie p ORDER BY p.titulo DESC")
    public List<PeliculaSerie> ordenDescendente();

}
