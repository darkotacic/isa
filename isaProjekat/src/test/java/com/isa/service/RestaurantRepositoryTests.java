package com.isa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Restaurant;
import com.isa.repository.RestaurantManagerRepository;
import com.isa.repository.RestaurantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantRepositoryTests {

	@Autowired
	RestaurantRepository rr;
	
	@Autowired
	RestaurantManagerRepository rmr;
	
	@Test
	public void getGradeForRestaurant() {
		Double grade = rr.getGradeForRestaurant((long) 1);
		assertEquals(grade, 5, 0.001);
	}
	
	@Test
	public void getByManager() {
		Restaurant t = rr.getByManager(rmr.findOne((long) 9));
		assertEquals(t.getId(),rr.findOne((long) 1).getId()); 
		
	}
}
