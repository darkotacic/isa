package com.entity;

import java.io.Serializable;

public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 2815826624648081174L;
	private Long orderId;
	private Long productId;
	private int quantity;
	private int isServed;
	
	public OrderItem() {
		this.orderId=new Long(0);
		this.productId=new Long(0);
		this.quantity=0;
		this.isServed=0;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIsServed() {
		return isServed;
	}

	public void setIsServed(int isServed) {
		this.isServed = isServed;
	}

}
