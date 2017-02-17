package com.isa.entity.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.Grade;

@Entity
@Table(name="GUEST")
public class Guest extends User {
	private static final long serialVersionUID = -8929827501630339454L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.sender", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Friend> sent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.reciever", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Friend> recieved;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "guest", cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<Grade> grades;
	
	public Guest(){
		
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
