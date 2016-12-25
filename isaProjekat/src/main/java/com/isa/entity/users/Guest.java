package com.isa.entity.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="GUEST")
public class Guest extends User {
	private static final long serialVersionUID = -8929827501630339454L;

	/*@ManyToMany
	private Set<Guest> sentRequests;
	
	@ManyToMany
	private Set<Guest> friends;
	
	@ManyToMany
	private Set<Guest> pendingRequests;*/

	public Guest(){
	}
}
