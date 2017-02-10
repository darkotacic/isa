package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Restaurant;

public interface RestaurantService {
	ResponseEntity<Restaurant> register(Restaurant r, String[] manager_ids, String[] product_ids, String[] grade_ids, String[] segment_ids);
}
