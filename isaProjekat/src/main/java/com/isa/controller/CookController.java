package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.WorkSchedule;
import com.isa.service.CookService;

@RestController
@RequestMapping("/cooks")
public class CookController {

	@Autowired
	private CookService cookService;
	
	//@Autowired
	//private 
	
	@RequestMapping(
			value = "/getWorkSchedules",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<WorkSchedule>> getWorkSchedules(){
		Iterable<WorkSchedule> schedules=cookService.getWorkScheduleForCooks();
		return new ResponseEntity<Iterable<WorkSchedule>>(schedules, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/getFoodOrderItems/{cookType}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<OrderItem>> getFoodOrderItems(@PathVariable("cookType")String cookType){
		ProductType pt=ProductType.valueOf(cookType);
		Iterable<OrderItem> foodItems=cookService.getFoodOrderItems(pt);
		return new ResponseEntity<Iterable<OrderItem>>(foodItems, HttpStatus.OK);
	}
}
