package no.systema.tvinn.sad.z.maintenance.manifest.express.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;

/**
 * TVINN Maintenance Manifest Express (Expressfortolling) Controller SADEFDEF 
 * 
 * @author oscardelatorre
 * @date Oct. 2020
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintTvinnSadManifestSadefdefController {
	private static final Logger logger = LoggerFactory.getLogger(MaintTvinnSadManifestSadefdefController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_manifest_sadefdef.do", method=RequestMethod.GET)
	public ModelAndView doSadefdef(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_manifest_sadefdef");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_manifest_sadefdef_delete.do", method={ RequestMethod.POST, RequestMethod.GET })
	public ModelAndView doDeleteSadefdef(@ModelAttribute ("record") JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_manifest_sadefdef");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String efavd = null;
		String mode = null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentEfavd")){
    				efavd = value;
    			}else if(element.startsWith("selectedStatus")){
    				mode = value;
    			}
    			
    		}
    	}
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//prepare the access CGI with RPG back-end
			 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_SADEFDEF_URL;
			 StringBuffer urlRequestParams = new StringBuffer();
			 urlRequestParams.append("user=" + appUser.getUser() + "&mode=" + mode + "&efavd=" + efavd );
			 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
			 if(jsonPayload!=null){
				
				JsonTvinnSadManifestContainer container = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				if(container!=null){
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, container.getErrMsg());
					}
				}
			 }
			
			
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_manifest_sadefdef_edit.do", method=RequestMethod.POST)
	public ModelAndView doEditSadefdef(@ModelAttribute ("record") JsonTvinnSadManifestRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_manifest_sadefdef");
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String mode = request.getParameter("updateId");
		if(StringUtils.isEmpty(mode)){ mode = "A"; }
		
		Map model = new HashMap();
		boolean validRecord = true;
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			if("A".equals(mode)){
				if(this.avdExists(appUser, recordToValidate.getEfavd())){
				   validRecord = false;	
				}
			}
				
			if(validRecord){	
				 this.adjustFieldsForUpdate(recordToValidate);
				 //prepare the access CGI with RPG back-end
				 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_SADEFDEF_URL;
				 StringBuffer urlRequestParams = new StringBuffer();
				 urlRequestParams.append("user=" + appUser.getUser() + "&mode=" + mode );
				 urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
				 
				 logger.warn("URL: " + BASE_URL);
				 logger.warn("URL PARAMS: " + urlRequestParams);
				
				 
				 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				 if(jsonPayload!=null){
					
					JsonTvinnSadManifestContainer container = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
					//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
					if(container!=null){
						if(StringUtils.isNotEmpty(container.getErrMsg())){
							model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, container.getErrMsg());
						}
					}
				 }
			} 
			//lists
			Collection list = this.populateList(appUser);
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param appUser
	 * @return
	 */
	private Collection<Object> populateList(SystemaWebUser appUser){
		Collection<Object> retval = new ArrayList<Object>();
		
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_DEFAULT_VALUES_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<Object> outputList = jsonTvinnSadManifestContainer.getList();	
			if(outputList!=null && outputList.size()>0){
				retval = outputList;
			}
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param efavd
	 * @return
	 */
	private boolean avdExists(SystemaWebUser appUser, String efavd){
		boolean retval = false;
		
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_DEFAULT_VALUES_EXPRESS_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&efavd=" + efavd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestContainer jsonTvinnSadManifestContainer = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
    		//the list will exists with match
    		if(jsonTvinnSadManifestContainer.getList()!=null && jsonTvinnSadManifestContainer.getList().size()>0){
    			logger.warn("Avd exists");
    			retval = true;
    		}
			
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(JsonTvinnSadManifestRecord recordToValidate){
		if(StringUtils.isEmpty(recordToValidate.getEfdtr())){ recordToValidate.setEfdtr("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfeta())){ recordToValidate.setEfeta("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfsjadt())){ recordToValidate.setEfsjadt("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEftsd())){ recordToValidate.setEftsd("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfdtin())){ recordToValidate.setEfdtin("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfetm())){ recordToValidate.setEfetm("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEfata())){ recordToValidate.setEfata("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEfatm())){ recordToValidate.setEfatm("0"); }
		if(StringUtils.isEmpty(recordToValidate.getEf3039e())){ recordToValidate.setEf3039e("0"); }
		
		if(StringUtils.isEmpty(recordToValidate.getEfknd())){ recordToValidate.setEfknd("0"); }
		
		
		if(!"0".equals(recordToValidate.getEfeta())){
			recordToValidate.setEfeta(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfeta()));
		}
		if(!"0".equals(recordToValidate.getEfsjadt())){
			recordToValidate.setEfsjadt(new ManifestDateManager().convertToDate_ISO(recordToValidate.getEfsjadt()));
		}
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;

	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;


}

