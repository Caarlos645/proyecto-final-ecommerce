package com.proyecto.ecommerce.backend.infraestructure.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.proyecto.ecommerce.backend.infraestructure.jwt.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
	public SecurityConfig(JWTAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}

	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration aut) throws Exception {
		return aut.getAuthenticationManager();
	}
	
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		    httpSecurity.cors(cors -> cors.configurationSource(request -> {
		        CorsConfiguration corsConfiguration = new CorsConfiguration();
		        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		        return corsConfiguration;
		    })).csrf(csrf -> csrf.disable()).authorizeHttpRequests(aut -> aut.anyRequest().permitAll());
		    
		    return httpSecurity.build();
		}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder ();
	}
}
