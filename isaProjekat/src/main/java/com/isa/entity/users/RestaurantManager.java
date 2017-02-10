package com.isa.entity.users;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.entity.RequestOffer;
import com.isa.entity.Restaurant;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name="RESTAURANT_MANAGER")
public class RestaurantManager extends User implements Serializable{

	private static final long serialVersionUID = 9073845010368338002L;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurantManager")
	@JsonIgnore
	private Set<RequestOffer> requestOffers;
	
	public RestaurantManager()  {
		
	}
	
	public Restaurant getRestaurnat() {
		return restaurant;
	}

	public void setRestaurnat(Restaurant restaurnat) {
		this.restaurant = restaurnat;
	}
}
