package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Grade;
import com.isa.entity.Order;
import com.isa.entity.users.Guest;

public interface GradeRepository extends CrudRepository<Grade, Long> {
	Grade findOne(Long id);

	@Query("select g from Grade g where g.order=?1 and g.guest=?2")
	public Grade fingGradeByOrder(Order orde,Guest guest);
	
}
