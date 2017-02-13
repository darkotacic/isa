package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "GRADE")
public class Grade implements Serializable {

	private static final long serialVersionUID = -5958329526192915938L;

	@Id
	@Column(name = "GRD_ID")
	@GeneratedValue
	private long id;

	@ManyToOne
	@JsonBackReference
	private Restaurant restaurant;

	@Column(name = "GRD_SERVICE")
	private double gradeOfService;

	@Column(name = "GRD_MEAL")
	private double gradeOfOrderItem;

	@Column(name = "GRD_RES")
	private double gradeOfRestaurant;

	public Grade() {

	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public long getId() {
		return id;
	}

	public void setGradeOfService(double gradeOfService) {
		this.gradeOfService = gradeOfService;
	}

	public void setGradeOfOrderItem(double gradeOfOrderItem) {
		this.gradeOfOrderItem = gradeOfOrderItem;
	}

	public void setGradeOfRestaurant(double gradeOfRestaurant) {
		this.gradeOfRestaurant = gradeOfRestaurant;
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
}
