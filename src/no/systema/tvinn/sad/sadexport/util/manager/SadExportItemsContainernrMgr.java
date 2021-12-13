package no.systema.tvinn.sad.sadexport.util.manager;

import java.util.*;

import org.apache.logging.log4j.*;

import no.systema.main.model.SystemaWebUser;
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
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.sadexport.util.RpgReturnResponseHandler;
import no.systema.tvinn.sad.sadexport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;
import no.systema.tvinn.sad.sadexport.service.SadExportSpecificTopicItemService;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainernrRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.model.jsonjackson.JsonSadAutoControlErrorContainer;



public class SadExportItemsContainernrMgr {
	private static final Logger logger = LogManager.getLogger(SadExportItemsContainernrMgr.class.getName());
	private UrlCgiProxyService urlCgiProxyService = null;
	private SadExportSpecificTopicItemService sadExportSpecificTopicItemService = null;
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
	public SadExportItemsContainernrMgr(SadExportSpecificTopicItemService sadExportSpecificTopicItemService, String avd, String opd, String lin, String svcnr){
		this.sadExportSpecificTopicItemService = sadExportSpecificTopicItemService;
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
	public List<JsonSadExportSpecificTopicItemContainernrRecord> getContainernrList(String user){
		List<JsonSadExportSpecificTopicItemContainernrRecord> list = new ArrayList<JsonSadExportSpecificTopicItemContainernrRecord>();

		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_CONTAINERNR_ITEMLIST_URL;
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
		JsonSadExportSpecificTopicItemContainernrContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemContainernrContainer(jsonPayload);
				if(container!=null){
					for(JsonSadExportSpecificTopicItemContainernrRecord  record : container.getContainerlist()){
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
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_UPDATE_CONTAINERNR_URL;
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
		JsonSadExportSpecificTopicItemContainernrContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.sadExportSpecificTopicItemService.getSadExportSpecificTopicItemContainernrContainer(jsonPayload);
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
	
