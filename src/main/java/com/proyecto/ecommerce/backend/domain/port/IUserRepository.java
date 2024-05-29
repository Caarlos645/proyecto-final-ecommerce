package com.proyecto.ecommerce.backend.domain.port;

import com.proyecto.ecommerce.backend.domain.model.User;

public interface IUserRepository {

	User save(User user);

	User findByEmail(String email);

	User findById(Integer id);

	Iterable<User> findAll();

	void deleteById(Integer id);

}
