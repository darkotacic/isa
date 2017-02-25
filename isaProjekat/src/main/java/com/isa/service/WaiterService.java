package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Waiter;
import com.isa.entity.users.Worker;

public interface WaiterService {

	public List<WorkSchedule> getWorkScheduleForWaiters();
	public Order addOrder(Order order);
	public Iterable<Order> findAllOrders(Restaurant restaurant);
	public Order updateOrder(Order order);
	public void deleteOrder(Order order);
	public Waiter updateWaiterInformation(Waiter waiter);
	public Iterable<Segment> getAllSegments();
	public Iterable<RestaurantTable> getAllTablesForSegment(Long segmentId);
	public RestaurantTable getTable(Long id);
	public Product getProduct(Long id);
	public void deleteOrderItem(Long oi);
	public Iterable<OrderItem> getOrderItemsForOrder(Order order);
	public WorkSchedule getWorkSchedule(Worker worker,Date date);
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate,Date endDate);
	public Waiter getWaiter(Long id);
	
}
