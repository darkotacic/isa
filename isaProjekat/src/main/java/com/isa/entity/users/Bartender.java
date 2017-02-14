package com.isa.entity.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.OrderItem;

@Entity
@Table(name = "BARTENDER")
public class Bartender extends Worker {

	private static final long serialVersionUID = -7382607325679638934L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bartender", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<OrderItem> orderedDrinks;

	public Bartender() {
	}
	
	@JsonIgnore
	public Set<OrderItem> getOrderedDrinks() {
		return orderedDrinks;
	}
	@JsonProperty
	public void setOrderedDrinks(Set<OrderItem> orderedDrinks) {
		this.orderedDrinks = orderedDrinks;
	}
	
}
