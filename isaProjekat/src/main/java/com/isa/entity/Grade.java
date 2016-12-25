package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="GRADE")
public class Grade implements Serializable {

	private static final long serialVersionUID = -5958329526192915938L;
	
	@Id
	@Column(name="GRD_ID")
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@Column(name="GRD_SERVICE")
	private double gradeOfService;
	
	@Column(name="GRD_MEAL")
	private double gradeOfOrderItem;
	
	@Column(name="GRD_RES")
	private double gradeOfRestaurant;
	
	public Grade() {
		
	}

	public double getGradeOfService() {
		return gradeOfService;
	}

	public double getGradeOfOrderItem() {
		return gradeOfOrderItem;
	}

	public double getGradeOfRestaurant() {
		return gradeOfRestaurant;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
