package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Long>{

	public Iterable<RestaurantTable> tables(Long id);
	
}
