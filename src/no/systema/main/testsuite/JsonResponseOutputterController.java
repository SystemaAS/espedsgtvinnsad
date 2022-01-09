package no.systema.main.testsuite;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.*;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
import no.systema.tvinn.sad.nctsimport.url.store.SadNctsImportUrlDataStore;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
//
import no.systema.tvinn.sad.z.maintenance.felles.url.store.TvinnSadMaintenanceFellesUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sad.url.store.TvinnSadMaintenanceUrlDataStoreGyldigeKoder;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadexport.url.store.TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStore;
import no.systema.tvinn.sad.z.maintenance.sadimport.url.store.TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder;
import no.systema.tvinn.sad.z.maintenance.nctsexport.url.store.TvinnNctsMaintenanceExportUrlDataStore;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Json Response Controller
 * 
 * This class is the controller driver for the testsuite - SKAT
 * All service-test communication from the outside world to espedsgskat.war is done through this gateway
 * 
 * @author oscardelatorre
 * @date Apr 09, 2018
 * 
 */

@Controller
public class JsonResponseOutputterController {
	private static Logger logger = LoggerFactory.getLogger(JsonResponseOutputterController.class.getName());
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static String JSON_START = "{";
	private static String JSON_END = "}";
	private static String JSON_QUOTES = "\"";
	private static String JSON_RECORD_SEPARATOR = ",";
	private static String JSON_FIELD_SEPARATOR = ",";
	
	private static String JSON_OPEN_LIST = "[";
	private static String JSON_CLOSE_LIST = "]";
	private static String JSON_OPEN_LIST_RECORD = "{";
	private static String JSON_CLOSE_LIST_RECORD = "}";
	//init the reflection manager here
	private JavaReflectionManager reflectionMgr = new JavaReflectionManager();
	
	
	/**
	 * Test call 
	 * http://localhost:8080/espedsgtvinnsad/sytsuite.do?user=OSCAR
	 * @return
	 */
	@RequestMapping(value="sytsuite.do", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String sytsuite( HttpSession session, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		try{
			logger.info("Inside sytsuite");
			//TEST-->logger.info("Servlet root:" + AppConstants.VERSION_SYJSERVICES);
			
			//get list
			logger.info("Init lists");
			List<Object> urlStoreObjectList = this.getUrlObjectList();
			List<TestersuiteObject> testersuiteObjectlist = new ArrayList<TestersuiteObject>(); 
			for(Object urlStoreObjRecord : urlStoreObjectList){
				logger.info("testing urlStore:" + urlStoreObjRecord.getClass().getName());
				reflectionMgr.buildList(testersuiteObjectlist, urlStoreObjRecord);
			}
			logger.info("After testersuiteObjectlist size;" + testersuiteObjectlist.size());
			
			//build the return JSON
			sb.append(JSON_START);
			sb.append(this.setFieldQuotes("user") + ":" + this.setFieldQuotes("OSCAR") + ",");
			sb.append(this.setFieldQuotes("list") + ":");
			sb.append(JSON_OPEN_LIST);
			int counter = 1;
			logger.info("Strax innan testersuiteObjectList loop...");
			for(TestersuiteObject record : testersuiteObjectlist){
				//logger.info("in the loop");
				if(counter>1){ sb.append(JSON_RECORD_SEPARATOR); }
				sb.append(JSON_OPEN_LIST_RECORD); 
				sb.append(JSON_QUOTES + "status" + JSON_QUOTES + ":" + JSON_QUOTES + jsonFixMgr.cleanRecord(record.getStatus()) + JSON_QUOTES);
				sb.append(JSON_FIELD_SEPARATOR );
				sb.append(JSON_QUOTES + "serviceUrl" + JSON_QUOTES + ":" + JSON_QUOTES + jsonFixMgr.cleanRecord(record.getServiceUrl()) + JSON_QUOTES);
				sb.append(JSON_FIELD_SEPARATOR );
				sb.append(JSON_QUOTES + "serviceName" + JSON_QUOTES + ":" + JSON_QUOTES + jsonFixMgr.cleanRecord(record.getServiceName()) + JSON_QUOTES);
				sb.append(JSON_FIELD_SEPARATOR );
				sb.append(JSON_QUOTES + "description" + JSON_QUOTES + ":" + JSON_QUOTES + jsonFixMgr.cleanRecord(record.getDescription()) + JSON_QUOTES);
				sb.append(JSON_FIELD_SEPARATOR );
				sb.append(JSON_QUOTES + "errMsg" + JSON_QUOTES + ":" + JSON_QUOTES + jsonFixMgr.cleanRecord(record.getErrMsg()) + JSON_QUOTES);
				sb.append(JSON_CLOSE_LIST_RECORD);
				counter++;
			}
			
			sb.append(JSON_CLOSE_LIST);
			sb.append(JSON_END);
			
			
		}catch(Exception e){
			session.invalidate();
			String error = "ERROR [JsonResponseOutputterController]" + e.toString();
			return error;
		}
	    session.invalidate();
		return sb.toString();
	}
	
	/**
	 * This list must be updated manually ...
	 * @param testModule
	 * @return
	 */
	private List<Object> getUrlObjectList(){
		List<Object> listObj= new ArrayList<Object>();
		Object urlStoreObj = null;
		
			urlStoreObj = new SadNctsExportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new SadNctsImportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new SadExportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new SadImportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnSadUrlDataStore();
			listObj.add(urlStoreObj);
			//
			urlStoreObj = new TvinnSadMaintenanceFellesUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new MaintenanceUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnNctsMaintenanceExportUrlDataStore();
			listObj.add(urlStoreObj);
			
			urlStoreObj = new TvinnSadMaintenanceUrlDataStoreGyldigeKoder();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnSadMaintenanceExportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnSadMaintenanceImportUrlDataStore();
			listObj.add(urlStoreObj);
			urlStoreObj = new TvinnSadMaintenanceImportUrlDataStoreGyldigeKoder();
			listObj.add(urlStoreObj);
			
			
		return listObj;
	}
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	private String setFieldQuotes(String value){
		String retval = value;
		retval = this.JSON_QUOTES + value + this.JSON_QUOTES;
		
		return retval;
	}
	
	
}

