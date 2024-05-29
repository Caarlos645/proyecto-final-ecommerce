package com.proyecto.ecommerce.backend.infraestructure.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.port.IProductRepository;
import com.proyecto.ecommerce.backend.infraestructure.entity.ProductEntity;
import com.proyecto.ecommerce.backend.infraestructure.mapper.ProductMapper;

@Repository
public class ProductCrudRepositoryImpl implements IProductRepository {

	private final IProductCrudRepository iProductCrudRepository;
	private final ProductMapper productMapper;

	public ProductCrudRepositoryImpl(IProductCrudRepository iProductCrudRepository, ProductMapper productMapper) {
		this.iProductCrudRepository = iProductCrudRepository;
		this.productMapper = productMapper;
	}

	@Override
	public Product save(Product product) {
		return productMapper.toProduct(iProductCrudRepository.save(productMapper.toProductEntity(product)));
	}

	@Override
	public Iterable<Product> findAll() {
		return productMapper.toProductList(iProductCrudRepository.findAll());
	}

	@Override
	public Product findById(Integer id) {
		return productMapper.toProduct(iProductCrudRepository.findById(id).orElseThrow(
			() -> new RuntimeException("Producto con id: " + id + " no se encuentra.")
				));
	}

	@Override
	public void deleteById(Integer id) {
		iProductCrudRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Producto con id: \" + id + \" no se encuentra.")
				);
		iProductCrudRepository.deleteById(id);

	}

	@Override
	public Iterable<Product> findAllByCategoryId(Integer idCategory) {
        Iterable<ProductEntity> productEntities = iProductCrudRepository.findAllByCategoryEntity_Id(idCategory);
        return productMapper.toProductList(productEntities);
    }

	@Override
	public Product findByName(String name) {
		return productMapper.toProduct(iProductCrudRepository.findByName(name));
	}

}
