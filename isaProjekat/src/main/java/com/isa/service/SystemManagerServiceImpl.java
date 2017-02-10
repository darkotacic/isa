package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.users.SystemManager;
import com.isa.repository.SystemManagerRepository;
import com.isa.repository.UserRepository;

@Service
@Transactional
public class SystemManagerServiceImpl implements SystemManagerService {
	
	@Autowired
	private SystemManagerRepository systemManagerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ResponseEntity<SystemManager> register(SystemManager sm) {
		if (this.userRepository.findByEmail(sm.getEmail()) != null || sm.getEmail() == null || sm.getName() == null
				|| sm.getPassword() == null || sm.getSurname() == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<SystemManager>(this.systemManagerRepository.save(sm),HttpStatus.CREATED);
	}

}
