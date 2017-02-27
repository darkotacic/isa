package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Guest;

public interface GuestRepository extends CrudRepository<Guest,Long>{
	
	@Query("select s.pk.reciever from Guest g join g.sent s where (s.status = 1 and s.pk.sender=?1) ")
	List<Guest> getFriendsForSender(Guest guest);
	
	Guest findByEmail(String email);
	
	@Query("select r.pk.sender from Guest g join g.recieved r where (r.status = 1 and r.pk.reciever=?1)")
	List<Guest> getFriendsForReciever(Guest guest);
	
	@Query("select s.pk.reciever from Guest g join g.sent s where (s.status = 0 and s.pk.sender=?1) ")
	List<Guest> getSentRequestsForGuest(Guest guest);
	
	@Query("select r.pk.sender from Guest g join g.recieved r where (r.status = 0 and r.pk.reciever=?1) ")
	List<Guest> getRecievedRequestsForGuest(Guest guest);
	
	@Query("select s.pk.reciever from Guest g join g.sent s where (s.pk.sender=?1) ")
	List<Guest> getLinksForSender(Guest guest);
	
	@Query("select r.pk.sender from Guest g join g.recieved r where (r.pk.reciever=?1)")
	List<Guest> getLinksForReciever(Guest guest);
	
	@Query("from Guest g where g != ?1")
	List<Guest> getAllGuests(Guest guest);
	
}