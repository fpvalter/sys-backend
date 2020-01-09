package com.sysbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sysbackend.domain.Categoria;

@SpringBootApplication
public class SysBackendApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(SysBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

	}

}
