package com.sysbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysbackend.domain.Categoria;
import com.sysbackend.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repository;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> cat = repository.findById(id);
		
		return cat.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()
				));
		
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
		
	}

}
