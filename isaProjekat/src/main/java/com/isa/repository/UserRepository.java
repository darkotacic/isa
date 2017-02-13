package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	public User findOne(String email);
	
	@SuppressWarnings("unchecked")
	public User save(User user);
	

}
