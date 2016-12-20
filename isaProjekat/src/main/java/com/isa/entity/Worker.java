package com.isa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="worker")
	private Set<WorkSchedule> schedules;
	
	public Worker() {
		super();
		this.schedules=new HashSet<>();
	}
	
	public Set<WorkSchedule> getWorkSchedules(){
		return schedules;
	}
	
}
