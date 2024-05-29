package com.proyecto.ecommerce.backend.infraestructure.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import io.jsonwebtoken.security.Keys;

public class Constants {
	public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	public static final String SUPER_SECRET_KEY = "okokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokokok";
	public static final long TOKEN_EXPIRATION_TIME = 1500000; // 15 minutos
	
	public static Key getSignedKey(String secretKey) {
		byte [] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
