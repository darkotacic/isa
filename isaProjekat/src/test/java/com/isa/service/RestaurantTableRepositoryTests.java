package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.RestaurantTable;
import com.isa.repository.RestaurantRepository;
import com.isa.repository.RestaurantTableRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestaurantTableRepositoryTests {

	@Autowired
	RestaurantRepository rr;
	
	@Autowired
	RestaurantTableRepository rtr;
	
	@Test
	public void seeIfCanDeleteSegment() {
		List<RestaurantTable> notFree = rtr.seeIfCanDeleteSegment((long) 1);
		assertEquals(notFree.size(), 0);
	}
}
