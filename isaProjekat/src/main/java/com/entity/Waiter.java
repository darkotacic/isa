package com.entity;

import java.util.ArrayList;

public class Waiter extends Worker {

	private static final long serialVersionUID = -9126299540081793972L;
	private ArrayList<Order> orders;
	private ArrayList<OrderItem> preparedItems;
	
	public Waiter() {
		super();
		this.orders=new ArrayList<>();
		this.preparedItems=new ArrayList<>();
	}
	
	public int getNumberOfOrders(){
		return orders.size();
	}
	
	public Order getOrder(int index){
		return orders.get(index);
	}
	
	public void addOrder(Order order){
		orders.add(order);
	}
	
	public void removeOrder(Order order){
		orders.remove(order);
	}
	
	public int getNumberOfPreparedItems(){
		return preparedItems.size();
	}
	
	public OrderItem getPreparedItemsAt(int index){
		return preparedItems.get(index);
	}
	
	public void addPreparedItem(OrderItem preparedItem){
		preparedItems.add(preparedItem);
	}
	
	public void removePreparedItem(OrderItem preparedItem){
		preparedItems.remove(preparedItem);
	}
	
}
