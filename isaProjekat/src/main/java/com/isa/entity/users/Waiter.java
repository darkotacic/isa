package com.isa.entity.users;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.isa.entity.PreparedItem;

@Entity 
@Table(name="WAITER")
public class Waiter extends Worker {

	private static final long serialVersionUID = -9126299540081793972L;
		
	@OneToMany(fetch=FetchType.LAZY,mappedBy="waiter")
	private Set<PreparedItem> preparedItems;

	public Waiter() {
	}
	
	public Set<PreparedItem> getPreparedItems() {
		return preparedItems;
	}
	
}
