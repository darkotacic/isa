package com.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;

public interface RestaurantManagerRepository extends CrudRepository<RestaurantManager, Long> {
		
	List<RestaurantManager> findByRestaurant(Restaurant t);
}
