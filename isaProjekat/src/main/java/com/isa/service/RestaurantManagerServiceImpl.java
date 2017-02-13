package com.isa.service;

import java.util.HashSet;
import java.util.Set;

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

/*	@Autowired
	private OrderRepository orderRepository;
*/
	@Override
	public ResponseEntity<Restaurant> updateRestaurantProfile(Restaurant r) {
		Restaurant temp = this.restaurantRepository.findOne(r.getId());
		temp.setDescription(r.getDescription());
		temp.setName(r.getName());
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(temp), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> defineRestaurantMenu(Long[] products, Long rest_id) {
		Restaurant t = this.restaurantRepository.findOne(rest_id);
		Set<Product> pro = new HashSet<Product>();
		if (t.getMenu() != null)
			pro = t.getMenu();
		for (int i = 0; i < products.length; i++) {
			Set<Restaurant> rest = new HashSet<Restaurant>();
			Product p = this.productRepository.findOne(products[i]);
			if (p.getRestaurants() != null)
				rest = p.getRestaurants();
			rest.add(t);
			pro.add(p);
			p.setRestaurants(rest);
			this.productRepository.save(p);
		}
		t.setMenu(pro);
		this.restaurantRepository.save(t);
		return new ResponseEntity<String>("vidi bazu", HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Product> addProductToMenu(Product p, Long r_id) {
		Restaurant t = this.restaurantRepository.findOne(r_id);
		Set<Product> pro = new HashSet<Product>();
		if (t.getMenu() != null)
			pro = t.getMenu();
		Set<Restaurant> rest = new HashSet<Restaurant>();
		rest.add(t);
		p.setRestaurants(rest);
		pro.add(p);
		t.setMenu(pro);
		this.restaurantRepository.save(t);
		return new ResponseEntity<Product>(this.productRepository.save(p), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<Segment> addSegmentToRestaurnat(Segment s, Long r_id) {
		Restaurant r = this.restaurantRepository.findOne(r_id);
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
		return new ResponseEntity<Segment>(this.segmentRepository.save(s), HttpStatus.ACCEPTED);
	}

}
