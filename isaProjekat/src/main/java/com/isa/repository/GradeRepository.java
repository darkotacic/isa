package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {
	Grade findOne(Long id);
}
