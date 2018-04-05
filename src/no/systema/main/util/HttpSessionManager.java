package no.systema.main.util;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;

import javawebparts.session.SessionSize;
/**
 * 
 * @author oscardelatorre
 * @date Apr 20, 2016
 * 
 */
public class HttpSessionManager implements HttpSessionAttributeListener {
	private static final Logger logger = Logger.getLogger(HttpSessionManager.class.getName());
	
	public void attributeAdded(HttpSessionBindingEvent evt){
		logSessionSize(evt);
	}
	public void attributeReplaced(HttpSessionBindingEvent evt){
		logSessionSize(evt);
	}
	public void attributeRemoved(HttpSessionBindingEvent evt){
		logSessionSize(evt);
	}
	
	private void logSessionSize(HttpSessionBindingEvent evt) {
		double sizeKiloBytes = getSessionKiloBytes(evt);
		String sessionId = evt.getSession().getId();
		logger.info("SessionId:" + sessionId + "," + sizeKiloBytes);
	}
	
	private double getSessionKiloBytes(HttpSessionBindingEvent evt){
		SessionSize sessionSize = new SessionSize(evt.getSession());
		return (double) sessionSize.getSessionSize() / 100;
	}
	/*
	public double getSessionKiloBytes(HttpSession session){
		SessionSize sessionSize = new SessionSize(session);
		logger.info("Session size (raw):" + sessionSize.getSessionSize());
		return (double) sessionSize.getSessionSize() / 100;
		
	}*/


}
	
