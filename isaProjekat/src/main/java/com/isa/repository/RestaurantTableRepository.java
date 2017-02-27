package com.isa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;

public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Long> {

	public Iterable<RestaurantTable> findBySegment(Segment segment);

	@Query("select t from Segment s inner join s.tables as t where s.restaurant = ?1")
	List<RestaurantTable> getTablesForRestaurant(Restaurant r);

	@Query("select t from Segment s inner join s.tables as t where s.id = ?1 and t.free = 'true'")
	List<RestaurantTable> seeIfCanDeleteSegment(Long id);
	
	RestaurantTable findBySegmentAndTableRowAndTableColumn(Segment s,int row, int column);
}
