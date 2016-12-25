package com.isa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.entity.users.Waiter;

@Entity
@Table(name="RES_ORD")
public class Order implements Serializable {

	private static final long serialVersionUID = -2545303099330416597L;
	
	@Id
	@Column(name="RES_ORD_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="RES_ORD_DATE")
	private Date date;
	
	@OneToOne(optional=false)
	private RestaurantTable table;
	
	@OneToOne(optional=false)
	private Waiter waiter;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="order")
	private Set<OrderItem> orderedItems;
	
	public Order() {
	}
	
	public Date getDate() {
		return date;
	}

	public Set<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	public Long getId(){
		return id;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public Waiter getWaiter() {
		return waiter;
	}
	
}
