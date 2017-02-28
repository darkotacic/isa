package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.users.Guest;

@Entity
@Table(name="RESERVATION")
public class Reservation  implements Serializable {


	private static final long serialVersionUID = 8170102871778902664L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Order> orders;
		
	@ManyToMany
	@JoinTable(name = "GUEST_RESERVATIONS", joinColumns = @JoinColumn(name = "RES_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"))
	@JsonIgnore
	private Set<Guest> people;
	
	@DecimalMin("00.00")
	@DecimalMax("24.00")
	@Digits(integer = 2, fraction = 2)
	@NotNull
	@Column(name = "RESERVATION_START")
	private double startTime;

	@DecimalMin("00.00")
	@DecimalMax("24.00")
	@Digits(integer = 2, fraction = 2)
	@NotNull
	@Column(name = "RESERVATION_END")
	private double endTime;
	
	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonIgnore
	public Set<Order> getOrders() {
		return orders;
	}
	@JsonProperty
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	@JsonIgnore
	public Set<Guest> getPeople() {
		return people;
	}
	@JsonProperty
	public void setPeople(Set<Guest> people) {
		this.people = people;
	}

}
