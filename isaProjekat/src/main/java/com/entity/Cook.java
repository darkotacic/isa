package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cook")
public class Cook extends Worker{

	private static final long serialVersionUID = 8374635758774458646L;
	
	@Column(name="profile")
	private String profile;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cook")
	private Set<OrderItem> orderedFood;
	
	public Cook() {
		super();
		this.profile="";
		this.orderedFood=new HashSet<>();
	}

	public String getProfile() {
		return profile;
	}

	public Set<OrderItem> getOrderedFood() {
		return orderedFood;
	}
	
}
