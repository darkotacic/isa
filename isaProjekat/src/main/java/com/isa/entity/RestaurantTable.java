package com.isa.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "RESTAURANT_TABLE")
public class RestaurantTable {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Segment segment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "table", orphanRemoval = true)
	@JsonIgnore
	private Set<Order> orders;

	@Min(1)
	@Max(20)
	@NotNull
	@Column(name = "RES_CHAIR")
	private int numberOfChairs;

	@NotNull
	@Column(name = "RES_FREE", columnDefinition = "boolean default true", insertable = true)
	private boolean free;

	public RestaurantTable() {

	}
	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}
	@JsonProperty
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public int getNumberOfChairs() {
		return numberOfChairs;
	}

	public void setNumberOfChairs(int numberOfChairs) {
		this.numberOfChairs = numberOfChairs;
	}

	public Long getId() {
		return id;
	}

	public Segment getSegment() {
		return segment;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

}
