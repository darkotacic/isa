package com.isa.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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

import com.isa.entity.OrderItem;
import com.isa.entity.OrderItemStatus;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.entity.users.User;
import com.isa.mail.SendEmail;
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
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("BARTENDER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Iterable<WorkSchedule> schedules=bartenderService.getWorkScheduleForBartenders(((Bartender)user).getRestaurant());
		return new ResponseEntity<Iterable<WorkSchedule>>(schedules, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getDrinkOrderItems",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<OrderItem>> getDringOrderItems(){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("BARTENDER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Iterable<OrderItem> drinkOrders=bartenderService.findDrinkOrderItems(((Bartender)user).getRestaurant());
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
		new SendEmail(temp.getOrder().getWaiter().getEmail(),"", "Notice about completion of the drink.", "The drink: "+temp.getProduct().getProductName()+", quantity: "+temp.getQuantity()+"\n is completed by bartender "+temp.getBartender().getSurname()+" for order "+temp.getOrder().getId()+" and ready to be served on table "+temp.getOrder().getTable().getId()+".").start();;
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
		Bartender temp=bartenderService.getBartender(bartender.getId());
		temp.setEmail(bartender.getEmail());
		temp.setUserName(bartender.getUserName());
		temp.setSurname(bartender.getSurname());
		temp.setPassword(bartender.getPassword());
		temp.setDateOfBirth(bartender.getDateOfBirth());
		temp.setShoeNumber(bartender.getShoeNumber());
		temp.setShirtSize(bartender.getShirtSize());
		Bartender b=bartenderService.updateBartenderInformation(temp);
		session.setAttribute("user", temp);
		return new ResponseEntity<Bartender>(b, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getWorkSchedules/{startDate}/{endDate}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<WorkSchedule>> getWorkScheduleBetween(@PathVariable("startDate")Date startDate,@PathVariable("endDate")Date endDate){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("BARTENDER"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		List<WorkSchedule> ws=null;
		ws = bartenderService.getWorkScheduleBetween(startDate, endDate,((Bartender)user).getRestaurant());
		Collections.sort(ws);
		return new ResponseEntity<Iterable<WorkSchedule>>(ws, HttpStatus.OK);
	}
	
}
