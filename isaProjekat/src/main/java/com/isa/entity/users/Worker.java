package com.isa.entity.users;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.entity.WorkSchedule;

@Entity
@Table(name="WORKER")
@Inheritance(strategy=InheritanceType.JOINED)
public class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	
	@Column(name="WRK_SHOE")
	private int shoeNumber;
	
	@Column(name="WRK_SHIRT")
	private String shirtSize;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="worker")
	@JsonIgnore
	protected Set<WorkSchedule> workSchedules;
	
	public Worker(){
		
	}
	
	public Worker(String name,String surname,String email,String password,Date date,String shirtSize,int shoeNumber){
		super(name,surname,email,password,date);
		this.shirtSize = shirtSize;
		this.shoeNumber = shoeNumber;
	}

	public int getShoeNumber() {
		return shoeNumber;
	}

	public String getShirtSize() {
		return shirtSize;
	}
	
	public Set<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}
}
