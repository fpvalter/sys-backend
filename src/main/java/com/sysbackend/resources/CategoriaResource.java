package com.sysbackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysbackend.domain.Categoria;
import com.sysbackend.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id ) {
		
		Categoria cat = service.buscar(id);
		
		return ResponseEntity.ok().body(cat);
				
	}
	
}
