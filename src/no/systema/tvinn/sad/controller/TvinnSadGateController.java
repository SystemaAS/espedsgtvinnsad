package no.systema.tvinn.sad.controller;

import java.net.InetAddress;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationContainer;
import no.systema.tvinn.sad.model.jsonjackson.authorization.JsonTvinnSadAuthorizationRecord;
import no.systema.tvinn.sad.service.TvinnSadAuthorizationService;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
//models


/**
 * Gateway to the TVINN-Sad Application
 * 
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 	
 */

@Controller
public class TvinnSadGateController {
	private static final Logger logger = Logger.getLogger(TvinnSadGateController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="tvinnsadgate.do", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView tvinnsadgate(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadgate");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu("INIT");
			logger.info("Inside method: tvinnsadgate");
			logger.info("appUser user:" + appUser.getUser());
			logger.info("appUser lang:" + appUser.getUsrLang());
			logger.info("appUser userAS400:" + appUser.getUserAS400());
			
			String authorizationOn = appUser.getAuthorizedTvinnSadUserAS400();
			String formSubmit = request.getParameter("formSubmit");
			
			if(authorizationOn!=null && !"".equals(authorizationOn)){
				//nothing since user has previously been granted permission.
			}else{
				if(formSubmit!=null && "Y".equals(formSubmit)){
					String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_GET_AUTHORIZATION_CODE;
					//url params
					String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser);
					//for debug purposes in GUI
					session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
					
					logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.info("URL: " + BASE_URL);
				    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				    	JsonTvinnSadAuthorizationContainer authorizationContainer = null;
				    	if(jsonPayload!=null){
				    		try{
				    			authorizationContainer = this.tvinnSadAuthorizationService.getContainer(jsonPayload);
				    			if(authorizationContainer.getErrMsg()!=null && !"".equals(authorizationContainer.getErrMsg())){
				    				session.setAttribute(AppConstants.ASPECT_ERROR_MESSAGE, authorizationContainer.getErrMsg());
					    			session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
					    							    				
				    			}else{
				    				//get now the authorization flags for both: TVINN-sign and OK flag = Y
				    				this.updateAppUser(authorizationContainer, appUser);
				    				//session updates
				    				session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
				    				session.removeAttribute(AppConstants.ASPECT_ERROR_MESSAGE);
				    				//debug
				    				logger.info("[After returning AS400]sign: " + appUser.getTvinnSadSign());
				    			}
				    		}catch(Exception e){
				    			e.printStackTrace();
				    		}
				    		
				    	}
				    	
					//Debug --> 
				    	logger.info(" --> jsonPayload:" + jsonPayload);
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	
				}
				
			}

	    	logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    session.setAttribute(AppConstants.ACTIVE_URL_RPG, AppConstants.ACTIVE_URL_RPG_INITVALUE);
		    
			return successView;
			
		}
	}

	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getUnauthorizedTdsErrorMessage(SystemaWebUser appUser){
		
		StringBuffer sb = new StringBuffer();
		sb.append("Brugeren: " + appUser.getUserAS400() + " har ikke brugertilladelse for TVINN./n");
		sb.append("Kontakt din systemadministrator.");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "usrAS400=" + appUser.getUserAS400());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwAS400=" + appUser.getPwAS400());
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param container
	 * @param appUser
	 */
	private void updateAppUser(JsonTvinnSadAuthorizationContainer container, SystemaWebUser appUser){
		for(JsonTvinnSadAuthorizationRecord record : container.getSadtillatelse()){
			appUser.setAuthorizedTvinnSadUserAS400(record.getOk());
			appUser.setTvinnSadSign(record.getSign());
				
		}
	}
	/**
	 * TEST METHOD
	 * @param container
	 * @param appUser
	 */
	private void updateAppUserFejk(JsonTvinnSadAuthorizationContainer container, SystemaWebUser appUser){
		appUser.setAuthorizedTvinnSadUserAS400("Y");
		appUser.setTvinnSadSign("OT");
		
	}
	
	
	//Wired - SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("tvinnSadAuthorizationService")
	private TvinnSadAuthorizationService tvinnSadAuthorizationService;
	@Autowired
	@Required
	public void setTvinnSadAuthorizationService (TvinnSadAuthorizationService value) { this.tvinnSadAuthorizationService = value; }
	public TvinnSadAuthorizationService getTvinnSadAuthorizationService(){ return this.tvinnSadAuthorizationService; }
		
}

