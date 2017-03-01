package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.isa.entity.Grade;
import com.isa.entity.Reservation;
import com.isa.entity.Segment;
import com.isa.entity.users.Guest;

public interface GuestService {
	
	ResponseEntity<List<Guest>> getFriendsForGuest(Long id);
	
	Guest register(Guest guest);
	
	Guest activate(String email);
	
	List<Reservation> getHistory(Long id);
	
	List<Segment> getSegments(Date date,Reservation r, Long resId);
	
	Reservation getReservation(Long id);
	
	Reservation createReservation(Reservation reservation,Long restaurantId);
	
	Guest inviteFriend(Long friendId,Long resId);
	
	
	
	ResponseEntity<List<Guest>> getNonFriendsForGuest(Long id);
	
	ResponseEntity<List<Guest>> getSentRequestsForGuest(Long id);
	
	ResponseEntity<List<Guest>>  getRecievedRequestsForGuest(Long id);
	
	ResponseEntity<Guest> sendRequest(Long user_id,Long reciever_id);
	
	ResponseEntity<Guest> acceptRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Guest> declineRequest(Long user_id,Long sender_id);
	
	ResponseEntity<Guest> removeFriend(Long user_id,Long friend_id);
	
	ResponseEntity<Grade> addGrade(Grade grade,Long reservationId);
	
	ResponseEntity<Grade> editGrade(Grade grade,Long reservationId);
	
	ResponseEntity<Grade> deleteGrade(Long reservationId);
}
