package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;
import com.isa.entity.RestaurantTable;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	Iterable<Order> findByTable(RestaurantTable t);
}
