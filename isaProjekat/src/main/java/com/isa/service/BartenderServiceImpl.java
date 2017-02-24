package com.isa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.OrderItem;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Bartender;
import com.isa.repository.BartenderRepository;
import com.isa.repository.OrderItemRepository;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class BartenderServiceImpl implements BartenderService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BartenderRepository bartenderRepository;
	
	@Override
	public Iterable<WorkSchedule> getWorkScheduleForBartenders() {
		return workScheduleRepository.getWorkScheduleForBartenders();
	}

	@Override
	public Iterable<OrderItem> findDrinkOrderItems() {
		return orderItemRepository.findDrinkOrderItems();
	}

	@Override
	public Bartender updateBartenderInformation(Bartender bartender) {
		return bartenderRepository.save(bartender);
	}

	@Override
	public Bartender getBartender(Long id) {
		return bartenderRepository.findOne(id);
	}

	@Override
	public List<WorkSchedule> getWorkScheduleBetween(Date startDate, Date endDate) {
		return workScheduleRepository.getWorkScheduleForBartendersBetween(startDate, endDate);
	}

}
