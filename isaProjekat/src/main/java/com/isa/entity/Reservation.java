package com.isa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="RESERVATION")
public class Reservation  implements Serializable {


	private static final long serialVersionUID = 8170102871778902664L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
}
