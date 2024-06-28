package com.example.enriquestyle.Literatura;

import com.example.enriquestyle.Literatura.principal.Principal;
import com.example.enriquestyle.Literatura.repository.iAutorRepository;
import com.example.enriquestyle.Literatura.repository.iLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private iLibroRepository repository;
	@Autowired
	private iAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, autorRepository);
		principal.muestraElMenu();
	}
}
