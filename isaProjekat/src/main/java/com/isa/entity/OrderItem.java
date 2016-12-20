package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderItem")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 2815826624648081174L;

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(mappedBy="orderItem")
	private Order order;
	
	@OneToOne(mappedBy="orderItem")
	private Product product;
	
	private int quantity;
	
	private int isServed;
	
	public OrderItem() {
		this.quantity=0;
		this.isServed=0;
	}

	public Long getId() {
		return id;
	}
	
	public Order getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getIsServed() {
		return isServed;
	}

}
