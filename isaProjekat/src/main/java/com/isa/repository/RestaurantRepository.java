package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
