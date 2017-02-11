package com.isa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.entity.users.Bidder;

@Entity
@Table(name="BIDDER_OFFER")
public class BidderOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7047367470293680281L;
	
	@Id
	@Column(name = "BIDDER_OFFER_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "BO_PRICE", unique = false, nullable = false)
	private Double price;
	
	@Column(name = "BO_DOD", unique = false, nullable = false)
	private Date dateOfDelivery;
	
	@Column(name = "BO_GARANTY", unique = false, nullable = true)
	private String garanty;
	
	@ManyToOne
	private Bidder bidder;
	
	@ManyToOne
	private RequestOffer requestOffer;
	
	public BidderOffer() {
		
	}

	public Double getPrice() {
		return price;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public String getGaranty() {
		return garanty;
	}

	public Bidder getBidder() {
		return bidder;
	}

	public RequestOffer getRequestOffer() {
		return requestOffer;
	}
	
	
	
}
