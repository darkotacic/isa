package com.isa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.isa.entity.users.Friend;
import com.isa.entity.users.Guest;

public interface GuestService {
	
	ResponseEntity<List<Guest>> getFriendsForGuest(Long id);
	
	ResponseEntity<Friend> sendRequest(Long sender_id,Long reciever_id);
}
