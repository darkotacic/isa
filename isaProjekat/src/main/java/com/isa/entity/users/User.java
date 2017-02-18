package com.isa.entity.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USER")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable {
	
	private static final long serialVersionUID = 2626562778387146532L;

	@Id
	@Column(name="USER_ID")
	@GeneratedValue
	private long id;
	
	@NotNull
	@Email(message = "Email must be a well-formed address")
	@Column(name="USER_EMAIL",unique=true,nullable=false)
	private String email;
	
	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(name="USER_NAME",unique=false,nullable=false)
	private String name;
	
	public long getId() {
		return id;
	}

	@Pattern(regexp="^[A-Z][a-z A-Z]*")
	@NotNull
	@Column(name="USER_SURNAME",unique=false,nullable=false)
	private String surname;
	
	@Pattern(regexp="\\w*")
	@NotNull
	@Column(name="USER_PASS",unique=false,nullable=false)
	private String password;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name="USER_DATE")
	private Date dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

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
	
	public User(String name, String surname, String email, String password, Date dateOfBirth) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
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
