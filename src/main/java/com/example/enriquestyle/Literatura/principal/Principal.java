package com.example.enriquestyle.Literatura.principal;

import com.example.enriquestyle.Literatura.model.*;
import com.example.enriquestyle.Literatura.repository.iAutorRepository;
import com.example.enriquestyle.Literatura.repository.iLibroRepository;
import com.example.enriquestyle.Literatura.service.ConsumoAPI;
import com.example.enriquestyle.Literatura.service.ConvierteDatos;
//import jakarta.transaction.Transactional;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;


import java.util.*;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static Scanner scanner;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private iLibroRepository repositorio;
    private iAutorRepository autorRepository;
    private List<Libros> libro = new ArrayList<>();


    public Principal(iLibroRepository repository, iAutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n
                    ********** BIBLIOTECA VIRTUAL **********
                    
                    1 - Buscar Libro 
                    2 - Mostrar Libros buscados
                    3 - Lista de Autores registrados
                    4 - Autores vivos de un determinado año
                    5 - Libros por idioma
                                      
                                  
                    0 - Salir de la Aplicacion
                    """;
            try {
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Porfavor ingrese un numero valido");
                teclado.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    librosGuardados();
                    break;
                case 3:
                    AutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresPorAnio();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar nuestra Biblioteca Virtual C:");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    System.out.println("Intentelo de nuevo");
                    muestraElMenu();
                    break;
            }
        }
    }
    private Libros getDatosLibros() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine().toLowerCase();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        LibroRespuestaAPI datos = conversor.convertirDatosJsonAJava(json, LibroRespuestaAPI.class);

        if (datos != null && datos.getResultadoLibros() != null && !datos.getResultadoLibros().isEmpty()) {
            DatosLibros primerLibro = datos.getResultadoLibros().stream()
                    .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                    .findFirst().get();
            return new Libros(primerLibro);
        } else {
            System.out.println("No se encontro el libro");
            return null;
        }
    }

    private void buscarLibro() {
        Libros libros = getDatosLibros();

        if (libros == null){
            System.out.println("Libro no encontrado, el valor es null");
            return;
        }

        try{
            boolean libroExistente = repositorio.existsByTitulo(libros.getTitulo());
            if (libroExistente){
                System.out.println("El libro ya existe en la base de datos");
            }else {
                repositorio.save(libros);
                System.out.println(libros.toString());
            }
        } catch (InvalidDataAccessApiUsageException e){
            System.out.println("No se puede encontrar el libro buscado");
        }
    }

//    @Transactional(readOnly = true)
    private void librosGuardados() {
        List<Libros> libros = repositorio.findAll();
        if (libros.isEmpty()) {
            System.out.println("No se encuentra en la base de datos.");
        }else {
            System.out.println("Libros encontrado en la base de datos:\n");
            for (Libros libro : libros) {
                System.out.println(libro.toString());
            }
        }
    }

    private void AutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores en la base de datos");
        }else {
            System.out.println("******************************************************");
            System.out.println("Estos son los Autores encontrados en la base de datos: \n");
            Set<String> autoresUnicos = new HashSet<>();
                for (Autor autor : autores) {
                    if (autoresUnicos.add(autor.getNombre())){
                        System.out.println("Nombre: "+autor.getNombre()+"\n" +"Fecha de Nacimiento: "+autor.getNacimientoAutor() +
                                "\n" + "Fallecio en: " + autor.getFallecimientoautor()+"\n");
                    }
                }
        }
    }

    private void buscarAutoresPorAnio(){
        System.out.println("Desde que año quieres consultar para ver los autores vivos");
        int busquedaPorAnio = Integer.parseInt(teclado.nextLine());
        //teclado.nextLine();

        List<Autor> autoresVivos = autorRepository.findByNacimientoAutorLessThanEqualAndFallecimientoAutorGreaterThanEqual
                (busquedaPorAnio, busquedaPorAnio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores que estuvieran vivos en el año " + busquedaPorAnio + ".");
        }else {
            System.out.println("Los autores vivos en el año " + busquedaPorAnio + " son: ");
            Set<String> autoresUnicos = new HashSet<>();

            for (Autor autor : autoresVivos) {
                if (autoresUnicos.add(autor.getNombre())) {
                    System.out.println("Autor: " + autor.getNombre());
                        }
                    }
                }
    }

    private void buscarLibroPorIdioma(){
        System.out.println("Ingrese el idioma en el que quiere buscar: \n");
        System.out.println("|------------------------------|");
        System.out.println("|         es - Español.        |");
        System.out.println("|         en - Ingles.         |");
        System.out.println("|------------------------------|\n");

        var idioma = teclado.nextLine();
        List<Libros> librosPorIdioma = repositorio.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros con ese idioma en la base de datos");
        }else {
            System.out.println("Libros con el idioma que elegiste son los siguientes:\n");
            for (Libros libros : librosPorIdioma) {
                System.out.println(libros.toString());
            }
        }
    }

}
