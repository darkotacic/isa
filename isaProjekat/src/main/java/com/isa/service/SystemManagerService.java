package com.isa.service;

import org.springframework.http.ResponseEntity;

import com.isa.entity.users.SystemManager;

public interface SystemManagerService {
	ResponseEntity<SystemManager> register(SystemManager sm);
}
