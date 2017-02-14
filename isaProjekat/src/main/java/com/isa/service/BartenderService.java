package com.isa.service;

import com.isa.entity.WorkSchedule;

public interface BartenderService {

	public Iterable<WorkSchedule> getWorkScheduleForBartenders();
	
}
