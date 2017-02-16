package com.isa.entity.users;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
@AssociationOverrides({
		@AssociationOverride(name = "pk.sender",
			joinColumns = @JoinColumn(name = "SENDER_ID")),
		@AssociationOverride(name = "pk.reciever",
			joinColumns = @JoinColumn(name = "RECIEVER_ID")) })
public class Friend implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8073014162857692185L;
	
	@EmbeddedId
	FriendId pk = new FriendId();
	
	@Column(name = "STATUS", nullable = false)
	private boolean status;
	
	@Transient
	private Guest getSender(){
		return pk.getSender();
	}
	
	@Transient
	public Guest getReciever(){
		return pk.getReciever();
	}
	
	public void setSender(Guest sender){
		pk.setSender(sender);
	}
	
	public void setReciever(Guest reciever){
		pk.setReciever(reciever);
	}

	public FriendId getPk() {
		return pk;
	}

	public void setPk(FriendId pk) {
		this.pk = pk;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
