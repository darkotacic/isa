package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Bidder;

public interface BidderRepository extends CrudRepository<Bidder, Long> {

	@SuppressWarnings("unchecked")
	Bidder save(Bidder b);
	
	Bidder findByEmail(String email);
	
	Bidder findOne(Long id);
	
}
