package com.proyecto.ecommerce.backend.infraestructure.adapter;

import org.springframework.stereotype.Repository;

import com.proyecto.ecommerce.backend.domain.model.Category;
import com.proyecto.ecommerce.backend.domain.port.ICategoryRepository;
import com.proyecto.ecommerce.backend.infraestructure.mapper.CategoryMapper;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

	private final ICategoryCRUDRepository categoryCRUDRepository;
	private final CategoryMapper categoryMapper;

	public CategoryCrudRepositoryImpl(ICategoryCRUDRepository categoryCRUDRepository, CategoryMapper categoryMapper) {
		this.categoryCRUDRepository = categoryCRUDRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public Category save(Category category) {
		return categoryMapper.toCategory(categoryCRUDRepository.save(categoryMapper.toCategoryEntity(category)));
	}

	@Override
	public Iterable<Category> findAll() {
		return categoryMapper.toCategoryList(categoryCRUDRepository.findAll());
	}

	@Override
	public Category findById(Integer id) {
		return categoryMapper.toCategory(categoryCRUDRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Categoría con id: " + id + " no existe.")
				));
	}

	@Override
	public void deleteById(Integer id) {
		categoryCRUDRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Categoria con id: " + id + " no existe.")
				);
		categoryCRUDRepository.deleteById(id);

	}

}
