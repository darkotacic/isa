package com.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Grade;
import com.isa.repository.GradeRepository;

@Service
@Transactional
public class GradeServiceImpl implements GradeService{

	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	public Grade addGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public void deleteGrade(Grade grade) {
		gradeRepository.delete(grade);
	}

}
