package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.BidderOffer;
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;
import com.isa.entity.users.RestaurantManager;

public interface BidderOfferRepository extends CrudRepository<BidderOffer, Long> {
	
	Iterable<BidderOffer> findByBidder(Bidder b);

	BidderOffer findByBidderAndRequestOffer(Bidder b, RequestOffer m);

	@Query("select bf from RequestOffer rf inner join rf.bidderOffers as bf where rf.restaurantManager = ?1")
	Iterable<BidderOffer> getBidderOffersForManager(RestaurantManager m);
	
	Iterable<BidderOffer> findByRequestOffer(RequestOffer ro);
}
