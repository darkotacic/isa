package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;
import com.isa.entity.RestaurantTable;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Iterable<Order> findByTable(RestaurantTable t);
	
	@Query("select sum(g.gradeOfOrderItem)/count(g.gradeOfOrderItem) from Order o inner join o.grades as g where o.id = ?1")
	double getGradeForOrder(Long t);
}
