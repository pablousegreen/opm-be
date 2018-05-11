package com.intelmas.dto.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/** Entity class of dashboard_user table on DB.
 * @author Intelma
 *
 */
@Table(value="dashboard_user")
public class DashboardEntity {

	@PrimaryKey
	private UUID dashboard_id;
	
	@Column(value="dashboard_title")
	private String dashboard_title;
	
//	@Column(value="dashboard_widget")
//	private Map<String,String> dashboard_widget;
	
	@Column(value="dashboard_widget")
	private String dashboard_widget;
	
	@Column(value="dashboard_status")
	private String dashboard_status;
	
	@Column(value="dashboard_usr")
	private UUID dashboard_usr;
	
	@Column(value="dashboard_date")
	private Date dashboard_date;

	public UUID getDashboard_id() {
		return dashboard_id;
	}

	public void setDashboard_id(UUID dashboard_id) {
		this.dashboard_id = dashboard_id;
	}

	public String getDashboard_title() {
		return dashboard_title;
	}

	public void setDashboard_title(String dashboard_title) {
		this.dashboard_title = dashboard_title;
	}

//	public Map<String, String> getDashboard_widget() {
//		return dashboard_widget;
//	}
//
//	public void setDashboard_widget(Map<String, String> dashboard_widget) {
//		this.dashboard_widget = dashboard_widget;
//	}
	
	

	public String getDashboard_status() {
		return dashboard_status;
	}

	public String getDashboard_widget() {
		return dashboard_widget;
	}

	public void setDashboard_widget(String dashboard_widget) {
		this.dashboard_widget = dashboard_widget;
	}

	public UUID getDashboard_usr() {
		return dashboard_usr;
	}

	public void setDashboard_usr(UUID dashboard_usr) {
		this.dashboard_usr = dashboard_usr;
	}

	public void setDashboard_status(String dashboard_status) {
		this.dashboard_status = dashboard_status;
	}

	public Date getDashboard_date() {
		return dashboard_date;
	}

	public void setDashboard_date(Date dashboard_date) {
		this.dashboard_date = dashboard_date;
	}
}
