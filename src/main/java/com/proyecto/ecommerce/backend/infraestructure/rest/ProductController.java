package com.proyecto.ecommerce.backend.infraestructure.rest;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.ecommerce.backend.application.ProductService;
import com.proyecto.ecommerce.backend.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/v1/admin/products")
@Slf4j
@AllArgsConstructor
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
public class ProductController {

	private final ProductService productService;
	
	@PostMapping
	public ResponseEntity<Product> save(@RequestParam("id") Integer id,
										@RequestParam("code") String code,
										@RequestParam("name") String name,
										@RequestParam("description") String description,
										@RequestParam("precio") BigDecimal precio,
										@RequestParam("urlImage") String urlImage,
										@RequestParam("userId") Integer userId,
										@RequestParam("categoryId") Integer categoryId
										) throws IOException {
		Product product = new Product();
		product.setId(id);
		product.setCode(code);
		product.setName(name);
		product.setDescription(description);
		product.setPrecio(precio);
		product.setCategoryId(categoryId);
		product.setUserId(userId);
		product.setUrlImage(urlImage);
		
		log.info("Nombre producto: {}", product.getName());
		return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/products/{idCategory}")
	public ResponseEntity<Iterable<Product>> findAllByCategoryId(@PathVariable("idCategory") Integer idCategory) {
		return ResponseEntity.ok(productService.findAllByCategoryId(idCategory));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id")Integer id) {
		return ResponseEntity.ok(productService.findById(id));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Product> findByName(@PathVariable("name")String name) {
		return ResponseEntity.ok(productService.findByName(name));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id")Integer id) {
		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
