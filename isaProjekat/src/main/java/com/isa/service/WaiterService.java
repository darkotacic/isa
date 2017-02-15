package com.isa.service;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.Product;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Waiter;

public interface WaiterService {

	public Iterable<WorkSchedule> getWorkScheduleForWaiters();
	public Order addOrder(Order order);
	public Iterable<Order> findAllOrders();
	public Order updateOrder(Order order);
	public void deleteOrder(Long orderId);
	public Waiter updateWaiterInformation(Waiter waiter);
	public Iterable<Segment> getAllSegments();
	public Iterable<RestaurantTable> getAllTablesForSegment(Long segmentId);
	public RestaurantTable getTable(Long id);
	public Product getProduct(Long id);
	public OrderItem addOrderItem(OrderItem oi);
	public void deleteOrderItem(Long oi);
	public Iterable<OrderItem> getOrderItemsForOrder(Order order);
	
}
