package com.example.enriquestyle.Literatura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer nacimientoAutor;
    private Integer fallecimientoAutor;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libros> libros;


    public Autor(){}

    public Autor(com.example.enriquestyle.Literatura.model.DatosAutor autor) {
        this.nombre = autor.nombre();
        this.nacimientoAutor = autor.nacimientoAutor();
        this.fallecimientoAutor = autor.fallecimientoautor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimientoAutor() {
        return nacimientoAutor;
    }

    public void setNacimientoAutor(Integer nacimientoAutor) {
        this.nacimientoAutor = nacimientoAutor;
    }

    public Integer getFallecimientoautor() {
        return fallecimientoAutor;
    }

    public void setFallecimientoautor(Integer fallecimientoautor) {
        this.fallecimientoAutor = fallecimientoautor;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return
                ", nombre = '" + nombre + '\'' +
                ", nacimientoAutor = " + nacimientoAutor +
                ", fallecimientoautor = " + fallecimientoAutor +
                '}';
    }
}
