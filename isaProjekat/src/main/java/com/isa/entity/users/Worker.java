package com.isa.entity.users;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.entity.WorkSchedule;

@Entity
@Table(name = "WORKER")
@Inheritance(strategy = InheritanceType.JOINED)
public class Worker extends User {

	private static final long serialVersionUID = -962979825853451275L;

	@Column(name = "WRK_SHOE")
	private int shoeNumber;

	@Column(name = "WRK_SHIRT")
	private String shirtSize;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "worker", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	protected Set<WorkSchedule> workSchedules;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "replacement", orphanRemoval = true)
	@JsonIgnore
	protected Set<WorkSchedule> replacements;

	public Worker() {

	}

	public Worker(String name, String surname, String email, String password, Date date, String shirtSize,
			int shoeNumber) {
		super(name, surname, email, password, date);
		this.shirtSize = shirtSize;
		this.shoeNumber = shoeNumber;
	}

	public Set<WorkSchedule> getReplacements() {
		return replacements;
	}

	public void setReplacements(Set<WorkSchedule> replacements) {
		this.replacements = replacements;
	}

	public void setShoeNumber(int shoeNumber) {
		this.shoeNumber = shoeNumber;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public void setWorkSchedules(Set<WorkSchedule> workSchedules) {
		this.workSchedules = workSchedules;
	}

	public int getShoeNumber() {
		return shoeNumber;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public Set<WorkSchedule> getWorkSchedules() {
		return workSchedules;
	}
}
