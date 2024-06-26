package com.proyecto.ecommerce.backend.infraestructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ecommerce.backend.application.CategoryService;
import com.proyecto.ecommerce.backend.domain.model.Category;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/admin/categories")
@Slf4j
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	// Crea y Actualiza si ya existe el objeto
	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Iterable<Category>> findAll() {
		return ResponseEntity.ok(categoryService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
		categoryService.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
