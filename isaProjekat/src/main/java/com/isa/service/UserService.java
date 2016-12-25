package com.isa.service;

import com.isa.entity.users.User;

public interface UserService {
	
	User logIn(User user);
	
	User register(User user);

}
