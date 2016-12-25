package com.isa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.entity.users.Waiter;

@Entity
@Table(name="preparedItem")
public class PreparedItem {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@OneToOne
	private OrderItem orderItem;
	
	@ManyToOne(optional=false)
	private Waiter waiter;
	
	public PreparedItem(){
		
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public Waiter getWaiter() {
		return waiter;
	}
}
