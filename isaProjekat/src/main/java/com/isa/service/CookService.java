package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Cook;

public interface CookService {
	
	public Iterable<WorkSchedule> getWorkScheduleForCooks();
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType);
	public Iterable<OrderItem> getTasks(Cook cook);
	public Cook updateCookInformation(Cook cook);
	public Cook getCook(Long id);
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate, Date endDate);
	
}
