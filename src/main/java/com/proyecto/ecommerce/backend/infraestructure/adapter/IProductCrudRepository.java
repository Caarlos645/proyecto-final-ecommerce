package com.proyecto.ecommerce.backend.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.ecommerce.backend.infraestructure.entity.ProductEntity;

public interface IProductCrudRepository extends CrudRepository<ProductEntity, Integer> {

	Iterable<ProductEntity> findAllByCategoryEntity_Id(Integer categoryId);

	ProductEntity findByName(String name);

	
}
