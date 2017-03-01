package com.isa.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Grade;
import com.isa.entity.Order;
import com.isa.entity.users.Guest;
import com.isa.repository.GradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeRepositoryTest {
	
	@Autowired
	private GradeRepository gradeRepository;
	private Order order;
	private Guest guest;
	
	@Before
	public void setUp(){
		order=new Order();
		order.setId(new Long(1));
		guest=new Guest();
		guest.setId(1);
	}
	
	@Test
	public void fingGradeByOrder(){
		Grade grade=gradeRepository.fingGradeByOrder(order, guest);
		boolean equal1=(grade.getGuest().getId()==guest.getId());
		boolean equal2=(grade.getOrder().getId().longValue()==order.getId().longValue());
		assertEquals((equal1 && equal2), true);
	}

}
