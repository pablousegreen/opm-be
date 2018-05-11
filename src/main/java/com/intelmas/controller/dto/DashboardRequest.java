package com.intelmas.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/** Model class of DashboardRequest for JSON objects
 * @author Brian Mm
 *
 */
@JsonInclude(Include.NON_NULL)
public class DashboardRequest {

	private String dashboard_id;

	private String dashboard_title;

	private String dashboard_user;

	private String dashboard_status;

//	private List<WidgetList> dashboard_widget_list = null;
	
	private String dashboard_widget_list;

	public String getDashboard_id() {
		return dashboard_id;
	}

	public void setDashboard_id(String dashboard_id) {
		this.dashboard_id = dashboard_id;
	}

	public String getDashboard_title() {
		return dashboard_title;
	}

	public void setDashboard_title(String dashboard_title) {
		this.dashboard_title = dashboard_title;
	}

	public String getDashboard_user() {
		return dashboard_user;
	}

	public void setDashboard_user(String dashboard_user) {
		this.dashboard_user = dashboard_user;
	}

	public String getDashboard_status() {
		return dashboard_status;
	}

	public void setDashboard_status(String dashboard_status) {
		this.dashboard_status = dashboard_status;
	}

	public String getDashboard_widget_list() {
		return dashboard_widget_list;
	}

	public void setDashboard_widget_list(String dashboard_widget_list) {
		this.dashboard_widget_list = dashboard_widget_list;
	}

	
}