package com.isa.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bartender")
public class Bartender extends Worker{

	private static final long serialVersionUID = -7382607325679638934L;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="bartender")
	private Set<OrderItem> orderedDrinks;
	
	public Bartender() {
	}
		
	public Set<OrderItem> getOrderedDrinks(){
		return orderedDrinks;
	}
}
