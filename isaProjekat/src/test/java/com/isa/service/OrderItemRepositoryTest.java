package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.OrderItemStatus;
import com.isa.entity.ProductType;
import com.isa.entity.Restaurant;
import com.isa.entity.users.Cook;
import com.isa.repository.OrderItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderItemRepositoryTest {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	private Restaurant restaurant;
	private Order order;
	private Cook cook;
	
	@Before
	public void setUp() throws Exception{
		restaurant=new Restaurant(1,"Dva stapica","Kineski restoran");
		order=new Order();
		order.setId(new Long(1));
		cook=new Cook();
		cook.setId(6);
	}
	
	@Test
	public void findByOrder(){
		List<OrderItem> items=orderItemRepository.findByOrder(order);
		assertEquals(items.size(),2);
	}
	
	@Test
	public void findFoodOrderItems(){
		List<OrderItem> foods=orderItemRepository.findFoodOrderItems(ProductType.FRIED, restaurant);
		boolean equal=true;
		for(OrderItem item:foods){
			if(item.getProduct().getProductType()!=ProductType.FRIED){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void findDrinkOrderItems(){
		List<OrderItem> drinks=orderItemRepository.findFoodOrderItems(ProductType.DRINK, restaurant);
		boolean equal=true;
		for(OrderItem item:drinks){
			if(item.getProduct().getProductType()!=ProductType.DRINK){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void getTasksForCook(){
		List<OrderItem> tasks=orderItemRepository.getTasksForCook(cook);
		boolean equal=true;
		for(OrderItem item:tasks){
			if(item.getCook().getId()!=cook.getId() || item.getOrderItemStatus()!=OrderItemStatus.ONPREPARATION){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
}
