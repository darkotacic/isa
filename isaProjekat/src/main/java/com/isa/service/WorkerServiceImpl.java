package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.repository.OrderItemRepository;
import com.isa.repository.OrderRepository;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public OrderItem addOrderItem(OrderItem oi) {
		return orderItemRepository.save(oi);
	}
	
	@Override
	public Order getOrder(Long id) {
		return orderRepository.findOne(id);
	}

	@Override
	public OrderItem getOrderItem(Long id) {
		return orderItemRepository.findOne(id);
	}

}
