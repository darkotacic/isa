package com.isa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Worker;

public interface WorkScheduleRepository extends CrudRepository<WorkSchedule,Long> {
	
	@Query("select ws from Waiter w inner join w.workSchedules as ws where ws.segment=?3 and ws.date=?1 and ws.startTime<=?2 and ws.endTime>=?2")
	public List<WorkSchedule> getWorkSchedulesForSegment(Date date,double time,Segment segment);
	
	@Query("select ws from Waiter w inner join w.workSchedules as ws where ws.date between ?1 and ?2 and w.restaurant=?3")
	public List<WorkSchedule> getWorkScheduleForWaitersBetween(Date startDate,Date endDate,Restaurant restaurant);
	
	@Query("select ws from Cook c inner join c.workSchedules as ws where ws.date between ?1 and ?2 and c.restaurant=?3")
	public List<WorkSchedule> getWorkScheduleForCooksBetween(Date startDate,Date endDate,Restaurant restaurant);
	
	@Query("select ws from Bartender b inner join b.workSchedules as ws where ws.date between ?1 and ?2 and b.restaurant=?3")
	public List<WorkSchedule> getWorkScheduleForBartendersBetween(Date startDate,Date endDate,Restaurant restaurant);
	
	@Query("select ws from Worker w inner join w.workSchedules as ws where ws.date between ?1 and ?2 and w.restaurant=?3")
	public List<WorkSchedule> getWorkScheduleBetween(Date stardDate,Date endDate,Restaurant restaurant);
	
	WorkSchedule findByWorkerAndDate(Worker w,Date d);
	
	@Query("select ws from WorkSchedule ws where ws.worker=?1 and ws.date=?2")
	public WorkSchedule getWorkSchedule(Worker worker,Date date);
	
	@Query("select ws from Worker w inner join w.workSchedules as ws where w.restaurant = ?1")
	List<WorkSchedule> getWorkScheduleForRestaurant(Restaurant r);
	
	List<WorkSchedule> findByWorker(Worker w);
	
	@Query("select ws from Waiter w inner join w.workSchedules as ws where w.restaurant = ?1 and ws.startTime=?2 and ws.date = ?3")
	List<WorkSchedule> getReplacements(Restaurant t, double startTime, Date date);
}
