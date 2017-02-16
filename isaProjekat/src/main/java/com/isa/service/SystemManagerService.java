package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.SystemManager;

public interface SystemManagerService {
	ResponseEntity<SystemManager> registerSystemManager(SystemManager sm);

	ResponseEntity<RestaurantManager> registerRestaurantManager(RestaurantManager sm, Long param);
	
	ResponseEntity<Restaurant> registerRestaurant(Restaurant r);
	
	ResponseEntity<String> removeRestaurant(Long r_id);

	String removeSystemManager(Long r_id);

	String removeRestaurantManager(Long r_id);

	ResponseEntity<Iterable<Restaurant>> getAllRestaurants();

	ResponseEntity<Iterable<RestaurantManager>> getRestaurantManagersForRestaurant(Long id);

	ResponseEntity<Iterable<SystemManager>> getAllSystemManager();

	
	

}
