package com.isa.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterControllerTest {

	@Autowired
	private WaiterController waiterController;
	private Order order;
	
	@Before
	public void setUp() {
		order=new Order();
		order.setId(new Long(1));
	}

	@Test
	public void createCheck() throws Exception {
		ResponseEntity<Order> check=waiterController.createCheck(order);
		assertEquals(check.getStatusCode(), HttpStatus.OK);
	}
	
}
