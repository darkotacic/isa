package com.isa.service;

import java.util.Date;
import java.util.List;

import com.isa.entity.OrderItem;
import com.isa.entity.Restaurant;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;

public interface BartenderService {

	public Iterable<WorkSchedule> getWorkScheduleForBartenders(Restaurant restaurant);
	public Iterable<OrderItem> findDrinkOrderItems(Restaurant restaurant);
	public Bartender updateBartenderInformation(Bartender bartender);
	public Bartender getBartender(Long id);
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate, Date endDate,Restaurant restaurant);
	
}
