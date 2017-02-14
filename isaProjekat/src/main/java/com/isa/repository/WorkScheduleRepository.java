package com.isa.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Worker;

public interface WorkScheduleRepository extends CrudRepository<WorkSchedule,Long> {

	@Query("select ws from Waiter w inner join w.workSchedules as ws")
	public Iterable<WorkSchedule> getWorkScheduleForWaiters();
	
	@Query("select ws from Cook c inner join c.workSchedules as ws")
	public Iterable<WorkSchedule> getWorkScheduleForCooks();
	
	@Query("select ws from Bartender b inner join b.workSchedules as ws")
	public Iterable<WorkSchedule> getWorkScheduleForBartenders();
	
	WorkSchedule findByWorkerAndDateAndStartTime(Worker w,Date d, double t);
	
}
