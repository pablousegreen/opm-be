package com.intelmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.controller.dto.NotificationRequest;
import com.intelmas.service.NotificationsService;

/** Class to catch requests from FE with Notifications operations.
 * @author Intelma
 *
 */
@CrossOrigin
@RestController
public class NotificationsController {
	
	@Autowired
	private NotificationsService notificationsService;

	/**
	 * @param newNotification JSON object with data to create/update notification.
	 * @return MessageResponse object with success/fail message after save process.
	 */
	@RequestMapping(value = "/notification", method = RequestMethod.POST)
	public MessageResponse saveNotification(@RequestBody NotificationRequest newNotification){
		
		return notificationsService.saveUpdate(newNotification);
		
	}
	
	/**
	 * @return List of NotificationRequest objects with all results found in DB.
	 */
	@RequestMapping(value = "/notifications/list", method = RequestMethod.GET)
	public List<NotificationRequest> getNotificationsList(){
		
		return notificationsService.getNotificationsList();
		
	}
}

