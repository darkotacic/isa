package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.users.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
	Iterable<Worker> findByRestaurant(Restaurant r);
}
