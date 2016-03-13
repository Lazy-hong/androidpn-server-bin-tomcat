package org.androidpn.server.dao.hibernate;

import java.util.List;

import org.androidpn.server.dao.NotificationDao;
import org.androidpn.server.model.Notification;
import org.androidpn.server.model.User;
import org.androidpn.server.service.UserNotFoundException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NotificationDaoHibernate extends HibernateDaoSupport implements NotificationDao{

	public void saveNotification(Notification notification) {
		getHibernateTemplate().saveOrUpdate(notification);
		getHibernateTemplate().flush();
		
		
	}
	


	public void deleteNotification(Notification notification) {
		getHibernateTemplate().delete(notification);
		
	}


    @SuppressWarnings("unchecked")
	public List<Notification> findNotificationsByUserName(String username) {
		List<Notification> list =getHibernateTemplate().find("from Notification where username = ?",username);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}



	@SuppressWarnings("unchecked")
	public void deleteNotificationByUUID(String uuid) {
		List<Notification> list =getHibernateTemplate().find("from Notification where uuid = ?",uuid);
		if(list!=null&&list.size()>0){
			Notification notification=list.get(0);
			deleteNotification(notification);
		}
		
		
	}

}
