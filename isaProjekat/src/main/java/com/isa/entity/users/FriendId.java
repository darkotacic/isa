package com.isa.entity.users;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class FriendId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3766981130679676460L;
	
	@ManyToOne
	private Guest sender;
	@ManyToOne
	private Guest reciever;

	
	public Guest getSender() {
		return sender;
	}
	public void setSender(Guest sender) {
		this.sender = sender;
	}
	public Guest getReciever() {
		return reciever;
	}
	public void setReciever(Guest reciever) {
		this.reciever = reciever;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sender == null) ? 0 : sender.hashCode());
		result = (int) (prime * result + reciever.getId());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		FriendId other = (FriendId) obj;

        if (sender != null ? !sender.equals(other.sender) : other.sender != null) return false;
        if (reciever != null ? !reciever.equals(other.reciever) : other.reciever != null)
            return false;

        return true;
	}
}
