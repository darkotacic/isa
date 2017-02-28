package com.isa.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Order;
import com.isa.entity.OrderStatus;
import com.isa.entity.Reservation;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.users.Friend;
import com.isa.entity.users.FriendId;
import com.isa.entity.users.Guest;
import com.isa.entity.users.GuestStatus;
import com.isa.entity.users.User;
import com.isa.repository.FriendRepository;
import com.isa.repository.GuestRepository;
import com.isa.repository.ReservationRepository;
import com.isa.repository.SegmentRepository;
import com.isa.repository.UserRepository;


@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	
	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;


	@Override
	public ResponseEntity<List<Guest>> getFriendsForGuest(Long id) {
		if(id != null){
			
			Guest guest = guestRepository.findOne(id);
			List<Guest>friends=this.guestRepository.getFriendsForSender(guest);
			List<Guest>friends1=this.guestRepository.getFriendsForReciever(guest);
			
			for(Guest g : friends1){
				friends.add(g);
			}
			
			return new ResponseEntity<List<Guest>>(friends,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	@Override
	public ResponseEntity<List<Guest>> getSentRequestsForGuest(Long id) {

		if(id!= null){
			Guest guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Guest>>(this.guestRepository.getSentRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Guest>> getRecievedRequestsForGuest(Long id) {
		
		if(id!= null){
			Guest guest = guestRepository.findOne(id);
			return new ResponseEntity<List<Guest>>(this.guestRepository.getRecievedRequestsForGuest(guest),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@Override
	public ResponseEntity<Guest> sendRequest(Long user_id, Long reciever_id) {
		
		if(user_id != null && reciever_id != null && user_id != reciever_id){
		
			Guest sender = guestRepository.findOne(user_id);
			Guest reciever = guestRepository.findOne(reciever_id);
			
			
			Friend f = new Friend();
			f.setReciever(reciever);
			f.setSender(sender);
			f.setStatus(false);
			
			sender.getSent().add(f);
			this.guestRepository.save(sender);
			return new ResponseEntity<Guest>(reciever,HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

	@Override
	public ResponseEntity<Guest> acceptRequest(Long user_id, Long sender_id) {
		if(sender_id != null && user_id != null && sender_id != user_id){
			Guest user = guestRepository.findOne(user_id);
			Guest sender = guestRepository.findOne(sender_id);
	
			Friend f = friendRepository.findOne(new FriendId(sender,user));
			f.setStatus(true);
			
			friendRepository.save(f);
			
			return new ResponseEntity<Guest>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<Guest> declineRequest(Long user_id, Long sender_id) {
		
		if(user_id != null && sender_id != null && user_id != sender_id){
			
			Guest user = guestRepository.findOne(user_id);
			Guest sender = guestRepository.findOne(sender_id);
			Friend f = friendRepository.findOne(new FriendId(sender,user));
			friendRepository.delete(f);
			return new ResponseEntity<Guest>(sender,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public ResponseEntity<Guest> removeFriend(Long user_id, Long friend_id) {
		
		if(user_id != null && friend_id != null && user_id != friend_id){
			
			Guest user = guestRepository.findOne(user_id);
			Guest friend = guestRepository.findOne(friend_id);
			
			Friend f = friendRepository.findOne(new FriendId(user,friend));
			
			if(f != null){
				if(f.isStatus()){
					friendRepository.delete(f);
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				Friend f1 = friendRepository.findOne(new FriendId(friend,user));
				if(f1 != null){
					if(f1.isStatus()){
						friendRepository.delete(f1);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			return new ResponseEntity<Guest>(friend,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	@Override
	public Guest register(Guest guest) {
		
		return guestRepository.save(guest);
		
	}


	@Override
	public ResponseEntity<List<Guest>> getNonFriendsForGuest(Long id) {
		Guest guest = guestRepository.findOne(id);
		
		List<Guest>friends=this.guestRepository.getLinksForSender(guest);
		List<Guest>friends1=this.guestRepository.getLinksForReciever(guest);
		
		for(Guest g : friends1){
			friends.add(g);
		}
		List<Guest> allGuests = guestRepository.getAllGuests(guest);
		
		for(Guest g : friends){
			for(Guest f : allGuests){
				if(g.getId() == f.getId()){
					allGuests.remove(f);
					break;
				}
			}
		}
		return new ResponseEntity<List<Guest>>(allGuests,HttpStatus.OK);
	}


	@Override
	public Guest activate(String email) {
		User user = userRepository.findByEmail(email);
		Guest old = guestRepository.findOne(user.getId());
		old.setStatus(GuestStatus.ACTIVE);
		Guest g = guestRepository.save(old);
		return g;
	}


	@Override
	public List<Segment> getSegments() {
		List<Segment> segments = (List<Segment>) segmentRepository.findAll();
		List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
		
		for(Reservation r : reservations){
			boolean flag = false;
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(new Date());
			double currentTime=calendar.get(Calendar.HOUR_OF_DAY)+(calendar.get(Calendar.MINUTE)/100.0);
			if(currentTime > r.getStartTime() && r.getEndTime() < currentTime){
				flag = true;
			}
			for(Order o : r.getOrders()){
				if(o.getOrderStatus().equals(OrderStatus.NOTPAID)){
					if(flag){
						for(Segment s : segments){
							for(RestaurantTable rt : s.getTables()){
								if(rt.getId() == o.getTable().getId()){
									rt.setFree(false);
									break;
								}
							}
						}
					}
				}
			}
		}
		
		return segments;
	}
	

}
