package com.isa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.OrderItem;
import com.isa.entity.OrderItemStatus;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.User;
import com.isa.service.BartenderService;
import com.isa.service.WorkerService;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {

	@Autowired
	private BartenderService bartenderService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value="/getWorkSchedules",
					method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<WorkSchedule>> getWorkSchedules(){
		Iterable<WorkSchedule> schedules=bartenderService.getWorkScheduleForBartenders();
		return new ResponseEntity<Iterable<WorkSchedule>>(schedules, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getDrinkOrderItems",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<OrderItem>> getDringOrderItems(){
		Iterable<OrderItem> drinkOrders=bartenderService.findDrinkOrderItems();
		return new ResponseEntity<Iterable<OrderItem>>(drinkOrders, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/notify",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> notify(@RequestBody OrderItem orderItem){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("BARTENDER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		OrderItem temp=workerService.getOrderItem(orderItem.getId());
		temp.setOrderItemStatus(OrderItemStatus.DONE);
		workerService.addOrderItem(temp);
		return new ResponseEntity<OrderItem>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/updateInformation",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Bartender> updateInformation(@RequestBody Bartender bartender){
		Bartender b=bartenderService.updateBartenderInformation(bartender);
		return new ResponseEntity<Bartender>(b, HttpStatus.OK);
	}
	
}
