package com.isa.entity.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.Grade;
import com.isa.entity.Reservation;

@Entity
@Table(name="GUEST")
public class Guest extends User {
	private static final long serialVersionUID = -8929827501630339454L;

	@Enumerated(EnumType.STRING)
	private GuestStatus status;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.sender", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Friend> sent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.reciever", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Friend> recieved;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guest", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Grade> grades;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "people", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Reservation> reservations;
	
	
	@JsonIgnore
	public Set<Reservation> getReservations() {
		return reservations;
	}


	@JsonProperty
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}



	public Guest(){
		
	}
	
	

	public GuestStatus getStatus() {
		return status;
	}



	public void setStatus(GuestStatus status) {
		this.status = status;
	}



	@JsonIgnore
	public Set<Friend> getSent() {
		return sent;
	}

	@JsonProperty
	public void setSent(Set<Friend> sent) {
		this.sent = sent;
	}

	@JsonIgnore
	public Set<Friend> getRecieved() {
		return recieved;
	}

	@JsonProperty
	public void setRecieved(Set<Friend> recieved) {
		this.recieved = recieved;
	}

	@JsonIgnore
	public Set<Grade> getGrades() {
		return grades;
	}

	@JsonProperty
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}
	
}
