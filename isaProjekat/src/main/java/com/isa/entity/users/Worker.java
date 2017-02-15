package com.isa.entity.users;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.Restaurant;
import com.isa.entity.WorkSchedule;

@Entity
@Table(name = "WORKER")
@Inheritance(strategy = InheritanceType.JOINED)
public class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;

	@Column(name = "WRK_SHOE")
	private int shoeNumber;

	@Column(name = "WRK_SHIRT")
	private String shirtSize;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "worker", orphanRemoval = true)
	@JsonIgnore
	protected Set<WorkSchedule> workSchedules = new HashSet<WorkSchedule>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "replacement", orphanRemoval = true)
	@JsonIgnore
	protected Set<WorkSchedule> replacements = new HashSet<WorkSchedule>();
	
	@ManyToOne
	protected Restaurant restaurant;

	public Worker() {

	}

	public Worker(String name, String surname, String email, String password, Date date, String shirtSize,
			int shoeNumber) {
		super(name, surname, email, password, date);
		this.shirtSize = shirtSize;
		this.shoeNumber = shoeNumber;
	}
	@JsonIgnore
	public Set<WorkSchedule> getReplacements() {
		return replacements;
	}
	@JsonProperty
	public void setReplacements(Set<WorkSchedule> replacements) {
		this.replacements = replacements;
	}
	
	public void setShoeNumber(int shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}
	@JsonProperty
	public void setWorkSchedules(Set<WorkSchedule> workSchedules) {
		this.workSchedules = workSchedules;
	}

	public int getShoeNumber() {
		return shoeNumber;
	}

	public String getShirtSize() {
		return shirtSize;
	}
	@JsonIgnore
	public Set<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
