package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.WorkSchedule;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class BartenderServiceImpl implements BartenderService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Override
	public Iterable<WorkSchedule> getWorkScheduleForBartenders() {
		return workScheduleRepository.getWorkScheduleForBartenders();
	}

}
