package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Friend;
import com.isa.entity.users.FriendId;

public interface FriendRepository extends CrudRepository<Friend, FriendId>{
	

}
