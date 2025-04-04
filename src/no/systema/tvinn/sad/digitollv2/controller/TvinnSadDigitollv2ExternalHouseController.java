package no.systema.tvinn.sad.digitollv2.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.validator.LoginValidator;
import no.systema.tvinn.sad.digitollv2.controller.service.AvdSignControllerService;
import no.systema.tvinn.sad.digitollv2.model.GenericDropDownDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmoattfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.ZadmohfRecord;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadDigitollDropDownListPopulationService;
import no.systema.tvinn.sad.digitollv2.service.ZadmohfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

@Controller
public class TvinnSadDigitollv2ExternalHouseController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2ExternalHouseController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_externalhouse.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditExternalHouse(@ModelAttribute ("record") ZadmohfRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		Collection<ZadmohfRecord> outputList = new ArrayList<ZadmohfRecord>();
		Map model = new HashMap();
		
		ModelAndView successView = new ModelAndView("tvinnsaddigitollv2_edit_externalhouse");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("action");
		String ehdkh = request.getParameter("ehdkh");
		String ehlnrt = request.getParameter("ehlnrt");
		String ehlnrm = request.getParameter("ehlnrm");
		
		//String ehlnrh = request.getParameter("ehlnrh");
		//in case the call comes from a redirect view (typically sending to the api digitoll and redirect to here) ...
		String redirect_errMsg = request.getParameter(SadDigitollConstants.REDIRECT_ERRMSG);
		if(StringUtils.isNotEmpty(redirect_errMsg)) {
			model.put("errorMessage", redirect_errMsg);
		}
		
		boolean isValidForFetch = true;
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Submit button (Update/Insert)
			/* NOT NECESSARY
			if(StringUtils.isNotEmpty(action) && action.equals("doUpdate")) {
				HouseValidator validator = new HouseValidator();
				validator.validate(recordToValidate, bindingResult);
			    //check for ERRORS
				if(bindingResult.hasErrors()){
		    		logger.error("[ERROR Validation] record does not validate)");
		    		this.setRecordAspects(appUser, recordToValidate);
		    		this.adjustSenderReceiverCommunication(recordToValidate);
		    		//now we have all aspects in record
					model.put("record", recordToValidate);
					isValidForFetch = false;
				
				}else{
			    	//adjust fields
					//TODO ? this.adjustFieldsForUpdate(appUser.getUser(), recordToValidate);
					
			    	String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(ehlnrt) && StringUtils.isNotEmpty(ehlnrm) && StringUtils.isNotEmpty(ehlnrh) ){
						mode = SadDigitollConstants.DB_MODE_UPDATE;
						
					}else {
						mode = SadDigitollConstants.DB_MODE_INSERT;
					}
					logger.info("MODE:" + mode + " before update in Controller ...");
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.houseControllerService.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
					if(dmlRetval < 0) {
						//error on update
						model.put("errorMessage", errMsg.toString());
						//put all aspects (sub-lists) only with update (not insert) error
						if(SadDigitollConstants.DB_MODE_UPDATE.equals(mode)){
							//TODO ? this.setRecordAspects(appUser, recordToValidate);
						}
						model.put("record", recordToValidate);
						isValidForFetch = false;
					}else {
						//this step is required for the FETCH-step since we want to get the newly created record for upcoming updates...
						 
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
							
							ehlnrt = String.valueOf(recordToValidate.getEhlnrt());
							ehlnrm = String.valueOf(recordToValidate.getEhlnrm());
							ehlnrh = String.valueOf(recordToValidate.getEhlnrh());
						}
						
					}
				}
			}
			*/
			
			//FETCH when applicable
			if(StringUtils.isNotEmpty(ehdkh) && isValidForFetch ){
				//get BASE URL
	    		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_EXTERNAL_HOUSECONSIGNMENT_URL;
	    		//add URL-parameters
	    		String urlRequestParams = "user=" + appUser.getUser() + "&ehdkh=" + ehdkh;
	    		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.warn("URL: " + BASE_URL);
		    	logger.warn("URL PARAMS: " + urlRequestParams);
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		
		    		ZadmohfContainer jsonContainer = this.zadmohfListService.getListContainer(jsonPayload);
		    		
		    		//----------------------------------------------------------------
					//now filter the topic list with the search filter (if applicable)
					//----------------------------------------------------------------
					outputList = jsonContainer.getList();
					if(outputList!=null){
						for(ZadmohfRecord record: outputList){
							//get
							this.setRecordAspects(appUser, record);
							//this.setTransportDto(appUser.getUser(), record);
							//this.setMasterDto(appUser.getUser(), record);
							//now we have all item lines in this house
							model.put("record", record);
							//logger.debug(record.toString());
							//Only if ERROR
							/* TODO ?
							if("M".equals(record.getEhst2())) {
								this.setLastErrorText(appUser, ehlnrt, ehlnrm, ehlnrh, model);
							}*/
						}
						//logger.debug(outputList.toString());
					}
					
		    	}
			}
			/*
			if("doCreate".equals(action)) {
				logger.info("Inside doCreate...");
				//in order to grab ehlnrt-parent
				this.setTransportDto(appUser.getUser(), recordToValidate);
				this.setMasterDto(appUser.getUser(), recordToValidate);
				//some default values
				recordToValidate.setEhdkht("N730");
				model.put("record", recordToValidate);
			}
			*/	
			//--------------------------------------
			//Final successView with domain objects
			//--------------------------------------
			//drop downs
			this.avdSignControllerService.populateAvdelningHtmlDropDownsFromJsonString(model, appUser, session);
			
			this.setDropDownService(model);
	    	
			logger.info("ehlnrt:" + ehlnrt);
			logger.info("ehlnrm:" + ehlnrm);
			
			model.put("ehlnrt", ehlnrt);
			model.put("ehlnrm", ehlnrm);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}	
		return successView;
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_delete_externalhouse_zadmoattf.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDeleteZadmoattfExternalHouse(HttpSession session, HttpServletRequest request){
		logger.info("Inside doDeleteZadmoattfExternalHouse");
		Map model = new HashMap();
		String id1 = "";
		String id2 = "";
		String id3 = "";
		String id4 = "";
		String id5 = "";
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		//logger.warn("####################################################");
    			//logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("current_id1")){
    				id1 = value;
    			}else if(element.startsWith("current_id2")){
    				id2 = value;
    			}else if(element.startsWith("current_id3")){
    				id3 = value;
    			}else if(element.startsWith("current_id4")){
    				id4 = value;
    			}else if(element.startsWith("current_id5")){
    				id5 = value;
    			}
    		}
    	}
	    String id = id1;
		String avsid = id2;
		String docref = id3;
		String dateSok = id4;
		String docname = id5;
		
		logger.info("id:" +  id + " avsid:" +  avsid + " docname:" +  docref);
		logger.debug("docname:" + docname);
	    ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_childwindow_externalhouse_attachments.do?action=doInit&date=" + dateSok + "&ctype=ehdkh&docref=" + docref);

		
	    SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//START
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		
		}else{
			//delete
			if(StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(docref) ) {
				ZadmoattfRecord recordToValidate = new ZadmoattfRecord();
				recordToValidate.setId(id);
				recordToValidate.setDocref(docref);
				String mode = "D";
			    
				logger.info("MODE:" + mode + " before update in Controller ...");
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				dmlRetval = this.deleteHouseZadmoattf(appUser.getUser(), recordToValidate, mode, errMsg);
				
				if(dmlRetval < 0) {
					//error on update
					model.put("errorMessage", errMsg.toString());
					logger.error(errMsg.toString());
				}else {
					//DELETE file from file system !!!!!!!!!!!!!!!!!!!!!
					try {
						Path p = Paths.get(docname);
                		logger.debug("Deleting..." + p.toString());
                    	Files.deleteIfExists(p);
	                } catch (Exception e) {
	                	logger.error(e.toString());
	                    e.printStackTrace();
	                }
				}
				
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    
		}
		
		return successView;
		
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_renderAttachment.do", method={ RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doRenderArchive(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doManifestRenderArchive...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			//String filePath = request.getParameter("doclnk");
			String filePath = "";
			
			Enumeration requestParameters = request.getParameterNames();
		    while (requestParameters.hasMoreElements()) {
		        String element = (String) requestParameters.nextElement();
		        String value = request.getParameter(element);
		        if (element != null && value != null) {
	        		//logger.warn("####################################################");
	    			//logger.warn("param Name : " + element + " value: " + value);
	    			if(element.startsWith("doclnk")){
	    				filePath = value;
	    			}//else if ... more here
	    		}
	    	}
			
			
			
			if(filePath!=null && !"".equals(filePath)){
				logger.info("STEP 1");
                String absoluteFilePath = filePath;
                if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderAttachmentDigitollExternalHousesUseCase(absoluteFilePath)){
                	logger.info("STEP 2: Invalid ?");
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
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int deleteHouseZadmoattf(String applicationUser, ZadmoattfRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_EXTERNAL_ATTACHMENTS_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((recordToValidate)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		GeneralUpdateContainer container = this.generalUpdateService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<GeneralUpdateRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(GeneralUpdateRecord record : outputList ){
					logger.info(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}
				}
			}
    	}
    	
    	return retval;
	}

	
	private void setRecordAspects(SystemaWebUser appUser, ZadmohfRecord record) {
		this.adjustFieldsForFetch(record);
		//get all items
		//this.getItemLines(appUser, record);
	}
	
	
	private void adjustFieldsForFetch(ZadmohfRecord recordToValidate){
		//Sent date
		if(recordToValidate.getEhdts()!=null && recordToValidate.getEhdts() > 0) {
			String tmpEmdtin = String.valueOf(recordToValidate.getEhdts());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(tmpEmdtin) && tmpEmdtin.length()==8) {
				int isoEhdts = Integer.parseInt(this.dateMgr.getDateFormatted_NO(tmpEmdtin, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEhdts(isoEhdts);
			}
		}
		//Decl.date
		if(recordToValidate.getEh0068a()!=null && recordToValidate.getEh0068a() > 0) {
			
			String isoDeclDate = String.valueOf(recordToValidate.getEh0068a());
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(isoDeclDate) && isoDeclDate.length()==8) {
				int declDate = Integer.parseInt(this.dateMgr.getDateFormatted_NO(isoDeclDate, DateTimeManager.ISO_FORMAT));
				recordToValidate.setEh0068a(declDate);
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpns())) {
			if("NO".equals(recordToValidate.getEhlks())) {
				recordToValidate.setEhpns(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpns()),4,"0"));
			}
		}
		//postnr norsk alltid 4-siffror
		if(StringUtils.isNotEmpty(recordToValidate.getEhpnm())) {
			if("NO".equals(recordToValidate.getEhlkm())) {
				recordToValidate.setEhpnm(StringUtils.leftPad(String.valueOf(recordToValidate.getEhpnm()),4,"0"));
			}
		}
	}
	
	
	/**
	 * 
	 * @param model
	 */
	private void setDropDownService(Map model) {
		//previous docs
		List<GenericDropDownDto> dto = this.digitollDropDownListPopulationService.getPreviousDocumentsList(); model.put("previousDocumentsDto", dto);
		//country
		dto = this.digitollDropDownListPopulationService.getCountryList(); model.put("countryDto", dto);
		//export types
		dto = this.digitollDropDownListPopulationService.getExportTypesDto(); model.put("exportTypesDto", dto);
	}
	
	
	@Autowired
	private SadDigitollDropDownListPopulationService digitollDropDownListPopulationService;
	
	@Autowired
	private AvdSignControllerService avdSignControllerService;
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private ZadmohfListService zadmohfListService;
	@Autowired
	private GeneralUpdateService generalUpdateService;
}
