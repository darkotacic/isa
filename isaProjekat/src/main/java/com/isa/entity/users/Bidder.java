package com.isa.entity.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.BidderOffer;

@Entity
@Table(name = "BIDDER")
public class Bidder extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7346796776981937378L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bidder", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<BidderOffer> bidderOffers = new HashSet<BidderOffer>();

	@Column(name = "FIRST_LOGIN", columnDefinition = "boolean default true", insertable = true)
	private boolean firstLogIn;

	public Bidder() {
	}
	@JsonIgnore
	public Set<BidderOffer> getBidderOffers() {
		return bidderOffers;
	}
	@JsonProperty
	public void setBidderOffers(Set<BidderOffer> bidderOffers) {
		this.bidderOffers = bidderOffers;
	}

	public void setFirstLogIn(boolean firstLogIn) {
		this.firstLogIn = firstLogIn;
	}

	public boolean isFirstLogIn() {
		return firstLogIn;
	}

}
