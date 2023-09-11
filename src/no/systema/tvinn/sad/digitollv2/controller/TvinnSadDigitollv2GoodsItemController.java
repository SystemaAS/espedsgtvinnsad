package no.systema.tvinn.sad.digitollv2.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.JsonDebugger;
import no.systema.main.validator.LoginValidator;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;
import no.systema.tvinn.sad.digitollv2.validator.HouseValidator;
import no.systema.tvinn.sad.manifest.express.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

@Controller
public class TvinnSadDigitollv2GoodsItemController {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollv2GoodsItemController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	DateTimeManager dateMgr = new DateTimeManager();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_edit_goodsitem.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doEditItem(@ModelAttribute ("record") SadmoifRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		
		Collection<SadmoifRecord> outputList = new ArrayList<SadmoifRecord>();
		Map model = new HashMap();
		
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String action = request.getParameter("action");
		String eilnrt = request.getParameter("eilnrt");
		String eilnrm = request.getParameter("eilnrm");
		String eilnrh = request.getParameter("eilnrh");
		String eili = request.getParameter("eili");
		String eiavd = request.getParameter("eiavd");
		String eipro = request.getParameter("eipro");
		String eitdn = request.getParameter("eitdn");
		ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_childwindow_goodsitem.do?action=doInit" + "&ehlnrt=" + eilnrt + "&ehlnrm=" + eilnrm + "&ehlnrh=" + eilnrh
																											+ "&ehavd=" + eiavd + "&ehpro=" + eipro + "&ehtdn=" + eitdn);
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			//Submit button (Update/Insert)
			if(StringUtils.isNotEmpty(action) && action.equals("doUpdate")) {
					String mode = "NA";
					//Update
					if(StringUtils.isNotEmpty(eili) ){
						mode = SadDigitollConstants.DB_MODE_UPDATE;
					}else {
						mode = SadDigitollConstants.DB_MODE_INSERT;
					}
					logger.info("MODE:" + mode + " before update in Controller ...");
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, mode, errMsg);
					if(dmlRetval < 0) {
						//error on update
						model.put("errorMessage", errMsg.toString());
						//put all aspects (sub-lists) only with update (not insert) error
						if(SadDigitollConstants.DB_MODE_UPDATE.equals(mode)){
							//this.setRecordAspects(appUser, recordToValidate);
						}
						
					}else {
						logger.info("OK after update item lines");
					}
					
				}
			}
			
			successView.addObject(TvinnSadConstants.DOMAIN_MODEL , model);
	   	
			return successView;
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_delete_goodsitem.do",  method={RequestMethod.GET, RequestMethod.POST} )
	public ModelAndView doDeleteItem(@ModelAttribute ("record") SadmoifRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		
		Map model = new HashMap();
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		
		String id1 = "";
		String id2 = "";
		String id3 = "";
		String id4 = "";
		String avd = "";
		String pro = "";
		String tdn = "";
		
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
    			}else if(element.startsWith("current_avd")){
    				avd = value;
    			}else if(element.startsWith("current_pro")){
    				pro = value;
    			}else if(element.startsWith("current_tdn")){
    				tdn = value;
    			}
    		}
    	}
	    
	    logger.info("Id1:" + id1); logger.info("Id2:" + id2); logger.info("Id3:" + id3); logger.info("id4:" + id4);
	    ModelAndView successView = new ModelAndView("redirect:tvinnsaddigitollv2_childwindow_goodsitem.do?action=doInit" + "&ehlnrt=" + id1 + "&ehlnrm=" + id2 + "&ehlnrh=" + id3
																											+ "&ehavd=" + avd + "&ehpro=" + pro + "&ehtdn=" + tdn);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_DIGITOLLV2);
			session.setAttribute(TvinnSadConstants.ACTIVE_URL_RPG_TVINN_SAD, TvinnSadConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			
			String mode = SadDigitollConstants.DB_MODE_DELETE;
			logger.info("MODE:" + mode + " before update in Controller ...");
			StringBuffer errMsg = new StringBuffer();
			int dmlRetval = 0;
			//Populate the record for delete
			recordToValidate.setEilnrt(Integer.valueOf(id1)); recordToValidate.setEilnrm(Integer.valueOf(id2)); recordToValidate.setEilnrh(Integer.valueOf(id3));recordToValidate.setEili(Integer.valueOf(id4));
			recordToValidate.setEiavd(Integer.valueOf(avd)); recordToValidate.setEipro(Integer.valueOf(pro)); recordToValidate.setEitdn(Integer.valueOf(tdn));
			
			dmlRetval = this.deleteRecord(appUser.getUser(), recordToValidate, mode, errMsg);
			if(dmlRetval < 0) {
				//error on delete
				model.put("errorMessage", errMsg.toString());
				//put all aspects (sub-lists) only with update (not insert) error
				if(SadDigitollConstants.DB_MODE_UPDATE.equals(mode)){
				}
				
			}else {
				logger.info("OK after delete item line!");
			}
		}
		
		return successView;
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param record
	 */
	private void getItemLines(SystemaWebUser appUser, SadmoifRecord itemRecord) {
		SadmohfRecord record = new SadmohfRecord();
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + appUser.getUser() + "&eilnrt=" + record.getEhlnrt() + "&eilnrm=" + record.getEhlnrm() + "&eilnrh=" + record.getEhlnrh();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.warn("URL: " + BASE_URL);
		logger.warn("URL PARAMS: " + urlRequestParams);
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
		//Debug --> 
		logger.debug(jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
			record.setListItemLines(jsonContainer.getList());
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
	private int updateRecord(String applicationUser, SadmoifRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_ITEMLINES_URL;
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
					}else {
						if(mode.equals(SadDigitollConstants.DB_MODE_INSERT)) {
							recordToValidate.setEilnrt(record.getId());
							recordToValidate.setEilnrm(record.getId2());
							recordToValidate.setEilnrh(record.getId3());
							recordToValidate.setEili(record.getId4());
						}
					}
				}
			}
    	}
    	
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
	private int deleteRecord(String applicationUser, SadmoifRecord recordToValidate, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_ITEMLINES_URL;
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
					}else {
						break;
					}
				}
			}
    	}
    	
    	return retval;
	}
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private GeneralUpdateService generalUpdateService;
}
