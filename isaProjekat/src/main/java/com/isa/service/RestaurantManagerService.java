package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;

public interface RestaurantManagerService {

	ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r);
	ResponseEntity<String> defineRestaurantMenu(String[] products, String name);
	ResponseEntity<Product> addProductToMenu(Product p, String r_name);
	ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, String r_name);
	ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id);
	ResponseEntity<String> removeSegment(Long id);
	ResponseEntity<String> removeRestaurantTable(Long t);
	ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t);
	ResponseEntity<Segment> updateSegment(Segment s);
	
	
}
