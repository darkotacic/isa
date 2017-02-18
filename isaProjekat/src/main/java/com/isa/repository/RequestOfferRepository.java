package com.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RequestOffer;
import com.isa.entity.users.RestaurantManager;

public interface RequestOfferRepository extends CrudRepository<RequestOffer, Long> {
	List<RequestOffer> findByStatus(boolean b);
	
	List<RequestOffer> findByRestaurantManager(RestaurantManager m);

}
