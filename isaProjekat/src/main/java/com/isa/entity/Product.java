package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@Column(name="PR_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="PR_NAME")
	private String name;
	
	@Column(name="PR_DES")
	private String description;
	
	@Column(name="PR_PRICE")
	private double price;
	
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	
	@ManyToOne
	private Restaurant restaurant;
	
	
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

	public Long getId() {
		return id;
	}
	
}
