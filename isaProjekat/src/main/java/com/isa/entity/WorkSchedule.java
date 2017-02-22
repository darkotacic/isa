package com.isa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.isa.entity.users.Worker;

@Entity
@Table(name = "WORK_SCHEDULE", uniqueConstraints = { @UniqueConstraint(columnNames = 
{ "WORKER_USER_ID", "WORK_SCH_DATE"})})
public class WorkSchedule implements Serializable {

	private static final long serialVersionUID = -5309553359477225086L;

	@Id
	@Column(name = "WORK_SCH_ID")
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Worker worker;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@NotNull
	@Column(name = "WORK_SCH_DATE")
	private Date date;

	@Column(name = "TWO_DAYS_SHIFT", columnDefinition = "boolean default false", insertable = true)
	private boolean twoDays;
	
	@Column(name = "SHIFT_DONE", columnDefinition = "boolean default false", insertable = true)
	private boolean done;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="mm.dd.yyyy") 
	@Column(name = "WORK_SCH_SECOND_DATE")
	private Date secondDate;
	
	@DecimalMin("00.00")
	@DecimalMax("24.00")
	@Digits(integer = 2, fraction = 2)
	@NotNull
	@Column(name = "WORK_SCH_START")
	private double startTime;

	@DecimalMin("00.00")
	@DecimalMax("24.00")
	@Digits(integer = 2, fraction = 2)
	@NotNull
	@Column(name = "WORK_SCH_END")
	private double endTime;

	@ManyToOne
	private Segment segment;

	@ManyToOne
	private Worker replacement;

	public WorkSchedule() {
	}

	public Long getId() {
		return id;
	}

	public double getStartTime() {
		return startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public Worker getReplacement() {
		return replacement;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

	public void setReplacement(Worker shift) {
		this.replacement = shift;
	}

	public boolean isTwoDays() {
		return twoDays;
	}

	public void setTwoDays(boolean twoDays) {
		this.twoDays = twoDays;
	}

	public Date getSecondDate() {
		return secondDate;
	}

	public void setSecondDate(Date secondDate) {
		this.secondDate = secondDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	

}
