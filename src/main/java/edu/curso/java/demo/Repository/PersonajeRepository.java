package edu.curso.java.demo.Repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.curso.java.demo.bo.Personaje;



@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
	
    @Query("SELECT c FROM Personaje c WHERE c.nombre LIKE :nombre")
    public List<Personaje> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Personaje c WHERE c.edad = :edad")
    public List<Personaje> filtrarPorEdad(@Param("edad") Long edad);

    @Query("SELECT c FROM Personaje c WHERE c.peso = :peso")
    public List<Personaje> filtrarPorPeso(@Param("peso") Long peso);


}
