package com.kaique.cursospringudemy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaique.cursospringudemy.domain.Categoria;
import com.kaique.cursospringudemy.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorID(Integer id) {
		return repo.findById(id).orElse(null);
	}
}
