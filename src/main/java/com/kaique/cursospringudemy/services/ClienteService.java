package com.kaique.cursospringudemy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.cursospringudemy.domain.Cliente;
import com.kaique.cursospringudemy.repositories.ClienteRepository;
import com.kaique.cursospringudemy.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscarPorID(Integer id) {
		return repo.findById(id).orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado" 
									+" id:" + id + " Tipo:" + Cliente.class.getName()
				));
	}
}
