package com.entity;

import java.util.ArrayList;

public class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;
	private ArrayList<WorkSchedule> schedules;
	
	public Worker() {
		super();
		this.schedules=new ArrayList<>();
	}
	
	public int getNumberOfWorkSchedules(){
		return schedules.size();
	}
	
	public WorkSchedule getWorkScheduleAt(int index){
		return schedules.get(index);
	}
	
	public void addWorkSchedule(WorkSchedule schedule){
		schedules.add(schedule);
	}
	
	public void removeWorkSchedule(WorkSchedule schedule){
		schedules.remove(schedule);
	}
	
}
