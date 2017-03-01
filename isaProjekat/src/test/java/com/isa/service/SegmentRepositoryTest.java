package com.isa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Restaurant;
import com.isa.entity.Segment;
import com.isa.repository.SegmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SegmentRepositoryTest {
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	private Restaurant restaurant;
	
	@Before
	public void setUp(){
		restaurant=new Restaurant(1,"Dva stapica","Kineski restoran");
	}
	
	@Test
	public void getSegmentsForRestautant(){
		Iterable<Segment> segments=segmentRepository.getSegmentsForRestautant(restaurant);
		boolean equal=true;
		for(Segment segment:segments){
			if(segment.getRestaurant().getId().longValue()!=restaurant.getId().longValue()){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
}
