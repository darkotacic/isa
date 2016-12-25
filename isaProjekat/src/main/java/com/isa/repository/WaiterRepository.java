package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Waiter;

public interface WaiterRepository extends CrudRepository<Waiter, Long> {

}
