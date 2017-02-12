package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RestaurantTable;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {
	
	@SuppressWarnings("unchecked")
	RestaurantTable save(RestaurantTable rt);
	
	void delete(RestaurantTable t);
	
	void delete(Long id);
}
