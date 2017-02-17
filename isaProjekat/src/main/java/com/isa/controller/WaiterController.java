package com.isa.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.OrderItemStatus;
import com.isa.entity.OrderStatus;
import com.isa.entity.Product;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.User;
import com.isa.entity.users.Waiter;
import com.isa.service.WaiterService;
import com.isa.service.WorkerService;

@RestController
@RequestMapping("/waiters")
public class WaiterController {

	@Autowired
	private WaiterService waiterService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(
			value = "/getWorkSchedules",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(readOnly=true)
	public ResponseEntity<Iterable<WorkSchedule>> getWorkspaceScheduleForWaiters(){
		Iterable<WorkSchedule> schedules=waiterService.getWorkScheduleForWaiters();
		return new ResponseEntity<Iterable<WorkSchedule>>(schedules, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value = "/createOrder/{tableId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> addOrder(@PathVariable("tableId")Long tableId){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("WAITER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		RestaurantTable rt=waiterService.getTable(tableId);
		Order order=new Order();
		order.setWaiter((Waiter)user);
		order.setTable(rt);
		double orderTime=calendar.get(Calendar.HOUR_OF_DAY)+(calendar.get(Calendar.MINUTE)/100.0);
		order.setTime(orderTime);
		order.setDate(date);
		order.setPrice(0);
		order.setOrderStatus(OrderStatus.NOTPAID);
		Order o=waiterService.addOrder(order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateOrder/{tableId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> editOrder(@RequestBody Order order,@PathVariable("tableId")Long tableId){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("WAITER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		RestaurantTable rt=waiterService.getTable(tableId);
		order.setTable(rt);
		order.setWaiter((Waiter)user);
		Order o=waiterService.updateOrder(order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getOrders",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<Order>> findAllOrders(){
		Iterable<Order> orders=waiterService.findAllOrders();
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deleteOrder",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public void deleteOrder(@RequestBody Order order){
		waiterService.deleteOrder(order);
	}
	
	@RequestMapping(
			value="/addOrderItem/{orderId}/{productId}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem,@PathVariable("orderId")Long orderId,@PathVariable("productId")Long productId){
		Order order=workerService.getOrder(orderId);
		orderItem.setOrder(order);
		Product product=waiterService.getProduct(productId);
		orderItem.setProduct(product);
		orderItem.setOrderItemStatus(OrderItemStatus.ONHOLD);
		OrderItem oi=workerService.addOrderItem(orderItem);
		return new ResponseEntity<OrderItem>(oi, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/updateOrderItem",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> editOrderItem(@RequestBody OrderItem orderItem){
		OrderItem temp=workerService.getOrderItem(orderItem.getId());
		orderItem.setOrder(temp.getOrder());
		orderItem.setProduct(temp.getProduct());
		OrderItem oi=workerService.addOrderItem(orderItem);
		return new ResponseEntity<OrderItem>(oi, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/deleteOrderItem",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> deleteOrderItem(@RequestBody OrderItem orderItem){
		OrderItem temp=workerService.getOrderItem(orderItem.getId());
		waiterService.deleteOrderItem(temp.getId());
		return new ResponseEntity<OrderItem>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/makeCheck",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> createCheck(@RequestBody Order order){
		Date checkDate=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(checkDate);
		Order o=workerService.getOrder(order.getId());
		Iterable<OrderItem> orderItems=waiterService.getOrderItemsForOrder(o);
		int price=0;
		for(OrderItem oi:orderItems){
			price+=oi.getProduct().getPrice()*oi.getQuantity();
		}
		WorkSchedule worker=waiterService.getWorkSchedule(o.getWaiter(), o.getDate());
		if(worker==null)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		double checkTime=calendar.get(Calendar.HOUR_OF_DAY)+(calendar.get(Calendar.MINUTE)/100.0);
		if(o.getTime()>checkTime)
			checkTime+=24.0;
		Waiter replacement=(Waiter) worker.getReplacement();
		if(checkTime>worker.getEndTime() && replacement!=null){
			WorkSchedule repSchedule=waiterService.getWorkSchedule(replacement, worker.getSecondDate());
			double waiter1Time=repSchedule.getStartTime()-o.getTime();
			double waiter2Time=checkTime-repSchedule.getStartTime();
			if(waiter2Time>waiter1Time)
				o.setWaiter(replacement);
		}
		o.setOrderStatus(OrderStatus.PAID);
		o.setPrice(price);
		waiterService.addOrder(o);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateInformation",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Waiter> updateInformation(@RequestBody Waiter waiter){
		Waiter w=waiterService.updateWaiterInformation(waiter);
		return new ResponseEntity<Waiter>(w, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getSegments",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Segment>> getAllSegments(){
		Iterable<Segment> segments=waiterService.getAllSegments();
		return new ResponseEntity<Iterable<Segment>>(segments, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getTablesForSegment/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<RestaurantTable>> getTablesForSegment(@PathVariable("id")Long segmentId){
		Iterable<RestaurantTable> tables=waiterService.getAllTablesForSegment(segmentId);
		return new ResponseEntity<Iterable<RestaurantTable>>(tables, HttpStatus.OK);
	}
	
}
