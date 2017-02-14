package com.isa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.isa.entity.WorkSchedule;
import com.isa.service.BartenderService;

@RestController
@RequestMapping("/bartenders")
public class BartenderController {

	@Autowired
	private BartenderService bartenderService;
	
	@RequestMapping(value="/getWorkSchedules",
					method=RequestMethod.GET,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public ResponseEntity<Iterable<WorkSchedule>> getWorkSchedules(){
		Iterable<WorkSchedule> schedules=bartenderService.getWorkScheduleForBartenders();
		return new ResponseEntity<Iterable<WorkSchedule>>(schedules, HttpStatus.OK);
	}
	
}
