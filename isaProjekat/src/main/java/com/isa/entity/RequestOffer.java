package com.isa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.users.RestaurantManager;

@Entity
@Table(name = "REQUEST_OFFER")
public class RequestOffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -715867275692660342L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "OFFERED_PRODUCTS", joinColumns = @JoinColumn(name = "RO_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID"))
	@JsonIgnore
	private Set<Product> products;

	@Column(name = "STATUS", columnDefinition = "boolean default true", insertable = true)
	private boolean status;

	@Future
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", unique = false, nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Future
	@Column(name = "EXPIRATION_DATE", unique = false, nullable = false)
	private Date expirationDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "requestOffer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<BidderOffer> bidderOffers;

	@ManyToOne
	private RestaurantManager restaurantManager;

	public RequestOffer() {

	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonIgnore
	public Set<BidderOffer> getBidderOffers() {
		return bidderOffers;
	}
	@JsonProperty
	public void setBidderOffers(Set<BidderOffer> bidderOffers) {
		this.bidderOffers = bidderOffers;
	}

	public Long getId() {
		return id;
	}
	@JsonProperty
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setRestaurantManager(RestaurantManager restaurantManager) {
		this.restaurantManager = restaurantManager;
	}
	@JsonIgnore
	public Set<Product> getProducts() {
		return products;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public RestaurantManager getRestaurantManager() {
		return restaurantManager;
	}

}
