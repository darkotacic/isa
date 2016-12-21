package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cookType")
public class CookType implements Serializable {
	
	
	private static final long serialVersionUID = 9154611665791776364L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="value")
	private String value;
	
	public CookType(){
		
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
