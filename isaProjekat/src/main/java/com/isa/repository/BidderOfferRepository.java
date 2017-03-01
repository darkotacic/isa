package com.isa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.BidderOffer;
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;

public interface BidderOfferRepository extends CrudRepository<BidderOffer, Long> {
	
	List<BidderOffer> findByBidder(Bidder b);

	BidderOffer findByBidderAndRequestOffer(Bidder b, RequestOffer m);

	List<BidderOffer> findByRequestOffer(RequestOffer ro);
}
