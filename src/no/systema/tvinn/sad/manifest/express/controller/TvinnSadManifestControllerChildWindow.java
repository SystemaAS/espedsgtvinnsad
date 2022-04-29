package no.systema.tvinn.sad.manifest.express.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.systema.jservices.common.values.FasteKoder;
//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestExportIdLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestExportIdLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestFileUploadValidationContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestPostalCodeContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestPostalCodeRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestChildwindowService;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadimport.service.SadImportGeneralCodesChildWindowService;

import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsContainer;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.JsonMaintSadImportKodttsRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.service.MaintSadImportKodttsService;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.z.main.maintenance.service.MaintMainKofastService;




/**
 * Manifest Express Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TvinnSadManifestControllerChildWindow {
	
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadManifestControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	//customer
	private final String DATATABLE_LIST = "list";
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	

	
	@RequestMapping(value="tvinnsadmanifest_childwindow_manifestinfo.do",  method={RequestMethod.GET} )
	public ModelAndView doManifestInfo(HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doManifestInfo");
		Map model = new HashMap();
		String id = request.getParameter("id");
		String raw = request.getParameter("raw");
		
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_manifestinfo");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			String url = TvinnSadManifestUrlDataStore.TVINN_SAD_CHILDWINDOW_MANIFEST_INFO_TOLL_URL;
			if(StringUtils.isNotEmpty(raw)){
				url = TvinnSadManifestUrlDataStore.TVINN_SAD_CHILDWINDOW_MANIFEST_RAW_INFO_TOLL_URL;
			}
			String BASE_URL = url;
    		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&id=" + id;
    		logger.info("URL: " + BASE_URL);
    		logger.info("PARAMS: " + urlRequestParamsKeys);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    		//Debug -->
	    	logger.debug(jsonPayload);
    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    		
    		model.put("content", jsonPayload);
    		
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
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
	@RequestMapping(value="tvinnsadmanifest_childwindow_postalcodes_sted2.do", params="action=doFind",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doFindPostalCodes(@ModelAttribute ("record") JsonTvinnSadManifestPostalCodeRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doFindPostalCodes");
		Collection outputList = new ArrayList();
		Map model = new HashMap();
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_postalcodes_sted2");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//to catch the sender since there could be more then one caller field
		String ctype = request.getParameter("ctype");
		model.put("ctype", ctype);
		
		if(appUser==null){
			return loginView;
			
		}else{
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_FRAKTKALKULATOR);
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			
			
		    //check for ERRORS
			if(bindingResult.hasErrors()){
	    		logger.info("[ERROR Validation] search-filter does not validate)");
	    		//put domain objects and do go back to the successView from here
	    		//this.setCodeDropDownMgr(appUser, model);
	    		model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
				successView.addObject(TvinnSadConstants.DOMAIN_MODEL, model);
				return successView;
	    		
		    }else{
				
	    		//prepare the access CGI with RPG back-end
	    		String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_CHILDWINDOW_POSTALCODE_STED2_URL;
	    		String urlRequestParamsKeys = this.getRequestUrlKeyParametersSearchChildWindow(recordToValidate, appUser);
	    		logger.info("URL: " + BASE_URL);
	    		logger.info("PARAMS: " + urlRequestParamsKeys);
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	    		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    		//Debug -->
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    
	    		if(jsonPayload!=null){
	    			JsonTvinnSadManifestPostalCodeContainer container = this.tvinnSadManifestChildwindowService.getPostalCodeListContainer(jsonPayload);
		    		if(container!=null){
		    			List<JsonTvinnSadManifestPostalCodeRecord> list = new ArrayList<JsonTvinnSadManifestPostalCodeRecord>();
		    			for(JsonTvinnSadManifestPostalCodeRecord  record : container.getDtoList()){
		    				//logger.info("ID:" + record.getVmtran());
		    				//logger.info("NAME:" + record.getVmnavn());
		    				list.add(record);
		    			}
		    			model.put("postalCodeList", list);
		    			model.put(TvinnSadConstants.DOMAIN_RECORD, recordToValidate);
		    		}
	    			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
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
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_tollstedcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitTollstedCodes(@ModelAttribute ("record") JsonTvinnSadCodeContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTollstedCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		String ktspnr = request.getParameter("ktspnr");
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_tollstedcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			List list00= this.getTollstedList(appUser.getUser(), ktspnr);
			model.put("tollstedCodeList", list00);
			
			List list = this.getCodeList(appUser, typeCode);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_released_cargolines.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitReleasedCargoLines(@ModelAttribute ("record") JsonTvinnSadManifestCargoLinesRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitReleasedCargoLines");
		Map model = new HashMap();
		
		String callerType = request.getParameter("ctype");
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_released_cargolines");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			Collection<JsonTvinnSadManifestCargoLinesRecord> list00= this.getReleasedCargoLinesList(appUser);
			model.put("releasedCargoLinesList", list00);
			model.put("callerType", callerType);
			model.put("parentClpro", recordToValidate.getClpro());
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_uploadFile.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitUploadFile(HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitUploadFile");
		Map model = new HashMap();
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_uploadfile");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			logger.warn(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}
	/**
	 * 
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_uploadFile.do", params="action=doSave", method = RequestMethod.POST)
    public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request ) {
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return "Not logged in...?";
		}else{
			logger.info("User:" + appUser.getUser());
	        if (!file.isEmpty()) {
        		String fileName = file.getOriginalFilename();
        		logger.info("FILE NAME:" + fileName);
                //validate file
        		JsonTvinnSadManifestFileUploadValidationContainer uploadValidationContainer = this.validateFileUpload(fileName, appUser);
                //if valid
                if(uploadValidationContainer!=null && "".equals(uploadValidationContainer.getErrMsg())){
	                	// TEST String rootPath = System.getProperty("catalina.home");
                		String rootPath	= uploadValidationContainer.getTmpdir();
                	    File dir = new File(rootPath);
                	    
		        	    try {
			                byte[] bytes = file.getBytes();
			                // Create the file on server
			                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
			                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			                stream.write(bytes);
			                stream.close();
			                logger.info("Server File Location=" + serverFile.getAbsolutePath());
			                //catch parameters
			                uploadValidationContainer.setWstur(request.getParameter("wstur"));
	        	    		uploadValidationContainer.setWsavd(request.getParameter("wsavd"));
	        	    		uploadValidationContainer.setWsopd(request.getParameter("wsopd"));
	        	    		uploadValidationContainer.setWstype(request.getParameter("wstype"));
	        	    		//this will check if either the wstur or wsavd/wsopd will save the upload
	        	    		uploadValidationContainer = this.saveFileUpload(uploadValidationContainer, fileName, appUser);
			                if(uploadValidationContainer!=null && uploadValidationContainer.getErrMsg()==""){
			                		String suffixMsg = "";
			                		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			                			suffixMsg = "  -->Tur:" + "["+ uploadValidationContainer.getWstur() + "]";
			                		}else{
			                			suffixMsg = "  -->Avd/Opd:" + "["+ uploadValidationContainer.getWsavd() + "/" + uploadValidationContainer.getWsopd() + "]";
			                		}
			                		return "You successfully uploaded file:" + fileName +  suffixMsg;
			                }else{
			                		return "You failed to upload [on MOVE] =" + fileName;
			                }
		        	    } catch (Exception e) {
		            		//run time upload error
		            		String absoluteFileName = rootPath + File.separator + fileName;
		            		return "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
		            }

                }else{
		        		if(uploadValidationContainer!=null){
		        			//Back-end error message output upon validation
		        			return uploadValidationContainer.getErrMsg();
		        		}else{
		        			return "NULL on upload file validation Object??";
		        		}
		        	}
	        } else {
	            return "You failed to upload an empty file.";
	        }
		}
    }
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_uploadfile_to_toll.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitArchiveDocs(HttpSession session, HttpServletRequest request){
		//this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitArchiveDocs");
		Map model = new HashMap();
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		String clrg = request.getParameter("clrg");
		String cl0068a = request.getParameter("cl0068a");
		String cl0068b = request.getParameter("cl0068b");
		
		
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_uploadfile_to_toll");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			Collection list = manifestExpressMgr.fetchArchiveDocs(appUser, wsavd, wsopd);
			model.put("list", list);
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			model.put("clrg", clrg);
			model.put("cl0068a", cl0068a);
			model.put("cl0068b", cl0068b);

			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_many_expid_per_cargoline.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doFetchManyExpId(HttpSession session, HttpServletRequest request){
		
		logger.warn("Inside: doFetchManyExpId");
		Map<String,Object> model = new HashMap();
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		String exportId = request.getParameter("wscleid");
		String exportType = request.getParameter("wscletyp");
		String clrg = request.getParameter("clrg");
		String cl0068a = request.getParameter("cl0068a");
		String cl0068b = request.getParameter("cl0068b");
		Integer newLineCounter = 1;
		//record
		JsonTvinnSadManifestExportIdLinesRecord record = new JsonTvinnSadManifestExportIdLinesRecord();
		record.setCmavd(wsavd);
		record.setCmtdn(wsopd);
		
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_many_expid_per_cargoline");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			Collection<JsonTvinnSadManifestExportIdLinesRecord> list = this.getExportIdLinesList(record, appUser, false);
			if(list!=null && list.size()>0) {
				newLineCounter = list.size();
				newLineCounter++;
			}
			model.put("list", list);
			model.put("newLineCounter", this.getNewLineCounter(list));
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			model.put("exportId", exportId);
			model.put("exportType", exportType);
			model.put("clrg", clrg);
			model.put("cl0068a", cl0068a);
			model.put("cl0068b", cl0068b);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * INSERT/UPDATE
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_many_expid_edit.do", params="action=doSave",  method={RequestMethod.POST} )
	public ModelAndView doSaveManyExpId(JsonTvinnSadManifestExportIdLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		logger.warn("Inside: doSaveManyExpId");
		Map<String,Object> model = new HashMap();
		String wsavd = request.getParameter("wsavd");
		String wsopd = request.getParameter("wsopd");
		String mode = request.getParameter("mode");
		Integer newLineCounter = 1;
		recordToValidate.setCmavd(wsavd);
		recordToValidate.setCmtdn(wsopd);
		logger.warn(recordToValidate.toString());
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_many_expid_per_cargoline");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//Update
			if( this.validForUpdateExpIds(recordToValidate) ) {
				logger.info("Before update/create/delete ...");
				this.updateExportIdLinesList(mode, recordToValidate, appUser);
				logger.info("After update/create/delete ...");
			}else {
				logger.warn("ERROR: Not valid dto for INSERT/UPDATE ... ? <check form parameters i JSP!>");
			}
			
			//Fetch list
			Collection<JsonTvinnSadManifestExportIdLinesRecord> list = this.getExportIdLinesList(recordToValidate, appUser, false);
			model.put("list", list);
			model.put("newLineCounter", this.getNewLineCounter(list));
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * DELETE
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmanifest_childwindow_many_expid_delete.do",  method={RequestMethod.POST} )
	public ModelAndView doDeleteManyExpId(JsonTvinnSadManifestExportIdLinesRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		logger.warn("Inside: doDeleteManyExpId");
		Map<String,Object> model = new HashMap();
		String wsavd = null;
		String wsopd = null;
		String cmavd = null;
		String cmtdn = null;
		String cmli = null;
		String mode = "D";
		Integer newLineCounter = 1;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);
	        if (element != null && value != null) {
        		logger.warn("####################################################");
    			logger.warn("param Name : " + element + " value: " + value);
    			if(element.startsWith("currentCmli")){
    				cmli = value;
    			}else if(element.startsWith("currentCmtdn")){
    				cmtdn = value;
    			}else if(element.startsWith("currentCmavd")){
    				cmavd = value; 
    			}else if(element.startsWith("wsavd")){
    				wsavd = value; 
    			}else if(element.startsWith("wsopd")){
    				wsopd = value; 
    			}
    		}
    	}
	    recordToValidate.setCmli(cmli);
		recordToValidate.setCmavd(cmavd);
		recordToValidate.setCmtdn(cmtdn);
		logger.warn(recordToValidate.toString());
		
		ModelAndView successView = new ModelAndView("tvinnsadmanifest_childwindow_many_expid_per_cargoline");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			//Update
			if( this.validForUpdateExpIds(recordToValidate) ) {
				logger.info("Before update/create/delete ...");
				this.updateExportIdLinesList(mode, recordToValidate, appUser);
				logger.info("After delete ...");
			}else {
				logger.warn("ERROR: Not valid dto for UPDATE/INSERT/DELETE ... ? <check form parameters i JSP!>");
			}
			
			//Fetch list
			Collection<JsonTvinnSadManifestExportIdLinesRecord> list = this.getExportIdLinesList(recordToValidate, appUser, false);
			model.put("list", list);
			model.put("newLineCounter", this.getNewLineCounter(list));
			model.put("wsavd", wsavd);
			model.put("wsopd", wsopd);
			
			
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(appUser, FasteKoder.SADEFETYPE.toString(), model, urlCgiProxyService, maintMainKofastService);
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	private Integer getNewLineCounter(Collection<JsonTvinnSadManifestExportIdLinesRecord> list) {
		Integer newLineCounter = 1;//default
		if(list!=null && list.size()>0) {
			for(JsonTvinnSadManifestExportIdLinesRecord line:list) {
				newLineCounter = Integer.parseInt(line.getCmli());
			}
			newLineCounter++;
		}
		return newLineCounter;
	}
	
	private boolean validForUpdateExpIds(JsonTvinnSadManifestExportIdLinesRecord dto) {
		boolean retval = false;
		if ( 	(StringUtils.isNotEmpty(dto.getCmavd()) && !dto.getCmavd().equals("0")) &&
				(StringUtils.isNotEmpty(dto.getCmtdn()) && !dto.getCmtdn().equals("0")) &&
				(StringUtils.isNotEmpty(dto.getCmli()) && !dto.getCmli().equals("0")) ) {
			retval = true;
		}
		
		return retval;
	}
	/**
	 * 
	 * @param uploadValidationContainer
	 * @param fileName
	 * @param appUser
	 * @return
	 */
	private JsonTvinnSadManifestFileUploadValidationContainer saveFileUpload(JsonTvinnSadManifestFileUploadValidationContainer uploadValidationContainer, String fileName, SystemaWebUser appUser){
		//prepare the access CGI with RPG back-end
		String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_MANIFEST_CHILDWINDOW_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL;
		String absoluteFileName = uploadValidationContainer.getTmpdir() + fileName;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		//Either TUR or AVD/OPD (order level)... Depending on the caller (Tur-level OR order-level)
		if(uploadValidationContainer.getWstur()!=null && !"".equals(uploadValidationContainer.getWstur())){
			urlRequestParamsKeys.append("&wstur=" + uploadValidationContainer.getWstur());
		}else{
			if(uploadValidationContainer.getWsavd()!=null && !"".equals(uploadValidationContainer.getWsavd())){
				urlRequestParamsKeys.append("&wsavd=" + uploadValidationContainer.getWsavd());
			}
			if(uploadValidationContainer.getWsopd()!=null && !"".equals(uploadValidationContainer.getWsopd())){
				urlRequestParamsKeys.append("&wsopd=" + uploadValidationContainer.getWsopd());
			}
		}
		urlRequestParamsKeys.append("&wstype=" + uploadValidationContainer.getWstype());
		urlRequestParamsKeys.append("&wsdokn=" + absoluteFileName);
		
		
		
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.tvinnSadManifestChildwindowService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	/**
	 * 
	 * @param fileName
	 * @param appUser
	 * @return
	 */
	private JsonTvinnSadManifestFileUploadValidationContainer validateFileUpload(String fileName, SystemaWebUser appUser){
		JsonTvinnSadManifestFileUploadValidationContainer uploadValidationContainer = null;
		//prepare the access CGI with RPG back-end
		String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_MANIFEST_CHILDWINDOW_UPLOAD_FILE_VALIDATION_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&wsdokn=" + fileName;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			uploadValidationContainer = this.tvinnSadManifestChildwindowService.getFileUploadValidationContainer(jsonPayload);
			logger.info(uploadValidationContainer.getErrMsg());
		}
		return uploadValidationContainer;
	}
	
	/**
	 * 
	 * @param searchFilter
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersSearchChildWindow(JsonTvinnSadManifestPostalCodeRecord searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(StringUtils.isNotEmpty(searchFilter.getSt2lk())){
			urlRequestParamsKeys.append("&st2lk=" + searchFilter.getSt2lk());
		}
		if(StringUtils.isNotEmpty(searchFilter.getSt2kod())){
			urlRequestParamsKeys.append("&st2kod=" + searchFilter.getSt2kod());
		}
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param ktspnr
	 * @return
	 */
	private List<JsonMaintSadImportKodttsRecord> getTollstedList(String applicationUser, String ktspnr){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT04R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		//Either id or alfa but not both...
		if(ktspnr!=null && !"".equals(ktspnr)){
			urlRequestParams.append("&ktspnr=" + ktspnr);
			urlRequestParams.append("&sh=y");			
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadImportKodttsRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadImportKodttsContainer container = this.maintSadImportKodttsService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadImportKodttsRecord record : list){
	        		//logger.info("KTSPNR:" + record.getKtspnr());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * 	1=Ekspedisjonstyper(import)
	 * 	2=Landkoder                     
	 *	3=Transaksjonstyper     
	 * 	4=Transportmåter               
	 *	5=Tollnedsettelser 
	 *	6=Preferanser                 
	 *	7=V.F. koder                 
	 *	8=Avgiftkoder 
	 *  8B=Avgiftkoder sekv.                     
	 *	9=Ekspedisjonstyper(eksport)              
	
	 *	A=Enhetskoder                          
	 *	B=Dok./Sertifikat kode (TVINN-import)  
	 *	C=Dok./sertifikat kode (TVINN-eksport) 
	 *	D=lagringssted                         
	 *	E=fylkeskoder                          
	 *	O=Typetilfelle (omberegning)
	 *	L=Incoterms
	 *	V=Valutakoder
	 * 
	 *  @param appUser
	 *  @param codeType
	 *  @return
	 * 
	 */
	
	private List<JsonTvinnSadCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonTvinnSadCodeRecord> list = new ArrayList<JsonTvinnSadCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams.toString());
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		JsonTvinnSadCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadImportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonTvinnSadCodeRecord  record : container.getKodlista()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Gets the list of released cargo lines in order to bind these to a specific tur
	 * @param appUser
	 * @return
	 */
	private Collection<JsonTvinnSadManifestCargoLinesRecord> getReleasedCargoLinesList(SystemaWebUser appUser){
		Collection<JsonTvinnSadManifestCargoLinesRecord> retval = null;
		//get BASE URL
		final String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&clpro=0&pick=1";
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
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
	
	
	private Collection<JsonTvinnSadManifestExportIdLinesRecord> getExportIdLinesList(JsonTvinnSadManifestExportIdLinesRecord dto, SystemaWebUser appUser, boolean oneLine){
		Collection<JsonTvinnSadManifestExportIdLinesRecord> outputList = null;
		
		//get BASE URL
		String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_EXPIDS_IN_CARGOLINE_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&cmavd=" + dto.getCmavd() + "&cmtdn=" + dto.getCmtdn());
		if(oneLine) {
			urlRequestParamsKeys.append("&cmli=" + dto.getCmli());
		}
		
		logger.warn("URL: " + BASE_URL);
		logger.warn("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		//Debug -->
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTvinnSadManifestExportIdLinesContainer container = this.tvinnSadManifestChildwindowService.getExportIdListContainer(jsonPayload);
			if(container!=null){
				outputList = container.getList();
				/*for(JsonTvinnSadManifestExportIdLinesRecord record : container.getList()){
					retval.add(record);
				}*/
			}
		}
		
    	return outputList;
	}
	/**
	 * Update several SE Export ids in a cargo line
	 * @param dto
	 * @param appUser
	 * @param oneLine
	 */
	private void updateExportIdLinesList(String mode, JsonTvinnSadManifestExportIdLinesRecord dto, SystemaWebUser appUser){
		
		//get BASE URL
		String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_EXPIDS_IN_CARGOLINE_URL;
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		urlRequestParamsKeys.append("user=" + appUser.getUser() + "&mode=" + mode );
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((dto));
		
		
		logger.warn("URL: " + BASE_URL);
		logger.warn("PARAMS: " + urlRequestParamsKeys + urlRequestParams);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString() + urlRequestParams);
		//Debug -->
		logger.warn(jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonTvinnSadManifestExportIdLinesContainer container = this.tvinnSadManifestChildwindowService.getExportIdListContainer(jsonPayload);
			if(container!=null){
				if(StringUtils.isNotEmpty(container.getErrMsg())){
					logger.error("ERROR on UPDATE ..." + container.getErrMsg());
				}
			}
		}
		
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private SadImportGeneralCodesChildWindowService sadImportGeneralCodesChildWindowService;
	
	@Autowired
	private MaintMainKofastService maintMainKofastService;
	
	
	@Autowired
	private MaintSadImportKodttsService maintSadImportKodttsService;
	
	@Autowired
	TvinnSadManifestChildwindowService tvinnSadManifestChildwindowService;
	
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;

	@Autowired
	private ManifestExpressMgr manifestExpressMgr;
}

