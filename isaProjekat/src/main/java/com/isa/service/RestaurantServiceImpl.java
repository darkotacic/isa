package com.isa.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Grade;
import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.Segment;
import com.isa.entity.users.RestaurantManager;
import com.isa.repository.GradeRepository;
import com.isa.repository.ProductRepository;
import com.isa.repository.RestaurantManagerRepository;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.SegmentRepository;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantManagerRepository restaurantManagerRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private SegmentRepository segmentRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseEntity<Restaurant> register(Restaurant r, String[] managers_ids, String[] product_ids,
			String[] grade_ids, String[] segment_ids) {
		Set<RestaurantManager> rm = new HashSet<RestaurantManager>();
		Set<Product> p = new HashSet<Product>();
		Set<Segment> s = new HashSet<Segment>();
		Set<Grade> g = new HashSet<Grade>();
		for (int i = 0; i < managers_ids.length; i++)
			rm.add(this.restaurantManagerRepository.findById(Long.parseLong(managers_ids[i])));
		for (int i = 0; i < product_ids.length; i++)
			p.add(this.productRepository.findOne(Long.parseLong(product_ids[i])));
		for (int i = 0; i < grade_ids.length; i++)
			g.add(this.gradeRepository.findOne(Long.parseLong(grade_ids[i])));
		for (int i = 0; i < segment_ids.length; i++)
			s.add(this.segmentRepository.findOne(Long.parseLong(segment_ids[i])));
		r.setGrades(g);
		r.setMenu(p);
		r.setRestaurantManagers(rm);
		r.setSegments(s);
		return new ResponseEntity<Restaurant>(this.restaurantRepository.save(r), HttpStatus.CREATED);
	}

}
