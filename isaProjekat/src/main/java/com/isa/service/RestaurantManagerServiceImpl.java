package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;
import com.isa.repository.RestaurantManagerRepository;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.UserRepository;

@Service
@Transactional
public class RestaurantManagerServiceImpl implements RestaurantManagerService {

	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestaurantRepository restaurnatRepository;

	@Override
	public ResponseEntity<RestaurantManager> register(RestaurantManager sm, String param) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null || sm.getEmail() == null || sm.getName() == null
				|| sm.getPassword() == null || sm.getSurname() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		if(this.restaurnatRepository.findById(Long.parseLong(param)) == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		Restaurant r = this.restaurnatRepository.findById(Long.parseLong(param));
		sm.setRestaurnat(r);
		return new ResponseEntity<RestaurantManager>(this.restaurantManagerRepository.save(sm), HttpStatus.CREATED);
	}

	@Override
	public Iterable<RestaurantManager> getAll() {
		// TODO Auto-generated method stub
		return this.restaurantManagerRepository.findAll();
	}

}
