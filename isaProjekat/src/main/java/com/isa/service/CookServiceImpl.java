package com.isa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isa.entity.Group;
import com.isa.entity.OrderItem;
import com.isa.entity.ProductType;
import com.isa.entity.Restaurant;
import com.isa.entity.WorkSchedule;
import com.isa.entity.users.Cook;
import com.isa.repository.CookRepository;
import com.isa.repository.OrderItemRepository;
import com.isa.repository.WorkScheduleRepository;

@Service
@Transactional
public class CookServiceImpl implements CookService {

	@Autowired
	private WorkScheduleRepository workScheduleRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private CookRepository cookRepository;

	@Override
	public Iterable<OrderItem> getFoodOrderItems(ProductType productType,Restaurant restaurant) {
		return orderItemRepository.findFoodOrderItems(productType,restaurant);
	}

	@Override
	public Iterable<OrderItem> getTasks(Cook cook) {
		return orderItemRepository.getTasksForCook(cook);
	}

	@Override
	public Cook updateCookInformation(Cook cook) {
		return cookRepository.save(cook);
	}

	@Override
	public Cook getCook(Long id) {
		return cookRepository.findOne(id);
	}

	@Override
	public List<Group> getWorkSchedulesForMonth(int monthNumber, Restaurant restaurant) throws ParseException {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1+monthNumber;
		int minDay=1,maxDay=31;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=sdf.parse(year+"-"+month+'-'+minDay);
		Date date2=sdf.parse(year+"-"+month+'-'+maxDay);
		List<WorkSchedule> schedules=workScheduleRepository.getWorkScheduleForCooksBetween(date1,date2,restaurant);
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

}
