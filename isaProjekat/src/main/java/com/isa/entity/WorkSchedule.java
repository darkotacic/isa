package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="workSchedule")
public class WorkSchedule implements Serializable{
	
	private static final long serialVersionUID = -5309553359477225086L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Worker worker;
	
	@Column(name="date")
	private String date;
	
	@Column(name="startTime")
	private double startTime;
	
	@Column(name="endTime")
	private double endTime;
	
	@OneToOne
	private Segment segment;
	
	@OneToOne
	private Worker shift;
	
	public WorkSchedule() {
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
