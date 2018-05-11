package com.intelmas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelmas.controller.dto.DashboardRequest;
import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.dto.model.DashboardEntity;
import com.intelmas.repository.cassandra.DashboardRepository;
import com.intelmas.service.DashboardService;


@Service
public class DashboardServiceImpl implements DashboardService {
	
	@Autowired
	private DashboardRepository dashboardRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Override
	public MessageResponse saveUpdate(DashboardRequest newDashboard) {
		DashboardEntity newDashIn =  new DashboardEntity();
		MessageResponse message = new MessageResponse();
		
		try {
			//Validate if dashboard request has a DB id, if true, this means already exists in DB. 
			//Otherwise creates a new one.
			if(newDashboard.getDashboard_id() != null && !newDashboard.getDashboard_id().isEmpty()){
				
				//get whole data from DB by ID provided
				newDashIn = dashboardRepository.findOne(UUID.fromString(newDashboard.getDashboard_id()));
				
				//replace values with the new ones in object.
				newDashIn.setDashboard_title(newDashboard.getDashboard_title());
				newDashIn.setDashboard_widget(newDashboard.getDashboard_widget_list());
				newDashIn.setDashboard_status(newDashboard.getDashboard_status());
				
			}else{
				
				newDashIn.setDashboard_id(UUID.randomUUID());
				newDashIn.setDashboard_title(newDashboard.getDashboard_title());
				newDashIn.setDashboard_widget(newDashboard.getDashboard_widget_list());
				newDashIn.setDashboard_status("enable");
				newDashIn.setDashboard_usr(UUID.fromString(newDashboard.getDashboard_user()));
				newDashIn.setDashboard_date(new Date());
			}
			
			dashboardRepository.save(newDashIn);
			
			message.setSuccess("Success");
			message.setMsg("OK");
		} catch (Exception e) {
			LOG.error(e.getMessage());
			message.setSuccess("Fail");
			message.setMsg("Algo salio mal.");
			message.setError(e.getMessage());
		}
		
		return message;
	}

	@Override
	public DashboardRequest getById(String id) {
		
		DashboardRequest myDashboard = new DashboardRequest();
		
		try {

			DashboardEntity dashObj = dashboardRepository.findOne(UUID.fromString(id));

			myDashboard.setDashboard_title(dashObj.getDashboard_title());
			myDashboard.setDashboard_widget_list(dashObj.getDashboard_widget());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myDashboard;
	}

	@Override
	public List<DashboardRequest> getListByUser(String user) {
		List<DashboardRequest> myDashboards = new ArrayList<DashboardRequest>();
		List<DashboardEntity> dashboards = null;
		
		try {
			//Get dashboards form DB by user id
			dashboards = dashboardRepository.getListByUser(UUID.fromString(user));
		
			//Discard elements with deleted in status row
			dashboards = dashboards.stream()
							.filter(param-> !"deleted".equals(param.getDashboard_status()))
							.collect(Collectors.toList());
			
			//Fill the data in DashboardRequest object for JSON response to FE.
			for (DashboardEntity dashboardObj : dashboards) {
				DashboardRequest dash = new DashboardRequest();
				
				dash.setDashboard_id(dashboardObj.getDashboard_id().toString());
				dash.setDashboard_title(dashboardObj.getDashboard_title());
				dash.setDashboard_status(dashboardObj.getDashboard_status());
				dash.setDashboard_user(dashboardObj.getDashboard_usr().toString());
				
				myDashboards.add(dash);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myDashboards;
	}

	@Override
	public MessageResponse delete(String dash_id) {
		MessageResponse message = new MessageResponse();
		DashboardEntity newDashIn =  new DashboardEntity();
		
		try {
			//get the data of dashboard stored in DB 
			newDashIn = dashboardRepository.findOne(UUID.fromString(dash_id));
			
			//replace in object the status value and update the row.
			newDashIn.setDashboard_status("deleted");
			
			dashboardRepository.save(newDashIn);
			
			message.setSuccess("Success");
			message.setMsg("Deleted");
		} catch (Exception e) {
			e.printStackTrace();
			message.setSuccess("Fail");
			message.setMsg("Algo salio mal.");
			message.setError(e.getMessage());
		}
		
		return message;
	}
}
