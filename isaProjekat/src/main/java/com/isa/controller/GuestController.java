package com.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.users.Friend;
import com.isa.entity.users.Guest;
import com.isa.service.GuestService;

@RestController
@RequestMapping(value="/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@RequestMapping(
			value = "/getFriends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getFriends(@PathVariable("id") Long user_id){
		return this.guestService.getFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Friend> sendRequest(@RequestParam(value = "sender_id") Long sender_id,@RequestParam(value = "reciever_id") Long reciever_id){
		return this.guestService.sendRequest(sender_id,reciever_id);
	}
	
}
