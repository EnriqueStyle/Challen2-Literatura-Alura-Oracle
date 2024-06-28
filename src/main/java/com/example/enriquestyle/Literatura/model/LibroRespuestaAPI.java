package com.example.enriquestyle.Literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroRespuestaAPI {

    @JsonAlias("results")
    List<DatosLibros> resultadoLibros;

    public List<DatosLibros> getResultadoLibros() {
        return resultadoLibros;
    }

    public void setResultadoLibros(List<DatosLibros> resultadoLibros){
        this.resultadoLibros = resultadoLibros;
    }
}
