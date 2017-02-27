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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "SEGMENT", uniqueConstraints = {
@UniqueConstraint(columnNames = { "RESTAURANT_RES_ID", "SGM_POS", "SGM_SMOKING" }) })
public class Segment implements Serializable {

	private static final long serialVersionUID = 5329505235746769431L;

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segment", orphanRemoval = true)
	@JsonIgnore
	private Set<RestaurantTable> tables = new HashSet<RestaurantTable>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "segment", orphanRemoval = true)
	@JsonIgnore
	private Set<WorkSchedule> schedules = new HashSet<WorkSchedule>();

	@ManyToOne
	private Restaurant restaurant;

	@Column(name = "SGM_SMOKING", columnDefinition = "boolean default true", insertable = true)
	private boolean smokingAllowed;
	
	@Column(name = "WIDTH")
	private int width;
	
	@Column(name = "HEIGHT")
	private int height;

	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	@NotNull
	@Column(name = "SGM_POS")
	private String position;

	public Segment() {

	}

	@JsonIgnore
	public Set<WorkSchedule> getWorkSchedule() {
		return schedules;
	}

	@JsonProperty
	public void setWorkSchedule(Set<WorkSchedule> segments) {
		this.schedules = segments;
	}

	@JsonProperty
	public void setTables(Set<RestaurantTable> tables) {
		this.tables = tables;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setSmokingAllowed(Boolean allowed) {
		this.smokingAllowed = allowed;
	}

	public Long getId() {
		return id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}

	@JsonIgnore
	public Set<RestaurantTable> getTables() {
		return tables;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
