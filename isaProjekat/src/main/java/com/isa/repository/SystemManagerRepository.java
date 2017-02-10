package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.SystemManager;

public interface SystemManagerRepository extends CrudRepository<SystemManager, Long> {
	@SuppressWarnings("unchecked")
	SystemManager save(SystemManager sm);
}
