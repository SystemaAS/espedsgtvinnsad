package no.systema.tvinn.sad.kundekontroll.brreg.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 28, 2016
 * 
 */
@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class LogoutBrregKontrollController {
	private static final Logger logger = LogManager.getLogger(LogoutBrregKontrollController.class.getName());
	private ModelAndView successView = new ModelAndView("dashboard");
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	@RequestMapping(value="logoutBrregKontroll.do", method=RequestMethod.GET)
	public ModelAndView logoutSporringOppdrag(HttpSession session, HttpServletResponse response){
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView view = null;
		
		if(appUser==null){
			 view = this.loginView;
		}else{
			logger.info("Logging out from Systema Kundedata kontroll ...");
			session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
			view = this.successView;
		}
		return view;
	}
}

