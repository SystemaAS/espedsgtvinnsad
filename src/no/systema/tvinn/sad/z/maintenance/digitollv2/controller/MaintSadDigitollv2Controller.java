package no.systema.tvinn.sad.z.maintenance.digitollv2.controller;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.model.SystemaWebUser;

import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;

/**
 * TVINN Maintenance Manifest Express (Expressfortolling) Controller 
 * 
 * @author oscardelatorre
 * @date Oct. 2020
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
public class MaintSadDigitollv2Controller {
	private static final Logger logger = LoggerFactory.getLogger(MaintSadDigitollv2Controller.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_digitollv2.do", method=RequestMethod.GET)
	public ModelAndView doManifest(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_digitollv2");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_MAINTENANCE_DIGITOLLV2);
			session.setAttribute(TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_TVINN_SAD_MAINTENANCE, TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
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
	private List<MaintenanceMainListObject> populateMaintenanceMainList(){
		List<MaintenanceMainListObject> listObject = new ArrayList<MaintenanceMainListObject>();
		
		//SADMOAF
		MaintenanceMainListObject object = new  MaintenanceMainListObject();    
		object.setId("1");
		object.setSubject("Avd - Vedl. avdeling for Digitoll V2");
		object.setCode("SADMOAF");
		object.setDbTable("SADMOAF");
		object.setStatus("G");
		object.setPgm("sadmoaf");
		listObject.add(object);
		//SADMOCF
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Adv.melding - Parter - MasterId Outbound");
		object.setCode("SADMOCF");
		object.setDbTable("SADMOCF");
		object.setStatus("G");
		object.setPgm("sadmocf");
		listObject.add(object);
		
		return listObject;
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	

}

