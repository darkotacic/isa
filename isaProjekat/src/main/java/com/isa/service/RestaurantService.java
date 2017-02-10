package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Restaurant;

public interface RestaurantService {
	ResponseEntity<Restaurant> register(Restaurant r);
}
