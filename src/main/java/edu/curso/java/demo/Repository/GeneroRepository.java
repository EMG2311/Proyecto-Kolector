package edu.curso.java.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.curso.java.demo.bo.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

	
}
