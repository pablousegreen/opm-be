package com.intelmas.dto.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/** Entity class for mapping of softalarms table in DB.
 * @author Intelma
 *
 */
@Table(value = "soft_alarms")
public class SoftAlarmsEntity {
	
	@PrimaryKey
	private UUID alarm_id;
	
	@Column(value="alarm_oss")
	private String alarm_oss;
	
	@Column(value="alarm_organisation")
	private String alarm_organisation;
	
	@Column(value="alarm_resolution")
	private String alarm_resolution;
	
	@Column(value="alarm_ftp")
	private FTPaccess alarm_ftp;
	
	@Column(value="alarm_kpi_id")
	private String alarm_kpi_id;
	
	@Column(value="alarm_status")
	private String alarm_status;
	
	@Column(value="alarm_create_date")
	private LocalDateTime alarm_create_date;

	public UUID getAlarm_id() {
		return alarm_id;
	}

	public void setAlarm_id(UUID alarm_id) {
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

	public FTPaccess getAlarm_ftp() {
		return alarm_ftp;
	}

	public void setAlarm_ftp(FTPaccess alarm_ftp) {
		this.alarm_ftp = alarm_ftp;
	}

	public String getAlarm_kpi_id() {
		return alarm_kpi_id;
	}

	public void setAlarm_kpi_id(String alarm_kpi_id) {
		this.alarm_kpi_id = alarm_kpi_id;
	}

	public LocalDateTime getAlarm_create_date() {
		return alarm_create_date;
	}

	public void setAlarm_create_date(LocalDateTime alarm_create_date) {
		this.alarm_create_date = alarm_create_date;
	}

	public String getAlarm_status() {
		return alarm_status;
	}

	public void setAlarm_status(String alarm_status) {
		this.alarm_status = alarm_status;
	}
	
}
