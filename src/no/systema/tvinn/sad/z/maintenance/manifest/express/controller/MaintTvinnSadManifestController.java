package no.systema.tvinn.sad.z.maintenance.manifest.express.controller;

import java.util.*;

import org.apache.logging.log4j.*;
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
@Scope("session")
public class MaintTvinnSadManifestController {
	private static final Logger logger = LogManager.getLogger(MaintTvinnSadManifestController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsadmaintenance_manifest.do", method=RequestMethod.GET)
	public ModelAndView doManifest(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("tvinnsadmaintenance_manifest");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_MAINTENANCE_EXPRESSFORTOLLING);
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
		MaintenanceMainListObject object = new  MaintenanceMainListObject();    
		
		object.setId("1");
		object.setSubject("Avd - Vedl. avdeling for ekspressfortolling");
		object.setCode("SADEFDEF");
		object.setDbTable("SADEFDEF");
		object.setStatus("G");
		object.setPgm("sadefdef");
		listObject.add(object);
		//
		/*object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Garantiref.");
		object.setCode("SADET_GARANTIREF");
		object.setText("TR030R / TRUGH,KODTVA,CUNDF,KODTS2,TRKODF");
		object.setDbTable("TRUGH");
		object.setPgm("tr030r");
		object.setStatus("G");
		listObject.add(object);
		*/	
		return listObject;
	}
	
	//SERVICES
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	

}

