package com.entity;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	private static final long serialVersionUID = 2626562778387146532L;
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	
	public User() {
		this.id=new Long(0);
		this.name="";
		this.surname="";
		this.email="";
		this.password="";
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
