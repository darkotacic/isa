package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Product;

public interface ProductRepository extends CrudRepository<Product, String> {
	Product findOne(String name);
	
	@SuppressWarnings("unchecked")
	Product save(Product p);
}
