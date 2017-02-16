package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;

public interface RestaurantManagerRepository extends CrudRepository<RestaurantManager, Long> {
		
	Iterable<RestaurantManager> findByRestaurant(Restaurant t);
}
