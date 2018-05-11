package com.intelmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.controller.dto.SoftAlarmRequest;
import com.intelmas.service.SoftAlarmsService;

/** Class to catch requests from FE with Dashboard operations.
 * @author Intelma
 *
 */
@CrossOrigin
@RestController
public class SoftAlarmsController {
	
	@Autowired
	private SoftAlarmsService softalarmService;

	/**
	 * @param newAlarm JSON object with data to create/update softalarm.
	 * @return MessageResponse object with success/fail message after save process.
	 */
	@RequestMapping(value = "/alarm", method = RequestMethod.POST)
	public MessageResponse saveAlarm(@RequestBody SoftAlarmRequest newAlarm){
		
		return softalarmService.saveUpdate(newAlarm);
		
	}
	
	/**
	 * @return List of SoftAlarmRequest objects with all results found in DB.
	 */
	@RequestMapping(value = "/alarms/list", method = RequestMethod.GET)
	public List<SoftAlarmRequest> getAlarmList(){
		
		return softalarmService.getList();
		
	}
}
