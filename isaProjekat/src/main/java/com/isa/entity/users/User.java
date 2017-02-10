package com.isa.entity.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="USER")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
	private static final long serialVersionUID = 2626562778387146532L;
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private Long id;

	@Column(name="USER_EMAIL",unique=true,nullable=false)
	private String email;
	
	@Column(name="USER_NAME",unique=false,nullable=false)
	private String name;
	
	@Column(name="USER_SURNAME",unique=false,nullable=false)
	private String surname;
	
	@Column(name="USER_PASS",unique=false,nullable=false)
	private String password;
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="USER_DATE")
	private Date dateOfBirth;
	
	
	public User(String name, String surname, String email, String password, Date dateOfBirth) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
	}
	
	public Long getId() {
		return id;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
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
	
}
