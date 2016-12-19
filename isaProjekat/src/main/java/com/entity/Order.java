package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order implements Serializable {

	private static final long serialVersionUID = -2545303099330416597L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(optional=false)
	private Table table;
	
	@Column(name="dateAndTime")
	private Date dateAndTime;
	
	@ManyToOne(optional=false)
	private Waiter firstWaiter;
	
	@ManyToOne(optional=false)
	private Waiter secondWaiter;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="order")
	private Set<OrderItem> orderedItems;
	
	public Order() {
		this.dateAndTime=new Date();
		this.orderedItems=new HashSet<>();
	}
	
	public Long getId(){
		return id;
	}

	public Table getTable() {
		return table;
	}

	public Date getRecivedDate() {
		return dateAndTime;
	}

	public Waiter getFirstWaiter() {
		return firstWaiter;
	}

	public Waiter getSecondWaiter() {
		return secondWaiter;
	}
	
	public Set<OrderItem> getOrderItems(){
		return orderedItems;
	}
	
}
