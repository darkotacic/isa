package com.isa.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	
	@Column(name="clothesSize")
	protected String clothesSize;
	
	@Column(name="shoeNumber")
	protected String shoeNumber;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="worker")
	protected Set<WorkSchedule> workSchedules;

	public String getClothesSize() {
		return clothesSize;
	}

	public void setClothesSize(String clothesSize) {
		this.clothesSize = clothesSize;
	}

	public String getShoeNumber() {
		return shoeNumber;
	}

	public void setShoeNumber(String shoeNumber) {
		this.shoeNumber = shoeNumber;
	}
	
	public Set<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}

}
