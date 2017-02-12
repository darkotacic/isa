package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PRODUCT")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@Column(name="PR_ID")
	@GeneratedValue
	private Long id;
	
	@Size(min=3, max=30)
	@Pattern(regexp="^[A-Z]\\w*")
	@NotNull
	@Column(name="PR_NAME", unique=true, nullable=false)
	private String name;
	
	@Column(name="PR_DES")
	@Pattern(regexp="^[A-Z]\\w*")
	@Size(max=60)
	private String description;
	
	@DecimalMin("0.10")
	@Digits(integer=4, fraction=2)
	@NotNull
	@Column(name="PR_PRICE",nullable=false)
	private double price;
	
	@Enumerated(EnumType.STRING)
	private ProductType productType;
	
	@ManyToMany
	@JoinTable(
		      name="RESTAURANT_PRODUCTS",
		      joinColumns=@JoinColumn(name="PR_ID", referencedColumnName="PR_ID"),
		      inverseJoinColumns=@JoinColumn(name="RES_ID", referencedColumnName="RES_ID"))
	@JsonIgnore
	private Set<Restaurant> restaurants;
	
	@ManyToMany
	@JsonIgnore
	private Set<RequestOffer> requestOffers;
	
	
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

	public Long getId() {
		return id;
	}
	
}
