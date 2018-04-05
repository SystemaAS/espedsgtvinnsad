package no.systema.tvinn.sad.z.maintenance.sadexport.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import no.systema.tvinn.sad.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.tvinn.sad.z.maintenance.main.util.TvinnSadMaintenanceConstants;

/**
 * TVINN Maintenance Export Topic Controller
 * 
 * @author oscardelatorre
 * @date Mar 26, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportController {
	private static final Logger logger = Logger.getLogger(MaintSadExportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");

	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "tvinnsadmaintenanceexport.do", method = RequestMethod.GET)
	public ModelAndView doExportList(HttpSession session, HttpServletRequest request) {
		ModelAndView successView = new ModelAndView("tvinnsadmaintenanceexport");
		SystemaWebUser appUser = (SystemaWebUser) session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);

		Map model = new HashMap();
		if (appUser == null) {
			return this.loginView;
		} else {
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_MAINTENANCE_EXPORT);
			session.setAttribute(TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_TVINN_SAD_MAINTENANCE,
					TvinnSadMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE);
			List list = this.populateMaintenanceMainList();
			model.put("list", list);
			successView.addObject(TvinnSadMaintenanceConstants.DOMAIN_MODEL, model);

			return successView;
		}
	}

	/**
	 * 
	 * @return
	 */
	private List<MaintenanceMainListObject> populateMaintenanceMainList() {
		List<MaintenanceMainListObject> listObject = new ArrayList<MaintenanceMainListObject>();
		MaintenanceMainListObject object = new MaintenanceMainListObject();
		object.setId("1");
		object.setSubject("Forendre Status på dekl.");
		object.setCode("SADE_STATUS");
		object.setText("SAD029 / SAEH,STS,KODTSI - Ref. GUI on SAD-Eksport");
		object.setDbTable("SAEH");
		object.setStatus("G");
		listObject.add(object);

		//
		object = new MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Avdelinger");
		object.setCode("SADE_AVD");
		object.setText("SYFTAE / STANDE - Ref.til Generelle Avd");
		object.setDbTable("STANDE");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new MaintenanceMainListObject();
		object.setId("3");
		object.setSubject("Løpenummer");
		object.setCode("SADE_LOPE");
		object.setText("SAD024 / SAEH,HEADF");
		object.setDbTable("SAEH-HEADF");
		object.setPgm("sad024");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new MaintenanceMainListObject();
		object.setId("4");
		object.setSubject("Fiskeavgifter");
		object.setCode("SADE_FISKAVG");
		object.setText("SAD015 / SADAVGE,TARI");
		object.setDbTable("SADAVGE");
		object.setPgm("sad015");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new MaintenanceMainListObject();
		object.setId("5");
		object.setSubject("Kunders vareregister");
		object.setCode("SADE_KUNDVAREREG");
		object.setText("SAD004 / SADL,FIRM,KODTSE,KODTSB,KODTS2,TARI,CUNDF");
		object.setDbTable("SADL");
		object.setPgm("sad004");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new MaintenanceMainListObject();
		object.setId("6");
		object.setSubject("Koder funksjonsfeil");
		object.setCode("SADE_FUNKFEIL");
		object.setText("TVI99D / TVINE");
		object.setDbTable("TVINE");
		object.setPgm("tvi99d");
		object.setStatus("G");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("7");
		object.setSubject("Gyldige koder (SAD)");
		object.setCode("SADI_GYLD_KODER");
		object.setText("SAD002 / KODTS9...");
		object.setDbTable("KODTS9...");
		object.setStatus("G");
		object.setPgm("sad002r");
		listObject.add(object);
		
		return listObject;
	}

	// SERVICES
	@Qualifier("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;

	@Autowired
	@Required
	public void setUrlCgiProxyService(UrlCgiProxyService value) {
		this.urlCgiProxyService = value;
	}

	public UrlCgiProxyService getUrlCgiProxyService() {
		return this.urlCgiProxyService;
	}

}
