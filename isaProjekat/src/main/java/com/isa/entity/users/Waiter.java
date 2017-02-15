package com.isa.entity.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.Order;

@Entity
@Table(name = "WAITER")
public class Waiter extends Worker {

	private static final long serialVersionUID = -9126299540081793972L;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "waiter", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Order> orders = new HashSet<Order>();

	public Waiter() {
	
	}
	
	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}
	@JsonProperty
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
