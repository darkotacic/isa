package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.users.RestaurantManager;

public interface RestaurantManagerService {
	ResponseEntity<RestaurantManager> register(RestaurantManager sm, String param);

	Iterable<RestaurantManager> getAll();
}
