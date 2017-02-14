package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Bartender;

public interface BartenderRepository extends CrudRepository<Bartender, Long> {

}
