package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.WorkSchedule;

public interface WorkScheduleRepository extends CrudRepository<WorkSchedule,Long> {

	@Query("from Waiter w left join w.workSchedules")
	public Iterable<WorkSchedule> getWorkScheduleForWaiters();
	
}
