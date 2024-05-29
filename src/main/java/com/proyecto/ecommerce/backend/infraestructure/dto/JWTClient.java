package com.proyecto.ecommerce.backend.infraestructure.dto;

public record JWTClient(Integer id,String token, String type, String username, String email) {

}
