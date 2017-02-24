package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.users.RestaurantManager;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	@Query("select sum(g.gradeOfRestaurant)/count(g.gradeOfRestaurant) from Restaurant r inner join r.grades as g where r.id = ?1")
	Double getGradeForRestaurant(Long t);
	
	@Query("select r from Restaurant r inner join r.restaurantManagers as rm where rm = ?1")
	Restaurant getByManager(RestaurantManager t);
}
