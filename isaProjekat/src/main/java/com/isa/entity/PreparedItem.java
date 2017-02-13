package com.isa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.isa.entity.users.Waiter;

@Entity
@Table(name = "preparedItem")
public class PreparedItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8560673730793975093L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "date")
	private Date date;

	@ManyToOne(optional = false)
	private Waiter waiter;

	public PreparedItem() {

	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Waiter getWaiter() {
		return waiter;
	}
}
