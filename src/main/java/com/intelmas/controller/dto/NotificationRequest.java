package com.intelmas.controller.dto;

import com.intelmas.dto.model.FTPaccess;

/** Model class for notification JSON object request
 * @author Intelma
 *
 */
public class NotificationRequest {

	private String notif_id;
	private String notif_description;
	private String notif_organisation;
	private String notif_kpi_id;
	private String notif_kpi_name;
	private String notif_treshold;
	private String notif_notification_type;
	private String notif_receiver;
	private String notif_node_name;
	private String notif_cell_name;
	private String notif_status;
	private int notif_frequency;
	private String notif_usr;
	private FTPaccess notif_ftp;
	
	public String getNotif_id() {
		return notif_id;
	}
	public void setNotif_id(String notif_id) {
		this.notif_id = notif_id;
	}
	public String getNotif_description() {
		return notif_description;
	}
	public String getNotif_organisation() {
		return notif_organisation;
	}
	public void setNotif_organisation(String notif_organisation) {
		this.notif_organisation = notif_organisation;
	}
	public void setNotif_description(String notif_description) {
		this.notif_description = notif_description;
	}
	public String getNotif_kpi_id() {
		return notif_kpi_id;
	}
	public void setNotif_kpi_id(String notif_kpi_id) {
		this.notif_kpi_id = notif_kpi_id;
	}
	public String getNotif_kpi_name() {
		return notif_kpi_name;
	}
	public void setNotif_kpi_name(String notif_kpi_name) {
		this.notif_kpi_name = notif_kpi_name;
	}
	public String getNotif_treshold() {
		return notif_treshold;
	}
	public void setNotif_treshold(String notif_treshold) {
		this.notif_treshold = notif_treshold;
	}
	public String getNotif_notification_type() {
		return notif_notification_type;
	}
	public void setNotif_notification_type(String notif_notification_type) {
		this.notif_notification_type = notif_notification_type;
	}
	public String getNotif_receiver() {
		return notif_receiver;
	}
	public void setNotif_receiver(String notif_receiver) {
		this.notif_receiver = notif_receiver;
	}
	public String getNotif_node_name() {
		return notif_node_name;
	}
	public void setNotif_node_name(String notif_node_name) {
		this.notif_node_name = notif_node_name;
	}
	public String getNotif_cell_name() {
		return notif_cell_name;
	}
	public void setNotif_cell_name(String notif_cell_name) {
		this.notif_cell_name = notif_cell_name;
	}
	public String getNotif_status() {
		return notif_status;
	}
	public void setNotif_status(String notif_status) {
		this.notif_status = notif_status;
	}
	public int getNotif_frequency() {
		return notif_frequency;
	}
	public void setNotif_frequency(int notif_frequency) {
		this.notif_frequency = notif_frequency;
	}
	public String getNotif_usr() {
		return notif_usr;
	}
	public void setNotif_usr(String notif_usr) {
		this.notif_usr = notif_usr;
	}
	public FTPaccess getNotif_ftp() {
		return notif_ftp;
	}
	public void setNotif_ftp(FTPaccess notif_ftp) {
		this.notif_ftp = notif_ftp;
	}
	
}
