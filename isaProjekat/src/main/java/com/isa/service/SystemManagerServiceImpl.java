package com.isa.service;

import java.util.List;

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
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(sm), HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<SystemManager> updateSystemManager(SystemManager sm) {
		SystemManager temp = this.systemManagerRepository.findOne(sm.getId());
		if(!temp.getEmail().equals(sm.getEmail()))
			if(this.userRepository.findByEmail(sm.getEmail()) != null)
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(sm.getDateOfBirth());
		temp.setEmail(sm.getEmail());
		temp.setPassword(sm.getPassword());
		temp.setSurname(sm.getSurname());
		temp.setUserName(sm.getUserName());
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(temp), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RestaurantManager> registerRestaurantManager(RestaurantManager sm, Long restaurant_id) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
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
	public ResponseEntity<Restaurant> updateRestaurant(Restaurant r) {
		Restaurant temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setRestaurantName(r.getRestaurantName());
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(temp), HttpStatus.CREATED);
	}


	@Override
	public ResponseEntity<Restaurant> removeRestaurant(Long r_id) {
		// TODO Auto-generated method stub
		this.restaurantRepository.delete(r_id);
		return new ResponseEntity<Restaurant>(this.restaurantRepository.findOne(r_id), HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<SystemManager> removeSystemManager(Long r_id) {
		this.systemManagerRepository.delete(r_id);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.findOne(r_id),
				HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<RestaurantManager> removeRestaurantManager(Long r_id) {
		this.restaurantManagerRepository.delete(r_id);
		return new ResponseEntity<RestaurantManager>(this.restaurantManagerRepository.findOne(r_id),
				HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<List<Restaurant>> getAllRestaurants() {
		return new ResponseEntity<List<Restaurant>>((List<Restaurant>) this.restaurantRepository.findAll(),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RestaurantManager>> getRestaurantManagersForRestaurant(Long id) {
		return new ResponseEntity<List<RestaurantManager>>((List<RestaurantManager>) this.restaurantManagerRepository
				.findByRestaurant(this.restaurantRepository.findOne(id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<SystemManager>> getAllSystemManager() {
		return new ResponseEntity<List<SystemManager>>((List<SystemManager>) this.systemManagerRepository.findAll(),
				HttpStatus.OK);
	}

}
