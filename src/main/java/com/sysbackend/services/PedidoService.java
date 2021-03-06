package com.sysbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysbackend.domain.Pedido;
import com.sysbackend.repositories.PedidoRepository;
import com.sysbackend.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> ped = repository.findById(id);
		
		return ped.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()
				));
	}
	
}
