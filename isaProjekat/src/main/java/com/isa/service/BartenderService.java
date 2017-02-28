package com.isa.service;

import java.text.ParseException;
import java.util.List;

import com.isa.entity.Group;
import com.isa.entity.OrderItem;
import com.isa.entity.Restaurant;
import com.isa.entity.users.Bartender;

public interface BartenderService {

	public Iterable<OrderItem> findDrinkOrderItems(Restaurant restaurant);
	public Bartender updateBartenderInformation(Bartender bartender);
	public Bartender getBartender(Long id);
	public List<Group> getWorkSchedulesForMonth(int monthNumber,Restaurant restaurant) throws ParseException;
	
}
