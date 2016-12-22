package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="restaurantManager")
public class RestaurantManager extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9073845010368338002L;
	
	@OneToOne
	private Restaurant restaurnat;
	
	public RestaurantManager()  {
		
	}
	
	public Restaurant getRestaurnat() {
		return restaurnat;
	}

	public void setRestaurnat(Restaurant restaurnat) {
		this.restaurnat = restaurnat;
	}
}
