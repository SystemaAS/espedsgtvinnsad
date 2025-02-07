package no.systema.tvinn.sad.manifest.express.controller.ajax;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.jservices.common.util.CommonClientHttpRequestInterceptor;
import no.systema.jservices.common.util.CommonResponseErrorHandler;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestExpressMgr;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.util.manager.ArchiveGoogleCloudManager;



/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
@Controller
public class TvinnSadManifestAjaxHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadManifestAjaxHandlerController.class.getName());
	private DateTimeManager dateMgr = new DateTimeManager();
	private final String GOOGLE_BUCKET_PREFIX_URL = ApplicationPropertiesUtil.getProperty("archive.cloud.endpoint.prefix");
	
	/**
	 * Gets a specific cargo line
	 * @param applicationUser
	 * @param tur
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificCargoLine_TvinnSadManifest.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTvinnSadManifestCargoLinesRecord> getSpecificCargoLine
	  						(@RequestParam String applicationUser, @RequestParam String htmlParams, HttpSession session ) {
		 logger.warn("Inside: getSpecificCargoLine_TvinnSadManifest.do()");
		 logger.warn(htmlParams);
		 Set result = new HashSet();
		 String[] params = htmlParams.split("@");
		 String pro = params[0].replace("clpro_", "");
		 String tdn = params[1].replace("cltdn_", "");
		 String avd = params[2].replace("clavd_", "");
		 
		 if(StringUtils.isNotEmpty(pro) && StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(tdn) ){
		 
			 //prepare the access CGI with RPG back-end
			 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL;
			 String urlRequestParams = "user=" + applicationUser + "&clpro=" + pro + "&clavd=" + avd + "&cltdn=" + tdn; 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 if(jsonPayload!=null){
				
				JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();
				for(JsonTvinnSadManifestCargoLinesRecord record : outputList){
					this.adjustFieldsForFetch(record);
					//must use the session user for Google Archive ...
					SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
					Collection listDocs = this.manifestExpressMgr.fetchArchiveDocs(appUser, avd, tdn);
					if(listDocs!=null){
						record.setGetdocs(listDocs);
					}
					result.add(record);
				}
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param requestParams
	 * @return
	 */
	@RequestMapping(value = "bindOppdragToTur_TvinnSadManifest.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTvinnSadManifestCargoLinesRecord> bindOppdragToTur
	  						(@RequestParam String applicationUser, @RequestParam String requestParams ) {
		 logger.warn("Inside: bindOppdragToTur_TvinnSadManifest.do()");
		 logger.warn(requestParams);
		 Set result = new HashSet();
		 
		 
		 //prepare the access CGI with RPG back-end
		 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_UPDATE_MANIFEST_EXPRESS_CARGOLINES_URL;
		 String urlRequestParams = "user=" + applicationUser + requestParams; 
		 logger.warn("URL: " + BASE_URL);
		 logger.warn("URL PARAMS: " + urlRequestParams);
		
		 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
		 if(jsonPayload!=null){
			
			JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
			//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
			Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();
			for(JsonTvinnSadManifestCargoLinesRecord record : outputList){
				this.adjustFieldsForFetch(record);
				result.add(record);
			}
		 }
		 
		 return result;
	 }
	
	
	/**
	 * Entry point in order to upload file to toll.no
	 * @param applicationUser
	 * @param docType
	 * @param docPath
	 * @param declNr
	 * @param declDate
	 * @param declSekvens
	 * @return
	 */
	//TODO
	@RequestMapping(value = "sendFileToToll_TvinnSadManifest.do", method = RequestMethod.GET)
	public @ResponseBody Set<String> sendFileToToll
	  						(@RequestParam String applicationUser, @RequestParam String docType, @RequestParam String docPath,
	  						 @RequestParam String declNr, @RequestParam String declDate, @RequestParam String declSekvens) {
		 
			logger.warn("Inside: sendFileToToll_TvinnSadManifest.do");
			logger.warn(docType);
			logger.warn(docPath);
			logger.warn(declNr);
			logger.warn(declDate);
			logger.warn(declSekvens);
			 
			Set result = new HashSet();
			//format NO date to ISO-reversed	 
			
			String declDateFormatted = dateMgr.getDateFormatted_ISO(declDate, DateTimeManager.NO_FORMAT, DateTimeManager.ISO_FORMAT_REVERSED);
			String declId = declNr + "-" + declDateFormatted + "-" + declSekvens;
			
			//find type
			String docTypeFormatted = this.getDocumentType(docType, docPath);
			
			if(docPath.startsWith("http")) {
				//Download to local directory from Google Cloud
				if(applicationUser.equalsIgnoreCase("OSCAR")) {
					logger.warn("####### -->Using downloadPdfFromGoogleCloudTest (hardcoded A12 + .pdf just for testing ...");
					docPath = new ArchiveGoogleCloudManager().downloadPdfFromGoogleCloudTestSimple(docPath);
				}else {
					docPath = new ArchiveGoogleCloudManager().downloadPdfFromGoogleCloudSimple(docPath);
				}
			}
			//old version with maskinporten token
			//String url = TvinnSadManifestUrlDataStore.TVINN_SAD_SEND_DOCUMENT_TO_TOLL_URL;
			
			//new version with maskinporten token + toll token
			String url = TvinnSadManifestUrlDataStore.TVINN_SAD_SEND_DOCUMENT_TO_TOLL_URL_V2;
			String BASE_URL = url;
	 		StringBuffer urlRequestParamsKeys = new StringBuffer();
	 		urlRequestParamsKeys.append("user=" + applicationUser + "&declId=" + declId + "&docType=" + docTypeFormatted );
	 		urlRequestParamsKeys.append("&docPath=" + docPath );
	 		logger.warn("URL: " + BASE_URL);
	 		logger.warn("PARAMS: " + urlRequestParamsKeys.toString());
	 		
	 		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
	 		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
	 		//Debug -->
		    logger.warn(jsonPayload);
	 		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	 	 
		 	result.add(jsonPayload);
		 	
		 	return result;
	 }
	

	
	/**
	 * 
	 * @param applicationUser
	 * @param htmlParams
	 * @return
	 */
	@RequestMapping(value = "getSpecificDefaultValue_TvinnSadManifest.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTvinnSadManifestRecord> getSpecificDefaultRecord
	  						(@RequestParam String applicationUser, @RequestParam String htmlParams ) {
		 logger.warn("Inside: getSpecificDefaultValue_TvinnSadManifest.do()");
		 logger.warn(htmlParams);
		 Set result = new HashSet();
		 String[] params = htmlParams.split("@");
		 String avd = params[0].replace("efavd_", "");
		 
		 if(StringUtils.isNotEmpty(avd)){
		 
			 //prepare the access CGI with RPG back-end
			 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_DEFAULT_VALUES_EXPRESS_URL;
			 String urlRequestParams = "user=" + applicationUser + "&efavd=" + avd; 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 if(jsonPayload!=null){
				
				 JsonTvinnSadManifestContainer container = this.tvinnSadManifestListService.getListContainerDefaultValues(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				if(container!=null){ 
					Collection<JsonTvinnSadManifestRecord> outputList = container.getList();
					for(JsonTvinnSadManifestRecord record : outputList){
						this.adjustFieldsForFetch(record);
						result.add(record);
					}
				}
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }
	/**
	 * 
	 * @param docType
	 * @param docPath
	 * @return
	 */
	private String getDocumentType(String docType, String docPath){
		//docType is too bad to elucidate something. We use the docPath since this carries information about type
		String retval = "faktura";
		if(docPath!=null){
			if(docPath.contains("/ZH") || docPath.contains("/ZO")){
				retval = "faktura";
			}else{
				//other types here (tillatelser, fraktregning, opprinnelsesdokumentasjon, fraktbrev)
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param record
	 */
	private void adjustFieldsForUpdate(JsonTvinnSadManifestCargoLinesRecord record){
		record.setCl0068a(new ManifestDateManager().convertToDate_ISO(record.getCl0068a()));
	}
	/**
	 * 
	 * @param record
	 */
	private void adjustFieldsForFetch(JsonTvinnSadManifestCargoLinesRecord record){
		record.setCl0068a(new ManifestDateManager().convertToDate_NO(record.getCl0068a()));
	}
	/**
	 * 
	 * @param record
	 */
	private void adjustFieldsForFetch(JsonTvinnSadManifestRecord record){
		if(StringUtils.isNotEmpty(record.getEfeta()) && !"0".equals(record.getEfeta())){
			record.setEfeta(new ManifestDateManager().convertToDate_NO(record.getEfeta()));
		}
		logger.debug(record.getEfsjadt());
		if(StringUtils.isNotEmpty(record.getEfsjadt()) && !"0".equals(record.getEfsjadt())){
			record.setEfsjadt(new ManifestDateManager().convertToDate_NO(record.getEfsjadt()));
		}
	}
	

	//SERVICES
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired
	private ManifestExpressMgr manifestExpressMgr;
}
