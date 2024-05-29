package com.proyecto.ecommerce.backend.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	private Integer id;
	private String name;
	private LocalDateTime dateCreated;
	private LocalDateTime dateUpdated;
}
