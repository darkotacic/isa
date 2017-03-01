package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Order;
import com.isa.entity.Restaurant;
import com.isa.repository.OrderRepository;
import com.isa.repository.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
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
	

	@Test
	public void getGradeForWorker(){
		double grade = orderRepository.getGradeForWorker((long) 3);
		assertEquals(grade, 6, 0.0001);
	}
	
	@Test
	public void getReservationsOfRestaurantForDay() throws ParseException{
		Date d = formatter.parse("02-28-2017");
		List<Order> orders = orderRepository.getReservationsOfRestaurantForDay(restaurantRepository.findOne((long) 1), d);
		assertEquals(orders.size(), 2);
	}
	@Test
	public void getReservationsOfRestaurantForWeek() throws ParseException{
		Date d = formatter.parse("02-26-2017");
		Date de = formatter.parse("03-04-2017");
		List<Order> orders = orderRepository.getReservationsOfRestaurantForWeek(restaurantRepository.findOne((long) 1), d, de);
		for(Order o : orders)
			System.out.println(o.getDate() + " date");
		assertEquals(orders.size(), 3);
	}
	
	@Test
	public void getGradeForOrder(){
		Double grade = orderRepository.getGradeForOrder((long) 1,(long) 1);
		assertEquals(grade, null);
	}
	
}
