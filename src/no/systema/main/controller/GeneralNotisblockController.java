package no.systema.main.controller;

import java.util.*;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.general.notisblock.NotisblockService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.StringManager;
import no.systema.main.util.DateTimeManager;
import no.systema.main.url.store.MainUrlDataStore;
//Notisblock
import no.systema.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockContainer;
import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;
import no.systema.main.validator.NotisblockValidator;



/**
 * Notisblock Controller
 * 
 * @author oscardelatorre
 * @date Jan 20, 2015
 * 
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class GeneralNotisblockController {
	private static final Logger logger = Logger.getLogger(GeneralNotisblockController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private final String SUBSYSTEM_TVINN_SAD_IMPORT = "sadi";
	private final String SUBSYSTEM_TVINN_SAD_EXPORT = "sade";
	private final StringManager strMgr = new StringManager();
	private final DateTimeManager dateMgr = new DateTimeManager();
		
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private final String ACTION_DO_DELETE = "doDelete";
	private final String ACTION_DO_UPDATE = "doUpdate";
	private final String MODE_UPDATE = "U";
	private final String MODE_DELETE = "D";
	private final String MODE_ADD = "A";
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {

    }
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="editNotisblock.do")
	public ModelAndView editNotisblock(@ModelAttribute ("record") JsonNotisblockRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		boolean bindingErrorsExist = false;
		boolean isNewItemLine = false;
		String updateMode = this.MODE_UPDATE; //default;
		
		logger.info("Inside: editNotisblock");
		//RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonNotisblockRecord jsonNotisblockRecord = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//potential actions (doFetch, doUpdate or doDelete)
		String action = request.getParameter("action");
		String subsystem = request.getParameter("subsys");
		String caller = request.getParameter("orig");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String sign = request.getParameter("sign");
		String omberegningFlag = null;
		String omberegningDate = null;
		String omberegningType = null;
		//
		model.put("avd", avd);
		model.put("opd", opd);
		model.put("sign", sign);
		model.put("orig", caller);
		//Fallback view in case no other view is provided
		ModelAndView successView = new ModelAndView("dashboard");
				
		if(this.SUBSYSTEM_TVINN_SAD_IMPORT.equals(subsystem)){
			omberegningFlag = request.getParameter("o2_sist");
			omberegningDate = request.getParameter("o2_sidt");
			omberegningType = request.getParameter("o2_simf");
			model.put("o2_sist", omberegningFlag);
			model.put("o2_sidt", omberegningDate);
			model.put("o2_simf", omberegningType);
			//view
			successView = new ModelAndView("tvinnsadimport_edit_notisblock");
		}else if(this.SUBSYSTEM_TVINN_SAD_EXPORT.equals(subsystem)){
			omberegningFlag = request.getParameter("o2_sest");
			omberegningDate = request.getParameter("o2_sedt");
			omberegningType = request.getParameter("o2_semf");
			model.put("o2_sest", omberegningFlag);
			model.put("o2_sedt", omberegningDate);
			model.put("o2_semf", omberegningType);
			//view
			successView = new ModelAndView("tvinnsadexport_edit_notisblock");
		
		}else if("tror_li".equals(subsystem)){
			//Oppdragsreg. Land import
			successView = new ModelAndView("tror_mainorderlandimport_notisblock");
		
		}else if("tror_le".equals(subsystem)){
			//Oppdragsreg. Land export
			successView = new ModelAndView("tror_mainorderlandexport_notisblock");
		
		}else if("tror_fi".equals(subsystem)){
			//Oppdragsreg. Land import
			successView = new ModelAndView("tror_mainorderflyimport_notisblock");
		
		}//ADD MORE as the notisblock is used for more modules 
		
		logger.info("ACTION: " + action);
		logger.info("SUBSYSTEM:" + subsystem);
		logger.info("CALLER:" + caller);
		logger.info("SIGN:" + sign);
		
		
		//START with controller now
		if(appUser==null){
			return this.loginView;
		}else{
			
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineId = request.getParameter("frtli");
			logger.info("[INFO lineId] " + lineId);
			if(lineId!=null && !"".equals(lineId)){
				//meaning that an update of a specific line is applicable here
			}else{
				isNewItemLine = true;
			}
			
			
			if(this.ACTION_DO_UPDATE.equals(action) || this.ACTION_DO_DELETE.equals(action)){
				
				if(this.ACTION_DO_UPDATE.equals(action)){
					NotisblockValidator validator = new NotisblockValidator();
					logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
					validator.validate(recordToValidate, bindingResult);
					//check for ERRORS
					if(bindingResult.hasErrors()){
						logger.info("[ERROR Validation] Record does not validate)");
				    	logger.info("[INFO lineId] " + lineId);
				    	bindingErrorsExist = true;
				    	model.put("record", recordToValidate);
					}
				}
			    //check for ERRORS
				if(bindingErrorsExist){
					//logger.info("[ERROR Validation] Record does not validate)");
				    //logger.info("[INFO lineId] " + lineId);
				    bindingErrorsExist = true;
				    model.put("record", recordToValidate);
				    	
			    }else{
		    		jsonNotisblockRecord = recordToValidate;
		    		this.adjustFields(jsonNotisblockRecord);
		    		if(!isNewItemLine){
	    			//-------
					//UPDATE
					//-------
					logger.info("UPDATE(only) ITEM (existent item) on process...");
	    			if(this.ACTION_DO_DELETE.equals(action)){ updateMode = this.MODE_DELETE;}
					    
					}else{
						//-------
						//CREATE
						//-------
						updateMode=this.MODE_ADD;
						logger.info("CREATE new ITEM line (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,sftxt) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						String ceilingLineNumber = request.getParameter("ceilingLineNumber");
						jsonNotisblockRecord.setFrtli(this.getNewLineNr(ceilingLineNumber));
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					logger.info("[INFO] ready to start line item update (create,edit,remove)");
					
					//---------------------------
					//get BASE URL = RPG-PROGRAM
		            //---------------------------
					String BASE_URL_UPDATE = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL;
					StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
					urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
					urlRequestParamsKeysBuffer.append("&frtavd=" + avd);
					urlRequestParamsKeysBuffer.append("&frtopd=" + opd);
					urlRequestParamsKeysBuffer.append("&frtdt=" + jsonNotisblockRecord.getFrtdt());
					urlRequestParamsKeysBuffer.append("&frtli=" + jsonNotisblockRecord.getFrtli());
					urlRequestParamsKeysBuffer.append("&mode=" + updateMode);
					//now put the values to update (edit, create new)
					if(!"D".equals(updateMode)){
						 urlRequestParamsKeysBuffer.append("&frttxt=" + jsonNotisblockRecord.getFrttxt());
						 urlRequestParamsKeysBuffer.append("&frtkod=" + jsonNotisblockRecord.getFrtkod());		 
					}
					urlRequestParamsKeys = urlRequestParamsKeysBuffer.toString();
					logger.info("URL: " + BASE_URL_UPDATE);
					logger.info("PARAMS: " + urlRequestParamsKeys);
					logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
					String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParamsKeys);
					logger.info(jsonPayload);
					logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
					 
					JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayload);
					logger.info("JsonNotisblockContainer:" + jsonNotisblockContainer);
					if(jsonNotisblockContainer!=null){
						//logger.info("A:" + jsonNotisblockContainer.getErrMsg());
						if( !"".equals(jsonNotisblockContainer.getErrMsg()) ){
							//Debug
							logger.info("[ERROR]:" + jsonNotisblockContainer.getErrMsg());
						}
					}
			    }
			}
			
			
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = MainUrlDataStore.SYSTEMA_NOTIS_BLOCK_FETCH_LIST_URL;
			StringBuffer urlRequestParamsKeysBuffer = new StringBuffer();
			urlRequestParamsKeysBuffer.append("user=" + appUser.getUser());
			urlRequestParamsKeysBuffer.append("&avd=" + avd);
			urlRequestParamsKeysBuffer.append("&opd=" + opd);
			
			urlRequestParamsKeys = urlRequestParamsKeysBuffer.toString();
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	    	logger.info("URL: " + BASE_URL_FETCH);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
	    	logger.info(jsonPayloadFetch);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonNotisblockContainer jsonNotisblockContainer = this.notisblockService.getNotisblockListContainer(jsonPayloadFetch);
	    	if(jsonNotisblockContainer!=null){
	    		Collection<JsonNotisblockRecord> list = new ArrayList<JsonNotisblockRecord>();
	    		list = jsonNotisblockContainer.getFreetextlist();
	    		for(JsonNotisblockRecord notisRecord : list){
	    			//DEBUG 
	    			//logger.info("Notisblock free text:" + notisRecord.getFrttxt());
	    			
	    		}
	    		
	    	}
    		this.setDomainObjectsForListInView(model, jsonNotisblockContainer);
	    	successView.addObject("model",model);
	    	//successView.addObject(Constants.EDIT_ACTION_ON_TOPIC, Constants.ACTION_FETCH);
	    	return successView;
		}
	}

	/**
	 * 
	 * @param ceilingLineNr
	 * @return
	 */
	private String getNewLineNr (String ceilingLineNr){
		String retval = null;
		try{
			if(ceilingLineNr!=null && !"".equals(ceilingLineNr)){
				Integer ceiling = Integer.valueOf(ceilingLineNr);
				ceiling++;
				retval = String.valueOf(ceiling);
			}else{
				retval = "1";
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.info("[FATAl ERROR]");
		}
		return retval;
	}
	
	/**
	 * 
	 * @param record
	 */
	private void adjustFields(JsonNotisblockRecord record){
		if(strMgr.isNotNull(record.getFrtdt())){
			record.setFrtdt( this.dateMgr.getCurrentDate_ISO() );
		}
	}
	
	/**
	 * Sets domain objects
	 * @param model
	 * @param container
	 * 
	 */
	private void setDomainObjectsForListInView(Map model, JsonNotisblockContainer container){
		List<JsonNotisblockRecord> list = new ArrayList<JsonNotisblockRecord>();
		if(container!=null){
			for (JsonNotisblockRecord record : container.getFreetextlist()){
				//Set the highest line number in order to track for a create new line further on (add new line)
				container.setCeilingLineNumber(record.getFrtli());
				list.add(record);
				
			}
		}
		model.put(AppConstants.DOMAIN_LIST, list);
		model.put(AppConstants.DOMAIN_CONTAINER_PARENT, container);
		
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("notisblockService")
	private NotisblockService notisblockService;
	@Autowired
	public void setNotisblockService (NotisblockService value){ this.notisblockService=value; }
	public NotisblockService getNotisblockService(){return this.notisblockService;}
	
	
	
	 
}

