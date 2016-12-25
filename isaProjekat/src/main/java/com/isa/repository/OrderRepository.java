package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
}
