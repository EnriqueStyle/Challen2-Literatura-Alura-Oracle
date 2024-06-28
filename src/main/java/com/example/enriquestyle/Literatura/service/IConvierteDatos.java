package com.example.enriquestyle.Literatura.service;

public interface IConvierteDatos {

    <T> T convertirDatosJsonAJava(String json, Class<T> clase);
}
