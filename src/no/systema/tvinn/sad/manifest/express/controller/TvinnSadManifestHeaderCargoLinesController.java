package no.systema.tvinn.sad.manifest.express.controller;

import java.util.*;
import java.util.regex.*;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.ModelMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.jservices.common.values.FasteKoder;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.StringManager;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.z.main.maintenance.service.MaintMainKofastService;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRpgContainer;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.express.validator.TvinnSadManifestHeaderCargoLinesValidator;
import no.systema.tvinn.sad.manifest.express.validator.TvinnSadManifestHeaderValidator;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
/**
 * Sad Manifest Header Items Controller
 * 
 * @author oscardelatorre
 * @date Sep 2018
 * 
 */

@Controller
public class TvinnSadManifestHeaderCargoLinesController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(3000);
	private static Logger logger = Logger.getLogger(TvinnSadManifestHeaderCargoLinesController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private LoginValidator loginValidator = new LoginValidator();
	private StringManager strMgr = new StringManager();
	DateTimeManager dateMgr = new DateTimeManager();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
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
	@RequestMapping(value="tvinnsadmanifest_edit_cargolines.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doManifestEditCargolines(@ModelAttribute ("record") JsonTvinnSadManifestCargoLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_edit_cargolines");
		logger.info("Inside: doManifestEditCargolines");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		JsonTvinnSadManifestRecord headerRecord = (JsonTvinnSadManifestRecord)session.getAttribute(TvinnSadConstants.SESSION_HEADER_RECORD_SADMANIFEST);
		
		Map model = new HashMap();
		String action = request.getParameter("action");
		String efuuid = request.getParameter("efuuid");
		String efsg = request.getParameter("efsg");
		String efavd = request.getParameter("efavd");
		//special treatment for efpro
		String efpro = request.getParameter("efpro");
		recordToValidate.setClpro(efpro);
		
		String updateFlag = request.getParameter("updateFlag");
		logger.warn("action:" + action);
		
		boolean isValidRecord = true;
		
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			logger.info("ASAVD:" + appUser.getAsavd());
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			if(TvinnSadConstants.ACTION_UPDATE.equals(action)){
				//validator
				TvinnSadManifestHeaderCargoLinesValidator validator = new TvinnSadManifestHeaderCargoLinesValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		isValidRecord = false;
		    		
			    }else{
			    	//adjust some db-fields
			    	this.adjustFieldsForUpdate(recordToValidate);
			    	
			    	//Start DML operations if applicable
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					
					if(strMgr.isNotNull( recordToValidate.getClpro()) && strMgr.isNotNull( recordToValidate.getCltdn()) ){
						logger.warn("doUpdate branch starting...");
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_UPDATE, errMsg);
							
					}else{
						logger.warn("doCreate branch starting...");
						logger.info("doCreate");
						recordToValidate.setClpro(efpro);
						recordToValidate.setClavd("0");//always 0
						recordToValidate.setCltdn("0");//always 0
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadConstants.MODE_ADD, errMsg);
					}
					logger.info(Calendar.getInstance().getTime() + " CONTROLLER end - timestamp");
					if(dmlRetval<0){
						isValidRecord = false;
						model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					}else{
						//Create OK. Prepare for upcoming Update
						//model.addAttribute("updateFlag", "1");
					}
			    }
			}
			//--------------
			//Fetch record
			//--------------
			//dropdowns
			this.setCodeDropDownMgr(appUser, model);
			
			if(StringUtils.isNotEmpty(recordToValidate.getClpro())) {
				//execute RPG first
				if(isValidRecord && this.isValidSad132RawFetch(headerRecord)){
					logger.warn("calling SAD132RAW ...");
					JsonTvinnSadManifestRpgContainer rpgContainer = this.executeRpgSad132RawForCargoLines(appUser, recordToValidate.getClpro());
					if(rpgContainer!=null){
						//check for errors
						if(StringUtils.isNotEmpty(rpgContainer.getErrMsg()) || StringUtils.isNotEmpty(rpgContainer.getErrMsgT()) ){
							//do not present in GUI only in logg
							String rpgErrorMessage = "SERVER_ERROR:" + rpgContainer.getErrMsg() + rpgContainer.getErrMsgT();
							logger.error(rpgErrorMessage);
							model.put(TvinnSadConstants.ASPECT_ERROR_MESSAGE, rpgErrorMessage);
						}
					}
				}
				//doFetch: always after RPG execution
				List<JsonTvinnSadManifestCargoLinesRecord> outputList = (List<JsonTvinnSadManifestCargoLinesRecord>)this.getList(appUser, recordToValidate.getClpro());
				model.put(TvinnSadConstants.DOMAIN_LIST, outputList );
	
				if("doUpdate".equals(action)){
					if(isValidRecord){
						//try to get the newly updated record in order to retain all values in the GUI
						for(JsonTvinnSadManifestCargoLinesRecord record : outputList){
							if (record.getClpro().equals(recordToValidate.getClpro())){
								if(record.getCltdn().equals(recordToValidate.getCltdn()) && record.getClavd().equals(recordToValidate.getClavd())){
									this.adjustFieldsForFetch(record);
									//get archive docs
									record.setGetdocs(this.manifestExpressMgr.fetchArchiveDocs(appUser.getUser(), record.getClavd(), record.getCltdn()));
									model.put(TvinnSadConstants.DOMAIN_RECORD, record);
								}
							}
						}
					}else{
						//in case of validation errors
						//this.adjustFieldsForFetch(recordToValidate);
						model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
					}
				}
				
				
			}
			
			if(action==null || "".equals(action)){ 
				action = "doUpdate";	
			}else if (action.equals(TvinnSadConstants.ACTION_CREATE)){
				action = "doUpdate";
			}
			
			model.put("action", action);
			model.put("efuuid", efuuid);
			model.put("efsg", efsg);
			model.put("efavd", efavd);
			model.put("efpro", efpro);
			model.put("updateFlag", updateFlag);
			
			
			logger.info("END of method");
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
			
			return successView;
		}
	}
	/**
	 * Checks if the back-end pgm should be called or not
	 * @param headerRecord
	 * @return
	 */
	private boolean isValidSad132RawFetch(JsonTvinnSadManifestRecord headerRecord){
		boolean retval = true;
		//These status should block the call of this back-end service pgm
		if(headerRecord.MANIFEST_INTERNAL_STATUS_DELETED.equals(headerRecord.getEfst()) || 
			headerRecord.MANIFEST_INTERNAL_STATUS_FINISHED_Z.equals(headerRecord.getEfst()) ){
			retval = false;
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param model
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_edit_cargolines_delete.do", method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doCargolinesDelete(JsonTvinnSadManifestCargoLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: doCargolinesDelete");
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		Map model = new HashMap();
		
		String clavd = null;
		String clpro = null;
		String cltdn = null;
		String euuid = null;
		String efsg = null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentClpro")){
    				clpro = value;
    			}else if(element.startsWith("currentCltdn")){
    				cltdn = value;
    			}else if(element.startsWith("currentClavd")){
    				clavd = value; 
    			}else if(element.startsWith("currentEuuid")){
    				euuid = value;
    			}else if(element.startsWith("currentEfsg")){
    				efsg = value;
    			}
    			
    		}
    	}
	    String redirect = "tvinnsadmanifest_edit_cargolines.do?action=doFetch&efpro=" + clpro + "&efsg=" + efsg + "&efavd=" + clavd + "&efuuid=" + euuid; 
	    ModelAndView successView = new ModelAndView("redirect:" + redirect);
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			
			if(strMgr.isNotNull( clpro ) && strMgr.isNotNull(cltdn) && strMgr.isNotNull(clavd)){
				recordToValidate.setClpro(clpro);
				recordToValidate.setCltdn(cltdn);
				recordToValidate.setClavd(clavd);
				int dmlRetval = 0;
				StringBuffer errMsg = new StringBuffer();
				//fetch record
				logger.warn("doRelease");
				//Update with release flag
				dmlRetval = this.releaseRecord(appUser.getUser(), recordToValidate, errMsg);
				if(dmlRetval<0){
					logger.info("ERROR on delete ... ??? check your code");
					
				}else{
					logger.info("doDelete = OK");
				}

			}

			return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_renderArchive.do", method={ RequestMethod.GET })
	public ModelAndView doManifestRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doManifestRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("doclnk");
			
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
	 * @param applicationUser
	 * @param recordToValidate
	 * @param errMsg
	 * @return
	 */
	private int releaseRecord(String applicationUser, JsonTvinnSadManifestCargoLinesRecord recordToValidate, StringBuffer errMsg){
		int retval = -1;
		String MODE_RELEASE = "R";
		String STATUS_FLAG = "S";
		
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + MODE_RELEASE + "&clst=" + STATUS_FLAG + "&clavd=" + recordToValidate.getClavd());
		urlRequestParams.append("&cltdn=" + recordToValidate.getCltdn() + "&clpro=" + recordToValidate.getClpro());
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(JsonTvinnSadManifestCargoLinesRecord record : outputList ){
					retval = 0;
					logger.info(record.toString());
				}
			}
    	}
    	
    	return retval;
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param tur
	 * @return
	 */
	private Collection<JsonTvinnSadManifestCargoLinesRecord> getList(SystemaWebUser appUser, String tur){
		Collection<JsonTvinnSadManifestCargoLinesRecord> retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&clpro=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();	
			retval = outputList;
    	}
    	return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param tur
	 * @return
	 */
	private JsonTvinnSadManifestRpgContainer executeRpgSad132RawForCargoLines(SystemaWebUser appUser, String tur){
		JsonTvinnSadManifestRpgContainer retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_EXECUTE_RPG_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&pro=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonTvinnSadManifestRpgContainer container = this.tvinnSadManifestListService.getContainerRpgResult(jsonPayload);
    		retval = container;
    	}
    	logger.warn("raw result from SAD132RAW:" + retval.toString());
    	return retval;
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param recordToValidate
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonTvinnSadManifestCargoLinesRecord recordToValidate, String mode, StringBuffer errMsg){
		int retval = -1;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
    		if(container!=null){
	    		//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
	    		Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();	
				if(outputList!=null && outputList.size()>0){
					for(JsonTvinnSadManifestCargoLinesRecord record : outputList ){
						retval = 0;
						logger.info(record.toString());
					}
				}
    		}
    	}
    	
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForUpdate(JsonTvinnSadManifestCargoLinesRecord recordToValidate){
		if(StringUtils.isEmpty(recordToValidate.getCl0068a()) || "0".equals(recordToValidate.getCl0068a())){
			//Meaning these fields have been disabled
			recordToValidate.setCl0068a("0");
			recordToValidate.setCl0068b("0");
		}else{
			recordToValidate.setCl0068a(new ManifestDateManager().convertToDate_ISO(recordToValidate.getCl0068a()));
			
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustFieldsForFetch(JsonTvinnSadManifestCargoLinesRecord recordToValidate){
		recordToValidate.setCl0068a(new ManifestDateManager().convertToDate_NO(recordToValidate.getCl0068a()));
	}
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFPR.toString(), model, urlCgiProxyService, maintMainKofastService);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.tvinnSadDropDownListPopulationService, 
																	 model,appUser,CodeDropDownMgr.CODE_2_COUNTRY, null, null);
	}
	
	//SERVICES
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private ManifestExpressMgr manifestExpressMgr;
	
	
}

