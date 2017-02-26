package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.Restaurant;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Cook;
import com.isa.repository.CookRepository;
import com.isa.repository.OrderItemRepository;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class CookServiceImpl implements CookService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CookRepository cookRepository;
	
	@Override
	public Iterable<WorkSchedule> getWorkScheduleForCooks(Restaurant restaurant) {
		return workScheduleRepository.getWorkScheduleForCooks(restaurant);
	}

	@Override
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType,Restaurant restaurant) {
		return orderItemRepository.findFoodOrderItems(productType,restaurant);
	}

	@Override
	public Iterable<OrderItem> getTasks(Cook cook) {
		return orderItemRepository.getTasksForCook(cook);
	}

	@Override
	public Cook updateCookInformation(Cook cook) {
		return cookRepository.save(cook);
	}

	@Override
	public Cook getCook(Long id) {
		return cookRepository.findOne(id);
	}

	@Override
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate, Date endDate,Restaurant restaurant) {
		return workScheduleRepository.getWorkScheduleForCooksBetween(startDate, endDate,restaurant);
	}

}
