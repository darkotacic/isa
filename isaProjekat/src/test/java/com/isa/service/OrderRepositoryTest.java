package com.isa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Order;
import com.isa.entity.Restaurant;
import com.isa.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;
	
	private Restaurant restaurant;
	
	@Before
	public void setUp(){
		restaurant=new Restaurant(1,"Dva stapica","Kineski restoran");
	}
	
	@Test
	public void getOrders(){
		Iterable<Order> orders=orderRepository.getOrders(restaurant);
		boolean equal=true;
		for(Order order:orders){
			if(order.getTable().getSegment().getRestaurant().getId().longValue()!=restaurant.getId().longValue()){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
}
