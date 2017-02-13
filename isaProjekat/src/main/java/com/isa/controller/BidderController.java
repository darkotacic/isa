package com.isa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.BidderOffer;
import com.isa.entity.users.Bidder;
import com.isa.service.BidderService;

@RestController
@RequestMapping(value="/bidders")
public class BidderController {
	
	@Autowired
	private BidderService bidderService;
	
	@RequestMapping(value = "/updateBidder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bidder> registerRestaurant(@RequestBody @Valid Bidder b) {
		return bidderService.updateProfile(b);
	}
	
	@RequestMapping(value = "/getBiddings", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<BidderOffer> getBiddings(@RequestParam(value = "id") String bidder_email) {
		return bidderService.getAllBiddingsForThisBidder(bidder_email);
	}
	
	@RequestMapping(value = "/registerBid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BidderOffer> registerBid(@RequestBody @Valid BidderOffer bo, @RequestParam(value="request_offer_id") String ro_id, @RequestParam(value="bidder_email")String b_email ) {
		return bidderService.registerBidderOffer(bo, Long.parseLong(ro_id), b_email);
	}
	
	@RequestMapping(value = "/updateBid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BidderOffer> UpdateBid(@RequestBody @Valid BidderOffer bo) {
		return bidderService.updateBidderOffer(bo);
	}
}
