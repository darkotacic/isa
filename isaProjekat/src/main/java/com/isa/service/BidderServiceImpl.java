package com.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.BidderOffer;
import com.isa.entity.RequestOffer;
import com.isa.entity.users.Bidder;
import com.isa.repository.BidderOfferRepository;
import com.isa.repository.BidderRepository;
import com.isa.repository.RequestOfferRepository;

@Service
@Transactional
public class BidderServiceImpl implements BidderService {

	@Autowired
	private BidderRepository bidderRepository;

	@Autowired
	private BidderOfferRepository bidderOfferRepository;

	@Autowired
	private RequestOfferRepository requestOfferRepository;

	@Override
	public ResponseEntity<Bidder> updateProfile(Bidder b) {
		Bidder temp = this.bidderRepository.findOne(b.getId());
		temp.setDateOfBirth(b.getDateOfBirth());
		temp.setEmail(b.getEmail());
		temp.setUserName(b.getUserName());
		temp.setPassword(b.getPassword());
		temp.setSurname(b.getSurname());
		return new ResponseEntity<Bidder>(this.bidderRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BidderOffer>> getAllBiddingsForThisBidder(Long bidder_id) {
		Bidder b = this.bidderRepository.findOne(bidder_id);
		return new ResponseEntity<List<BidderOffer>>(this.bidderOfferRepository.findByBidder(b), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BidderOffer> registerBidderOffer(BidderOffer bo, Long ro_id, Long b_id) {
		Bidder temp = this.bidderRepository.findOne(b_id);
		RequestOffer temp1 = this.requestOfferRepository.findOne(ro_id);
		if(bo.getDateOfDelivery().before(temp1.getExpirationDate()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		bo.setBidder(temp);
		bo.setRequestOffer(temp1);
		return new ResponseEntity<BidderOffer>(this.bidderOfferRepository.save(bo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<BidderOffer> updateBidderOffer(BidderOffer bo) {
		BidderOffer temp = this.bidderOfferRepository.findOne(bo.getId());
		temp.setDateOfDelivery(bo.getDateOfDelivery());
		temp.setGaranty(bo.getGaranty());
		temp.setPrice(bo.getPrice());
		return new ResponseEntity<BidderOffer>(this.bidderOfferRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BidderOffer> deleteBidderOffer(Long bidder_id) {
		this.bidderOfferRepository.delete(bidder_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RequestOffer>> getActiveRequestOffers() {
		return new ResponseEntity<List<RequestOffer>>(this.requestOfferRepository.findByStatus(true), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BidderOffer> getBidderOfferByBidderAndRequestOffer(Long b_id, Long ro_id) {
		return new ResponseEntity<BidderOffer>(this.bidderOfferRepository.findByBidderAndRequestOffer(this.bidderRepository.findOne(b_id),  this.requestOfferRepository.findOne(ro_id)), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestOffer> getRequestOffer(Long id) {
		return new ResponseEntity<RequestOffer>(this.requestOfferRepository.findOne(id), HttpStatus.OK);
	}

}
