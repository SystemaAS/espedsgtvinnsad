package no.systema.tvinn.sad.sadimport.util.manager;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.main.service.UrlCgiProxyService;
/*
import no.systema.tds.service.TdsBilagdaHandlingarYKoderService;
import no.systema.tds.service.TdsTillaggskoderService;
import no.systema.tds.model.jsonjackson.codes.JsonTdsBilagdaHandlingarYKoderRecord;
import no.systema.tds.model.jsonjackson.codes.JsonTdsTillaggskodRecord;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetContainer;
import no.systema.tds.model.jsonjackson.validation.JsonTdsMangdEnhetRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.util.manager.TdsImportControllerAjaxCommonFunctionsMgr;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemFormanskoderRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
*/
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainernrContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemContainernrRecord;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;


public class SadImportItemsContainernrMgr {
	private static final Logger logger = LogManager.getLogger(SadImportItemsContainernrMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private SadImportSpecificTopicItemService sadImportSpecificTopicItemService = null;
	NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	//Should be set after the constructor call
	private String avd = null;
	private String opd = null;
	private String lin = null;
	private String svcnr = null;
	
	
	/**
	 * 
	 * @param record
	 */
	public SadImportItemsContainernrMgr(SadImportSpecificTopicItemService sadImportSpecificTopicItemService, String avd, String opd, String lin, String svcnr){
		this.sadImportSpecificTopicItemService = sadImportSpecificTopicItemService;
		this.avd = avd;
		this.opd = opd;
		this.lin = lin;
		this.svcnr = svcnr;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @param lineNr
	 * @return
	 */
	public List<JsonSadImportSpecificTopicItemContainernrRecord> getContainernrList(String user){
		List<JsonSadImportSpecificTopicItemContainernrRecord> list = new ArrayList<JsonSadImportSpecificTopicItemContainernrRecord>();

		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_CONTAINERNR_ITEMLIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + user);
		urlRequestParams.append("&avd=" + this.avd);
		urlRequestParams.append("&opd=" + this.opd);
		urlRequestParams.append("&lin=" + this.lin);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonSadImportSpecificTopicItemContainernrContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemContainernrContainer(jsonPayload);
				if(container!=null){
					for(JsonSadImportSpecificTopicItemContainernrRecord  record : container.getContainerlist()){
						list.add(record);
						//logger.info("##:" + record.getSvcnr());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param recordToValidate
	 * @param mode
	 * @return
	 */
	public boolean updateContainernr(String user, String mode){
		boolean retval = true;

		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_UPDATE_CONTAINERNR_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + user);
		urlRequestParams.append("&avd=" + this.avd);
		urlRequestParams.append("&opd=" + this.opd);
		urlRequestParams.append("&lin=" + this.lin);
		urlRequestParams.append("&svcnr=" + this.svcnr);
		urlRequestParams.append("&mode=" + mode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		logger.info(jsonPayload);
		JsonSadImportSpecificTopicItemContainernrContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemContainernrContainer(jsonPayload);
				if(container!=null){
					
				}else{
				  retval = true;	
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return retval;
	}
	
	
}
	
