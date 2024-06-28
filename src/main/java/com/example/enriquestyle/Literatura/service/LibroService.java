//package com.example.enriquestyle.Literatura.service;
//
//import com.example.enriquestyle.Literatura.model.Libros;
//import com.example.enriquestyle.Literatura.repository.LibroRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class LibroService {
//    @Autowired
//    private LibroRepository libroRepository;
//
//    public void guardarLibro(Libros libros){
//        libroRepository.save(libros);
//    }
//
//    public List<Libros> getAllLibros() {
//        return libroRepository.findAll();
//    }
//
//    public List<Libros> getLibrosByIdioma(String idiomas) {
//        return libroRepository.findByIdioma(idiomas);
//    }
//}
