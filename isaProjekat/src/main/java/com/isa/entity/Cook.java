package com.isa.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cook")
public class Cook extends Worker{

	private static final long serialVersionUID = 8374635758774458646L;
		
	@OneToOne
	private CookType cookType;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="cook")
	private Set<OrderItem> orderedFood;
	
	public Cook() {

	}

	public CookType getCookType() {
		return cookType;
	}

	public Set<OrderItem> getOrderedFood() {
		return orderedFood;
	}
	
}
