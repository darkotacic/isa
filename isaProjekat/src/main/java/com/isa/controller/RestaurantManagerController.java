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

import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
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
	public ResponseEntity<String> register(@RequestParam(value="names") String[] names, @RequestParam(value="rest") String rest) {
		return restaurantManagerService.defineRestaurantMenu(names, rest);
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
}
