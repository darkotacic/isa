package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;
import com.isa.entity.RestaurantTable;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Iterable<Order> findByTable(RestaurantTable t);	
	@Query("select sum(g.gradeOfService)/count(g.gradeOfService) from Order o inner join o.grades as g where o.waiter.id = ?1")
	Double getGradeForWorker(Long t);
	
	
	@Query("select sum(g.gradeOfOrderItem)/count(g.gradeOfOrderItem) from Order o inner join o.orderedItems as oi inner join o.grades as g where oi.product.id = ?1 and o.table.segment.restaurant.id = ?2")
	Double getGradeForOrder(Long t, Long id);
}
