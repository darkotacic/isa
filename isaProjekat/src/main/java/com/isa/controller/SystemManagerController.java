package com.isa.controller;

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

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.SystemManager;
import com.isa.service.SystemManagerService;

@RestController
@RequestMapping(value = "/systemmanagers")
public class SystemManagerController {

	@Autowired 
	private SystemManagerService systemManagerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager> updateSystem(@RequestBody @Valid SystemManager sm) {
		session.setAttribute("user", sm);
		return systemManagerService.updateSystemManager(sm);
	}
	
	@RequestMapping(value = "/getAllRestaurants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return systemManagerService.getAllRestaurants();
	}
	
	@RequestMapping(value = "/getRestaurantManagersForRestaurant", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<RestaurantManager>> getRestaurantManagersForRestaurant(@RequestParam(value="id") Long id) {
		return systemManagerService.getRestaurantManagersForRestaurant(id);
	}
	
	@RequestMapping(value = "/getAllSystemManager", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<SystemManager>> getAllSystemManager() {
		return systemManagerService.getAllSystemManager();
	}
	
	@RequestMapping(value = "/registerRestaurantManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantManager> register(@RequestBody @Valid RestaurantManager sm, @RequestParam(value="id") Long id) {
		return systemManagerService.registerRestaurantManager(sm, id);
	}
	
	@RequestMapping(value = "/registerSystem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager> registerSystem(@RequestBody @Valid SystemManager sm) {
		return systemManagerService.registerSystemManager(sm);
	}
	
	@RequestMapping(value = "/registerRestaurant", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Restaurant> registerRestaurant(@RequestBody @Valid Restaurant r) {
		return systemManagerService.registerRestaurant(r);
	}
	
	@RequestMapping(value = "/updateRestaurant", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody @Valid Restaurant r) {
		return systemManagerService.updateRestaurant(r);
	}
	
	@RequestMapping(value = "/deleteRestaurant", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Restaurant> deleteRestaurant(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeRestaurant(r_id);
	}
	@RequestMapping(value = "/deleteSystemManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<SystemManager>  deleteSystemManager(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeSystemManager(r_id);
	}
	@RequestMapping(value = "/deleteRestaurantManager", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<RestaurantManager> deleteRestaurantManager(@RequestParam(value="id") Long r_id) {
		return systemManagerService.removeRestaurantManager(r_id);
	}
}
