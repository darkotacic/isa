package com.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.users.Waiter;

public interface WaiterRepository extends CrudRepository<Waiter, Long> {

	@Query("select sum(o.price) from Waiter w inner join w.orders as o where w.restaurant = ?1 and o.orderStatus='PAID' and o.date between ?2 and ?3")
	Double getEarningsForRestaurant(Restaurant t, Date startDate, Date endDate);
	@Query("select sum(o.price) from Waiter w inner join w.orders as o where w.id = ?1 and o.orderStatus='PAID'")
	Double getEarningsForWaiter(Long id);
	
	List<Waiter> findByUserNameAndRestaurant(String name, Restaurant res);
}
