package com.proyecto.ecommerce.backend.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderProduct {
	private Integer id;
	private BigDecimal quantity;
	private BigDecimal precio;
	private Integer productId;

	public BigDecimal getTotalItem() {
	    if (this.precio == null) {
	        return BigDecimal.ZERO;
	    }
	    return this.precio.multiply(this.quantity);
	}
}
