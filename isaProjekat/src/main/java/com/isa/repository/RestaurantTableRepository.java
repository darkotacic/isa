package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import java.util.List;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {
	
	@SuppressWarnings("unchecked")
	RestaurantTable save(RestaurantTable rt);
	
	void delete(RestaurantTable t);
	
	void delete(Long id);
	
	List<RestaurantTable> findBySegment(Segment segment);
}
