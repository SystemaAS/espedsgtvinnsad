package no.systema.main.controller;

import org.springframework.web.servlet.ModelAndView;

import no.systema.main.util.AppConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;
import org.springframework.context.annotation.Scope;
import org.apache.log4j.Logger;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;



@Controller
/*@SessionAttributes(Constants.APP_USER_KEY)
@Scope("session")*/
public class LoginController2 {
	private static final Logger logger = Logger.getLogger(LoginController2.class.getName());
	
	private ModelAndView loginView = new ModelAndView("login");

	/**
	 * This RequestMapping was originally called login.do but there will
	 * be no need to use this method in case of the absence of the appUser in the sub-module's session.
	 * 
	 * The absence of the user in session will cause a default to a login.do not existent giving an error to he end-user. 
	 * We will handle this later on ... Right now it is imortant to disconnect this RequestMapping since no sub-modules should default to the 
	 * login form. Only the espedsg2 (dashboard servlet)
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("loginOBSOLETE.do")
	public ModelAndView login(Model model){
		logger.info("Before login controller execution");
		
		String message = "Welcome till Systema eSped";
		model.addAttribute("messageTag", message);
		//This SystemaWebUser instance is just to comply to the dynamic css property that MUST be in place in the JSP-Login window BEFORE the login
		//NOTE: The real SystemaWebUser is set in the Dashboard controller after the approval of the login
		SystemaWebUser userForCssPurposes = new SystemaWebUser();
		
		//Override default
		userForCssPurposes.setCssEspedsg(AppConstants.CSS_ESPEDSG);
		if(userForCssPurposes.getCssEspedsg().toLowerCase().contains("toten")){
			//Override default
			userForCssPurposes.setEspedsgLoginTitle("Toten Transport AS – EspedSG");
		}
		
		model.addAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, userForCssPurposes);
		loginView.addObject("model",model);
		//
		logger.info("After login controller execution");
		
		return this.loginView;
	}
    
}

