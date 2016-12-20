package com.isa.entity;

import java.util.Set;

public class Restaurant {
	private Long id;
	private String name;
	private String description;
	private Set<Long> foodMenu;
	private Set<Long> drinkMenu;
	private Set<Long> segments;
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Long> getFoodMenu() {
		return foodMenu;
	}
	public void setFoodMenu(Set<Long> foodMenu) {
		this.foodMenu = foodMenu;
	}
	public Set<Long> getDrinkMenu() {
		return drinkMenu;
	}
	public void setDrinkMenu(Set<Long> drinkMenu) {
		this.drinkMenu = drinkMenu;
	}
	public Set<Long> getSegments() {
		return segments;
	}
	public void setSegments(Set<Long> segments) {
		this.segments = segments;
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
