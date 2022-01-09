package no.systema.tvinn.sad.admin.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.validator.UserValidator;
import no.systema.tror.model.JsonTrorOrderListContainer;
import no.systema.tror.model.JsonTrorOrderListRecord;
import no.systema.tror.service.TrorMainOrderListService;
import no.systema.tror.url.store.TrorUrlDataStore;
import no.systema.tror.util.TrorConstants;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.admin.filter.SearchFilterSadAdminTransportList;
import no.systema.tvinn.sad.admin.validator.SadAdminTransportListValidator;
import no.systema.tvinn.sad.admin.url.store.SadAdminUrlDataStore;

import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadAvdelningRecord;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureContainer;
import no.systema.tvinn.sad.model.jsonjackson.avdsignature.JsonTvinnSadSignatureRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.service.SadImportTopicListService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
//
import no.systema.tvinn.sad.admin.service.SadAdminTransportService;
import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminTransportListContainer;



/**
 * 
 * SAD Admin Transport Controller 
 * 
 * @author oscardelatorre
 * @date May 26, 2014
 * 
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadAdminTransportController {
	
	private static final Logger logger = LoggerFactory.getLogger(SadAdminTransportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1500);
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_transport.do", method=RequestMethod.GET)
	public ModelAndView doSkatExportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadadmin_transport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterSadAdminTransportList searchFilter = new SearchFilterSadAdminTransportList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_ADMIN);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			//lists
			this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
			this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
			//init filter with users signature (for starters)
			searchFilter.setSign(appUser.getTvinnSadSign());
			successView.addObject("searchFilter" , searchFilter);
			//init the rest
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_transport", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFind(@ModelAttribute ("record") SearchFilterSadAdminTransportList recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tvinnsadadmin_transport");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
	    	
			//-----------
			//Validation
			//-----------
			SadAdminTransportListValidator validator = new SadAdminTransportListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
		    		logger.info("[ERROR Validation] search-filter does not validate)");
		    		//put domain objects and do go back to the successView from here
		    		//drop downs
				this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
				this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		    	
				successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				//----------------------------------------------
				//get Search Filter and populate (bind) it here
				//----------------------------------------------
		    		SearchFilterSadAdminTransportList searchFilter = new SearchFilterSadAdminTransportList();
				ServletRequestDataBinder binder = new ServletRequestDataBinder(searchFilter);
	            //binder.registerCustomEditor(...); // if needed
	            binder.bind(request);
	            
	            //get BASE URL
		    		final String BASE_URL = SadAdminUrlDataStore.TVINN_SAD_ADMIN_BASE_TRANSPORT_LIST_URL;
		    		//add URL-parameters
		    		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
				session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL + "==>params: " + urlRequestParams.toString()); 
			    	logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

				//Debug --> 
				logger.info(jsonPayload);
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
			    	if(jsonPayload!=null){
			    		JsonSadAdminTransportListContainer jsonSkatAdminTransportListContainer = this.sadAdminTransportService.getSadAdminTransportListContainer(jsonPayload);
					//-----------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//-----------------------------------------------------------
					outputList = jsonSkatAdminTransportListContainer.getOrderList();

					
					//--------------------------------------
					//Final successView with domain objects
					//--------------------------------------
					//drop downs
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			    		//domain and search filter
					successView.addObject(TvinnSadConstants.DOMAIN_LIST,outputList);
					successView.addObject("searchFilter", searchFilter);
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
			    	
					return successView;
					
				}else{
					logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}
		    }
		}
		
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_toTrorList.do", method=RequestMethod.GET)
	public ModelAndView doGoToTrorList(HttpSession session, HttpServletRequest request){
		String TYPE_LAND_IMPORT = "A";
		String TYPE_LAND_EXPORT = "B";
		String TYPE_AIR_IMPORT = "C";
		String TYPE_AIR_EXPORT = "D";
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
				
		ModelAndView successView = null;//new ModelAndView("redirect:tror_mainorderlist.do?action=doFind");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		SearchFilterSadAdminTransportList searchFilter = new SearchFilterSadAdminTransportList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TROR);
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = TrorUrlDataStore.TROR_BASE_MAIN_ORDER_LIST_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			//add URL-parameters
    		StringBuffer urlRequestParams = new StringBuffer(); 
    		urlRequestParams.append("user=" + appUser.getUser());
    		urlRequestParams.append("&heavd=" + avd + "&heopd=" + opd + "&hesg=" + sign);
    		
    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	
	    	String typeOfOppdrag = ""; //default;
	    	String status = "";//default;
	    	JsonTrorOrderListContainer orderListContainer = this.trorMainOrderListService.getMainListContainer(jsonPayload);
    		if(orderListContainer!=null){
    			for(JsonTrorOrderListRecord record: orderListContainer.getDtoList()){
    				//remove invalid cases for this UCase
    				typeOfOppdrag = record.getHeur(); 
    				status = record.getHest();
    				logger.info("heur:" + typeOfOppdrag);
    				logger.info("status:X" + status + "X");
    				
    			}
	    		
    		}
	    	//redirect back to the correct Oppdrag ...
			if(TYPE_LAND_IMPORT.equals(typeOfOppdrag)){
				if("".equals(status) || "U".equals(status) || "O".equals(status) || "F".equals(status) ){
					//editable
					//successView = new ModelAndView("redirect:tror_mainorderlandimport.do?action=doFetch" + "&heavd=" + avd + "&heopd=" + opd + "&hesg=" + sign );
					
					//should be change to the above but some session variables are missing so we go to the list = entry point
					successView = new ModelAndView("redirect:tror_mainorderlist.do?action=doFind" + "&avd=" + avd + "&orderNr=" + opd + "&sign=" + sign );
				}else{
					//read only
					successView = new ModelAndView("redirect:tror_mainorderlist.do?action=doFind" + "&avd=" + avd + "&orderNr=" + opd + "&sign=" + sign );
				}
			}else{
				successView = new ModelAndView("redirect:tror_mainorderlist.do?action=doFind" + "&avd=" + avd + "&orderNr=" + opd + "&sign=" + sign );
			}

	    	return successView;
		}
	}
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String UPPDRAG_TYPE = "A"; //All avd in the company (for Transportuppdrag)
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + UPPDRAG_TYPE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadAvdelningContainer container = this.tvinnSadDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonTvinnSadAvdelningRecord> list = new ArrayList();
			for(JsonTvinnSadAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String UPPDRAG_TYPE = "F"; //TDS (TODO for Transportuppdrag ?) 
		
		try{
			String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + UPPDRAG_TYPE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadSignatureContainer container = this.tvinnSadDropDownListPopulationService.getSignatureContainer(url);
			List<JsonTvinnSadSignatureRecord> list = new ArrayList();
			for(JsonTvinnSadSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(TvinnSadConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSadAdminTransportList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("tvinnSadDropDownListPopulationService")
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	@Autowired
	public void setTvinnSadDropDownListPopulationService (TvinnSadDropDownListPopulationService value){ this.tvinnSadDropDownListPopulationService=value; }
	public TvinnSadDropDownListPopulationService getTvinnSadDropDownListPopulationService(){return this.tvinnSadDropDownListPopulationService;}
	
	@Qualifier ("sadAdminTransportService")
	private SadAdminTransportService sadAdminTransportService;
	@Autowired
	@Required
	public void setSadAdminTransportService (SadAdminTransportService value){ this.sadAdminTransportService = value; }
	public SadAdminTransportService getSadAdminTransportService(){ return this.sadAdminTransportService; }
	
	
	@Qualifier ("trorMainOrderListService")
	private TrorMainOrderListService trorMainOrderListService;
	@Autowired
	@Required
	public void setTrorMainOrderListService (TrorMainOrderListService value){ this.trorMainOrderListService = value; }
	public TrorMainOrderListService getTrorMainOrderListService(){ return this.trorMainOrderListService; }
	
	
}

