package com.proyecto.ecommerce.backend.application;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.ecommerce.backend.domain.model.Product;
import com.proyecto.ecommerce.backend.domain.port.IProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductService {

	private final IProductRepository iProductRepository;


	public ProductService(IProductRepository iProductRepository) {
		this.iProductRepository = iProductRepository;
	}
	

	public Product save(Product product) throws IOException {
		return this.iProductRepository.save(product);
	}

	public Iterable<Product> findAll() {
		return this.iProductRepository.findAll();
	}

	public Product findById(Integer id) {
		return iProductRepository.findById(id);
	}

	public void deleteById(Integer id) {
		Product product = findById(id);
		String nameFile = product.getUrlImage().substring(29); // Si tenemos otro nombre de proyecto u otra cosa cambia el substring
		log.info("Este es el nombre de la imagen: {}", nameFile);
		this.iProductRepository.deleteById(id);
	}


	public Iterable<Product> findAllByCategoryId(Integer idCategory) {
		return this.iProductRepository.findAllByCategoryId(idCategory);
	}


	public Product findByName(String name) {
		return iProductRepository.findByName(name);
	}
}
