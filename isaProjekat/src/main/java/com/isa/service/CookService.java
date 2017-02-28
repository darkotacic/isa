package com.isa.service;

import java.text.ParseException;
import java.util.List;

import com.isa.entity.Group;
import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.Restaurant;
import com.isa.entity.users.Cook;

public interface CookService {
	
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType,Restaurant restaurant);
	public Iterable<OrderItem> getTasks(Cook cook);
	public Cook updateCookInformation(Cook cook);
	public Cook getCook(Long id);
	public List<Group> getWorkSchedulesForMonth(int monthNumber,Restaurant restaurant) throws ParseException;
	
}
