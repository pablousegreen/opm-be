package com.intelmas.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.controller.dto.SoftAlarmRequest;
import com.intelmas.dto.model.FTPaccess;
import com.intelmas.dto.model.SoftAlarmsEntity;
import com.intelmas.repository.cassandra.SoftAlarmsRepository;
import com.intelmas.service.SoftAlarmsService;
import com.intelmas.utils.TimeManager;

@Service
public class SoftAlarmsServiceImpl implements SoftAlarmsService {
	
	@Autowired
	private SoftAlarmsRepository softalarmsRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(SoftAlarmsServiceImpl.class);

	@Override
	public MessageResponse saveUpdate(SoftAlarmRequest newAlarm) {
		SoftAlarmsEntity newAlarmIn = new SoftAlarmsEntity();
		MessageResponse message = new MessageResponse();
		
		try {
			if (newAlarm.getAlarm_id() != null && !newAlarm.getAlarm_id().isEmpty()) {
				
				newAlarmIn = softalarmsRepository.findOne(UUID.fromString(newAlarm.getAlarm_id()));
				
				FTPaccess ftp = new FTPaccess();

				if (newAlarm.getAlarm_ftp() != null) {

					ftp.setUser(newAlarm.getAlarm_ftp().getUser());
					ftp.setPass(newAlarm.getAlarm_ftp().getPass());
					ftp.setIp(newAlarm.getAlarm_ftp().getIp());
					ftp.setPort(newAlarm.getAlarm_ftp().getPort());
					ftp.setPath(newAlarm.getAlarm_ftp().getPath());
				}
				
				newAlarmIn.setAlarm_oss(newAlarm.getAlarm_oss());
				newAlarmIn.setAlarm_organisation(newAlarm.getAlarm_organisation());
				newAlarmIn.setAlarm_resolution(newAlarm.getAlarm_resolution());
				newAlarmIn.setAlarm_ftp(newAlarm.getAlarm_ftp());
				newAlarmIn.setAlarm_kpi_id(newAlarm.getAlarm_kpi_id());
				newAlarmIn.setAlarm_status(newAlarm.getAlarm_status());

			} else {
				newAlarmIn.setAlarm_id(UUID.randomUUID());
				//newAlarmIn.setAlarm_oss(newAlarm.getAlarm_oss_id());
				newAlarmIn.setAlarm_oss(newAlarm.getAlarm_oss());
				newAlarmIn.setAlarm_organisation(newAlarm.getAlarm_organisation());
				newAlarmIn.setAlarm_resolution(newAlarm.getAlarm_resolution());
				newAlarmIn.setAlarm_ftp(newAlarm.getAlarm_ftp());
				newAlarmIn.setAlarm_kpi_id(newAlarm.getAlarm_kpi_id());
				newAlarmIn.setAlarm_create_date(LocalDateTime.now());
				newAlarmIn.setAlarm_status("enable");
			}
			
			softalarmsRepository.save(newAlarmIn);
			
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
	public List<SoftAlarmRequest> getList() {
		List<SoftAlarmsEntity> alarmsList = new ArrayList<SoftAlarmsEntity>();
		List<SoftAlarmRequest> alarmsListResp = new ArrayList<SoftAlarmRequest>();
		
		try {
			softalarmsRepository.findAll().forEach(alarmsList::add);
			
			alarmsList = alarmsList.stream()
							.filter(param-> !"deleted".equals(param.getAlarm_status()))
							.collect(Collectors.toList());
			
			for (SoftAlarmsEntity alarm : alarmsList) {
				SoftAlarmRequest alarmResp = new SoftAlarmRequest();
				
				alarmResp.setAlarm_id(alarm.getAlarm_id().toString());
				alarmResp.setAlarm_oss(alarm.getAlarm_oss());
				alarmResp.setAlarm_organisation(alarm.getAlarm_organisation());
				alarmResp.setAlarm_resolution(alarm.getAlarm_resolution());
				alarmResp.setAlarm_ftp(alarm.getAlarm_ftp());
				alarmResp.setAlarm_kpi_id(alarm.getAlarm_kpi_id().toString());
				alarmResp.setAlarm_status(alarm.getAlarm_status());
				alarmResp.setAlarm_create_date(alarm.getAlarm_create_date().format(TimeManager.dateformat));
				
				alarmsListResp.add(alarmResp);
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return alarmsListResp;
	}

}
