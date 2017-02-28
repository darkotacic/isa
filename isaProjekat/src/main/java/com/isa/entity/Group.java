package com.isa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Group implements Serializable{

	private static final long serialVersionUID = 1063875163196479478L;
	
	private Date date;
	private ArrayList<WorkSchedule> schedules;
	
	public Group(Date date) {
		this.date=date;
		this.schedules=new ArrayList<>();
	}
	
	public void addSchedule(WorkSchedule schedule){
		schedules.add(schedule);
	}
	
	public WorkSchedule getScheduleAt(int index){
		return schedules.get(index);
	}
	
	public int getNumberOfSchedules(){
		return schedules.size();
	}
	
	public String getDate(){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		int m=calendar.get(Calendar.MONTH)+1;
		return calendar.get(Calendar.DATE)+"."+m+"."+calendar.get(Calendar.YEAR)+".";
	}

	public ArrayList<WorkSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<WorkSchedule> schedules) {
		this.schedules = schedules;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDateAsDate(){
		return date;
	}
	
}
