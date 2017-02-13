package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.users.Waiter;

public interface WaiterRepository extends CrudRepository<Waiter, String> {

}
