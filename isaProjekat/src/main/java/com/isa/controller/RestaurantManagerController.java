package com.isa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.Cook;
import com.isa.entity.users.Waiter;
import com.isa.service.RestaurantManagerService;

@RestController
@RequestMapping(value="/restaurantManagers")
public class RestaurantManagerController {
	
	@Autowired 
	private RestaurantManagerService restaurantManagerService;
	
	@RequestMapping(value = "/updateRestaurant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> register(@RequestBody @Valid Restaurant r) {
		return restaurantManagerService.updateRestaurantProfile(r);
	}
	@RequestMapping(value = "/defineRestaurantMenu", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestParam(value="product_ids") Long[] ids, @RequestParam(value="rest_id") Long rest_id) {
		return restaurantManagerService.defineRestaurantMenu(ids, rest_id);
	}
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> addProduct(@RequestBody @Valid Product p, @RequestParam(value="rest_id") Long rest_id) {
		return restaurantManagerService.addProductToMenu(p, rest_id);
	}
	@RequestMapping(value = "/addRestaurantTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantTable> addRestaurantTable(@RequestBody @Valid RestaurantTable rt, @RequestParam(value="segment") Long id) {
		return restaurantManagerService.addRestaurantTableToSegment(rt, id);
	}
	@RequestMapping(value = "/addSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> addSegment(@RequestBody @Valid Segment s, @RequestParam(value="rest") Long id) {
		return restaurantManagerService.addSegmentToRestaurnat(s, id);
	}
	@RequestMapping(value = "/removeSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeSegment(@RequestParam(value="id") Long id) {
		
		return restaurantManagerService.removeSegment(id);
	}
	@RequestMapping(value = "/removeRestaurantTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeRestaurantTable(@RequestParam(value="id") Long id) {
		return restaurantManagerService.removeRestaurantTable(id);
	}
	@RequestMapping(value = "/updateRestaurantTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantTable> updateRestaurantTable(@RequestBody @Valid RestaurantTable rt) {
		return restaurantManagerService.updateRestaurantTable(rt);
	}
	@RequestMapping(value = "/updateSegment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Segment> updateSegment(@RequestBody @Valid Segment s) {
		return restaurantManagerService.updateSegment(s);
	}
	@RequestMapping(value = "/registerCook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cook> updateSegment(@RequestBody @Valid Cook s) {
		return restaurantManagerService.registerCook(s);
	}

	@RequestMapping(value = "/registerWaiter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Waiter> registerWaiter(@RequestBody @Valid Waiter s) {
		return restaurantManagerService.registerWaiter(s);
	}
	
	@RequestMapping(value = "/registerBartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bartender> registerBartender(@RequestBody @Valid Bartender s) {
		return restaurantManagerService.registerBartender(s);
	}

	@RequestMapping(value = "/removeWorker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> removeWorker(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeWorker(id);
	}
	
	@RequestMapping(value = "/registerWorkSchedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WorkSchedule> registerWorkSchedule(@RequestBody @Valid WorkSchedule s, @RequestParam(value = "segment_id",required=false, defaultValue="0") Long s_id, @RequestParam(value = "worker_id") Long w_id, @RequestParam(value = "repl_id", required=false, defaultValue="0") Long r_id) {
		return restaurantManagerService.registerWorkSchedule(s, w_id, s_id, r_id);
	}
	
	@RequestMapping(value = "/removeWorkSchedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String removeWorkSchedule(@RequestParam(value = "id") Long id) {
		return restaurantManagerService.removeWorkSchedule(id);
	}
}
