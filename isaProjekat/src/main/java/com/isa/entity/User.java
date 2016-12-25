package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 2626562778387146532L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	protected Long id;
	
	@Column(name="name",unique=false,nullable=false)
	protected String name;
	
	@Column(name="surname",unique=false,nullable=false)
	protected String surname;
	
	@Column(name="email",unique=true,nullable=false)
	protected String email;
	
	@Column(name="password",unique=false,nullable=false)
	protected String password;
	
	@Column(name="birthday",unique=false,nullable=false)
	protected String birthday;

	public User() {
		this.id=new Long(0);
		this.name="";
		this.surname="";
		this.email="";
		this.password="";
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
}
