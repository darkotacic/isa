package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.users.RestaurantManager;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -2730772573761285789L;

	@Id
	@Column(name = "RES_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z]\\w*")
	@NotNull
	@Column(name = "RES_NAME", unique = false, nullable = false)
	private String name;

	@Size(max = 50)
	@Pattern(regexp = "^[A-Z]\\w*")
	@NotNull
	@Column(name = "RES_DESC", unique = false, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "restaurants", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JsonIgnore
	private Set<Product> menu;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Segment> segments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Grade> grades;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RestaurantManager> restaurantManagers;

	public Restaurant() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	public Set<Grade> getGrades() {
		return grades;
	}
	@JsonIgnore
	public Set<Product> getMenu() {
		return menu;
	}
	@JsonIgnore
	public Set<Segment> getSegments() {
		return segments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@JsonIgnore
	public Set<RestaurantManager> getRestaurantManagers() {
		return restaurantManagers;
	}
	@JsonProperty
	public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
		this.restaurantManagers = restaurantManagers;
	}
	@JsonProperty
	public void setMenu(Set<Product> menu) {
		this.menu = menu;
	}
	@JsonProperty
	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}
	@JsonProperty
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
