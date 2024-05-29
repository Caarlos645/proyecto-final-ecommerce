package com.proyecto.ecommerce.backend.infraestructure.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.ecommerce.backend.application.UserService;
import com.proyecto.ecommerce.backend.domain.model.User;

@Service
public class CustomUserDetailService implements UserDetailsService{
	private UserService userService;
	
	public CustomUserDetailService(UserService userService) {
		this.userService = userService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username); 
		return org.springframework.security.core.userdetails.User.builder().username(user.getEmail())
				.password(user.getPassword()).roles(user.getUserType().name()).build();
	}
	
}
