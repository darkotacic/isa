package com.isa.conntroller;

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
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Waiter;
import com.isa.service.WaiterService;

@RestController
@RequestMapping("/waiters")
public class WaiterController {

	@Autowired
	private WaiterService waiterService;
	
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
			value = "/createOrder",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
		Order o=waiterService.addOrder(order);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateOrder",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Order> editOrder(@RequestBody Order order){
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
			value = "/deleteOrders",
			method = RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public void deleteOrder(Long orderId){
		waiterService.deleteOrder(orderId);
	}
	
	@RequestMapping(
			value = "/updateInformation",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Waiter> updateInformation(Waiter waiter){
		Waiter w=waiterService.updateWaiterInformation(waiter);
		return new ResponseEntity<Waiter>(w, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/updateInfromation",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<Segment>> updateInformation(){
		Iterable<Segment> segments=waiterService.getAllSegments();
		return new ResponseEntity<Iterable<Segment>>(segments, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/getTablesForSegment/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<RestaurantTable>> getTablesForSegment(@PathVariable("id")Long segmentId){
		Iterable<RestaurantTable> tables=waiterService.getAllTablesForSegment(segmentId);
		return new ResponseEntity<Iterable<RestaurantTable>>(tables, HttpStatus.OK);
	}
	
}
