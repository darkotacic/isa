package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private double price;
	
	@OneToOne
	private ProductType productType;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@ManyToOne
	private Restaurant restaurant1;
	
	public Product(){
		
	}
	
	
	public ProductType getProductType() {
		return productType;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}


	public Restaurant getRestaurant1() {
		return restaurant1;
	}


	public Long getId() {
		return id;
	}
	
}
