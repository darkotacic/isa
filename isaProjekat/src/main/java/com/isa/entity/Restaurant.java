package com.isa.entity;

import java.io.Serializable;
import java.util.HashSet;
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
import com.isa.entity.users.Worker;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -2730772573761285789L;

	@Id
	@Column(name = "RES_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "RES_NAME", unique = false, nullable = false)
	private String restaurantName;

	@Size(max = 50)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@NotNull
	@Column(name = "RES_DESC", unique = false, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "restaurants", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JsonIgnore
	private Set<Product> menu = new HashSet<Product>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Segment> segments = new HashSet<Segment>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Grade> grades = new HashSet<Grade>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<RestaurantManager> restaurantManagers = new HashSet<RestaurantManager>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Worker> workers = new HashSet<Worker>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Reservation> reservations=new HashSet<Reservation>();

	public Restaurant() {

	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String name) {
		this.restaurantName = name;
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
	@JsonIgnore
	public Set<Worker> getWorkers() {
		return workers;
	}
	@JsonProperty
	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Set<Reservation> getReservations() {
		return reservations;
	}

	@JsonProperty
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

}
