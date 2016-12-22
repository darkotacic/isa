package com.isa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="restaurantTable")
public class RestaurantTable {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Segment segment;
	
	@Column(name="numberOfChairs")
	private int numberOfChairs;
	
	public int getNumberOfChairs() {
		return numberOfChairs;
	}
	public void setNumberOfChairs(int numberOfChairs) {
		this.numberOfChairs = numberOfChairs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Segment getSegment() {
		return segment;
	}
}
