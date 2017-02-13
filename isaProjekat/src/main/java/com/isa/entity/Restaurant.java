package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.entity.users.RestaurantManager;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant implements Serializable {

	private static final long serialVersionUID = -2730772573761285789L;

	@Id
	@Size(min=3, max=30)
	@Pattern(regexp="^[A-Z]\\w*")
	@NotNull
	@Column(name = "RES_NAME", unique = true, nullable = false)
	private String name;

	@Size(max=50)
	@Pattern(regexp="^[A-Z]\\w*")
	@NotNull
	@Column(name = "RES_DESC", unique = false, nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "restaurants")
	@JsonIgnore
	private Set<Product> menu;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	@JsonIgnore
	private Set<Segment> segments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	@JsonIgnore
	private Set<Grade> grades;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
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

	public Set<Grade> getGrades() {
		return grades;
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

	public Set<RestaurantManager> getRestaurantManagers() {
		return restaurantManagers;
	}

	public void setRestaurantManagers(Set<RestaurantManager> restaurantManagers) {
		this.restaurantManagers = restaurantManagers;
	}

	public void setMenu(Set<Product> menu) {
		this.menu = menu;
	}

	public void setSegments(Set<Segment> segments) {
		this.segments = segments;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

}
