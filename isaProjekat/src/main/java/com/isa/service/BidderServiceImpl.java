package com.isa.service;

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
		if (b.getEmail() == null || b.getName() == null || b.getSurname() == null || b.getPassword() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfBirth(b.getDateOfBirth());
		temp.setEmail(b.getEmail());
		temp.setName(b.getName());
		temp.setPassword(b.getPassword());
		temp.setSurname(b.getSurname());
		return new ResponseEntity<Bidder>(this.bidderRepository.save(temp), HttpStatus.OK);
	}

	@Override
	public Iterable<BidderOffer> getAllBiddingsForThisBidder(Long bidder_id) {
		Bidder b = this.bidderRepository.findOne(bidder_id);
		return bidderOfferRepository.allForBidder(b);
	}

	@Override
	public ResponseEntity<BidderOffer> registerBidderOffer(BidderOffer bo, Long ro_id, Long b_id) {
		Bidder temp = this.bidderRepository.findOne(b_id);
		RequestOffer temp1 = this.requestOfferRepository.findOne(ro_id);
		bo.setBidder(temp);
		bo.setRequestOffer(temp1);
		if (bo.getDateOfDelivery() == null || bo.getPrice() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		return new ResponseEntity<BidderOffer>(this.bidderOfferRepository.save(bo), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<BidderOffer> updateBidderOffer(BidderOffer bo) {
		BidderOffer temp = this.bidderOfferRepository.findOne(bo.getId());
		if (bo.getDateOfDelivery() == null || bo.getPrice() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		temp.setDateOfDelivery(bo.getDateOfDelivery());
		temp.setGaranty(bo.getGaranty());
		temp.setPrice(bo.getPrice());
		return new ResponseEntity<BidderOffer>(this.bidderOfferRepository.save(temp), HttpStatus.OK);
	}

}
