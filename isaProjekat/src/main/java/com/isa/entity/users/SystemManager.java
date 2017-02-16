package com.isa.entity.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.isa.entity.users.User;

@Entity
@Table(name ="SYSTEM_MANAGER")
public class SystemManager extends User{

	private static final long serialVersionUID = 115429919910622853L;
	
	@Column(name = "PREDEFINED", columnDefinition = "boolean default false", insertable = false)
	private boolean predefined;
	
	public SystemManager() {
		super();
	}

	public boolean isPredefined() {
		return predefined;
	}

	public void setPredefined(boolean predefined) {
		this.predefined = predefined;
	}
	
}
