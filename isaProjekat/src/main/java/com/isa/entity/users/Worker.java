package com.isa.entity.users;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="WORKER")
@Inheritance(strategy=InheritanceType.JOINED)
public class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	
	@Column(name="WRK_SHOE")
	private int shoeNumber;
	
	@Column(name="WRK_SHIRT")
	private int shirtSize;
	
	public Worker(){
		
	}
	
	public Worker(String name,String surname,String email,String password,Date date,int shirtSize,int shoeNumber){
		super(name,surname,email,password,date);
		this.shirtSize = shirtSize;
		this.shoeNumber = shoeNumber;
	}

	public int getShoeNumber() {
		return shoeNumber;
	}

	public int getShirtSize() {
		return shirtSize;
	}
}
