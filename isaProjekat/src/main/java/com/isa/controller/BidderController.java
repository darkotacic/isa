package com.isa.controller;

import java.util.List;

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
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;
import com.isa.service.BidderService;

@RestController
@RequestMapping(value="/bidders")
public class BidderController {
	
	@Autowired
	private BidderService bidderService;
	
	@RequestMapping(value = "/updateBidder", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bidder> registerRestaurant(@RequestBody @Valid Bidder b) {
		return bidderService.updateProfile(b);
	}
	
	@RequestMapping(value = "/getBiddingsForBidder", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BidderOffer>> getBiddings(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.getAllBiddingsForThisBidder(bidder_id);
	}
	
	@RequestMapping(value = "/getActiveBiddingsForBidder", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BidderOffer>> getActiveBiddings(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.getAllBiddingsForThisBidder(bidder_id);
	}
	
	@RequestMapping(value = "/registerBid", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BidderOffer> registerBid(@RequestBody @Valid BidderOffer bo, @RequestParam(value="request_offer_id") Long ro_id, @RequestParam(value="bidder_id") Long b_id) {
		return bidderService.registerBidderOffer(bo, ro_id, b_id);
	}
	
	@RequestMapping(value = "/updateBid", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BidderOffer> UpdateBid(@RequestBody @Valid BidderOffer bo) {
		return bidderService.updateBidderOffer(bo);
	}
	
	@RequestMapping(value = "/deleleBid", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String DeleteBid(@RequestParam(value = "id") Long bidder_id) {
		return bidderService.deleteBidderOffer(bidder_id);
	}
	
	@RequestMapping(value = "/getActiveRequestOffers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RequestOffer>> getActiveRequestOffers() {
		return bidderService.getActiveRequestOffers();
	}
}
