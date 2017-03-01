package com.isa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Group;
import com.isa.entity.Order;
import com.isa.entity.OrderItem;
import com.isa.entity.Product;
import com.isa.entity.Restaurant;
import com.isa.entity.RestaurantTable;
import com.isa.entity.Segment;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Waiter;
import com.isa.entity.users.Worker;
import com.isa.repository.OrderItemRepository;
import com.isa.repository.OrderRepository;
import com.isa.repository.ProductRepository;
import com.isa.repository.RestaurantTableRepository;
import com.isa.repository.SegmentRepository;
import com.isa.repository.WaiterRepository;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class WaiterServiceImpl implements WaiterService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WaiterRepository waiterRepository;
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Autowired
	private RestaurantTableRepository restaurantTableRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public Order addOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Order updateOrder(Order order){
		return orderRepository.save(order);
	}
	
	@Override
	public Iterable<Order> findAllOrders(Restaurant restaurant){
		return orderRepository.getOrders(restaurant);
	}
	
	@Override
	public void deleteOrder(Order order){
		orderRepository.delete(order);
	}
	
	@Override
	public Waiter updateWaiterInformation(Waiter waiter) {
		return waiterRepository.save(waiter);
	}

	@Override
	public Iterable<Segment> getAllSegments(Restaurant restaurant) {
		return segmentRepository.getSegmentsForRestautant(restaurant);
	}
	
	@Override
	public Iterable<RestaurantTable> getAllTablesForSegment(Long segmentId) {
		Segment segment=segmentRepository.findOne(segmentId);
		return restaurantTableRepository.findBySegment(segment);
	}

	@Override
	public RestaurantTable getTable(Long id) {
		return restaurantTableRepository.findOne(id);
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public void deleteOrderItem(Long id) {
		orderItemRepository.delete(id);
	}

	@Override
	public Iterable<OrderItem> getOrderItemsForOrder(Order order) {
		return orderItemRepository.findByOrder(order);
	}

	@Override
	public WorkSchedule getWorkSchedule(Worker worker, Date date) {
		return workScheduleRepository.getWorkSchedule(worker,date);
	}

	@Override
	public List<Group> getWorkSchedulesForMonth(int monthNumber,Restaurant restaurant) throws ParseException {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1+monthNumber;
		int minDay=1,maxDay=31;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=sdf.parse(year+"-"+month+'-'+minDay);
		Date date2=sdf.parse(year+"-"+month+'-'+maxDay);
		List<WorkSchedule> schedules=workScheduleRepository.getWorkScheduleForWaitersBetween(date1,date2,restaurant);
		Group[] groups=new Group[31];
		for(int i=0;i<groups.length;i++){
			groups[i]=new Group(sdf.parse(year+"-"+month+"-"+minDay++));
			for(WorkSchedule schedule:schedules){
				if(groups[i].getDateAsDate().compareTo(schedule.getDate())==0)
					groups[i].addSchedule(schedule);
			}
		}
		List<Group> val = Arrays.asList(groups);
		return val;
	}

	@Override
	public Waiter getWaiter(Long id) {
		return waiterRepository.findOne(id);
	}

	@Override
	public Segment getSegment(Long id) {
		return segmentRepository.findOne(id);
	}

	@Override
	public WorkSchedule getWorkScheduleForSegment(Segment segment) {
		Date date=new Date();
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		double time=calendar.get(Calendar.HOUR_OF_DAY)+(calendar.get(Calendar.MINUTE)/100.0);
		List<WorkSchedule> schedules=workScheduleRepository.getWorkSchedulesForSegment(date, time, segment);
		if(schedules.size()<=0){
			return null;
		}
		return schedules.get(new Random().nextInt(schedules.size()));
	}

	@Override
	public WorkSchedule getWorkScheduleForSegment(Segment segment,double startTime,Date date) {

		List<WorkSchedule> schedules=workScheduleRepository.getWorkSchedulesForSegment(date, startTime, segment);
		if(schedules.size()<=0){
			return null;
		}
		return schedules.get(new Random().nextInt(schedules.size()));
	}
}
