package com.intelmas.service;

import java.util.List;

import com.intelmas.controller.dto.MessageResponse;
import com.intelmas.controller.dto.NotificationRequest;


/** Interface to define methods for implementation of notifications operations.
 * @author Intelma
 *
 */
public interface NotificationsService {

	/**
	 * @param newNotification object to save/update in DB.
	 * @return MessageResponse with result success/fail of the operation
	 */
	MessageResponse saveUpdate(NotificationRequest newNotification);

	/**
	 * @return List of notifications stored in DB
	 */
	List<NotificationRequest> getNotificationsList();

}
