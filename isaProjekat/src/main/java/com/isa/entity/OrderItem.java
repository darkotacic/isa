package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.entity.users.Bartender;
import com.isa.entity.users.Cook;

@Entity
@Table(name="ORD_IT")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 2815826624648081174L;

	@Id
	@Column(name="ORD_IT_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="ORD_IT_QUA")
	private int quantity;
	
	@ManyToOne
	private Order order;
	
	@OneToOne
	private Product product;
	
	@ManyToOne
	private Cook cook;
	
	@ManyToOne
	private Bartender bartender;
	
	public OrderItem() {
	}
	
	public Bartender getBartender() {
		return bartender;
	}

	public Cook getCook() {
		return cook;
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

}
