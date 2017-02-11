package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.RequestOffer;

public interface RequestOfferRepository extends CrudRepository<RequestOffer, Long> {

	@SuppressWarnings("unchecked")
	RequestOffer save(RequestOffer r);
	
	RequestOffer findOne(Long id);
}
