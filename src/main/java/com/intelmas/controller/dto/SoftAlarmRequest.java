package com.intelmas.controller.dto;

import com.intelmas.dto.model.FTPaccess;

/** Model class for softalarm JSON object request
 * @author Intelma
 *
 */
public class SoftAlarmRequest {

	private String alarm_id;
	private String alarm_oss;
	private String alarm_organisation;
	private String alarm_resolution;
	private String alarm_kpi_id;
	private String alarm_status;
	private String alarm_create_date;
	private FTPaccess alarm_ftp;
	
	public String getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}
	public String getAlarm_oss() {
		return alarm_oss;
	}
	public void setAlarm_oss(String alarm_oss) {
		this.alarm_oss = alarm_oss;
	}
	public String getAlarm_organisation() {
		return alarm_organisation;
	}
	public void setAlarm_organisation(String alarm_organisation) {
		this.alarm_organisation = alarm_organisation;
	}
	public String getAlarm_resolution() {
		return alarm_resolution;
	}
	public void setAlarm_resolution(String alarm_resolution) {
		this.alarm_resolution = alarm_resolution;
	}
	public String getAlarm_kpi_id() {
		return alarm_kpi_id;
	}
	public void setAlarm_kpi_id(String alarm_kpi_id) {
		this.alarm_kpi_id = alarm_kpi_id;
	}
	public String getAlarm_status() {
		return alarm_status;
	}
	public void setAlarm_status(String alarm_status) {
		this.alarm_status = alarm_status;
	}
	public String getAlarm_create_date() {
		return alarm_create_date;
	}
	public void setAlarm_create_date(String alarm_create_date) {
		this.alarm_create_date = alarm_create_date;
	}
	public FTPaccess getAlarm_ftp() {
		return alarm_ftp;
	}
	public void setAlarm_ftp(FTPaccess alarm_ftp) {
		this.alarm_ftp = alarm_ftp;
	}
}
