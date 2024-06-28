package com.example.enriquestyle.Literatura.repository;

import com.example.enriquestyle.Literatura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iLibroRepository extends JpaRepository<Libros, Long> {

    boolean existsByTitulo(String titulo);

    Libros findByTituloContainsIgnoreCase(String titulo);

    List<Libros> findByIdioma(String idioma);


}


