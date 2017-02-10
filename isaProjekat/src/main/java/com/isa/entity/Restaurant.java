package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.entity.users.RestaurantManager;

@Entity
@Table(name="RESTAURANT")
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = -2730772573761285789L;
	
	@Id
	@Column(name="RES_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="RES_NAME",unique=false,nullable=false)
	private String name;
	
	@Column(name="RES_DESC",unique=false,nullable=false)
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	@JsonIgnore
	private Set<Product> menu;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	@JsonIgnore
	private Set<Segment> segments;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	@JsonIgnore
	private Set<Grade> grades; 
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	@JsonIgnore
	private Set<RestaurantManager> restaurantManagers; 
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Grade> getGrades() {
		return grades;
	}
	
	public Set<RestaurantManager> getRestraurantManagers() {
		return restaurantManagers;
	}
	
	public Set<Product> getMenu() {
		return menu;
	}
	public Set<Segment> getSegments() {
		return segments;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}

}
