package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.BidderOffer;
import com.isa.entity.users.Bidder;

public interface BidderService {
	
	ResponseEntity<Bidder> updateProfile(Bidder b);

	Iterable<BidderOffer> getAllBiddingsForThisBidder(String bidder_id);

	ResponseEntity<BidderOffer> registerBidderOffer(BidderOffer bo, Long ro_id, String b_id);

	ResponseEntity<BidderOffer> updateBidderOffer(BidderOffer bo);
	
	
}
