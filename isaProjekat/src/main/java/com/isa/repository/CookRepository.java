package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Cook;

public interface CookRepository extends CrudRepository<Cook, Long> {

}
