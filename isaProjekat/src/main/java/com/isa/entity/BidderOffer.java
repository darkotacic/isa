package com.isa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name = "BO_PRICE", unique = false, nullable = false)
	private Double price;
	
	@Column(name = "BO_DOD", unique = false, nullable = false)
	private Date dateOfDelivery;
	
	@Column(name = "BO_GARANTY", unique = false, nullable = true)
	private String garanty;
	
	@Column(columnDefinition = "varchar(10) default 'UN_DECIDED'", insertable = true)
	@Enumerated(EnumType.STRING)
	private BidderOfferStatus offerStatus = BidderOfferStatus.UN_DECIDED;
	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public BidderOfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(BidderOfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public void setGaranty(String garanty) {
		this.garanty = garanty;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public void setRequestOffer(RequestOffer requestOffer) {
		this.requestOffer = requestOffer;
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
