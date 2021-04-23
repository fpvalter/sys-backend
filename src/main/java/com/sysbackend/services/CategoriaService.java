package com.sysbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.sysbackend.domain.Categoria;
import com.sysbackend.repositories.CategoriaRepository;
import com.sysbackend.services.exception.DataIntegrityException;
import com.sysbackend.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		
		Optional<Categoria> cat = repository.findById(id);
		
		return cat.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()
				));
		
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
		
	}

	public Categoria update(Categoria categoria) {		
		this.find(categoria.getId());			
		return repository.save(categoria);
	}

	public void delete(Integer id) {
		this.find(id);
		
		try {
			repository.deleteById(id);
		} catch(DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Nao é possível excluir uma categoria que tenha produtos");
		}
	}

}
