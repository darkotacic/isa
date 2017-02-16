package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RequestOffer;
import com.isa.entity.users.RestaurantManager;

public interface RequestOfferRepository extends CrudRepository<RequestOffer, Long> {
	Iterable<RequestOffer> findByStatus(boolean b);
	
	Iterable<RequestOffer> findByRestaurantManager(RestaurantManager m);
}
