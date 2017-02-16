package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;
import com.isa.entity.users.SystemManager;
import com.isa.repository.RestaurantManagerRepository;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.SystemManagerRepository;
import com.isa.repository.UserRepository;

@Service
@Transactional
public class SystemManagerServiceImpl implements SystemManagerService {

	@Autowired
	private SystemManagerRepository systemManagerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;
	

	@Override
	public ResponseEntity<SystemManager> registerSystemManager(SystemManager sm) {
		if (this.userRepository.findOne(sm.getId()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RestaurantManager> registerRestaurantManager(RestaurantManager sm, Long restaurant_id) {
		if (this.userRepository.findByEmail(sm.getEmail())!= null)
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);	
		if (this.restaurantRepository.findOne(restaurant_id) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		Restaurant r = this.restaurantRepository.findOne(restaurant_id);
		sm.setRestaurant(r);
		RestaurantManager rs = this.restaurantManagerRepository.save(sm);
		return new ResponseEntity<RestaurantManager>(rs, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Restaurant> registerRestaurant(Restaurant r) {
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(r), HttpStatus.CREATED);
	}
	

	@Override
	public ResponseEntity<String> removeRestaurant(Long r_id) {
		// TODO Auto-generated method stub
		this.restaurantRepository.delete(r_id);
		return new ResponseEntity<String>("Oha", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public String removeSystemManager(Long r_id) {
		this.systemManagerRepository.delete(r_id);
		return "vidjecemo sta nam na frontu treba";
	}

	@Override
	public String removeRestaurantManager(Long r_id) {
		this.restaurantManagerRepository.delete(r_id);
		return "isto ko gore";
	}

	@Override
	public ResponseEntity<Iterable<Restaurant>> getAllRestaurants() {
		return new ResponseEntity<Iterable<Restaurant>>(this.restaurantRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<RestaurantManager>> getRestaurantManagersForRestaurant(Long id) {
		return new ResponseEntity<Iterable<RestaurantManager>>(this.restaurantManagerRepository.findByRestaurant(this.restaurantRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Iterable<SystemManager>> getAllSystemManager() {
		return new ResponseEntity<Iterable<SystemManager>>(this.systemManagerRepository.findAll(), HttpStatus.OK);
	}

}
