package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Reservation;
import com.isa.entity.users.Guest;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	@Query("select friends from Reservation r join r.people friends where r=?1")
	List<Guest> getHistoryFriends(Reservation r);
}
