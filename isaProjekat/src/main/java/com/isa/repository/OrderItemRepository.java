package com.isa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.Order;
import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	List<OrderItem> findByOrder(Order order);
	
	@Query("select oi from OrderItem oi inner join oi.product as p where p.productType=?1 and oi.status='ONHOLD'")
	List<OrderItem> findFoodOrderItems(ProductType productType);
	
}
