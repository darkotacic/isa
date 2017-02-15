package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Product;
import com.isa.entity.RequestOffer;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.Bidder;
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
	ResponseEntity<Cook> registerCook(Cook c, Long id);
	ResponseEntity<Bartender> registerBartender(Bartender w, Long id);
	ResponseEntity<Waiter> registerWaiter(Waiter w, Long id);
	ResponseEntity<WorkSchedule> registerWorkSchedule(WorkSchedule w, Long worker_id, Long segment_id, Long replacement_id);
	String removeWorkSchedule(Long id);
	ResponseEntity<Bidder> registerBidder(Bidder b);
	ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id, Long[] pro_ids);
	ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro, Long[] pro_add_ids, Long[] pro_rem_ids);
	String removeRequestOffer(Long ro);
	
	
}
