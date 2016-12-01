package com.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

	private static final long serialVersionUID = -2545303099330416597L;
	private int tableNumber;
	private String recivedDate;
	private Long waiterId;
	private Long currentWaiterId;
	private ArrayList<OrderItem> orderedItems;
	
	public Order() {
		this.tableNumber=0;
		this.recivedDate="";
		this.waiterId=new Long(0);
		this.currentWaiterId=new Long(0);
		this.orderedItems=new ArrayList<>();
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getRecivedDate() {
		return recivedDate;
	}

	public void setRecivedDate(String recivedDate) {
		this.recivedDate = recivedDate;
	}

	public Long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}

	public Long getCurrentWaiterId() {
		return currentWaiterId;
	}

	public void setCurrentWaiterId(Long currentWaiterId) {
		this.currentWaiterId = currentWaiterId;
	}
	
	public int getNumberOfOrderedItems(){
		return orderedItems.size();
	}
	
	public OrderItem getOrderedItemAt(int index){
		return orderedItems.get(index);
	}
	
	public void addOrderedItem(OrderItem item){
		orderedItems.add(item);
	}
	
	public void removeOrderedItem(OrderItem item){
		orderedItems.remove(item);
	}
	
}
