package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.BidderOffer;
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;

public interface BidderOfferRepository extends CrudRepository<BidderOffer, Long> {
	
	Iterable<BidderOffer> findByBidder(Bidder b);

	BidderOffer findByBidderAndRequestOffer(Bidder b, RequestOffer m);
}
