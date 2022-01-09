package no.systema.tvinn.sad.z.maintenance.felles.controller;

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
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariContainer;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariRecord;
import no.systema.tvinn.sad.z.maintenance.felles.service.MaintSadFellesTariService;
import no.systema.tvinn.sad.z.maintenance.felles.validator.MaintSadFellesSad010rValidator;
import no.systema.tvinn.sad.z.maintenance.felles.url.store.TvinnSadMaintenanceFellesUrlDataStore;


/**
 *  TVINN Maintenance Felles Sad010r Controller 
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadFellesSad010rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSadFellesSad010rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenancefelles_sad010r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintFellesList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenancefelles_sad010r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String tatanr = request.getParameter("searchTatanr");
		String taalfa = request.getParameter("searchTaalfa");
		String tatxt = request.getParameter("searchTatxt");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintSadFellesTariRecord> list = new ArrayList();
	    	if( (tatanr!=null && !"".equals(tatanr)) || (taalfa!=null && !"".equals(taalfa)) ||  (tatxt!=null && !"".equals(tatxt))){
	    		list = fetchList(appUser.getUser(), tatanr, taalfa, tatxt);
	    	}
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	model.put("tatanr", tatanr);
	    	model.put("taalfa", taalfa);
	    	model.put("tatxt", tatxt);

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
	@RequestMapping(value="tvinnsadmaintenancefelles_sad010r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintFellesEdit(@ModelAttribute ("record") JsonMaintSadFellesTariRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenancefelles_sad010r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//adjust values
			this.adjustSomeRecordValues(recordToValidate);
			//Move on
			MaintSadFellesSad010rValidator validator = new MaintSadFellesSad010rValidator();
			if(TvinnSadMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
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
				model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
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
					model.put(TvinnSadMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(TvinnSadMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
	    	List<JsonMaintSadFellesTariRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getTatanr(), recordToValidate.getTaalfa(), recordToValidate.getTatxt());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 */
	private void adjustSomeRecordValues(JsonMaintSadFellesTariRecord recordToValidate){
		final String ZERO = "0";
		//--------
		//Dates
		//--------
		if(recordToValidate.getTadtrNO()!=null && !"".equals(recordToValidate.getTadtrNO())){
			recordToValidate.setTadtr(dateFormatter.convertToDate_ISO(recordToValidate.getTadtrNO()));
		}else{
			recordToValidate.setTadtr(recordToValidate.getTadato());
		}
		if(recordToValidate.getTadatoNO()!=null && !"".equals(recordToValidate.getTadatoNO())){
			recordToValidate.setTadato(dateFormatter.convertToDate_ISO(recordToValidate.getTadatoNO()));
		}else{
			recordToValidate.setTadato(ZERO);
		}
		if(recordToValidate.getTadtsNO()!=null && !"".equals(recordToValidate.getTadtsNO())){
			recordToValidate.setTadts(dateFormatter.convertToDate_ISO(recordToValidate.getTadtsNO()));
		}else{
			recordToValidate.setTadts(ZERO);
		}
		//-----------------
		//Decimal amounts
		//-----------------
		if(recordToValidate.getTaordb()!=null && !"".equals(recordToValidate.getTaordb())){
			String tmp = recordToValidate.getTaordb().replace(",", ".");
			recordToValidate.setTaordb(tmp);
		}else{
			recordToValidate.setTaordb(ZERO);
		}
		if(recordToValidate.getTaeftb()!=null && !"".equals(recordToValidate.getTaeftb())){
			String tmp = recordToValidate.getTaeftb().replace(",", ".");
			recordToValidate.setTaeftb(tmp);
		}else{
			recordToValidate.setTaeftb(ZERO);
		}
		if(recordToValidate.getTaefb()!=null && !"".equals(recordToValidate.getTaefb())){
			String tmp = recordToValidate.getTaefb().replace(",", ".");
			recordToValidate.setTaefb(tmp);
		}else{
			recordToValidate.setTaefb(ZERO);
		}
		if(recordToValidate.getTaeosb()!=null && !"".equals(recordToValidate.getTaeosb())){
			String tmp = recordToValidate.getTaeosb().replace(",", ".");
			recordToValidate.setTaeosb(tmp);
		}else{
			recordToValidate.setTaeosb(ZERO);
		}
		
		if(recordToValidate.getTatsjb()!=null && !"".equals(recordToValidate.getTatsjb())){
			String tmp = recordToValidate.getTatsjb().replace(",", ".");
			recordToValidate.setTatsjb(tmp);
		}else{
			recordToValidate.setTatsjb(ZERO);
		}
		if(recordToValidate.getTatyrb()!=null && !"".equals(recordToValidate.getTatyrb())){
			String tmp = recordToValidate.getTatyrb().replace(",", ".");
			recordToValidate.setTatyrb(tmp);
		}else{
			recordToValidate.setTatyrb(ZERO);
		}
		if(recordToValidate.getTaisrb()!=null && !"".equals(recordToValidate.getTaisrb())){
			String tmp = recordToValidate.getTaisrb().replace(",", ".");
			recordToValidate.setTaisrb(tmp);
		}else{
			recordToValidate.setTaisrb(ZERO);
		}
		if(recordToValidate.getTaellb()!=null && !"".equals(recordToValidate.getTaellb())){
			String tmp = recordToValidate.getTaellb().replace(",", ".");
			recordToValidate.setTaellb(tmp);
		}else{
			recordToValidate.setTaellb(ZERO);
		}
		//
		if(recordToValidate.getTabulb()!=null && !"".equals(recordToValidate.getTabulb())){
			String tmp = recordToValidate.getTabulb().replace(",", ".");
			recordToValidate.setTabulb(tmp);
		}else{
			recordToValidate.setTabulb(ZERO);
		}
		if(recordToValidate.getTapolb()!=null && !"".equals(recordToValidate.getTapolb())){
			String tmp = recordToValidate.getTapolb().replace(",", ".");
			recordToValidate.setTapolb(tmp);
		}else{
			recordToValidate.setTapolb(ZERO);
		}
		if(recordToValidate.getTaromb()!=null && !"".equals(recordToValidate.getTaromb())){
			String tmp = recordToValidate.getTaromb().replace(",", ".");
			recordToValidate.setTaromb(tmp);
		}else{
			recordToValidate.setTaromb(ZERO);
		}
		if(recordToValidate.getTan05b()!=null && !"".equals(recordToValidate.getTan05b())){
			String tmp = recordToValidate.getTan05b().replace(",", ".");
			recordToValidate.setTan05b(tmp);
		}else{
			recordToValidate.setTan05b(ZERO);
		}
		if(recordToValidate.getTan06b()!=null && !"".equals(recordToValidate.getTan06b())){
			String tmp = recordToValidate.getTan06b().replace(",", ".");
			recordToValidate.setTan06b(tmp);
		}else{
			recordToValidate.setTan06b(ZERO);
		}
		if(recordToValidate.getTan07b()!=null && !"".equals(recordToValidate.getTan07b())){
			String tmp = recordToValidate.getTan07b().replace(",", ".");
			recordToValidate.setTan07b(tmp);
		}else{
			recordToValidate.setTan07b(ZERO);
		}
		if(recordToValidate.getTaungb()!=null && !"".equals(recordToValidate.getTaungb())){
			String tmp = recordToValidate.getTaungb().replace(",", ".");
			recordToValidate.setTaungb(tmp);
		}else{
			recordToValidate.setTaungb(ZERO);
		}
		
		if(recordToValidate.getTaslob()!=null && !"".equals(recordToValidate.getTaslob())){
			String tmp = recordToValidate.getTaslob().replace(",", ".");
			recordToValidate.setTaslob(tmp);
		}else{
			recordToValidate.setTaslob(ZERO);
		}
		if(recordToValidate.getTamulb()!=null && !"".equals(recordToValidate.getTamulb())){
			String tmp = recordToValidate.getTamulb().replace(",", ".");
			recordToValidate.setTamulb(tmp);
		}else{
			recordToValidate.setTamulb(ZERO);
		}
		if(recordToValidate.getTaoulb()!=null && !"".equals(recordToValidate.getTaoulb())){
			String tmp = recordToValidate.getTaoulb().replace(",", ".");
			recordToValidate.setTaoulb(tmp);
		}else{
			recordToValidate.setTaoulb(ZERO);
		}
		if(recordToValidate.getTagrlb()!=null && !"".equals(recordToValidate.getTagrlb())){
			String tmp = recordToValidate.getTagrlb().replace(",", ".");
			recordToValidate.setTagrlb(tmp);
		}else{
			recordToValidate.setTagrlb(ZERO);
		}
		if(recordToValidate.getTaferb()!=null && !"".equals(recordToValidate.getTaferb())){
			String tmp = recordToValidate.getTaferb().replace(",", ".");
			recordToValidate.setTaferb(tmp);
		}else{
			recordToValidate.setTaferb(ZERO);
		}
		if(recordToValidate.getTaistb()!=null && !"".equals(recordToValidate.getTaistb())){
			String tmp = recordToValidate.getTaistb().replace(",", ".");
			recordToValidate.setTaistb(tmp);
		}else{
			recordToValidate.setTaistb(ZERO);
		}
		if(recordToValidate.getTamarb()!=null && !"".equals(recordToValidate.getTamarb())){
			String tmp = recordToValidate.getTamarb().replace(",", ".");
			recordToValidate.setTamarb(tmp);
		}else{
			recordToValidate.setTamarb(ZERO);
		}
		if(recordToValidate.getTan08b()!=null && !"".equals(recordToValidate.getTan08b())){
			String tmp = recordToValidate.getTan08b().replace(",", ".");
			recordToValidate.setTan08b(tmp);
		}else{
			recordToValidate.setTan08b(ZERO);
		}
		if(recordToValidate.getTan09b()!=null && !"".equals(recordToValidate.getTan09b())){
			String tmp = recordToValidate.getTan09b().replace(",", ".");
			recordToValidate.setTan09b(tmp);
		}else{
			recordToValidate.setTan09b(ZERO);
		}
		if(recordToValidate.getTan10b()!=null && !"".equals(recordToValidate.getTan10b())){
			String tmp = recordToValidate.getTan10b().replace(",", ".");
			recordToValidate.setTan10b(tmp);
		}else{
			recordToValidate.setTan10b(ZERO);
		}
		if(recordToValidate.getTamexb()!=null && !"".equals(recordToValidate.getTamexb())){
			String tmp = recordToValidate.getTamexb().replace(",", ".");
			recordToValidate.setTamexb(tmp);
		}else{
			recordToValidate.setTamexb(ZERO);
		}
		if(recordToValidate.getTavgab()!=null && !"".equals(recordToValidate.getTavgab())){
			String tmp = recordToValidate.getTavgab().replace(",", ".");
			recordToValidate.setTavgab(tmp);
		}else{
			recordToValidate.setTavgab(ZERO);
		}
		//
		if(recordToValidate.getTan01b()!=null && !"".equals(recordToValidate.getTan01b())){
			String tmp = recordToValidate.getTan01b().replace(",", ".");
			recordToValidate.setTan01b(tmp);
		}else{
			recordToValidate.setTan01b(ZERO);
		}
		if(recordToValidate.getTan02b()!=null && !"".equals(recordToValidate.getTan02b())){
			String tmp = recordToValidate.getTan02b().replace(",", ".");
			recordToValidate.setTan02b(tmp);
		}else{
			recordToValidate.setTan02b(ZERO);
		}
		if(recordToValidate.getTan03b()!=null && !"".equals(recordToValidate.getTan03b())){
			String tmp = recordToValidate.getTan03b().replace(",", ".");
			recordToValidate.setTan03b(tmp);
		}else{
			recordToValidate.setTan03b(ZERO);
		}
		if(recordToValidate.getTan04b()!=null && !"".equals(recordToValidate.getTan04b())){
			String tmp = recordToValidate.getTan04b().replace(",", ".");
			recordToValidate.setTan04b(tmp);
		}else{
			recordToValidate.setTan04b(ZERO);
		}
		if(recordToValidate.getTan11b()!=null && !"".equals(recordToValidate.getTan11b())){
			String tmp = recordToValidate.getTan11b().replace(",", ".");
			recordToValidate.setTan11b(tmp);
		}else{
			recordToValidate.setTan11b(ZERO);
		}
		if(recordToValidate.getTan12b()!=null && !"".equals(recordToValidate.getTan12b())){
			String tmp = recordToValidate.getTan12b().replace(",", ".");
			recordToValidate.setTan12b(tmp);
		}else{
			recordToValidate.setTan12b(ZERO);
		}
		//
		if(recordToValidate.getTan13b()!=null && !"".equals(recordToValidate.getTan13b())){
			String tmp = recordToValidate.getTan13b().replace(",", ".");
			recordToValidate.setTan13b(tmp);
		}else{
			recordToValidate.setTan13b(ZERO);
		}
		if(recordToValidate.getTan14b()!=null && !"".equals(recordToValidate.getTan14b())){
			String tmp = recordToValidate.getTan14b().replace(",", ".");
			recordToValidate.setTan14b(tmp);
		}else{
			recordToValidate.setTan14b(ZERO);
		}
		if(recordToValidate.getTan15b()!=null && !"".equals(recordToValidate.getTan15b())){
			String tmp = recordToValidate.getTan15b().replace(",", ".");
			recordToValidate.setTan15b(tmp);
		}else{
			recordToValidate.setTan15b(ZERO);
		}
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param tatanr
	 * @param xX 
	 * @return
	 */
	private List<JsonMaintSadFellesTariRecord> fetchList(String applicationUser, String tatanr, String taalfa, String tatxt){
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD010R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		//Either id or alfa but not both...
		if(tatanr!=null && !"".equals(tatanr)){
			urlRequestParams.append("&tatanr=" + tatanr);
		}else if (taalfa!=null && !"".equals(taalfa)){
			urlRequestParams.append("&taalfa=" + taalfa);
		}else if (tatxt!=null && !"".equals(tatxt)){
			urlRequestParams.append("&tatxt=" + tatxt);
		}
		else{
			//no further search. Just return an empty list
			return new ArrayList();
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintSadFellesTariRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintSadFellesTariContainer container = this.maintSadFellesTariService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintSadFellesTariRecord record : list){
	        		//logger.info("TATANR:" + record.getTatanr());
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
	private int updateRecord(String applicationUser, JsonMaintSadFellesTariRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = TvinnSadMaintenanceFellesUrlDataStore.TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD010R_DML_UPDATE_URL;
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
    		JsonMaintSadFellesTariContainer container = this.maintSadFellesTariService.doUpdate(jsonPayload);
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
	
	
	@Qualifier ("maintSadFellesTariService")
	private MaintSadFellesTariService maintSadFellesTariService;
	@Autowired
	@Required
	public void setMaintSadFellesTariService (MaintSadFellesTariService value){ this.maintSadFellesTariService = value; }
	public MaintSadFellesTariService getMaintSadFellesTariService(){ return this.maintSadFellesTariService; }
	
}

