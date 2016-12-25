package com.isa.entity.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.entity.OrderItem;

@Entity
@Table(name="COOK")
public class Cook extends Worker{

	private static final long serialVersionUID = 8374635758774458646L;
		
	@Enumerated(EnumType.STRING)
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
