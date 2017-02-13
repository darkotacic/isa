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
		if (this.userRepository.findOne(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RestaurantManager> registerRestaurantManager(RestaurantManager sm, String restaurant_name) {
		if (this.userRepository.findOne(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);	
		if (this.restaurantRepository.findOne(restaurant_name) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		Restaurant r = this.restaurantRepository.findOne(restaurant_name);
		sm.setRestaurnat(r);
		return new ResponseEntity<RestaurantManager>(this.restaurantManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Restaurant> registerRestaurant(Restaurant r) {
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(r), HttpStatus.CREATED);
	}

}
