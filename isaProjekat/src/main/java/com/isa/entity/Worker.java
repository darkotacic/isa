package com.isa.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	
	
}
