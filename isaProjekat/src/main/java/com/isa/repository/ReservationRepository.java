package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	

}
