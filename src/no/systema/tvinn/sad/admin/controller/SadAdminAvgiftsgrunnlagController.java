package no.systema.tvinn.sad.admin.controller;

import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;

import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.io.PayloadContentFlusher;

import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.admin.filter.SearchFilterSadAdminAvggrunnlag;


import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;

//
import no.systema.tvinn.sad.admin.url.store.SadAdminUrlDataStore;
import no.systema.tvinn.sad.admin.service.SadAdminAvggrunnlagService;
import no.systema.tvinn.sad.admin.model.jsonjackson.topic.JsonSadAdminAvggrunnlagListContainer;
import no.systema.tvinn.sad.admin.validator.SadAdminAvggrunnlagListValidator;


/**
 * 
 * Avg.grunnlag Controller 
 * 
 * @author oscardelatorre
 * @date Sep 14, 2016
 * 
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SadAdminAvgiftsgrunnlagController {
	
	private static final Logger logger = Logger.getLogger(SadAdminAvgiftsgrunnlagController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	/**
	 * This link is accessed from within the TVINN ADMIN menu
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_avggrunnlag.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doAvggrunnlag(@ModelAttribute ("record") SearchFilterSadAdminAvggrunnlag searchFilter, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadadmin_avggrunnlag");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String action = request.getParameter("action");
		
		//logger.info(searchFilter.getFromDate());
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_ADMIN);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//init the rest
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			successView.addObject(TvinnSadConstants.DOMAIN_LIST,new ArrayList());
			
	    	return successView;
		}
	}
	/**
	 * This is targeted to external customers (our customer's customer). 
	 * This link is accessed from within the DASHBOARD 
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_avggrunnlag_external.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doAvggrunnlagExternal(@ModelAttribute ("record") SearchFilterSadAdminAvggrunnlag searchFilter, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadadmin_avggrunnlag_external");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_AVGGRUNNLAG_EXTERNAL);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
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
	
	@RequestMapping(value="tvinnsadadmin_avggrunnlag.do", params="action=doCalculate",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCalculate(@ModelAttribute ("record") SearchFilterSadAdminAvggrunnlag recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tvinnsadadmin_avggrunnlag");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			SadAdminAvggrunnlagListValidator validator = new SadAdminAvggrunnlagListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		    	successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				
		    	outputList = this.getList(appUser, recordToValidate);

				//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				model.put(TvinnSadConstants.DOMAIN_LIST,outputList);
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject("searchFilter", recordToValidate);
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");			    	
				return successView;
			
		    }
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
	@RequestMapping(value="tvinnsadadmin_avggrunnlag_external.do", params="action=doCalculate",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCalculateExternal(@ModelAttribute ("record") SearchFilterSadAdminAvggrunnlag recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		
		ModelAndView successView = new ModelAndView("tvinnsadadmin_avggrunnlag_external");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			//-----------
			//Validation
			//-----------
			SadAdminAvggrunnlagListValidator validator = new SadAdminAvggrunnlagListValidator();
			logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
		    validator.validate(recordToValidate, bindingResult);
		    
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		    	successView.addObject(TvinnSadConstants.DOMAIN_LIST, new ArrayList());
				successView.addObject("searchFilter", recordToValidate);
				return successView;
	    		
		    }else{
				
		    	outputList = this.getList(appUser, recordToValidate);

				//--------------------------------------
				//Final successView with domain objects
				//--------------------------------------
				model.put(TvinnSadConstants.DOMAIN_LIST,outputList);
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    		//domain and search filter
				successView.addObject("searchFilter", recordToValidate);
				logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");			    	
				return successView;
			
		    }
		}
		
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="tvinnsadadmin_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
				
                String absoluteFilePath = filePath;
                
                //must know the file type in order to put the correct content type on the Servlet response.
                String fileType = this.payloadContentFlusher.getFileType(filePath);
                if(AppConstants.DOCUMENTTYPE_PDF.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_PDF);
                }else if(AppConstants.DOCUMENTTYPE_TIFF.equals(fileType) || AppConstants.DOCUMENTTYPE_TIF.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TIFF);
                }else if(AppConstants.DOCUMENTTYPE_JPEG.equals(fileType) || AppConstants.DOCUMENTTYPE_JPG.equals(fileType)){
                		response.setContentType(AppConstants.HTML_CONTENTTYPE_JPEG);
                }else if(AppConstants.DOCUMENTTYPE_TXT.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
                }else if(AppConstants.DOCUMENTTYPE_DOC.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_WORD);
                }else if(AppConstants.DOCUMENTTYPE_XLS.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
                }else if(AppConstants.DOCUMENTTYPE_CSV.equals(fileType)){
            			response.setContentType(AppConstants.HTML_CONTENTTYPE_EXCEL);
                }
                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
                response.setHeader ("Content-disposition", "filename=\"archiveDocument." + fileType + "\"");
                
                logger.info("Start flushing file payload...");
                //send the file output to the ServletOutputStream
                try{
                		this.payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
                		//payloadContentFlusher.flushServletOutput(response, "plain text test...".getBytes());
                	
                }catch (Exception e){
                		e.printStackTrace();
                }
            }
			//this to present the output in an independent window
            return(null);
		}	
	}	


	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(SearchFilterSadAdminAvggrunnlag searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		//Adjust dates NO to ISO
		searchFilter.setFromDate(this.dateTimeMgr.getDateFormatted_ISO(searchFilter.getFromDate(), "ddMMyy"));
		searchFilter.setToDate(this.dateTimeMgr.getDateFormatted_ISO(searchFilter.getToDate(), "ddMMyy"));
		
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvggCustomerId()!=null && !"".equals(searchFilter.getAvggCustomerId())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "siknk=" + searchFilter.getAvggCustomerId());
		}
		if(searchFilter.getFromDate()!=null && !"".equals(searchFilter.getFromDate())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datof=" + searchFilter.getFromDate());
		}
		if(searchFilter.getToDate()!=null && !"".equals(searchFilter.getToDate())){
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datot=" + searchFilter.getToDate());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @return
	 */
	private List getList(SystemaWebUser appUser, SearchFilterSadAdminAvggrunnlag recordToValidate){
		Collection list = new ArrayList();
        //get BASE URL
		final String BASE_URL = SadAdminUrlDataStore.TVINN_SAD_ADMIN_BASE_AVGGRUNNLAG_LIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(recordToValidate, appUser);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
	    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

		//Debug --> 
		logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadAdminAvggrunnlagListContainer jsonSadAdminAvggrunnlagListContainer = this.sadAdminAvggrunnlagService.getSadAdminAvggrunnlagListContainer(jsonPayload);
			if(jsonSadAdminAvggrunnlagListContainer!=null && jsonSadAdminAvggrunnlagListContainer.getOrderMVAreport()!=null){
				list = jsonSadAdminAvggrunnlagListContainer.getOrderMVAreport();
			}
    	}
    	return (List)list;
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
	
	
	@Qualifier ("sadAdminAvggrunnlagService")
	private SadAdminAvggrunnlagService sadAdminAvggrunnlagService;
	@Autowired
	@Required
	public void setSadAdminAvggrunnlagService (SadAdminAvggrunnlagService value){ this.sadAdminAvggrunnlagService = value; }
	public SadAdminAvggrunnlagService getSadAdminAvggrunnlagService(){ return this.sadAdminAvggrunnlagService; }
	
	
}

