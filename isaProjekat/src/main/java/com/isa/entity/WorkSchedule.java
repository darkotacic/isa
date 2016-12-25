package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.entity.users.Worker;

@Entity
@Table(name="WORK_SCHEDULE")
public class WorkSchedule implements Serializable{
	
	private static final long serialVersionUID = -5309553359477225086L;

	@Id
	@Column(name="WORK_SCH_ID")
	@GeneratedValue
	private Long id;

	@OneToOne
	private Worker worker;
	
	@Column(name="WORK_SCH_DATE")
	private String date;
	
	@Column(name="WORK_SCH_START")
	private double startTime;
	
	@Column(name="WORK_SCH_END")
	private double endTime;
	
	@OneToOne
	private Worker shift;
	
	public WorkSchedule() {
	}

	public Worker getWorker() {
		return worker;
	}

	public String getDate() {
		return date;
	}

	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public Worker getShift() {
		return shift;
	}
	
}
