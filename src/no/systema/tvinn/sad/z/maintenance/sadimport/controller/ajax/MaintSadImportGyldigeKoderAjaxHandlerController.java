package no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax;

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

import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsoRecord;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsdRecord;
import no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadKodtsaRecord;
import no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder.JsonMaintSadExportKodtseRecord;
import no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder.*;
import no.systema.tvinn.sad.z.maintenance.sadimport.controller.ajax.manager.MaintSadImportGyldigeKoderAjaxHandlerManager;

/**
 *  TVINN Maintenance Import SAD002 - Gyldige koder Controller 
 * 
 * @author oscardelatorre
 * @date May 16, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSadImportGyldigeKoderAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSadImportGyldigeKoderAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param applicationUser
	 * @param avgId
	 * @param skvId
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts1r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts1Record>getRecordSad002_kodts1
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts1r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts1Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts1(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts2r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts2Record>getRecordSad002_kodts2
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts2r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts2Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts2(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param avgId
	 * @param skvId
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts3r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts3Record>getRecordSad002_kodts3
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts3r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts3Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts3(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts4r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts4Record>getRecordSad002_kodts4
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts4r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts4Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts4(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts5r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts5Record>getRecordSad002_kodts5
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts5r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts5Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts5(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts6r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts6Record>getRecordSad002_kodts6
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts6r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts6Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts6(applicationUser, id);
    	
    	return result;
	
	}
	
	@RequestMapping(value="getSpecificRecord_sad002_kodts7r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts7Record>getRecordSad002_kodts7
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts7r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts7Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts7(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodts8r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodts8Record>getRecordSad002_kodts8
	  	(@RequestParam String applicationUser, @RequestParam String avgId, @RequestParam String skvId) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodts8r.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodts8Record> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodts8(applicationUser, avgId, skvId);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordImport_sad002_kodtsar.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsaRecord>getRecordSad002_kodtsa
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordImport_sad002_kodtsar.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsaRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodtsa(applicationUser, id);
    	
    	return result;
	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_sad002_kodtsbr.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadImportKodtsbRecord>getRecordSad002_kodtsb
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodtsbr.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadImportKodtsbRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodtsb(applicationUser, id);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordImport_sad002_kodtsdr.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsdRecord>getRecordSad002_kodtsd
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecordImport_sad002_kodtsdr.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsdRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodtsd(applicationUser, id);
    	
    	return result;
	
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	@RequestMapping(value="getSpecificRecordImport_sad002_kodtsor.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List <JsonMaintSadKodtsoRecord>getRecordSad002_kodtso
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getSpecificRecord_sad002_kodtsor.do ";
		logger.info(METHOD + " Inside...");
		List<JsonMaintSadKodtsoRecord> result = new ArrayList();
	 	//get table
		result = (List)this.maintSadImportGyldigeKoderAjaxHandlerManager.fetchListKodtso(applicationUser, id);
    	
    	return result;
	
	}
	
	
	@Qualifier ("maintSadImportGyldigeKoderAjaxHandlerManager")
	private MaintSadImportGyldigeKoderAjaxHandlerManager maintSadImportGyldigeKoderAjaxHandlerManager;
	@Autowired
	@Required
	public void setMaintSadImportGyldigeKoderAjaxHandlerManager (MaintSadImportGyldigeKoderAjaxHandlerManager value){ this.maintSadImportGyldigeKoderAjaxHandlerManager = value; }
	public MaintSadImportGyldigeKoderAjaxHandlerManager getMaintSadImportGyldigeKoderAjaxHandlerManager(){ return this.maintSadImportGyldigeKoderAjaxHandlerManager; }
	
	
	
	
}

