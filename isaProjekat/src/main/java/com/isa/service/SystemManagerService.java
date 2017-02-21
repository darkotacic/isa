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
	
	ResponseEntity<Restaurant> removeRestaurant(Long r_id);

	ResponseEntity<SystemManager> removeSystemManager(Long r_id);

	ResponseEntity<RestaurantManager> removeRestaurantManager(Long r_id);

	ResponseEntity<List<Restaurant>> getAllRestaurants();

	ResponseEntity<List<RestaurantManager>> getRestaurantManagersForRestaurant(Long id);

	ResponseEntity<List<SystemManager>> getAllSystemManager();

	ResponseEntity<SystemManager> updateSystemManager(SystemManager sm);

	ResponseEntity<Restaurant> updateRestaurant(Restaurant r);

	
	

}
