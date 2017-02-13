package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.repository.ProductRepository;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.RestaurantTableRepository;
import com.isa.repository.SegmentRepository;

@Service
@Transactional
public class RestaurantManagerServiceImpl implements RestaurantManagerService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SegmentRepository segmentRepository;

	@Autowired
	private RestaurantTableRepository restaurantTableRepository;

	@Override
	public ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r) {
		Restaurant temp = this.restaurantRepository.findOne(r.getName());
		temp.setDescription(r.getDescription());
		temp.setName(r.getName());
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(temp), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> defineRestaurantMenu(String[] products, String rest_name) {
		/*
		 * Restaurant r = this.restaurantRepository.findByName(rest_name);
		 * for(int i = 0; i < products.length; i++) { Product p =
		 * this.productRepository.findByName(products[i]);
		 * //this.restaurantRepository.write(p.getId(), r.getId()); }
		 */
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Product> addProductToMenu(Product p, String r_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, String r_name) {
		Restaurant r = this.restaurantRepository.findOne(r_name);
		s.setRestaurant(r);
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<RestaurantTable> addRestaurantTableToSegment(RestaurantTable t, Long segment_id) {
		Segment s = this.segmentRepository.findOne(segment_id);
		t.setSegment(s);
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> removeSegment(Long id) {
		this.segmentRepository.delete(id);
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<String> removeRestaurantTable(Long id) {

		this.restaurantTableRepository.delete(id);
		return new ResponseEntity<String>("Vidi bazu", HttpStatus.MOVED_PERMANENTLY);
	}

	@Override
	public ResponseEntity<RestaurantTable> updateRestaurantTable(RestaurantTable t) {
		return new ResponseEntity<RestaurantTable>(this.restaurantTableRepository.save(t), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Segment> updateSegment(Segment s) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.ACCEPTED);
	}

}
