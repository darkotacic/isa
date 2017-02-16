package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
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
	public Iterable<WorkSchedule> getWorkScheduleForCooks() {
		return workScheduleRepository.getWorkScheduleForCooks();
	}

	@Override
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType) {
		return orderItemRepository.findFoodOrderItems(productType);
	}

	@Override
	public Iterable<OrderItem> getTasks(Cook cook) {
		return orderItemRepository.getTasksForCook(cook);
	}

	@Override
	public Cook updateCookInformation(Cook cook) {
		return cookRepository.save(cook);
	}

}
