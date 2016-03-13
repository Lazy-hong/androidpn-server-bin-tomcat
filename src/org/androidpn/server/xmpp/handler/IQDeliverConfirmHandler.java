package org.androidpn.server.xmpp.handler;

import org.androidpn.server.service.NotificationService;
import org.androidpn.server.service.ServiceLocator;
import org.androidpn.server.xmpp.UnauthorizedException;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.Session;
import org.dom4j.Element;
import org.xmpp.packet.IQ;
import org.xmpp.packet.PacketError;

public class IQDeliverConfirmHandler extends IQHandler {
	private static final String NAMESPACE = "androidpn:iq:delivercomfirm";
	private NotificationService notificationService;
	public IQDeliverConfirmHandler() {
		notificationService=ServiceLocator.getNotificationService();
	}
	@Override
	public IQ handleIQ(IQ packet) throws UnauthorizedException {
		  IQ reply = null;
		  ClientSession session = sessionManager.getSession(packet.getFrom());
	        if (session == null) {
	            log.error("Session not found for key " + packet.getFrom());
	            reply = IQ.createResultIQ(packet);
	            reply.setChildElement(packet.getChildElement().createCopy());
	            reply.setError(PacketError.Condition.internal_server_error);
	            return reply;
	        }
	        //判断session.getStatus()的状态是为了防止恶意请求，判断是否经过了验证
            if(session.getStatus() == Session.STATUS_AUTHENTICATED&&IQ.Type.set.equals(packet.getType())){
            	 Element query = packet.getChildElement();
            	 String uuid = query.elementText("uuid");
            	 notificationService.deleteNotificationByUUID(uuid);
            }
		return null;
	}

	@Override
	public String getNamespace() {
		// TODO Auto-generated method stub
		return NAMESPACE;
	}

}
