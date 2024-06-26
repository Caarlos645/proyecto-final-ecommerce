package com.proyecto.ecommerce.backend.infraestructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ecommerce.backend.application.ProductService;
import com.proyecto.ecommerce.backend.domain.model.Product;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
public class HomeController {
	private final ProductService productService;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id")Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}
	
}
