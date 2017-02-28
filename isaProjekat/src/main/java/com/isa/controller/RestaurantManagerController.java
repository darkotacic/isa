package com.isa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.BidderOffer;
import com.isa.entity.Order;
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
import com.isa.mail.SendEmail;
import com.isa.service.RestaurantManagerService;

@RestController
@RequestMapping(value = "/restaurantmanagers")
public class RestaurantManagerController {

	@Autowired
	private RestaurantManagerService restaurantManagerService;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantManager> update(@RequestBody @Valid RestaurantManager r) {
		session.setAttribute("user", r);
		return restaurantManagerService.update(r);
	}

	@RequestMapping(value = "/updateRestaurant", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Restaurant> register(@RequestBody @Valid Restaurant r) {
		return restaurantManagerService.updateRestaurantProfile(r);
	}

	@RequestMapping(value = "/addExistingProduct", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Product> register(@RequestParam(value = "product_id") Long ids,
			@RequestParam(value = "rest_id") Long rest_id) {
		return restaurantManagerService.addExistingProductToMenu(ids, rest_id);
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Product> removeProduct(@RequestParam(value = "product_id") Long ids,
			@RequestParam(value = "rest_id") Long rest_id) {
		return restaurantManagerService.removeProductFromMenu(ids, rest_id);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Product> addProduct(@RequestBody @Valid Product p,
			@RequestParam(value = "rest_id") Long rest_id) {
		return restaurantManagerService.addNewProductToMenu(p, rest_id);
	}

	@RequestMapping(value = "/addRestaurantTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantTable> addRestaurantTable(@RequestBody @Valid RestaurantTable rt,
			@RequestParam(value = "segment") Long id) {
		return restaurantManagerService.addRestaurantTableToSegment(rt, id);
	}

	@RequestMapping(value = "/addSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> addSegment(@RequestBody @Valid Segment s, @RequestParam(value = "rest") Long id) {
		return restaurantManagerService.addSegmentToRestaurnat(s, id);
	}

	@RequestMapping(value = "/removeSegment", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> removeSegment(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeSegment(id);
	}

	@RequestMapping(value = "/removeRestaurantTable", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantTable> removeRestaurantTable(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeRestaurantTable(id);
	}

	@RequestMapping(value = "/updateRestaurantTable", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantTable> updateRestaurantTable(@RequestBody @Valid RestaurantTable rt,
			@RequestParam(value = "id") Long id) {
		return restaurantManagerService.updateRestaurantTable(rt, id);
	}

	@RequestMapping(value = "/updateSegment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Segment> updateSegment(@RequestBody @Valid Segment s) {
		return restaurantManagerService.updateSegment(s);
	}

	@RequestMapping(value = "/registerCook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Cook> updateSegment(@RequestBody @Valid Cook s, @RequestParam(value = "id") Long id) {
		return restaurantManagerService.registerCook(s, id);
	}

	@RequestMapping(value = "/registerWaiter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Waiter> registerWaiter(@RequestBody @Valid Waiter s, @RequestParam(value = "id") Long id) {
		return restaurantManagerService.registerWaiter(s, id);
	}

	@RequestMapping(value = "/registerBartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Bartender> registerBartender(@RequestBody @Valid Bartender s,
			@RequestParam(value = "id") Long id) {
		return restaurantManagerService.registerBartender(s, id);
	}

	@RequestMapping(value = "/removeWorker", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Worker> removeWorker(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeWorker(id);
	}

	@RequestMapping(value = "/registerWorkSchedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> registerWorkSchedule(@RequestBody @Valid WorkSchedule s,
			@RequestParam(value = "segment_id", required = false, defaultValue = "0") Long s_id,
			@RequestParam(value = "worker_id") Long w_id) {
		return restaurantManagerService.registerWorkSchedule(s, w_id, s_id);
	}

	@RequestMapping(value = "/updateWorkSchedule", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> updateWorkSchedule(@RequestBody @Valid WorkSchedule s) {
		return restaurantManagerService.updateWorkSchedule(s);
	}

	@RequestMapping(value = "/updateWorkScheduleSetReplacement", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetReplacement(@RequestParam(value = "repl_id") Long s,
			@RequestParam(value = "ws_id") Long w) {
		return restaurantManagerService.updateWorkScheduleSetReplacement(s, w);
	}

	@RequestMapping(value = "/updateWorkScheduleSetSegment", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetSegment(@RequestParam(value = "segment_id") Long s,
			@RequestParam(value = "ws_id") Long w) {
		return restaurantManagerService.updateWorkScheduleSetSegment(s, w);
	}

	@RequestMapping(value = "/updateWorkScheduleSetWorker", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> updateWorkScheduleSetWorker(@RequestParam(value = "worker_id") Long s,
			@RequestParam(value = "ws_id") Long w) {
		return restaurantManagerService.updateWorkScheduleSetWorker(s, w);
	}

	@RequestMapping(value = "/removeWorkSchedule", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> removeWorkSchedule(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeWorkSchedule(id);
	}

	@RequestMapping(value = "/registerRequestOffer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> registerRequestOffer(@RequestBody @Valid RequestOffer ro,
			@RequestParam(value = "rm_id") Long r_id) {
		return restaurantManagerService.registerRequestOffer(ro, r_id);
	}

	@RequestMapping(value = "/updateRequestOffer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> updateRequestOffer(@RequestBody @Valid RequestOffer ro) {
		return restaurantManagerService.updateRequestOffer(ro);
	}

	@RequestMapping(value = "/removeProductFromRequestOffer", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Product> removeProductFromRequestOffer(@RequestParam(value = "product_id") Long ids,
			@RequestParam(value = "ro_id") Long rest_id) {
		return restaurantManagerService.removeProductFromRequestOffer(ids, rest_id);
	}

	@RequestMapping(value = "/addProductToRequestOffer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Product> addProductToRequestOffer(@RequestParam(value = "product_id") Long id,
			@RequestParam(value = "rest_id") Long rest_id) {
		return restaurantManagerService.addProductToRequestOffer(id, rest_id);
	}

	@RequestMapping(value = "/removeRequestOffer", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> removeRequestOffer(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeRequestOffer(id);
	}

	@RequestMapping(value = "/registerBidder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Bidder> registerBidder(@RequestBody @Valid Bidder s) {
		return restaurantManagerService.registerBidder(s);
	}

	@RequestMapping(value = "/getAllWorkersForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Worker>> getAllWorkersForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllWorkersForRestaurant(id);
	}

	@RequestMapping(value = "/getAllWorkSchedulesForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllWorkSchedulesForRestaurant(id);
	}

	@RequestMapping(value = "/getAllWorkSchedulesForWorker", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<WorkSchedule>> getAllWorkSchedulesForWorker(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllWorkSchedulesForWorker(id);
	}

	@RequestMapping(value = "/getAllRequestOffersForManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RequestOffer>> getAllRequestOffersForManager(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllRequestOffersForManager(id);
	}

	@RequestMapping(value = "/getAllBidderOffersForManagerOffers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<BidderOffer>> getAllBidderOffersForManagerOffers(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllBidderOffersForManagerOffers(id);
	}

	@RequestMapping(value = "/getAllBidderOffersForRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<BidderOffer>> getAllBidderOffersForRequestOffer(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllBidderOffersForRequestOffer(id);
	}

	@RequestMapping(value = "/getAllSegmentsForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Segment>> getAllSegmentsForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllSegmentsForRestaurant(id);
	}

	@RequestMapping(value = "/getAllTablesForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RestaurantTable>> getAllTablesForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllTablesForRestaurant(id);
	}

	@RequestMapping(value = "/getAllTablesForSegment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RestaurantTable>> getAllTablesForSegment(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllTablesForSegment(id);
	}

	@RequestMapping(value = "/getAllProductsForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Product>> getAllProductsForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllProductsForRestaurant(id);
	}

	@RequestMapping(value = "/getAllProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Product>> getAllProducts() {
		return restaurantManagerService.getAllProducts();
	}

	@RequestMapping(value = "/getAllProductsForRequestOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Product>> getAllProductsForRequestOffer(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getAllProductsForRequestOffer(id);
	}

	@RequestMapping(value = "/getPossableReplacements", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<WorkSchedule>> getPossableReplacements(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getPossableReplacements(id);
	}

	@RequestMapping(value = "/acceptBidderOffer", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RequestOffer> acceptBidderOffer(@RequestParam(value = "bid_id") Long r_id,
			@RequestParam(value = "req_id") Long q_id) {
		List<BidderOffer> biddings = this.restaurantManagerService.getAllBidderOffersForRequestOffer(q_id).getBody();
		for (int i = 0; i < biddings.size(); i++) {
			if (biddings.get(i).getId() == r_id) {
				@SuppressWarnings("unused")
				SendEmail se = new SendEmail(biddings.get(i).getBidder().getEmail(),
						"<a href=http://http://localhost:8080/#!/home> Check </a>", "Notification",
						"Your bidder offer " + biddings.get(i).getId() + " is accepted. Check here :");
			} else {
				@SuppressWarnings("unused")
				SendEmail se = new SendEmail(biddings.get(i).getBidder().getEmail(),
						"<a href=http://http://localhost:8080/#!/home> Check </a>", "Notification",
						"Your bidder offer " + biddings.get(i).getId() + " is declined. Check here :");
			}
		}
		return restaurantManagerService.acceptBidderOffer(r_id, q_id);
	}

	@RequestMapping(value = "/getGradeForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getGradeForRestaurant(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.gradeForRestaurant(id);
	}

	@RequestMapping(value = "/getGradeForOrder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getGradeForOrder(@RequestParam(value = "id") Long id, @RequestParam(value = "res_id") Long res_id) {
		return restaurantManagerService.gradeForOrder(id, res_id);
	}

	@RequestMapping(value = "/getGradeForWaiter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getGradeForWaiter(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.gradeForWorker(id);
	}

	@RequestMapping(value = "/getRestaurantEarnings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getRestaurantEarnings(@RequestParam(value = "id") Long id,
			@RequestParam(value = "start") String startDate, @RequestParam(value = "end") String endDate)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date start = formatter.parse(startDate);
		Date end = formatter.parse(endDate);
		return restaurantManagerService.restaurantEarnings(id, start, end);
	}

	@RequestMapping(value = "/getWaiterEarnings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double getWaiterEarnings(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.waiterEarinings(id);
	}

	@RequestMapping(value = "/getRestaurantForManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Restaurant> getRestaurantForManager(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getRestaurantForManager(id);
	}

	@RequestMapping(value = "/checkIfRequestOfferExpired", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfRequestOfferExpired() {
		return restaurantManagerService.checkIfRequestOfferExpired();
	}

	@RequestMapping(value = "/checkIfWorkScheduleIsDone", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfWorkScheduleIsDone() {
		return restaurantManagerService.checkIfWorkScheduleIsDone();
	}

	@RequestMapping(value = "/checkIfSegmentCanBeDeleted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public double checkIfSegmentCanBeDeleted(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.checkIfSegmentCanBeDeleted(id);
	}

	@RequestMapping(value = "/getAllWaitersByNameAndRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Waiter>> getAllWaitersByNameAndRestaurant(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name) {
		return restaurantManagerService.getAllWaitersByNameAndRestaurant(id, name);
	}

	@RequestMapping(value = "/getAllProductsByNameAndRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Product>> getAllProductsByNameAndRestaurant(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name) {
		return restaurantManagerService.getAllProductsByNameAndRestaurant(id, name);
	}

	@RequestMapping(value = "/getRestaurantTable", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantTable> getRestaurantTable(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getRestaurantTable(id);
	}

	@RequestMapping(value = "/getWorkSchedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<WorkSchedule> getWorkSchedule(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getWorkSchedule(id);
	}

	@RequestMapping(value = "/getBidderOffer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<BidderOffer> getBidderOffer(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.getBidderOffer(id);
	}
	
	@RequestMapping(value = "/getReservationsForWeek", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Order>> getReservationsForWeek(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return restaurantManagerService.getReservationsForWeek(id, startDate);
	}
	
	@RequestMapping(value = "/getReservationsForDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Order>> getReservationsForDay(@RequestParam(value = "id") Long id, @RequestParam(value = "date") String startDate) throws ParseException {
		return restaurantManagerService.getReservationsForDay(id, startDate);
	}
}
