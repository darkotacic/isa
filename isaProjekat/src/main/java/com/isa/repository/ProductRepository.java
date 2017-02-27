package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("select p from Restaurant r inner join r.menu as p where r.id= ?1")
	List<Product> getProductsForRestaurant(Long t);
	
	@Query("select p from Restaurant r inner join r.menu as p where r.id= ?2 and p.id = ?1")
	Product seeIfBelongsToRestaurant(Long id, Long res_id);
	
	@Query("select p from RequestOffer ro inner join ro.products as p where ro.id= ?1")
	List<Product> getProductsForRequestOffer(Long t);
	
	@Query("select p from Restaurant r inner join r.menu as p where p.productName = ?1 and r.id = ?2")
	List<Product> findProductByRestaurantAndName(String name, Long id );
}
