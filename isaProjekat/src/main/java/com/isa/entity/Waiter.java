package com.isa.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity 
@Table(name="waiter")
public class Waiter extends Worker {

	private static final long serialVersionUID = -9126299540081793972L;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="waiter")
	private Set<PreparedItem> preparedItems;
	
	public Set<PreparedItem> getPreparedItems() {
		return preparedItems;
	}

	public Waiter() {

	}

	
}
