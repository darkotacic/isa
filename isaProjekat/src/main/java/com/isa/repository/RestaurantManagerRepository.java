package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.RestaurantManager;

public interface RestaurantManagerRepository extends CrudRepository<RestaurantManager, Long> {
	@SuppressWarnings("unchecked")
	RestaurantManager save(RestaurantManager sm);

	RestaurantManager findOne(Long id);
	
	Iterable<RestaurantManager> findAll();
}
