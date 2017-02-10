package com.isa.entity.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.entity.BidderOffer;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "BIDDER")
public class Bidder extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7346796776981937378L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bidder")
	@JsonIgnore
	private Set<BidderOffer> bidderOffers;

	@Column(name = "FIRST_LOGIN")
	private boolean firstLogIn;

	public Bidder() {
	}
}
