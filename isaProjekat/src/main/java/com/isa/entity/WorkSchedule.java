package com.isa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.isa.entity.users.Worker;

@Entity
@Table(name = "WORK_SCHEDULE")
public class WorkSchedule implements Serializable {

	private static final long serialVersionUID = -5309553359477225086L;

	@Id
	@Column(name = "WORK_SCH_ID")
	@GeneratedValue
	private Long id;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "WORKER_SCHEDULE", joinColumns = @JoinColumn(name = "WORK_SCH_ID", referencedColumnName = "WORK_SCH_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"))
	@JsonManagedReference
	private Set<Worker> worker;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(name = "WORK_SCH_DATE")
	@Future
	private Date date;

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
	@JsonBackReference
	private Segment segment;

	@ManyToOne
	@JsonBackReference
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

	public Set<Worker> getWorker() {
		return worker;
	}

	public void setWorker(Set<Worker> worker) {
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

}
