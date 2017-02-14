package com.isa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.entity.users.Waiter;

@Entity
@Table(name = "RES_ORD")
public class Order implements Serializable {

	private static final long serialVersionUID = -2545303099330416597L;

	@Id
	@Column(name = "RES_ORD_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "RES_ORD_DATE")
	@Temporal(TemporalType.DATE)
	private Date date;

	@ManyToOne(optional = false)
	private RestaurantTable table;

	@ManyToOne(optional = false)
	private Waiter waiter;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<OrderItem> orderedItems;

	public Order() {
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	@JsonProperty
	public void setOrderedItems(Set<OrderItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public Date getDate() {
		return date;
	}
	@JsonIgnore
	public Set<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	public Long getId() {
		return id;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public Waiter getWaiter() {
		return waiter;
	}

}
