package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	Product findOne(Long id);
	
	Product findByName(String name);
	
	@SuppressWarnings("unchecked")
	Product save(Product p);
}
