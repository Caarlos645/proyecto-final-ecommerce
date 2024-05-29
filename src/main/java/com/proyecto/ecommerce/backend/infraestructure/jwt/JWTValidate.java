package com.proyecto.ecommerce.backend.infraestructure.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyecto.ecommerce.backend.infraestructure.service.CustomUserDetailService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class JWTValidate {
	// Validamos que el token venga en la petición
	public static boolean tokenExists (HttpServletRequest request, HttpServletResponse response) {
		String header = request.getHeader(Constants.HEADER_AUTHORIZATION);
		if (header == null || !header.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			return false;
		
		}
		return true;
	}
	
	// Validamos que el token es correcto
	public static Claims JWTValid(HttpServletRequest request) {
		String jwtToken = request.getHeader(Constants.HEADER_AUTHORIZATION).replace(Constants.TOKEN_BEARER_PREFIX, "");
		return Jwts.parserBuilder().setSigningKey(Constants.getSignedKey(Constants.SUPER_SECRET_KEY))
				.build().parseClaimsJws(jwtToken).getBody();
	}
	
	// Autenticamos al usuario en el flujo que estamos siguiendo 
	public static void setAuthentication (Claims claims, CustomUserDetailService customUserDetailService) {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
