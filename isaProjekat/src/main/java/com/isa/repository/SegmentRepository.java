package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Long>{

	Segment findByRestaurantAndId(Restaurant t, Long id);
	List<Segment> findByRestaurant(Restaurant r);
	
	@Query("select s from Segment s where s.restaurant=?1")
	public Iterable<Segment> getSegmentsForRestautant(Restaurant restaurant);
	
}
