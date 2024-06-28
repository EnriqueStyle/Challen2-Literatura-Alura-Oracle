package com.example.enriquestyle.Literatura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Esto ayudara a que el ID sea autoincrementable
    private Long Id;
//    private Long libroId;
    @Column(unique = true)
    private String titulo;
    private String idioma;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libros(){}

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        if (datosLibros.autor() != null && !datosLibros.autor().isEmpty()) {
            this.autor = new Autor(datosLibros.autor().get(0));
        } else {
            this.autor = null;
        }
        this.idioma = idiomaModificado(datosLibros.idioma());
    }

    public Libros(Libros libros) {
    }

    private String idiomaModificado(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "Desconocido";
        }
        return idiomas.get(0);
    }

    @Override
    public String toString() {
        return "Libros " +
                "Id = " + Id +
                ", \ntitulo = '" + titulo + '\'' +
                ", \nidioma = '" + idioma +
                ", \nautor = " + (autor != null ? autor.getNombre() : "N/A")+"\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
