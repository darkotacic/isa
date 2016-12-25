package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SEGMENT")
public class Segment implements Serializable{
	
	private static final long serialVersionUID = 5329505235746769431L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="segment")
	private Set<RestaurantTable> tables;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@Column(name="SGM_REST")
	private String restrictions;
	
	@Column(name="SGM_POS")
	private String position;
	
	public Long getId() {
		return id;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getRestrictions() {
		return restrictions;
	}
	
	public Set<RestaurantTable> getTables() {
		return tables;
	}
	
	public Restaurant getRestaurant() {
		return restaurant;
	}	
	
}
