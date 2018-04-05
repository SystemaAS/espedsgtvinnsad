package no.systema.tvinn.sad.z.maintenance.sadimport.controller;

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
 * TVINN Maintenance Import SAD002 Controller 
 * 
 * @author oscardelatorre
 * @date May 16, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportSad002rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportSad002rController.class.getName());
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
	@RequestMapping(value="tvinnsadmaintenanceimport_sad002r.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceimport_sad002r");
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
		
		object.setId("1");
		object.setSubject("Ekspeditionstyper");
		object.setText("SAD002 / KODTS1");
		object.setDbTable("KODTS1");
		object.setStatus("G");
		object.setPgm("sad002_kodts1r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("2");
		object.setSubject("Landkoder");
		object.setText("SAD002 / KODTS2");
		object.setDbTable("KODTS2");
		object.setStatus("G");
		object.setPgm("sad002_kodts2r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("3");
		object.setSubject("Transaksjonstyper");
		object.setText("SAD002 / KODTS3");
		object.setDbTable("KODTS3");
		object.setStatus("G");
		object.setPgm("sad002_kodts3r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("4");
		object.setSubject("Transportmåter");
		object.setText("SAD002 / KODTS4");
		object.setDbTable("KODTS4");
		object.setStatus("G");
		object.setPgm("sad002_kodts4r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("5");
		object.setSubject("Tollnedsettelser");
		object.setText("SAD002 / KODTS5");
		object.setDbTable("KODTS5");
		object.setStatus("G");
		object.setPgm("sad002_kodts5r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("6");
		object.setSubject("Preferanser");
		object.setText("SAD002 / KODTS6");
		object.setDbTable("KODTS6");
		object.setStatus("G");
		object.setPgm("sad002_kodts6r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("7");
		object.setSubject("V.F.koder");
		object.setText("SAD002 / KODTS7");
		object.setDbTable("KODTS7");
		object.setStatus("G");
		object.setPgm("sad002_kodts7r");
		listObject.add(object);
		//
		object = new  MaintenanceGyldigekoderListObject();
		object.setId("8");
		object.setSubject("Avgiftskoder");
		object.setText("SAD002 / KODTS8");
		object.setDbTable("KODTS8");
		object.setStatus("G");
		object.setPgm("sad002_kodts8r");
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
		object.setId("B");
		object.setSubject("Dok./Sertifikat kode");
		object.setText("SAD002 / KODTSB");
		object.setDbTable("KODTSB");
		object.setStatus("G");
		object.setPgm("sad002_kodtsbr");
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

