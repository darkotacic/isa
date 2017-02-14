package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Order;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Waiter;
import com.isa.repository.OrderRepository;
import com.isa.repository.RestaurantTableRepository;
import com.isa.repository.SegmentRepository;
import com.isa.repository.WaiterRepository;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class WaiterServiceImpl implements WaiterService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WaiterRepository waiterRepository;
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	
	@Override
	public Iterable<WorkSchedule> getWorkScheduleForWaiters(){
		return workScheduleRepository.getWorkScheduleForWaiters();
	}
	
	@Override
	public Order addOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Order updateOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Iterable<Order> findAllOrders(){
		return orderRepository.findAll();
	}
	
	@Override
	public void deleteOrder(Long orderId){
		orderRepository.delete(orderId);
	}
	
	@Override
	public Waiter updateWaiterInformation(Waiter waiter) {
		return waiterRepository.save(waiter);
	}

	@Override
	public Iterable<Segment> getAllSegments() {
		return segmentRepository.findAll();
	}
	
	@Override
	public Iterable<RestaurantTable> getAllTablesForSegment(Long segmentId) {
		Segment segment=segmentRepository.findOne(segmentId);
		return restaurantTableRepository.findBySegment(segment);
	}

}
