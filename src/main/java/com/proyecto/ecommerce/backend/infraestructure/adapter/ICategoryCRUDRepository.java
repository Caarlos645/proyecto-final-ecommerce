package com.proyecto.ecommerce.backend.infraestructure.adapter;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.ecommerce.backend.infraestructure.entity.CategoryEntity;

public interface ICategoryCRUDRepository extends CrudRepository<CategoryEntity, Integer>{

}
