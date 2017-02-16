package com.isa.service;

import com.isa.entity.OrderItem;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;

public interface BartenderService {

	public Iterable<WorkSchedule> getWorkScheduleForBartenders();
	public Iterable<OrderItem> findDrinkOrderItems();
	public Bartender updateBartenderInformation(Bartender bartender);
	
}
