package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.users.Guest;
import com.isa.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestRepositoryTest {
	
	@Autowired
	private GuestRepository guestRepository;
	private Long guestId;
	
	@Before
	public void setUp(){
		guestId = (long) 1;
	}
	
	@Test
	public void getSentRequests(){
		Guest guest = guestRepository.findOne(guestId);
		List<Guest> sentRequests =guestRepository.getSentRequestsForGuest(guest);
		assertEquals(4, sentRequests.size());
	}
	
	@Test
	public void getRecievedRequests(){
		Guest guest = guestRepository.findOne(guestId);
		List<Guest> recievedRequests =guestRepository.getRecievedRequestsForGuest(guest);
		assertEquals(1, recievedRequests.size());
	}
	
	
	@Test
	public void getAllGuests(){
		Guest guest = guestRepository.findOne(guestId);
		List<Guest> allGuests = guestRepository.getAllGuests(guest);
		assertEquals(5, allGuests.size());
	}

}
