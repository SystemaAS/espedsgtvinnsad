package no.systema.main.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.net.URLEncoder;
//application imports
import no.systema.main.util.AppConstants;


@Controller
public class LogoutController {
	private static final Logger logger = Logger.getLogger(LogoutController.class.getName());
	
	
	/**
	 * 
	 * @param session
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="logout.do", method={RequestMethod.POST, RequestMethod.GET} )
	public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request){
		
		//String user = request.getParameter("user");
		//String pwd = request.getParameter("password");
		//String aes = request.getParameter("aes");
		
		
		if (session!=null){ 
            session.removeAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
            session.invalidate();
            logger.info("Session invalidated..." + Calendar.getInstance().getTime());       
        }
		try{
			//issue a redirect for a fresh start. POST (and not GET) should be the final version of a sendRedirect (Maybe via RedirectView)
			//response.sendRedirect("/espedsg2/logonDashboard.do?ru=" + URLEncoder.encode(user,"UTF-8") + "&dp=" + URLEncoder.encode(pwd,"UTF-8") + "&aes=" + aes);
			response.sendRedirect("/espedsg2/dashboard.do");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
    
}

