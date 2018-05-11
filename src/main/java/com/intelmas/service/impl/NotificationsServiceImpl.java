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
import com.intelmas.controller.dto.NotificationRequest;
import com.intelmas.dto.model.FTPaccess;
import com.intelmas.dto.model.NotificationsEntity;
import com.intelmas.repository.cassandra.NotificationsRepository;
import com.intelmas.service.NotificationsService;

@Service
public class NotificationsServiceImpl implements NotificationsService {

	@Autowired
	private NotificationsRepository notificationsRepository;
	
	private static final Logger LOG = LoggerFactory.getLogger(NotificationsServiceImpl.class);

	@Override
	public MessageResponse saveUpdate(NotificationRequest newNotification) {
		NotificationsEntity newNotificationIn = new NotificationsEntity();
		MessageResponse message = new MessageResponse();

		try {
			//Validate if the request contains an ID of notification
			//If true it means notification already exists
			if (newNotification.getNotif_id() != null && !newNotification.getNotif_id().isEmpty()) {

				//Get the notification by Id and create a new object
				newNotificationIn = notificationsRepository.findOne(UUID.fromString(newNotification.getNotif_id()));

				FTPaccess ftp = new FTPaccess();

				//If notification has FTP data replace the values with request values.
				//Could be the same values and this no affects the current data, just if it's different
				if (newNotification.getNotif_ftp() != null) {

					ftp.setUser(newNotification.getNotif_ftp().getUser());
					ftp.setPass(newNotification.getNotif_ftp().getPass());
					ftp.setIp(newNotification.getNotif_ftp().getIp());
					ftp.setPort(newNotification.getNotif_ftp().getPort());
					ftp.setPath(newNotification.getNotif_ftp().getPath());

				}

				//Replace values in object from DB with values from request
				newNotificationIn.setNotif_description(newNotification.getNotif_description());
				newNotificationIn.setNotif_kpi_id(newNotification.getNotif_kpi_id());
				newNotificationIn.setNotif_kpi_name(newNotification.getNotif_kpi_name());
				newNotificationIn.setNotif_treshold(newNotification.getNotif_treshold());
				newNotificationIn.setNotif_notification_type(newNotification.getNotif_notification_type());
				newNotificationIn.setNotif_node_name(newNotification.getNotif_node_name());
				newNotificationIn.setNotif_cell_name(newNotification.getNotif_cell_name());
				newNotificationIn.setNotif_status(newNotification.getNotif_status());
				newNotificationIn.setNotif_frequency(newNotification.getNotif_frequency());
				newNotificationIn.setNotif_status(newNotification.getNotif_status());
				newNotificationIn.setNotif_organisation(newNotification.getNotif_organisation());
				newNotificationIn.setNotif_receiver(newNotification.getNotif_receiver());
				newNotificationIn.setNotif_ftp_access(ftp);

			} else {
				//Creates a object to store new record in DB table.
				
				newNotificationIn.setNotif_id(UUID.randomUUID());
				newNotificationIn.setNotif_description(newNotification.getNotif_description());
				newNotificationIn.setNotif_organisation(newNotification.getNotif_organisation());
				newNotificationIn.setNotif_kpi_id(newNotification.getNotif_kpi_id());
				newNotificationIn.setNotif_kpi_name(newNotification.getNotif_kpi_name());
				newNotificationIn.setNotif_treshold(newNotification.getNotif_treshold());
				newNotificationIn.setNotif_notification_type(newNotification.getNotif_notification_type());
				newNotificationIn.setNotif_node_name(newNotification.getNotif_node_name());
				newNotificationIn.setNotif_cell_name(newNotification.getNotif_cell_name());
				newNotificationIn.setNotif_status(newNotification.getNotif_status());
				newNotificationIn.setNotif_frequency(newNotification.getNotif_frequency());
				newNotificationIn.setNotif_status("enable");
				newNotificationIn.setNotif_usr(newNotification.getNotif_usr());
				newNotificationIn.setNotif_receiver(newNotification.getNotif_receiver());
				newNotificationIn.setNotif_ftp_access(newNotification.getNotif_ftp());
				newNotificationIn.setNotif_create_date(LocalDateTime.now());

			}

			notificationsRepository.save(newNotificationIn);

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
	public List<NotificationRequest> getNotificationsList() {
		List<NotificationsEntity> notificationsList = new ArrayList<>();
		List<NotificationRequest> notificationsListReturn = new ArrayList<NotificationRequest>();
		
		try {
			//Get all notifications from DB
			notificationsRepository.findAll().forEach(notificationsList::add);
			
			notificationsList = notificationsList.stream()
									.filter(param-> !"deleted".equals(param.getNotif_status()))
									.collect(Collectors.toList());
			
			for (NotificationsEntity notificationObj : notificationsList) {
				NotificationRequest notif = new NotificationRequest();
				
				notif.setNotif_id(notificationObj.getNotif_id().toString());
				notif.setNotif_description(notificationObj.getNotif_description());
				notif.setNotif_organisation(notificationObj.getNotif_organisation());
				notif.setNotif_kpi_id(notificationObj.getNotif_kpi_id());
				notif.setNotif_kpi_name(notificationObj.getNotif_kpi_name());
				notif.setNotif_treshold(notificationObj.getNotif_treshold());
				notif.setNotif_notification_type(notificationObj.getNotif_notification_type());
				notif.setNotif_receiver(notificationObj.getNotif_receiver());
				notif.setNotif_node_name(notificationObj.getNotif_node_name());
				notif.setNotif_cell_name(notificationObj.getNotif_cell_name());
				notif.setNotif_status(notificationObj.getNotif_status());
				notif.setNotif_usr(notificationObj.getNotif_usr());
				notif.setNotif_ftp(notificationObj.getNotif_ftp_access());
				notif.setNotif_frequency(notificationObj.getNotif_frequency());
				
				notificationsListReturn.add(notif);
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return notificationsListReturn;
	}

}
