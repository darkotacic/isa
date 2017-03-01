package com.isa.controller;

import java.text.ParseException;
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

import com.isa.entity.Group;
import com.isa.entity.OrderItem;
import com.isa.entity.OrderItemStatus;
import com.isa.entity.ProductType;
import com.isa.entity.users.Cook;
import com.isa.entity.users.User;
import com.isa.mail.SendEmail;
import com.isa.service.CookService;
import com.isa.service.WorkerService;

@RestController
@RequestMapping("/cooks")
public class CookController {

	@Autowired
	private CookService cookService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private HttpSession session; 
	
	@RequestMapping(
			value = "/getWorkSchedules",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Group>> getWorkSchedules(){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("COOK"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		List<Group> schedules;
		try {
			schedules = cookService.getWorkSchedulesForMonth(0,((Cook)user).getRestaurant());
		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Group>>(schedules, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getFoodOrderItems/{cookType}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<OrderItem>> getFoodOrderItems(@PathVariable("cookType")String cookType){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("COOK"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		ProductType pt=ProductType.valueOf(cookType);
		Iterable<OrderItem> foodItems=cookService.getFoodOrderItems(pt,((Cook)user).getRestaurant());
		return new ResponseEntity<Iterable<OrderItem>>(foodItems, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/startPreparing",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> startPreparing(@RequestBody OrderItem orderItem){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("COOK"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		OrderItem startPrepare=workerService.getOrderItem(orderItem.getId());
		startPrepare.setCook((Cook)user);
		startPrepare.setOrderItemStatus(OrderItemStatus.ONPREPARATION);
		OrderItem temp=workerService.addOrderItem(startPrepare);
		new SendEmail(temp.getOrder().getWaiter().getEmail(),"", "Notice of beginning of the preparation of the meal.", "The meal: "+temp.getProduct().getProductName()+", quantity: "+temp.getQuantity()+"\n was started with preparation for order "+temp.getOrder().getId()+", table "+temp.getOrder().getTable().getId()+" by the chef "+temp.getCook().getSurname()+".").start();
		return new ResponseEntity<OrderItem>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getTasks",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<OrderItem>> getTasks(){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("COOK"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Iterable<OrderItem> tasks=cookService.getTasks((Cook) user);
		return new ResponseEntity<Iterable<OrderItem>>(tasks, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/notify",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<OrderItem> notify(@RequestBody OrderItem orderItem){
		OrderItem temp=workerService.getOrderItem(orderItem.getId());
		temp.setOrderItemStatus(OrderItemStatus.DONE);
		workerService.addOrderItem(temp);
		new SendEmail(temp.getOrder().getWaiter().getEmail(),"", "Notice about completion of the meal.", "The meal: "+temp.getProduct().getProductName()+", quantity: "+temp.getQuantity()+"\n is completed for order "+temp.getOrder().getId()+" and ready to be served on table "+temp.getOrder().getTable().getId()+".").start();
		return new ResponseEntity<OrderItem>(temp, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Cook> updateInformation(@RequestBody Cook cook){
		Cook temp=cookService.getCook(cook.getId());
		temp.setEmail(cook.getEmail());
		temp.setUserName(cook.getUserName());
		temp.setSurname(cook.getSurname());
		temp.setPassword(cook.getPassword());
		temp.setDateOfBirth(cook.getDateOfBirth());
		temp.setShoeNumber(cook.getShoeNumber());
		temp.setShirtSize(cook.getShirtSize());
		temp.setFirstLogIn(false);
		Cook c=cookService.updateCookInformation(temp);
		session.setAttribute("user", temp);
		return new ResponseEntity<Cook>(c, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getWorkSchedulesForMonth/{monthNumber}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Group>> getWorkSchedulesForMonth(@PathVariable("monthNumber")int monthNumber){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("COOK"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		List<Group> schedules;
		try {
			schedules = cookService.getWorkSchedulesForMonth(monthNumber,((Cook)user).getRestaurant());
		} catch (ParseException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Group>>(schedules, HttpStatus.OK);
	}
	
}
