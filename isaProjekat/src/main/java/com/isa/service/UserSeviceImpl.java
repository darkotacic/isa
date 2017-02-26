package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.users.User;
import com.isa.repository.UserRepository;

@Service
@Transactional
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User logIn(User user) {
		String email = user.getEmail();
		return this.userRepository.findByEmail(email);
	}


	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
