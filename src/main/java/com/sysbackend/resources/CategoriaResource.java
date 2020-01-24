package com.sysbackend.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sysbackend.domain.Categoria;
import com.sysbackend.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id ) {
		
		Categoria cat = service.find(id);
		
		return ResponseEntity.ok().body(cat);
				
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		
		categoria = service.insert(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(
			@PathVariable Integer id,
			@RequestBody Categoria categoria) {
		
		categoria.setId(id);
		categoria = service.update(categoria);
						
		return ResponseEntity.noContent().build();
	}
	
}
