package com.isa.service;

import java.util.List;

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

	ResponseEntity<List<Restaurant>> getAllRestaurants();

	ResponseEntity<List<RestaurantManager>> getRestaurantManagersForRestaurant(Long id);

	ResponseEntity<List<SystemManager>> getAllSystemManager();

	
	

}
