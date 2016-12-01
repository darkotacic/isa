package com.entity;

import java.io.Serializable;

public class WorkSchedule implements Serializable{
	
	private static final long serialVersionUID = -5309553359477225086L;
	private Long workerId;
	private String date;
	private double startTime;
	private double endTime;
	private Long shiftId;
	
	public WorkSchedule() {
		this.workerId=new Long(0);
		this.date="";
		this.startTime=0;
		this.endTime=0;
		this.shiftId=new Long(0);
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public Long getShiftId() {
		return shiftId;
	}

	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}
	
}
