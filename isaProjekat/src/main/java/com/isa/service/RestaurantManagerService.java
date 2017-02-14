package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.Cook;
import com.isa.entity.users.Waiter;

public interface RestaurantManagerService {

	ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r);
	ResponseEntity<String> defineRestaurantMenu(Long[] products, Long id);
	ResponseEntity<Product> addProductToMenu(Product p, Long r_id);
	ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id);
	ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id);
	ResponseEntity<String> removeSegment(Long id);
	ResponseEntity<String> removeRestaurantTable(Long t);
	ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t);
	ResponseEntity<Segment> updateSegment(Segment s);
	ResponseEntity<String> removeWorker(Long id);
	ResponseEntity<Cook> registerCook(Cook c);
	ResponseEntity<Bartender> registerBartender(Bartender w);
	ResponseEntity<Waiter> registerWaiter(Waiter w);
	
	
}
