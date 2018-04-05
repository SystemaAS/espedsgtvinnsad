package no.systema.tvinn.sad.z.maintenance.sadimport.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.main.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlContainer;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.JsonMaintSadSadlRecord;
import no.systema.tvinn.sad.z.maintenance.sad.service.MaintSadSadlService;
import no.systema.tvinn.sad.z.maintenance.sadexport.service.MaintSadExportKodts6Service;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.validator.MaintSadImportSad004rValidator;


/**
 *  TVINN Maintenance Import Sad001ar Controller - Gml. Kunders vareregister
 * 
 * @author oscardelatorre
 * @date May 31, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportSad004rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportSad004rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceimport_sad004r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad004r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String kundnr = request.getParameter("searchKundnr");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintSadSadlRecord> list = new ArrayList();
	    	if( (kundnr!=null && !"".equals(kundnr)) ){
	    		list = this.fetchList(appUser.getUser(), null, kundnr); //to accomplish wild card search
	    	}
	    	//set domain objets
			this.populateDropDowns(model, appUser.getUser());
	    	model.put("dbTable", dbTable);
	    	model.put("kundnr", kundnr);
	    	model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
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
	@RequestMapping(value="tvinnsadmaintenanceimport_sad004r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintSadSadlRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad004r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Save for forward backup
			String originalFreeText = recordToValidate.getSltxt();
			//Move on
			MaintSadImportSad004rValidator validator = new MaintSadImportSad004rValidator();
			JsonMaintSadSadlRecord recordToValidateOrg = null; 
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				//adjust values
				recordToValidateOrg=SerializationUtils.clone(recordToValidate); //keep due to advanced adjustments between UI and DB
				this.adjustSomeRecordValues(recordToValidate);
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidateOrg);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (TvinnSadMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(TvinnSadMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(TvinnSadMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(TvinnSadMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, TvinnSadMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					model.put("kundnr", recordToValidate.getSlknr());
					model.put("updateId", updateId);
					//adjust back the free text (prior to the appends: r31, pref and mf
					recordToValidate.setSltxt(originalFreeText);
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidateOrg);
				}
				
			}
			//------------
			//FETCH table
			//------------
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action) ){
				recordToValidate.setSlalfa(null);
			}
			List<JsonMaintSadSadlRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getSlalfa(), recordToValidate.getSlknr());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("kundnr", recordToValidate.getSlknr());
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			this.populateDropDowns(model, appUser.getUser()); 
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}

	private void populateDropDowns(Map model, String applicationUser){
		codeDropDownMgr.populatePrefCodesHtmlDropDownsSadExport(this.urlCgiProxyService, this.maintSadExportKodts6Service, model, applicationUser); 
		codeDropDownMgr.populateR31HtmlDropDownsSadExport( model);
		codeDropDownMgr.populateMfHtlDropDownSadExport(model);
	}	
	
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustSomeRecordValues(JsonMaintSadSadlRecord recordToValidate){
		final String ZERO = "0";
		//-----------------
		//Decimal amounts
		//-----------------
		if(recordToValidate.getSlvekt()!=null && !"".equals(recordToValidate.getSlvekt())){
			String tmp = recordToValidate.getSlvekt().replace(",", ".");
			recordToValidate.setSlvekt(tmp);
		}else{
			recordToValidate.setSlvekt(ZERO);
		}
		if(recordToValidate.getSltanr()!=null && !"".equals(recordToValidate.getSltanr())){
			String tmp = recordToValidate.getSltanr().replace(",", ".");
			recordToValidate.setSltanr(tmp);
		}else{
			recordToValidate.setSltanr(ZERO);
		}
		if(recordToValidate.getSlsats()!=null && !"".equals(recordToValidate.getSlsats())){
			String tmp = recordToValidate.getSlsats().replace(",", ".");
			recordToValidate.setSlsats(tmp);
		}else{
			recordToValidate.setSlsats(ZERO);
		}
		//-----------------------------
		//(2) START Adjust free text.
		//-----------------------------
		if(recordToValidate!=null){
			//Position 21,22,23 are reserved and must be appended
			String SPACE = " ";
			int PURE_FTX_UPPER_LIMIT_POSITION = 20;
			StringBuffer str = new StringBuffer(recordToValidate.getSltxt());
			int len = str.length();
			for (int x=len+1;x<=PURE_FTX_UPPER_LIMIT_POSITION;x++){
				str.append(SPACE);
			}
			//now append r31,pref and mf into postion 21,22,23 respectively and in order
			if(recordToValidate.getR31()!=null && !"".equals(recordToValidate.getR31())){
				str.append(recordToValidate.getR31());
			}else{
				str.append(SPACE);
			}
			if(recordToValidate.getPref()!=null && !"".equals(recordToValidate.getPref())){
				str.append(recordToValidate.getPref());
			}else{
				str.append(SPACE);
			}
			if(recordToValidate.getMf()!=null && !"".equals(recordToValidate.getMf())){
				str.append(recordToValidate.getMf());
			}else{
				str.append(SPACE);
			}
			//Update sltxt
			recordToValidate.setSltxt(str.toString());
			//END - adjust free text
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param levenr
	 * @return
	 */
	private List<JsonMaintSadSadlRecord> fetchList(String applicationUser, String id, String levenr){
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		if(levenr!=null && !"".equals(levenr)){
			urlRequestParams.append("&slknr=" + levenr);
			if(id!=null){
				urlRequestParams.append("&slalfa=" + id);
			}
			
		}else{
			//no further search. Just return an empty list
			return new ArrayList();
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadSadlRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadSadlContainer container = this.maintSadSadlService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadSadlRecord record : list){
	        		//logger.info("VARENR:" + record.getSlalfa());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintSadSadlRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = TvinnSadMaintenanceImportUrlDataStore.TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadSadlContainer container = this.maintSadSadlService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = TvinnSadMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}
    	
    	return retval;
    
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintSadSadlService")
	private MaintSadSadlService maintSadSadlService;
	@Autowired
	@Required
	public void setMaintSadImportSadlService (MaintSadSadlService value){ this.maintSadSadlService = value; }
	public MaintSadSadlService getMaintSadImportSadlService(){ return this.maintSadSadlService; }
	
	@Qualifier ("maintSadExportKodts6Service")
	private MaintSadExportKodts6Service maintSadExportKodts6Service;
	@Autowired
	@Required
	public void setMaintSadExportKodts6Service (MaintSadExportKodts6Service value){ this.maintSadExportKodts6Service = value; }
	public MaintSadExportKodts6Service getMaintSadExportKodts6Service(){ return this.maintSadExportKodts6Service; }


}

