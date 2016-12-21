package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "productType")
public class ProductType implements Serializable {
	
	private static final long serialVersionUID = 4101182883304774940L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="value")
	private String value;
	
	public ProductType(){
		
	}

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
