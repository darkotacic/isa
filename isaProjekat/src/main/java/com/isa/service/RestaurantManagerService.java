package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.isa.entity.BidderOffer;
import com.isa.entity.Product;
import com.isa.entity.RequestOffer;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.Bidder;
import com.isa.entity.users.Cook;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.Waiter;
import com.isa.entity.users.Worker;

public interface RestaurantManagerService {

	ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r);

	ResponseEntity<Product> removeProductFromMenu(Long product, Long id);

	ResponseEntity<Product> addNewProductToMenu(Product p, Long r_id);
	
	ResponseEntity<Product> addExistingProductToMenu(Long p_id, Long r_id);

	ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id);

	ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id);

	ResponseEntity<Segment> removeSegment(Long id);

	ResponseEntity<RestaurantTable> removeRestaurantTable(Long t);

	ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t, Long id);

	ResponseEntity<Segment> updateSegment(Segment s);

	ResponseEntity<Worker> removeWorker(Long id);

	ResponseEntity<Cook> registerCook(Cook c, Long id);

	ResponseEntity<Bartender> registerBartender(Bartender w, Long id);

	ResponseEntity<Waiter> registerWaiter(Waiter w, Long id);

	ResponseEntity<WorkSchedule> registerWorkSchedule(WorkSchedule w, Long worker_id, Long segment_id);

	ResponseEntity<WorkSchedule> removeWorkSchedule(Long id);

	ResponseEntity<Bidder> registerBidder(Bidder b);

	ResponseEntity<RequestOffer> registerRequestOffer(RequestOffer ro, Long r_id);
	
	ResponseEntity<Product> addProductToRequestOffer(Long ro_id, Long p_id);
	
	ResponseEntity<Product> removeProductFromRequestOffer(Long ro_id, Long p_id);

	ResponseEntity<RequestOffer> updateRequestOffer(RequestOffer ro);

	ResponseEntity<RequestOffer> removeRequestOffer(Long ro);

	ResponseEntity<List<Worker>> getAllWorkersForRestaurant(Long id);

	ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForRestaurant(Long id);

	ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForWorker(Long id);

	ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(Long id);

	ResponseEntity<List<BidderOffer>> getAllBidderOffersForManagerOffers(Long id);

	ResponseEntity<List<BidderOffer>> getAllBidderOffersForRequestOffer(Long id);

	ResponseEntity<List<Segment>> getAllSegmentsForRestaurant(Long id);

	ResponseEntity<List<RestaurantTable>> getAllTablesForRestaurant(Long id);

	ResponseEntity<List<RestaurantTable>> getAllTablesForSegment(Long id);

	ResponseEntity<List<Product>> getAllProductsForRestaurant(Long id);

	ResponseEntity<RequestOffer> acceptBidderOffer(Long r_id, Long q_id);

	ResponseEntity<List<WorkSchedule>> getPossableReplacements(Long id);

	ResponseEntity<List<Product>> getAllProductsForRequestOffer(Long id);

	ResponseEntity<WorkSchedule> updateWorkSchedule(WorkSchedule w);

	ResponseEntity<WorkSchedule> updateWorkScheduleSetReplacement(Long s, Long w);

	ResponseEntity<WorkSchedule> updateWorkScheduleSetSegment(Long s, Long w);

	ResponseEntity<WorkSchedule> updateWorkScheduleSetWorker(Long s, Long w);
	
	ResponseEntity<List<Waiter>> getAllWaitersByNameAndRestaurant(Long id, String name);

	ResponseEntity<List<Product>> getAllProductsByNameAndRestaurant(Long id, String name);

	double gradeForOrder(Long id, Long res_id);
	double gradeForWorker(Long id);

	double gradeForRestaurant(Long id);

	double restaurantEarnings(Long id, Date startDate, Date endDate);

	double waiterEarinings(Long id);

	double checkIfRequestOfferExpired();

	ResponseEntity<Restaurant> getRestaurantForManager(Long id);

	ResponseEntity<List<Product>> getAllProducts();

	double checkIfWorkScheduleIsDone();

	double checkIfSegmentCanBeDeleted(Long id);

	ResponseEntity<RestaurantManager> update(RestaurantManager r);


}
