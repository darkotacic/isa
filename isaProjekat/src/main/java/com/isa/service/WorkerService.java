package com.isa.service;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;

public interface WorkerService {

	public Order getOrder(Long id);
	public OrderItem getOrderItem(Long id);
	
}
