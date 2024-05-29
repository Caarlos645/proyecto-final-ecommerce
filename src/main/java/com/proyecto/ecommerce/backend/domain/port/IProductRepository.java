package com.proyecto.ecommerce.backend.domain.port;

import com.proyecto.ecommerce.backend.domain.model.Product;

public interface IProductRepository {

	Product save(Product product);

	Iterable<Product> findAll();

	Product findById(Integer id);

	void deleteById(Integer id);

	Iterable<Product> findAllByCategoryId(Integer idCategory);

	Product findByName(String name);
}
