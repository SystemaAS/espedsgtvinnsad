package no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaRecord;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdRecord;

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.*;
import no.systema.tvinn.sad.z.maintenance.sadexport.controller.ajax.manager.MaintSadExportGyldigeKoderAjaxHandlerManager;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadImportKodtsbRecord;

/**
 *  TVINN Maintenance Export SAD002 - Gyldige koder Controller 
 * 
 * @author oscardelatorre
 * @date May 16, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadExportGyldigeKoderAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadExportGyldigeKoderAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param applicationUser
	 * @param avgId
	 * @param skvId
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts9r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadExportKodts9Record>getRecordSad002_kodts9
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts9r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadExportKodts9Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodts9(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodtscr.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadExportKodtscRecord>getRecordSad002_kodtsb
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodtscr.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadExportKodtscRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodtsc(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordExport_sad002_kodtsar.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsaRecord>getRecordSad002_kodtsa
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordExport_sad002_kodtsar.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsaRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodtsa(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordExport_sad002_kodtsdr.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsdRecord>getRecordSad002_kodtsd
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordImport_sad002_kodtsdr.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsdRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodtsd(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordExport_sad002_kodtser.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadExportKodtseRecord>getRecordSad002_kodtse
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordExport_sad002_kodtser.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadExportKodtseRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodtse(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordExport_sad002_kodtsor.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsoRecord>getRecordSad002_kodtso
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordExport_sad002_kodtsor.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsoRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadExportGyldigeKoderAjaxHandlerManager.fetchListKodtso(applicationUser, id);
    	
    	return result;
	
	}
	
	@Qualifier ("maintSadExportGyldigeKoderAjaxHandlerManager")
	private MaintSadExportGyldigeKoderAjaxHandlerManager maintSadExportGyldigeKoderAjaxHandlerManager;
	@Autowired
	@Required
	public void setMaintSadExportGyldigeKoderAjaxHandlerManager (MaintSadExportGyldigeKoderAjaxHandlerManager value){ this.maintSadExportGyldigeKoderAjaxHandlerManager = value; }
	public MaintSadExportGyldigeKoderAjaxHandlerManager getMaintSadExportGyldigeKoderAjaxHandlerManager(){ return this.maintSadExportGyldigeKoderAjaxHandlerManager; }
	
	
	
	
}

