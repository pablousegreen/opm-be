package com.intelmas.service;

import java.util.List;

import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.controller.dto.SoftAlarmRequest;


public interface SoftAlarmsService {

	/**
	 * @param newAlarm object to save/update in DB
	 * @return MessageResponse with result success/fail of the operation
	 */
	MessageResponse saveUpdate(SoftAlarmRequest newAlarm);

	/**
	 * @return List of softalarms stored in DB
	 */
	List<SoftAlarmRequest> getList();

}
