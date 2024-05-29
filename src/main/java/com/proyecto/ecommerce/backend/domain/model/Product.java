package com.proyecto.ecommerce.backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private Integer id;
	private String name;
	private String code;
	private String description;
	private String urlImage;
	private BigDecimal precio;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
	private Integer userId;
	private Integer categoryId;
}
