package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByEmail(String email);

}
