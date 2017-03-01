package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.repository.RestaurantRepository;
import com.isa.repository.WaiterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaiterRepositoryTests {

	@Autowired
	private WaiterRepository waiterRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Test
	public void getEarningsForRestaurant(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(2017, 01, 28);
		Date startDate=calendar.getTime();
		calendar.set(2017, 02, 03);
		Date endDate=calendar.getTime();
		double er = waiterRepository.getEarningsForRestaurant(restaurantRepository.findOne((long) 1), startDate, endDate);
		assertEquals(er,990.6, 0.001);
	}
	
	@Test
	public void getEarningsForWaiter(){
		double er = waiterRepository.getEarningsForWaiter((long) 3);
		assertEquals(er, 990.6, 0.001);
	}
}
