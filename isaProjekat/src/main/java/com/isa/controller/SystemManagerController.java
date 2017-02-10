package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.SystemManager;
import com.isa.service.RestaurantManagerService;
import com.isa.service.RestaurantService;
import com.isa.service.SystemManagerService;

@RestController
@RequestMapping(value = "/systemManager")
public class SystemManagerController {

	@Autowired
	private RestaurantManagerService restaurantManagerService;
	
	@Autowired 
	private SystemManagerService systemManagerService;
	
	@Autowired
	private RestaurantService restaurantService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestaurantManager> register(@RequestBody RestaurantManager sm, @RequestParam(value="id") String param) {
		return restaurantManagerService.register(sm, param);
	}
	
	@RequestMapping(value = "/registerSystem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SystemManager> registerSystem(@RequestBody SystemManager sm) {
		return systemManagerService.register(sm);
	}
	
	@RequestMapping(value="/getAllRestaurantManagers",
			method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<RestaurantManager> getAllRestaurantManagers() {
		return restaurantManagerService.getAll();
	}
	@RequestMapping(value = "/registerRestaurant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Restaurant> registerRestaurant(@RequestBody Restaurant r) {
		return restaurantService.register(r);
	}
}
