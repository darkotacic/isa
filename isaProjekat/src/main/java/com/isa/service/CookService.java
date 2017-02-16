package com.isa.service;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Cook;

public interface CookService {
	
	public Iterable<WorkSchedule> getWorkScheduleForCooks();
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType);
	public Iterable<OrderItem> getTasks(Cook cook);
	public Cook updateCookInformation(Cook cook);
	
}
