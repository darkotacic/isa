package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.Restaurant;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Cook;

public interface CookService {
	
	public Iterable<WorkSchedule> getWorkScheduleForCooks(Restaurant restaurant);
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType,Restaurant restaurant);
	public Iterable<OrderItem> getTasks(Cook cook);
	public Cook updateCookInformation(Cook cook);
	public Cook getCook(Long id);
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate, Date endDate,Restaurant restaurant);
	
}
