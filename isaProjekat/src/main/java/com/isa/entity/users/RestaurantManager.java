package com.isa.entity.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.RequestOffer;
import com.isa.entity.Restaurant;

@Entity
@Table(name = "RESTAURANT_MANAGER")
public class RestaurantManager extends User implements Serializable {

	private static final long serialVersionUID = 9073845010368338002L;

	@ManyToOne
	private Restaurant restaurant;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RequestOffer> requestOffers = new HashSet<RequestOffer>();

	public RestaurantManager() {

	}

	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	@JsonIgnore
	public Set<RequestOffer> getRequestOffers() {
		return requestOffers;
	}
	@JsonProperty
	public void setRequestOffers(Set<RequestOffer> requestOffers) {
		this.requestOffers = requestOffers;
	}
}
