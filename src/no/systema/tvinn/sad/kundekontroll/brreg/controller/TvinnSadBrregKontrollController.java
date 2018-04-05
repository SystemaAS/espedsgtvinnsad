package no.systema.tvinn.sad.kundekontroll.brreg.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckContainer;
import no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson.JsonEnhetsRegisteretDataCheckRecord;
import no.systema.tvinn.sad.kundekontroll.brreg.service.BrregEnhetsRegisteretService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;
import no.systema.tvinn.sad.z.maintenance.sad.mapper.url.request.UrlRequestParameterMapper;


/**
 *  Controller viewing kunde kontroll againt data.brreg.no
 * 
 * @author Fredrik Möller
 * @date Sep 26, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class TvinnSadBrregKontrollController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(TvinnSadBrregKontrollController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsad_brreg_kundekontroll.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doBrregKundeKontrollList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsad_brreg_kundekontroll");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String dbTable = request.getParameter("id");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonEnhetsRegisteretDataCheckRecord> list = this.fetchList(appUser.getUser());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
			model.put(TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
			
			session.setAttribute(session.getId() + TvinnSadMaintenanceConstants.SESSION_LIST, list);

	    	successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	

	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="invalidaKunderMainListExcelView.do", method={RequestMethod.GET})
	public ModelAndView getItemListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "invalidaKunderMainListExcelView";
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonEnhetsRegisteretDataCheckRecord> list = null;
		
        response.setHeader ("Content-disposition", "filename=\"" + EXCEL_VIEW + ".xls\"");

		if(appUser==null){
			return this.loginView;
		}else{
			list = (List)session.getAttribute(session.getId() + TvinnSadMaintenanceConstants.SESSION_LIST);
		}	
		
		return new ModelAndView(EXCEL_VIEW, TvinnSadMaintenanceConstants.DOMAIN_LIST, list);
	}	
	
	
	private List<JsonEnhetsRegisteretDataCheckRecord> fetchList(String applicationUser){
		String BASE_URL = TvinnSadUrlDataStore.TVINN_SAD_BRREG_GET_KUNDEDATA_KONTROLL_URL;
		String urlRequestParams = "user=" + applicationUser;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//logger.info("jsonPayload="+jsonPayload); To big
    	List<JsonEnhetsRegisteretDataCheckRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonEnhetsRegisteretDataCheckContainer container = this.brregEnhetsRegisteretService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
/*	        	for(JsonEnhetsRegisteretDataCheckRecord record : list){
	        	  logger.info("record:" + record.toString());
	        	}
*/
	        }
    	}
    	return list;
    	
	}
	

	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("brregEnhetsRegisteretService")
	private BrregEnhetsRegisteretService brregEnhetsRegisteretService;
	@Autowired
	@Required
	public void setBrregEnhetsRegisteretService (BrregEnhetsRegisteretService value){ this.brregEnhetsRegisteretService = value; }
	public BrregEnhetsRegisteretService getBrregEnhetsRegisteretService(){ return this.brregEnhetsRegisteretService; }
	
}

