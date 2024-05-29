package com.proyecto.ecommerce.backend.infraestructure.adapter;

import org.springframework.stereotype.Repository;

import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.model.User;
import com.proyecto.ecommerce.backend.domain.port.IUserRepository;
import com.proyecto.ecommerce.backend.infraestructure.mapper.UserMapper;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

	private final IUserCRUDRepository crudRepository;
	private final UserMapper userMapper;

	public UserCrudRepositoryImpl(IUserCRUDRepository crudRepository, UserMapper userMapper) {
		this.crudRepository = crudRepository;
		this.userMapper = userMapper;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userMapper.toUser(crudRepository.save(userMapper.toUserEntity(user)));
	}

	@Override
	public User findByEmail(String email) {
		return userMapper.toUser(crudRepository.findByEmail(email).orElseThrow(
				() -> new RuntimeException ("Usuario con email: " + email + " no encontrado.")
				));
	}

	@Override
	public User findById(Integer id) {
		return userMapper.toUser(crudRepository.findById(id).get());
	}

	@Override
	public Iterable<User> findAll() {
		return userMapper.toUser(crudRepository.findAll());
	}
	
	public void deleteById(Integer id) {
		crudRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Producto con id: \" + id + \" no se encuentra.")
				);
		crudRepository.deleteById(id);

	}
}
