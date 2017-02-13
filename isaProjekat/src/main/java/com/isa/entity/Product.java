package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@Column(name = "PR_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z]\\w*")
	@NotNull
	@Column(name = "PR_NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "PR_DES")
	@Pattern(regexp = "^[A-Z]\\w*")
	@Size(max = 60)
	private String description;

	@DecimalMin("0.10")
	@Digits(integer = 4, fraction = 2)
	@NotNull
	@Column(name = "PR_PRICE", nullable = false)
	private double price;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JoinTable(name = "RESTAURANT_PRODUCTS", joinColumns = @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID"), inverseJoinColumns = @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID"))
	@JsonManagedReference
	private Set<Restaurant> restaurants;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "products")
	@JsonBackReference
	private Set<RequestOffer> requestOffers;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	@JsonManagedReference
	private Set<OrderItem> item;

	public Product() {

	}

	public Set<OrderItem> getItem() {
		return item;
	}

	public void setItem(Set<OrderItem> item) {
		this.item = item;
	}

	public long getId() {
		return id;
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

	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public Set<RequestOffer> getRequestOffers() {
		return requestOffers;
	}

	public void setRequestOffers(Set<RequestOffer> requestOffers) {
		this.requestOffers = requestOffers;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
