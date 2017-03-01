package com.isa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.isa.entity.Group;
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

	public Order addOrder(Order order);
	public Iterable<Order> findAllOrders(Restaurant restaurant);
	public Order updateOrder(Order order);
	public void deleteOrder(Order order);
	public Waiter updateWaiterInformation(Waiter waiter);
	public Iterable<Segment> getAllSegments(Restaurant restaurant);
	public Iterable<RestaurantTable> getAllTablesForSegment(Long segmentId);
	public RestaurantTable getTable(Long id);
	public Product getProduct(Long id);
	public void deleteOrderItem(Long oi);
	public Iterable<OrderItem> getOrderItemsForOrder(Order order);
	public WorkSchedule getWorkSchedule(Worker worker,Date date);
	public List<Group> getWorkSchedulesForMonth(int monthNumber,Restaurant restaurant) throws ParseException;
	public Waiter getWaiter(Long id);
	public Segment getSegment(Long id);
	public WorkSchedule getWorkScheduleForSegment(Segment segment);
	WorkSchedule getWorkScheduleForSegment(Segment segment, double startTime,Date date);
	
}
