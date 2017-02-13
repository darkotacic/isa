package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.RestaurantManager;

public interface RestaurantManagerRepository extends CrudRepository<RestaurantManager, String> {
	@SuppressWarnings("unchecked")
	RestaurantManager save(RestaurantManager sm);

	RestaurantManager findOne(String name);
	
	Iterable<RestaurantManager> findAll();
}
