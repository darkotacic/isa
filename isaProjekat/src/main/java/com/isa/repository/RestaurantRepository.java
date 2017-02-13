package com.isa.repository;

import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
	@SuppressWarnings("unchecked")
	Restaurant save(Restaurant r);
	
	//Iterable<Restaurant> findByName(String name);
	
	Restaurant findOne(Long id);
	
/*	@Modifying
	@Query("insert into restaurant_products(pr_id, res_id) values (?1, ?2)")
	RestaurantProducts write(Long res_id, Long pro_id);
	*/
}
