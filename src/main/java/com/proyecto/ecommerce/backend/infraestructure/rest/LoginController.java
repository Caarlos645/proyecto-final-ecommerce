package com.proyecto.ecommerce.backend.infraestructure.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.ecommerce.backend.application.UserService;
import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.infraestructure.dto.JWTClient;
import com.proyecto.ecommerce.backend.infraestructure.dto.UserDTO;
import com.proyecto.ecommerce.backend.infraestructure.jwt.JWTGenerator;

@RestController
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final JWTGenerator jwtGenerator;
	private final UserService userService; 

	public LoginController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator,
			UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtGenerator = jwtGenerator;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<JWTClient> login (@RequestBody UserDTO userDto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		User user = userService.findByEmail(userDto.username());
		
		String token = jwtGenerator.getToken(userDto.username());
		JWTClient jwtClient = new JWTClient(user.getId(),token, user.getUserType().toString(), user.getFirstName() ,user.getEmail());
		return new ResponseEntity<>(jwtClient, HttpStatus.OK);
	}
}
