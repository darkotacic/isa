package com.entity;

import java.util.ArrayList;

public class Bartender extends Worker{

	private static final long serialVersionUID = -7382607325679638934L;
	private ArrayList<OrderItem> orderedDrinks;
	
	public Bartender() {
		super();
		this.orderedDrinks=new ArrayList<>();
	}
	
	public int getNumberOfOrderedDrinks(){
		return orderedDrinks.size();
	}
	
	public OrderItem getOrderedDrinkAt(int index){
		return orderedDrinks.get(index);
	}
	
	public void addOrderedDrink(OrderItem orderedDrink){
		orderedDrinks.add(orderedDrink);
	}
	
	public void removeOrderedDrink(OrderItem orderedDrink){
		orderedDrinks.remove(orderedDrink);
	}
}
