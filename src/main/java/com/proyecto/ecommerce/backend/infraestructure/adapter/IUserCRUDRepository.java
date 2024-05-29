package com.proyecto.ecommerce.backend.infraestructure.adapter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.proyecto.ecommerce.backend.infraestructure.entity.UserEntity;

public interface IUserCRUDRepository extends CrudRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByEmail(String email);
}
