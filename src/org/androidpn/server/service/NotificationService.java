package org.androidpn.server.service;

import java.util.List;

import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;

public interface NotificationService {
	void saveNotification(Notification notification);
	List<Notification> findNotificationsByUserName(String username);
	void deleteNotification(Notification notification);
	void deleteNotificationByUUID(String uuid);
}
