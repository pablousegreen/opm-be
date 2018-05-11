package com.intelmas.service;

import java.util.List;

import com.intelmas.controller.dto.DashboardRequest;
import com.intelmas.controller.dto.MessageResponse;

public interface DashboardService {

	/**
	 * @param DashboardRequest object
	 * @return MessageResponse object with response of result process.
	 */
	MessageResponse saveUpdate(DashboardRequest newDashboard);

	/**
	 * @param ID dashboard of DB
	 * @return DashboardRequest object with the data requested by FE
	 */
	DashboardRequest getById(String id);

	
	/**
	 * @param ID user 
	 * @return DashboardRequest list with dashboards related to that user.
	 */
	List<DashboardRequest> getListByUser(String user);

	/**
	 * @param dash_id dashboard ID that user wants to delete
	 * @return MessageResponse with result succes/fail of the process.
	 */
	MessageResponse delete(String dash_id);

}
