package com.example.enriquestyle.Literatura.repository;

import com.example.enriquestyle.Literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface iAutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findAll();

    List<Autor> findByNacimientoAutorLessThanEqualAndFallecimientoAutorGreaterThanEqual(int nacimiento, int fallecimiento);

    Optional<Autor> findFirstByNombreContainsIgnoreCase(String escritor);
}
