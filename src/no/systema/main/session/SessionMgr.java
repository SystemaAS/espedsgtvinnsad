/**
 * 
 */
package no.systema.main.session;

import javax.servlet.http.HttpSession;
import org.slf4j.*;

import java.util.*;

/**
 * The class is used to manage session methods in a general way from different modules
 * 
 * @author oscardelatorre
 * @date Sep 5, 2013
 * 
 * 
 */
public class SessionMgr {
	private static final Logger logger = LoggerFactory.getLogger(SessionMgr.class.getName());

	/**
	 * Clears the session from attributes regarding UOPP (Ufortollede Oppdrag)
	 * @param session
	 */
	public void removeSessionAttributes_UOPP (HttpSession session){
		String PATTERN = "_UOPP";
		Enumeration records = session.getAttributeNames();
		while (records.hasMoreElements()) {
	        String attribute = (String) records.nextElement();
	        if(attribute!=null && attribute.contains(PATTERN)){
	        		session.removeAttribute(attribute);
	    	        logger.info("[INFO] removing Session attribute: " + attribute);
	        }
		}
	}
	
	/**
	 * 
	 * @param session
	 * 
	 */
	public void removeSessionAttributes_SENDLEV (HttpSession session){
		String PATTERN = "_SENDLEV";
		Enumeration records = session.getAttributeNames();
		while (records.hasMoreElements()) {
	        String attribute = (String) records.nextElement();
	        if(attribute!=null && attribute.contains(PATTERN)){
	        		session.removeAttribute(attribute);
	    	        logger.info("[INFO] removing Session attribute: " + attribute);
	        }
		}
	}
	
}
