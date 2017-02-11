package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.BidderOffer;
import com.isa.entity.users.Bidder;

public interface BidderOfferRepository extends CrudRepository<BidderOffer, Long> {

	@Query("SELECT fn FROM BidderOffer fn WHERE fn.bidder = ?1")
	Iterable<BidderOffer> allForBidder(Bidder b);

}
