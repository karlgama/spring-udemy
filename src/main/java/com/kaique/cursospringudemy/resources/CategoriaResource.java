package com.kaique.cursospringudemy.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaique.cursospringudemy.domain.Categoria;
import com.kaique.cursospringudemy.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> encontrarPorId(@PathVariable Integer id) {
		Categoria obj = service.buscarPorID(id);
		
		//return obj != null ? ResponseEntity.ok(obj): ResponseEntity.notFound().build();
		return ResponseEntity.ok(obj);
	}
	
}
