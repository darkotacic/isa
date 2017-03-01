package com.isa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Grade;
import com.isa.entity.Order;
import com.isa.entity.OrderStatus;
import com.isa.entity.Reservation;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.users.Friend;
import com.isa.entity.users.FriendId;
import com.isa.entity.users.Guest;
import com.isa.entity.users.GuestStatus;
import com.isa.entity.users.User;
import com.isa.entity.users.UserRole;
import com.isa.repository.FriendRepository;
import com.isa.repository.GradeRepository;
import com.isa.repository.GuestRepository;
import com.isa.repository.ReservationRepository;
import com.isa.repository.RestaurantRepository;
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

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private HttpSession session;

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
	public List<Segment> getSegments(Date date,Reservation re,Long resId) {
		List<Segment> segments = (List<Segment>) segmentRepository.findAll();
		List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
		
		for(Reservation r : reservations){
			if(r.getRestaurant().getId() == resId){
				boolean flag = false;
				if((re.getStartTime() >= r.getStartTime() && re.getStartTime() <= r.getEndTime()) || (re.getEndTime() >= r.getStartTime() && re.getEndTime() <= r.getEndTime())){
					flag = true;
				}
				for(Order o : r.getOrders()){
					if(o.getOrderStatus().equals(OrderStatus.NOTPAID) && o.getDate().equals(date)){
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
		}
		
		return segments;
	}

	
	@Override
	public ResponseEntity<Grade> addGrade(Grade grade, Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=new Grade(grade.getGradeOfService(), grade.getGradeOfOrderItem(), grade.getGradeOfRestaurant());
			g.setOrder(order);
			g.setRestaurant(order.getTable().getSegment().getRestaurant());
			g.setGuest((Guest)user);
			gradeRepository.save(g);
		}
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Grade> editGrade(Grade grade, Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=gradeRepository.fingGradeByOrder(order, (Guest)user);
			g.setGradeOfOrderItem(grade.getGradeOfOrderItem());
			g.setGradeOfRestaurant(grade.getGradeOfRestaurant());
			g.setGradeOfService(grade.getGradeOfService());
			gradeRepository.save(g);
		}
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<Grade> deleteGrade(Long reservationId) {
		User user=(User) session.getAttribute("user");
		if(user==null || user.getUserRole()!=UserRole.GUEST)
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		Reservation reservation=reservationRepository.findOne(reservationId);
		for(Order order:reservation.getOrders()){
			Grade g=gradeRepository.fingGradeByOrder(order, (Guest)user);
			gradeRepository.delete(g);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public Reservation createReservation(Reservation reservation,Long restaurantId) {
		Restaurant r = restaurantRepository.findOne(restaurantId);
		reservation.setRestaurant(r);
		return reservationRepository.save(reservation);
	}


	@Override
	public Reservation getReservation(Long id) {
		return reservationRepository.findOne(id);
	}


	@Override
	public Guest inviteFriend(Long friendId, Long resId) {
		Guest g = guestRepository.findOne(friendId);
		Reservation r = reservationRepository.findOne(resId);
		//new SendEmail(g.getEmail(),"<a href=http://localhost:8080/guests/activate?email="+g.getEmail()+">OVDE</a>", "Reservation invitation", "To accept click here:").start();
		r.getPeople().add(g);
		reservationRepository.save(r);
		return g;
		
	}


	@Override
	public List<Reservation> getHistory(Long id) {
		List<Reservation> reservations = (List<Reservation>) reservationRepository.findAll();
		List<Reservation> history = new ArrayList<Reservation>();
		
		for(Reservation r : reservations){
			boolean guestPresent = false;
			boolean isHistory = true;
			for(Guest f : r.getPeople()){
				if(f.getId()==id){
					guestPresent = true;
					break;
				}
			}
			if(guestPresent){
				for(Order o : r.getOrders()){
					if(o.getOrderStatus().equals(OrderStatus.NOTPAID)){
						isHistory = false;
						break;
					}
				}
				if(isHistory){
					history.add(r);
				}
			}
		}
		return history;
	}


	@Override
	public List<Guest> getHistoryFriends(Long resId) {
		Reservation r = reservationRepository.findOne(resId);
		return reservationRepository.getHistoryFriends(r);
	}

}
