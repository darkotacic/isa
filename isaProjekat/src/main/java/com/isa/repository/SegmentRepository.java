package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Segment;

public interface SegmentRepository extends CrudRepository<Segment, Long>{

	Segment findOne(Long id);
	
	@SuppressWarnings("unchecked")
	Segment save(Segment s);
	
	void delete(Segment s);
	
	void delete(Long id);
	
}
