package com.proyecto.ecommerce.backend.infraestructure.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ecommerce.backend.application.RegistrationService;
import com.proyecto.ecommerce.backend.domain.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "https://proyecto-final-ecommerce-production.up.railway.app")
@Slf4j
public class RegistrationController {
	private final RegistrationService registrationService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public RegistrationController(RegistrationService registrationService,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.registrationService = registrationService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}



	@PostMapping("/register")
	public ResponseEntity<User> register (@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return new ResponseEntity<>(registrationService.register(user), HttpStatus.CREATED);
	}
}
