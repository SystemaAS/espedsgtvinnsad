package no.systema.tvinn.sad.nctsimport.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveRecord;

import no.systema.tvinn.sad.nctsimport.service.SadNctsImportSpecificTopicService;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.nctsimport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.manager.ArchiveGoogleCloudManager;



/**
 * 
 * TVINN-NCTS Import Topic Archive Controller 
 * 
 * @author oscardelatorre
 * @date Mar 9, 2015
 * 
 */

@Controller
@Scope("session")
public class SadNctsImportHeaderArchiveController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SadNctsImportHeaderArchiveController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	
	
	
	/**
	 * Renders the GUI view
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_archive.do",  method= RequestMethod.GET)
	public ModelAndView doShowList(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("tvinnsadnctsimport_archive");
		logger.info("Method: doShowList [RequestMapping-->tvinnsadnctsimport_archive.do]");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		logger.info("Avd:" + avd);
		logger.info("Opd:" + opd);
		this.setDomainObjectsInView(request, model);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SadNctsImportUrlDataStore.NCTS_IMPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSadNctsImportSpecificTopicArchiveContainer container = this.sadNctsImportSpecificTopicService.getNctsImportSpecificTopicArchiveContainer(jsonPayload);
		    		//adjust to google cloud if needed
		    		container = new ArchiveGoogleCloudManager().adjustUrl(appUser, container);
		    		
		    		//add domain objects here
		    		this.setDomainObjectsInView(model, container);
		    		this.setDomainObjectsInView(request, model);
		    		
		    		successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
				successView.addObject(TvinnSadConstants.DOMAIN_LIST,container.getArchiveElements());
		    		
		    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadnctsimport_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doNctsImportRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doNctsImportRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
				
                String absoluteFilePath = filePath;
                if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{	
                
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
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSadNctsImportSpecificTopicArchiveRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @return
	 */
	private void setDomainObjectsInView(Map model, JsonSadNctsImportSpecificTopicArchiveContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSadNctsImportSpecificTopicArchiveRecord record : container.getArchiveElements()){
			model.put(TvinnSadConstants.DOMAIN_RECORD, record);
		}
		
	}
	
	/**
	 * In order to pass some crucial GET parameters comming from the specific topic selection in previous update-topic action
	 * 
	 * @param request
	 * @param model
	 *
	 */
	private void setDomainObjectsInView(HttpServletRequest request, Map model){
		//SET HEADER RECORDS  (from request)
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String godsNr = request.getParameter("godsNr");
		String mrnNr = request.getParameter("mrnNr");
		String datum = request.getParameter("datum");
		
		model.put("opd", opd);
		model.put("avd", avd);
		model.put("sign", sign);
		model.put("status", status);
		model.put("godsNr", godsNr);
		model.put("mrnNr", mrnNr);
		model.put("datum", datum);
		
	}
	
	/**
	 * 
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSadNctsImportSpecificTopicArchiveRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(TvinnSadConstants.DOMAIN_RECORD, record);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(TvinnSadConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
	}
			
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("sadNctsImportSpecificTopicService")
	private SadNctsImportSpecificTopicService sadNctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setSadNctsImportSpecificTopicService (SadNctsImportSpecificTopicService value){ this.sadNctsImportSpecificTopicService = value; }
	public SadNctsImportSpecificTopicService getSadNctsImportSpecificTopicService(){ return this.sadNctsImportSpecificTopicService; }
	
	
	
}

