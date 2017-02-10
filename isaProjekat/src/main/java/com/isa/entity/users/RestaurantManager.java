package com.isa.entity.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.entity.Restaurant;

@Entity
@Table(name="RESTAURANT_MANAGER")
public class RestaurantManager extends User implements Serializable{

	private static final long serialVersionUID = 9073845010368338002L;
	
	@ManyToOne
	private Restaurant restaurant;
	
	public RestaurantManager()  {
		
	}
	
	public Restaurant getRestaurnat() {
		return restaurant;
	}

	public void setRestaurnat(Restaurant restaurnat) {
		this.restaurant = restaurnat;
	}
}
