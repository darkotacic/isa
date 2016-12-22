package com.isa.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2730772573761285789L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name",unique=false,nullable=false)
	private String name;
	
	@Column(name="description",unique=false,nullable=false)
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant1")
	private Set<Product> foodMenu;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Product> drinkMenu;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Segment> segments;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Grade> grades; 
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getFoodMenu() {
		return foodMenu;
	}
	public Set<Product> getDrinkMenu() {
		return drinkMenu;
	}
	public Set<Segment> getSegments() {
		return segments;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}

}
