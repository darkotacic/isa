package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Restaurant;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.UserRole;
import com.isa.entity.users.Waiter;
import com.isa.repository.WorkScheduleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkScheduleRepositoryTest {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	private Date startDate;
	private Date endDate;
	private Segment segment;
	private Restaurant restaurant;
	private Waiter waiter;
	
	@Before
	public void setUp(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(2017, 01, 28);
		startDate=calendar.getTime();
		calendar.set(2017, 02, 03);
		endDate=calendar.getTime();
		segment=new Segment();
		segment.setId(new Long(1));
		restaurant=new Restaurant(1,"Dva stapica","Kineski restoran");
		waiter=new Waiter();
		waiter.setId(3);
	}
	
	@Test
	public void getWorkSchedulesForSegment(){
		Iterable<WorkSchedule> schedules=workScheduleRepository.getWorkSchedulesForSegment(endDate, 8.0, segment);
		boolean equal=true;
		for(WorkSchedule schedule:schedules){
			if(schedule.getDate().compareTo(endDate)!=0 || schedule.getStartTime()!=8.0 || schedule.getSegment().getId().longValue()!=segment.getId().longValue()){
				equal=false;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void getWorkScheduleForWaitersBetween(){
		Iterable<WorkSchedule> schedules=workScheduleRepository.getWorkScheduleForWaitersBetween(startDate, endDate, restaurant);
		boolean equal=true;
		for(WorkSchedule schedule:schedules){
			if(schedule.getWorker().getUserRole()!=UserRole.WAITER){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void getWorkScheduleForCooksBetween(){
		Iterable<WorkSchedule> schedules=workScheduleRepository.getWorkScheduleForCooksBetween(startDate, endDate, restaurant);
		boolean equal=true;
		for(WorkSchedule schedule:schedules){
			if(schedule.getWorker().getUserRole()!=UserRole.COOK){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void getWorkScheduleForBartendersBetween(){
		Iterable<WorkSchedule> schedules=workScheduleRepository.getWorkScheduleForBartendersBetween(startDate, endDate, restaurant);
		boolean equal=true;
		for(WorkSchedule schedule:schedules){
			if(schedule.getWorker().getUserRole()!=UserRole.BARTENDER){
				equal=false;
				break;
			}
		}
		assertEquals(equal, true);
	}
	
	@Test
	public void getWorkSchedule(){
		WorkSchedule schedule=workScheduleRepository.getWorkSchedule(waiter, startDate);
		boolean equal=(schedule.getStartTime()==8.0);
		assertEquals(equal, true);
	}
	
}
