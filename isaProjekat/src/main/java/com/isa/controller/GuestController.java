package com.isa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.Grade;
import com.isa.entity.Order;
import com.isa.entity.users.Guest;
import com.isa.entity.users.User;
import com.isa.mail.SendEmail;
import com.isa.service.GradeService;
import com.isa.service.GuestService;
import com.isa.service.WorkerService;

@RestController
@RequestMapping(value="/guests")
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private HttpSession session;
	
	
	@RequestMapping(
			value = "/friends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getFriends(@PathVariable("id") Long user_id){
		return this.guestService.getFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/nonFriends/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getNonFriends(@PathVariable("id") Long user_id){
		return this.guestService.getNonFriendsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sentRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getSentRequests(@PathVariable("id") Long user_id){
		return this.guestService.getSentRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/recievedRequests/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<List<Guest>> getRecievedRequests(@PathVariable("id") Long user_id){
		return this.guestService.getRecievedRequestsForGuest(user_id);
	}
	
	@RequestMapping(
			value = "/sendRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> sendRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "reciever_id") Long reciever_id){
		return this.guestService.sendRequest(user_id,reciever_id);
	}
	
	@RequestMapping(
			value = "/acceptRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> acceptRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.acceptRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/declineRequest",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> declineRequest(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "sender_id") Long sender_id){
		return this.guestService.declineRequest(user_id,sender_id);
	}
	
	@RequestMapping(
			value = "/removeFriend",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> removeFriend(@RequestParam(value = "user_id") Long user_id,@RequestParam(value = "friend_id") Long friend_id){
		return this.guestService.removeFriend(user_id,friend_id);
	}
	
	@RequestMapping(
			value="/addGrade/{orderId}",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> addGrade(@RequestBody Grade grade,@PathVariable("orderId")Long orderId){
		User user=(User) session.getAttribute("user");
		if(user==null || !user.getUserRole().toString().equals("GUEST"))
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		grade.setGuest((Guest) user);
		Order order=workerService.getOrder(orderId);
		grade.setOrder(order);
		grade.setRestaurant(order.getWaiter().getRestaurant());
		gradeService.addGrade(grade);
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}
	
	@RequestMapping(
			value="/deleteGrade",
			method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Grade> deleteGrade(@RequestBody Grade grade){
		gradeService.deleteGrade(grade);
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value="/register",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> register(@RequestBody Guest guest) throws Exception{
		Guest g = guestService.register(guest);
		@SuppressWarnings("unused")
		SendEmail se = new SendEmail(g.getEmail(),"<a href=http://localhost:8080/guests/activate/"+g.getEmail()+">OVDE</a>");
		return new ResponseEntity<Guest>(g, HttpStatus.CREATED);	
	}
	@RequestMapping(
			value="/activate/{email}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Guest> activate(@PathVariable("email") String email) throws Exception{
		Guest g = guestService.activate(email);
		return new ResponseEntity<Guest>(g, HttpStatus.OK);	
	}
	
}
