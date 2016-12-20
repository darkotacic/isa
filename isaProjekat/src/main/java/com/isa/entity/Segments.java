package com.isa.entity;

import java.util.Set;

public class Segments {
	private Long id;
	private Set<Long> tables;
	private boolean smokingAllowed;
	private String position;
	
	public Long getId() {
		return id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isSmokingAllowed() {
		return smokingAllowed;
	}
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
	public Set<Long> getTables() {
		return tables;
	}
	public void setTables(Set<Long> tables) {
		this.tables = tables;
	}
}
