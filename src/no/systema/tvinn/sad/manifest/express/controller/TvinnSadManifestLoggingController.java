package no.systema.tvinn.sad.manifest.express.controller;

import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
 
import org.apache.logging.log4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.util.io.StackTraceUtil;
import no.systema.main.model.SystemaWebUser;


import no.systema.tvinn.sad.manifest.express.filter.SearchFilterManifestList;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestLoggingContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestLoggingRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;




/**
 * Tvinn Sad Manifest Logging Controller 
 * 
 * @author oscardelatorre
 * @date Nov 2020
 * 
 */
@Controller
public class TvinnSadManifestLoggingController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(TvinnSadManifestLoggingController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	DateTimeManager dateMgr = new DateTimeManager();
	
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	private StackTraceUtil stackTraceUtil = new StackTraceUtil();
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_logging.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doLogging(HttpSession session, HttpServletRequest request){
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_logging");
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String efuuid = request.getParameter("efuuid");
		String efsg = request.getParameter("efsg");
		String efavd = request.getParameter("efavd");
		String efpro = request.getParameter("efpro");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
			model.put("efuuid", efuuid);
			model.put("efsg", efsg);
			model.put("efavd", efavd);
			model.put("efpro", efpro);
			//set list
			this.setMainList(model, appUser, efpro);
			//put model on view
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
		}
		return successView;
	}
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 * @param pro
	 * 
	 */
	private void setMainList(Map model, SystemaWebUser appUser, String pro){
		
		try{
			String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_LOGGING_URL ;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append("&pro=" + pro);
			//Now build the URL and send to the back end via the drop down service
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.warn("SIGN BASE_URL:"  + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
			logger.warn("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonTvinnSadManifestLoggingContainer container = this.tvinnSadManifestListService.getLoggingContainer(jsonPayload);
		    Collection<JsonTvinnSadManifestLoggingRecord> mainList = new ArrayList();
			if(container!=null && container.getLogg()!=null){
				for(JsonTvinnSadManifestLoggingRecord record: container.getLogg()){
					record.setOwn_fileName(FilenameUtils.getName(record.getWurl()));
					mainList.add(record);
				}
			}
			model.put(TvinnSadConstants.DOMAIN_LIST, mainList);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_logging_renderJson.do", method={ RequestMethod.GET })
	public ModelAndView doManifestLoggingRenderJson(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doManifestLoggingRenderJson...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			String absoluteFilePath = request.getParameter("fp");
			
			if(StringUtils.isNotEmpty(absoluteFilePath)){
                
        		if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{	
        		
	                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTPLAIN);
	                response.setHeader ("Content-disposition", "filename=\"jsonPayload.txt\"");
	                
	                
	                logger.info("Start flushing file payload...");
	                //send the file output to the ServletOutputStream
	                try{
	            		payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
	                }catch (Exception e){
	            		e.printStackTrace();
	            		payloadContentFlusher.flushServletOutput(response, this.stackTraceUtil.printStackTrace(e).getBytes());
	                }
                }
            }
			//this to present the output in an independent window
            return(null);
		}
			
	}	
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	
}

