package com.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	public Iterable<Order> findByTable(RestaurantTable t);	
	@Query("select sum(g.gradeOfService)/count(g.gradeOfService) from Order o inner join o.grades as g where o.waiter.id = ?1 and o.orderStatus='PAID'")
	Double getGradeForWorker(Long t);
	
	@Query("select sum(g.gradeOfOrderItem)/count(g.gradeOfOrderItem) from Order o inner join o.orderedItems as oi inner join o.grades as g where oi.product.id = ?1 and o.table.segment.restaurant.id = ?2 and o.orderStatus='PAID' and oi.product.productType != 'DRINK'")
	Double getGradeForOrder(Long t, Long id);
	
	@Query("select o from Order o inner join o.table as table inner join table.segment as segment inner join segment.restaurant as res where res=?1")
	public Iterable<Order> getOrders(Restaurant restaurant);

	@Query("select o from Reservation r inner join r.orders as o where r.restaurant = ?1 and o.date = ?2")
	List<Order> getReservationsOfRestaurantForDay(Restaurant rest, Date date);
	
	@Query("select o from Reservation r inner join r.orders as o where r.restaurant = ?1 and o.date between ?2 and ?3")
	List<Order> getReservationsOfRestaurantForWeek(Restaurant rest, Date date, Date end);
	
}
