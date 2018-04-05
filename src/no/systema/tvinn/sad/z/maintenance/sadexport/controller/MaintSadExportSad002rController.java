package no.systema.tvinn.sad.z.maintenance.sadexport.controller;

import java.util.*;

import org.apache.log4j.Logger;
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

import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceGyldigekoderListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;

/**
 * TVINN Maintenance Export SAD002 Controller 
 * 
 * @author oscardelatorre
 * @date Okt 26, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportSad002rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadExportSad002rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenanceexport_sad002r.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceexport_sad002r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//lists
			List list = this.populateGyldigekoderMainList();
			//init the rest
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @return
	 */
	private List<MaintenanceGyldigekoderListObject> populateGyldigekoderMainList(){
		List<MaintenanceGyldigekoderListObject> listObject = new ArrayList<MaintenanceGyldigekoderListObject>();
		MaintenanceGyldigekoderListObject object = new  MaintenanceGyldigekoderListObject();
		
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("9");
		object.setSubject("Ekspedisjonstyper");
		object.setText("SAD002 / KODTS9");
		object.setDbTable("KODTS9");
		object.setStatus("G");
		object.setPgm("sad002_kodts9r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("A");
		object.setSubject("Enhetskoder");
		object.setText("SAD002 / KODTSA");
		object.setDbTable("KODTSA");
		object.setStatus("G");
		object.setPgm("sad002_kodtsar");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("C");
		object.setSubject("Dok./Sertifikat kode");
		object.setText("SAD002 / KODTSC");
		object.setDbTable("KODTSC");
		object.setStatus("G");
		object.setPgm("sad002_kodtscr");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("D");
		object.setSubject("Lagringssted");
		object.setText("SAD002 / KODTSD");
		object.setDbTable("KODTSD");
		object.setStatus("G");
		object.setPgm("sad002_kodtsdr");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("E");
		object.setSubject("Fylkeskoder");
		object.setText("SAD002 / KODTSE");
		object.setDbTable("KODTSE");
		object.setStatus("G");
		object.setPgm("sad002_kodtser");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("O");
		object.setSubject("Typetilfelle (omberegning)");
		object.setText("SAD002 / KODTSO");
		object.setDbTable("KODTSO");
		object.setStatus("G");
		object.setPgm("sad002_kodtsor");
		listObject.add(object);
		return listObject;
		
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	

}

