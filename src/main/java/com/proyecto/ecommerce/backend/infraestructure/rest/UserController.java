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

import com.proyecto.ecommerce.backend.application.UserService;
import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.model.User;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable("id") Integer id) {
		return userService.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id")Integer id) {
		userService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
