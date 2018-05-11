package com.intelmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelmas.controller.dto.DashboardRequest;
import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.service.DashboardService;

/** Class to catch requests from FE with Dashboard operations.
 * @author Brian Mm 
 *
 */
@CrossOrigin
@RestController
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;

	
	/**
	 * @param newDashboard JSON object with data to create/update dashboard.
	 * @return MessageResponse object with success/fail message after save process.
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.POST)
	public MessageResponse saveDashboard(@RequestBody DashboardRequest newDashboard){
		
		return dashboardService.saveUpdate(newDashboard);
		
	}
	
	/**
	 * @param id of dashboard to get from DB.
	 * @return DashboardRequest object with information found in DB.
	 */
	@RequestMapping(value = "/dashboard/{id}", method = RequestMethod.GET)
	public DashboardRequest getDashboard(@PathVariable(value = "id") String id){
		
		return dashboardService.getById(id);
		
	}
	
	/**
	 * @param user id related to dashboards saved to get from DB.
	 * @return List of DashboardRequest objects with all results found in DB.
	 */
	@RequestMapping(value = "/dashboard/list/{user}", method = RequestMethod.GET)
	public List<DashboardRequest> getDashboardList(@PathVariable(value = "user") String user){
		
		return dashboardService.getListByUser(user);
		
	}
	
	/**
	 * @param dash_id to delete in DB.
	 * @return MessageResponse object with success/fail message after delete process. 
	 */
	@RequestMapping(value = "/dashboard/delete/{dash_id}", method = RequestMethod.GET)
	public MessageResponse deleteDashboard(@PathVariable(value = "dash_id") String dash_id){
		
		return dashboardService.delete(dash_id);
		
	}
}
