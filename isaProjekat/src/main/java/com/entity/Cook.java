package com.entity;

import java.util.ArrayList;

public class Cook extends Worker{

	private static final long serialVersionUID = 8374635758774458646L;
	private String profile;
	private ArrayList<OrderItem> orderedFood;
	
	public Cook() {
		super();
		this.profile="";
		this.orderedFood=new ArrayList<>();
	}
	
	public int getNumberOfOrderedFood(){
		return this.orderedFood.size();
	}
	
	public OrderItem getOrderedFoodAt(int index){
		return this.orderedFood.get(index);
	}
	
	public void addOrderedFood(OrderItem orderedFood){
		this.orderedFood.add(orderedFood);
	}
	
	public void removeOrderedFood(OrderItem orderedFood){
		this.orderedFood.remove(orderedFood);
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
