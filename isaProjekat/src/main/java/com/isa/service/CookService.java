package com.isa.service;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.WorkSchedule;

public interface CookService {
	
	public Iterable<WorkSchedule> getWorkScheduleForCooks();
	
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType);
	
}
