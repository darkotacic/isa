package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.users.Cook;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

	public List<OrderItem> findByOrder(Order order);
	
	@Query("select oi from OrderItem oi inner join oi.product as p where p.productType=?1 and oi.status='ONHOLD'")
	public List<OrderItem> findFoodOrderItems(ProductType productType);
	
	@Query("select oi from OrderItem oi inner join oi.product as p where p.productType='DRINK' and oi.status='ONHOLD'")
	public List<OrderItem> findDrinkOrderItems();
	
	@Query("select oi from OrderItem oi where oi.cook=?1 and oi.status='ONPREPARATION'")
	public List<OrderItem> getTasksForCook(Cook cook);
	
}
