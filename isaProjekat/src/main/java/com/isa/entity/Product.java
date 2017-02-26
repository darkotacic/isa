package com.isa.entity;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 4047551117092218814L;

	@Id
	@Column(name = "PR_ID")
	@GeneratedValue
	private long id;

	@Size(min = 3, max = 30)
	@Pattern(regexp = "^[A-Z][a-z_ A-Z]*")
	@NotNull
	@Column(name = "PR_NAME", nullable = false)
	private String productName;

	@Column(name = "PR_DES")
	@Pattern(regexp = "^[A-Z][a-z_ A-Z0-9]*")
	@Size(max = 60)
	private String description;

	@NotNull
	@Digits(integer=6, fraction=2)
	@Column(name = "PR_PRICE", nullable = false)
	private Double price;

	@Enumerated(EnumType.STRING)
	private ProductType productType;

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@JoinTable(name = "RESTAURANT_PRODUCTS", joinColumns = @JoinColumn(name = "PR_ID", referencedColumnName = "PR_ID"), inverseJoinColumns = @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID"))
	@JsonIgnore
	private Set<Restaurant> restaurants = new HashSet<Restaurant>();

	@ManyToMany(cascade = { CascadeType.REMOVE, CascadeType.MERGE }, mappedBy = "products")
	@JsonIgnore
	private Set<RequestOffer> requestOffers = new HashSet<RequestOffer>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	@JsonIgnore
	private Set<OrderItem> item = new HashSet<OrderItem>();

	public Product() {

	}
	@JsonIgnore
	public Set<OrderItem> getItem() {
		return item;
	}
	@JsonProperty
	public void setItem(Set<OrderItem> item) {
		this.item = item;
	}

	public long getId() {
		return id;
	}

	public ProductType getProductType() {
		return productType;
	}

	public String getProductName() {
		return productName;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}
	@JsonIgnore
	public Set<Restaurant> getRestaurants() {
		return restaurants;
	}
	@JsonProperty
	public void setRestaurants(Set<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	@JsonIgnore
	public Set<RequestOffer> getRequestOffers() {
		return requestOffers;
	}
	@JsonProperty
	public void setRequestOffers(Set<RequestOffer> requestOffers) {
		this.requestOffers = requestOffers;
	}

	public void setProductName(String name) {
		this.productName = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
