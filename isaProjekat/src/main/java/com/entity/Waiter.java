package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.OneToMany;

@Entity 
@Table(name="waiter")
public class Waiter extends Worker {

	private static final long serialVersionUID = -9126299540081793972L;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="waiter")
	private Set<Order> orders;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="waiter")
	private Set<OrderItem> preparedItems;
	
	public Waiter() {
		super();
		this.orders=new HashSet<>();
		this.preparedItems=new HashSet<>();
	}
	
	public Set<Order> getOrders(){
		return orders;
	}
	
	public Set<OrderItem> getOrderItems(){
		return preparedItems;
	}
	
}
