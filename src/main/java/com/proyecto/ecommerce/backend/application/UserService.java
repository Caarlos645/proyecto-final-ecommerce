package com.proyecto.ecommerce.backend.application;

import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.domain.port.IUserRepository;

public class UserService {

	private final IUserRepository iUserRepository;

	public UserService(IUserRepository iUserRepository) {
		this.iUserRepository = iUserRepository;
	}

	public User save(User user) {
		return iUserRepository.save(user);
	}

	public User findById(Integer id) {
		return iUserRepository.findById(id);
	}
	
	public User findByEmail (String email) {
		return iUserRepository.findByEmail(email);
	}
	
	public Iterable<User> findAll() {
		return this.iUserRepository.findAll();
	}

	public void deleteById(Integer id) {
		User user = findById(id);
		this.iUserRepository.deleteById(id);
	}
	
//	public User findByEmail(String email) {
//		return iUserRepository.findByEmail(email);
//	}

}
