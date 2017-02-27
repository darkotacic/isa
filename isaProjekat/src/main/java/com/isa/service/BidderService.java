package com.isa.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.isa.entity.BidderOffer;
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;

public interface BidderService {
	
	ResponseEntity<Bidder> updateProfile(Bidder b);

	ResponseEntity<List<BidderOffer>> getAllBiddingsForThisBidder(Long bidder_id);

	ResponseEntity<BidderOffer> registerBidderOffer(BidderOffer bo, Long ro_id, Long b_id);

	ResponseEntity<BidderOffer> updateBidderOffer(BidderOffer bo);
	
	ResponseEntity<BidderOffer> getBidderOfferByBidderAndRequestOffer(Long b_id, Long ro_id);

	ResponseEntity<BidderOffer> deleteBidderOffer(Long bidder_id);

	ResponseEntity<List<RequestOffer>> getActiveRequestOffers();

	ResponseEntity<RequestOffer> getRequestOffer(Long id);

	ResponseEntity<BidderOffer> getBidderOffer(Long id);
	
	
}
