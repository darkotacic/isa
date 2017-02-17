package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	@Query("select sum(g.gradeOfRestaurant)/count(g.gradeOfRestaurant) from Restaurant r inner join r.grades as g where r.id = ?1")
	double getGradeForRestaurant(Long t);
}
