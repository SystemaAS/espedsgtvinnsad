package no.systema.tvinn.sad.util.manager;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.*;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingRecord;
import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.jservices.common.util.CommonClientHttpRequestInterceptor;
import no.systema.jservices.common.util.CommonResponseErrorHandler;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveRecord;
import no.systema.tvinn.sad.manifest.express.controller.ajax.DtoNodeGoogleCloudApiContainer;
import no.systema.tvinn.sad.manifest.express.controller.ajax.DtoNodeGoogleCloudApiContainerTest;
import no.systema.tvinn.sad.manifest.express.controller.ajax.DtoNodeGoogleCloudApiFile;
import no.systema.tvinn.sad.manifest.express.controller.ajax.DtoNodeGoogleCloudApiFileTest;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.archive.JsonSadNctsExportSpecificTopicArchiveRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.logging.JsonSadNctsExportSpecificTopicLoggingRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive.JsonSadNctsImportSpecificTopicArchiveRecord;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging.JsonSadNctsImportSpecificTopicLoggingRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveContainer;

/**
 * 
 * @author oscardelatorre
 * Aug 2021
 * 
 */
@Service
public class ArchiveGoogleCloudManager {
	private static final Logger logger = LoggerFactory.getLogger(ArchiveGoogleCloudManager.class.getName());
	//private final String GOOGLE_BUCKET_PREFIX_URL = "https://storage.googleapis.com/gc_"; //complete should be e.g--> "https://storage.googleapis.com/gc_a12/si20210003100088296FVQzjftv.pdf"
	
	//API-SYSTEMA -->File passthrough, kan brukes for å vise fil til bruker. Returnere faktisk fil (pdf) :
	//GET http://10.13.3.22:9886/api/files?companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
	//private final String GOOGLE_BUCKET_PREFIX_URL = "http://10.13.3.22:9886/api/files?";
	
	private final String GOOGLE_BUCKET_PREFIX_URL = ApplicationPropertiesUtil.getProperty("archive.cloud.endpoint.prefix");
	
	private final String SAAS_ROOT_PATH_ON_FILE_SYSTEM = "/asp/";
	private final String SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE = "/ASP/";
	
	private final String LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM = "/pdf/";
	private final String EDI_EDISE_PATH_ON_FILE_SYSTEM = "/EDISE";
	private final String EDI_EDIRE_PATH_ON_FILE_SYSTEM = "/EDIRE";
	
	private final String GOOGLE = "google";
	private final String SYSTEMA_HOST_IP = "10.13.3.22";
	private final String SYSTEMA_HOST_DNS = "gw.systema.no";
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSadExportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSadExportSpecificTopicArchiveContainer container ) {
		JsonSadExportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadExportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
		
		return result;
	}
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSadImportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSadImportSpecificTopicArchiveContainer container ) {
		JsonSadImportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadImportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			logger.warn(record.getDocumentName());
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSkatNctsExportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSkatNctsExportSpecificTopicArchiveContainer container ) {
		JsonSkatNctsExportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSkatNctsExportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSadNctsImportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSadNctsImportSpecificTopicArchiveContainer container ) {
		JsonSadNctsImportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadNctsImportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	public JsonSadNctsImportSpecificTopicLoggingContainer adjustUrl(SystemaWebUser appUser, JsonSadNctsImportSpecificTopicLoggingContainer container ) {
		JsonSadNctsImportSpecificTopicLoggingContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadNctsImportSpecificTopicLoggingRecord record : container.getLogg()) {
			String originalUrl = record.getWurl();
			record.setWurl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setLogg(newList);
		}
				
		return result;
	}
	
	public JsonSadNctsExportSpecificTopicLoggingContainer adjustUrl(SystemaWebUser appUser, JsonSadNctsExportSpecificTopicLoggingContainer container ) {
		JsonSadNctsExportSpecificTopicLoggingContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadNctsExportSpecificTopicLoggingRecord record : container.getLogg()) {
			String originalUrl = record.getWurl();
			record.setWurl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setLogg(newList);
		}
				
		return result;
	}
	
	public JsonSadExportSpecificTopicLoggingContainer adjustUrl(SystemaWebUser appUser, JsonSadExportSpecificTopicLoggingContainer container ) {
		JsonSadExportSpecificTopicLoggingContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadExportSpecificTopicLoggingRecord record : container.getLogg()) {
			String originalUrl = record.getWurl();
			record.setWurl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setLogg(newList);
		}
				
		return result;
	}
	
	public JsonSadImportSpecificTopicLoggingContainer adjustUrl(SystemaWebUser appUser, JsonSadImportSpecificTopicLoggingContainer container ) {
		JsonSadImportSpecificTopicLoggingContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSadImportSpecificTopicLoggingRecord record : container.getLogg()) {
			String originalUrl = record.getWurl();
			record.setWurl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setLogg(newList);
		}
				
		return result;
	}
	
	
	public JsonTvinnSadManifestArchivedDocsContainer adjustUrl(SystemaWebUser appUser, JsonTvinnSadManifestArchivedDocsContainer container ) {
		JsonTvinnSadManifestArchivedDocsContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonTvinnSadManifestArchivedDocsRecord record : container.getGetdoc()) {
			String originalUrl = record.getDoclnk();
			record.setDoclnk(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setGetdoc(newList);
		}
				
		return result;
	}
	
	
	/**
	 * Special case for ZEM since this is retrieve with JAVA services (through db-table:ARKIVP) and not with RPG-service pgm
	 * 
	 * @param appUser
	 * @param originalPdfDir
	 * @param list
	 * @return
	 */
	public Collection<ArkivpDao> adjustUrl(SystemaWebUser appUser, String originalPdfDir, Collection<ArkivpDao> list) {
		Collection<ArkivpDao> newList = new ArrayList<ArkivpDao>();
		
		//change the url to the external google cloud url
		for(ArkivpDao record : list) {
			String originalUrl = originalPdfDir + record.getArlink();
			record.setOwn_url(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		return newList;
		
	}
	
	/**
	 * Call to the Node API at Systema is:
	 * Example
	 * POST https://gw.systema.no:9886/api/getfiles
	 *	Content-Type: application/json	
	 *	{ "LocalWritePath": "/temp/", "Files": [ { "Companyid": "a12", "Filename": "si201300170000658WtGAj87xw0.pdf" } ] }
	 * 
	 * @param gcFile
	 * @return
	 */
	
	//Denna kan inte användas förrän API:et ger support till java naming convention. Inte Companyid men också companyid...
	private String downloadPdfFromGoogleCloud(String gcFile) {
		//adjust to the getfiles end-point
		int x = this.GOOGLE_BUCKET_PREFIX_URL.indexOf("/files?");
		String apiRoot = this.GOOGLE_BUCKET_PREFIX_URL.substring(0,x);
		
		RestTemplate restTemplate = this.restTemplate();
		String tmpFileAbsolutePath = null;
		//TEST 
		//DtoContainerTest dtoContainer = this.getNodeApiParamsTest(gcFile);
		//PROD 
		DtoNodeGoogleCloudApiContainer dtoContainer = this.getNodeApiParams(gcFile);
		logger.warn(dtoContainer.toString());
		
		URI uri = UriComponentsBuilder
				.fromUriString(apiRoot)
				.path("/getfiles")
				.queryParam("LocalWritePath", dtoContainer.getLocalWritePath())
				.queryParam("Files", dtoContainer.getFiles() ) 
				.build()
				.toUri();
		
		try {
			HttpHeaders headerParams = new HttpHeaders();
			//headerParams.add("Accept", "*/*");
			headerParams.add("Content-Type", "application/json");
			
			HttpEntity<?> entity = new HttpEntity<>(headerParams);
			
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
			String json = response.getBody();
			logger.warn(json);
			//Build the new file path
			for(DtoNodeGoogleCloudApiFile record: dtoContainer.getFiles()) {
				tmpFileAbsolutePath = dtoContainer.getLocalWritePath() + record.getFilename();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("tmpFileAbsolutePath:" + tmpFileAbsolutePath);
		
		return tmpFileAbsolutePath;
		
	}
	
	
	public String downloadPdfFromGoogleCloudSimple(String gcFile) {
		//adjust to the getfiles end-point
		int x = this.GOOGLE_BUCKET_PREFIX_URL.indexOf("/files?");
		String apiRoot = this.GOOGLE_BUCKET_PREFIX_URL.substring(0,x);
				
		RestTemplate restTemplate = this.restTemplate();
		String tmpFileAbsolutePath = null;
		try {
			//TEST 
			DtoNodeGoogleCloudApiContainer dtoContainer = this.getNodeApiParams(gcFile);
			ObjectWriter ow = new ObjectMapper().writer();//.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(dtoContainer);
			logger.warn("A:" + json);
			//json = ow.writeValueAsString(dtoContainer.getFiles());
			//logger.info("B:" + json);
			
			String url = apiRoot + "/getfiles";
			String requestJson = json;
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
		
			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			logger.warn(response.getStatusCode().toString());
			String answer = restTemplate.postForObject(url, entity, String.class);
			logger.warn(answer);
			
			//Build the new file path (in local machine)
			for(DtoNodeGoogleCloudApiFile record: dtoContainer.getFiles()) {
				tmpFileAbsolutePath = dtoContainer.getLocalWritePath() + record.getFilename();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.warn("tmpFileAbsolutePath:" + tmpFileAbsolutePath);
		
		return tmpFileAbsolutePath;
		
	}
	
	public String downloadPdfFromGoogleCloudTest(String gcFile) {
		//adjust to the getfiles end-point
		int x = this.GOOGLE_BUCKET_PREFIX_URL.indexOf("/files?");
		String apiRoot = this.GOOGLE_BUCKET_PREFIX_URL.substring(0,x);
				
		RestTemplate restTemplate = this.restTemplate();
		String tmpFileAbsolutePath = null;
		try {
			//TEST 
			DtoNodeGoogleCloudApiContainerTest dtoContainer = this.getNodeApiParamsTest(gcFile);
			ObjectWriter ow = new ObjectMapper().writer();//.withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(dtoContainer);
			logger.warn("A:" + json);
			//json = ow.writeValueAsString(dtoContainer.getFiles());
			//logger.info("B:" + json);
			
			String url = apiRoot + "/getfiles";
			String requestJson = json;
		
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
		
			HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			logger.warn(response.getStatusCode().toString());
			String answer = restTemplate.postForObject(url, entity, String.class);
			logger.warn(answer);
			
			//Build the new file path (in local machine)
			for(DtoNodeGoogleCloudApiFileTest record: dtoContainer.getFiles()) {
				tmpFileAbsolutePath = dtoContainer.getLocalWritePath() + record.getFilename();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		logger.info("tmpFileAbsolutePath:" + tmpFileAbsolutePath);
		
		return tmpFileAbsolutePath;
		
	}
	
	/**
	 * Extract values from parameters in order to comply to the Node API getfiles POST method
	 * gcFile example value: http://gw.systema.no:9886/api/files?companyid=a24&filename=4CR_4bb36639.pdf
	 * @param gcFile
	 * @return
	 */
	private DtoNodeGoogleCloudApiContainer getNodeApiParams(String gcFile){
		//Map<String,String> map = new HashMap<String, String>();
		DtoNodeGoogleCloudApiContainer dtoContainer = new DtoNodeGoogleCloudApiContainer();
		String COMPANY_ID = "companyid=";
		String FILE_NAME = "&filename=";
		String AS400_TEMP_DIR = "/temp/"; //always this in our machine
		
		String tmp =gcFile;
		int i = tmp.indexOf(COMPANY_ID);
		int j = tmp.indexOf(FILE_NAME);
		String rawCompanyId = tmp.substring(i,j);
		String rawFilename = tmp.substring(j);
		String companyId = rawCompanyId.replace(COMPANY_ID, "");
		String fileName = rawFilename.replace(FILE_NAME, "");
		//fill Dtos
		DtoNodeGoogleCloudApiFile dtoFile = new DtoNodeGoogleCloudApiFile();
		dtoFile.setCompanyid(companyId);
		dtoFile.setFilename(fileName);
		List list = new ArrayList();
		list.add(dtoFile);
		//
		dtoContainer.setFiles(list);
		dtoContainer.setLocalWritePath(AS400_TEMP_DIR);
		
		return dtoContainer;
	}
	
	private DtoNodeGoogleCloudApiContainerTest getNodeApiParamsTest(String gcFile){
		//Map<String,String> map = new HashMap<String, String>();
		DtoNodeGoogleCloudApiContainerTest dtoContainer = new DtoNodeGoogleCloudApiContainerTest();
		String COMPANY_ID = "companyid=";
		String FILE_NAME = "&filename=";
		String AS400_TEMP_DIR = "/temp/"; //always this in our machine
		
		String tmp =gcFile;
		int i = tmp.indexOf(COMPANY_ID);
		int j = tmp.indexOf(FILE_NAME);
		String rawCompanyId = tmp.substring(i,j);
		String rawFilename = tmp.substring(j);
		String companyId = rawCompanyId.replace(COMPANY_ID, "");
		String fileName = rawFilename.replace(FILE_NAME, "");
		//fill Dtos
		DtoNodeGoogleCloudApiFileTest dtoFile = new DtoNodeGoogleCloudApiFileTest();
		dtoFile.setCompanyid("a12");
		dtoFile.setFilename("si201300170000658WtGAj87xw0.pdf");
		List list = new ArrayList();
		list.add(dtoFile);
		//
		dtoContainer.setFiles(list);
		dtoContainer.setLocalWritePath(AS400_TEMP_DIR);
		
		return dtoContainer;
	}
	/**
	 * 
	 * @param suffix
	 * @param strToReplace
	 * @return
	 */
	private String adjustPdfSuffix(String suffix, String strToReplace) {
		String retval = suffix.replace(strToReplace, "");
		
		//Exception for A25 and some others. We must convert all directories to lower case (google cloud will have only lower case)
		if(strToReplace.equals(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE)) {
			int index = suffix.lastIndexOf("/");
			String dirs = suffix.substring(0, index);
			String file = suffix.substring(index);
			//change all directories to lower case
			dirs = dirs.toLowerCase();
			dirs = dirs.replace(SAAS_ROOT_PATH_ON_FILE_SYSTEM, "");
			//concat. Will end up with this result string: "a25/xxxxx.pdf"
			retval = dirs + file;
			
		}
		
		return retval;
		
	}
	/**
	 * Special case in case we are using SYSTEMAs API and not Google directly 
	 * @param suffix
	 * @param strToReplace
	 * @return
	 */
	private String adjustPdfApiSuffix(String suffix, String strToReplace) {
		String result = "";
		String tmp = this.adjustPdfSuffix(suffix, strToReplace);
		logger.warn(tmp);
		//We now have a string in this format: a12/xxxx.pdf
		//We aim to have this suffix: "companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
		int index = tmp.lastIndexOf("/");
		String companyid = tmp.substring(0, index);
		String filename = tmp.substring(index + 1);
		
		result = "companyid=" + companyid + "&filename=" + filename;
		
		return result;
		
	}
	/**
	 * 
	 * @param suffix
	 * @param strToReplace
	 * @return
	 */
	private String adjustPdfApiSuffixLocalhost(String suffix, String strToReplace) {
		String LOCALHOST_BUCKET_ON_GOOGLE = "sytst"; //Systemas own test env on the cloud
		String result = "";
		String tmp = this.adjustPdfSuffix(suffix, strToReplace);
		logger.warn(tmp);
		//We now have a string in this format: a12/xxxx.pdf
		//We aim to have this suffix: "companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
		int index = tmp.lastIndexOf("/");
		//String companyid = tmp.substring(0, index);
		String companyid = LOCALHOST_BUCKET_ON_GOOGLE;
		String filename = tmp.substring(index + 1);
		
		result = "companyid=" + companyid + "&filename=" + filename;
		
		return result;
		
	}
	
	/**
	 * 
	 * @param url
	 * @param isSaas
	 * @return
	 */
	private String adjustEdiFileApiSuffix(String url, boolean isSaas) {
		String LOCALHOST_BUCKET_ON_GOOGLE = "sytst"; //Systemas own test env on the cloud
		String result = "";
		//We aim to have this suffix: "companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
		int index = url.lastIndexOf("/");
		//String companyid = tmp.substring(0, index);
		String companyid = LOCALHOST_BUCKET_ON_GOOGLE;
		if(isSaas) { companyid = url.substring(1,4).toLowerCase(); }
		String filename = url.substring(index + 1);
		
		result = "companyid=" + companyid + "&filename=" + filename;
		logger.warn("google cloud edi-file:" + result);
		return result;
		
	}
	
	/**
	 * 
	 * @param appUser
	 * @param url
	 * @return
	 */
	private String setAdjustedUrl(SystemaWebUser appUser, String url) {
		String retval = url;
		logger.warn("original url:" + url);
		if(StringUtils.isNotEmpty(url) && !url.contains("http")) {
			if(url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM) || url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE) ) {
				logger.warn("Saas!");
				logger.warn("local url:" + url);
				if(!new File(url).exists()){
					logger.warn("File does not exists locally!...going to google cloud ...");
					String strToReplace = SAAS_ROOT_PATH_ON_FILE_SYSTEM;
					if(url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE)) { strToReplace = SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE; }
					
					if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.GOOGLE)) {
						//do it
						retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfSuffix(url, strToReplace);
						logger.warn("cloud url:" + retval);
						
					}else if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_IP) || GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_DNS)) {
						//implement other API in case the direct google API is not used. Probably an inhouse API (Vidars)
						retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfApiSuffix(url, strToReplace);
						logger.warn("api url:" + retval);
						
					}
				}
				
			}else if(url.toLowerCase().startsWith(LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM) && (appUser.getServletHostWithoutHttpPrefix().equals("localhost") || appUser.getServletHostWithoutHttpPrefix().equals("gw.systema.no"))) {
				logger.warn("localhost! or gw.systema test");
				logger.info("local url:" + url);
				if(!new File(url).exists()){
					logger.warn("File does not exists locally!...going to google cloud ...");
					String strToReplace = LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM;
					
					if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.GOOGLE)) {
						//do it
						retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfSuffix(url, strToReplace);
						// TEST record.setUrl(googleBucketPrefix + "a12/si20210003100088296FVQzjftv.pdf");
						logger.warn("cloud url:" + retval);
						
					}else if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_IP) || GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_DNS)) {
						//implement other API in case the direct google API is not used. Probably an inhouse API (Vidars)
						retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfApiSuffixLocalhost(url, strToReplace);
						logger.warn("api url:" + retval);
						
					}
				}
				
			}else if(url.contains(this.EDI_EDISE_PATH_ON_FILE_SYSTEM) || url.contains(this.EDI_EDIRE_PATH_ON_FILE_SYSTEM)) {
				//branch for the EDI logg files on (text files or xml)
				logger.warn("EDI log files " + url);
				if(!new File(url).exists()){
					logger.warn("File does not exists locally!...going to google cloud ...");
					//branch for SaaS:ar and local test
					if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_IP) || GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_DNS)) {
						//implement other API in case the direct google API is not used. Probably an inhouse API (Vidars)
						boolean isSaas = false;
						if(url.startsWith("/A")) { isSaas = true;}
						retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustEdiFileApiSuffix(url, isSaas);
						logger.warn("api url:" + retval);
						
					}
				}
			}
			logger.warn("adjusted url:" + retval);
		}else {
			logger.warn("original and adjusted url:" + retval);
		}
		
		return retval;
	}
	
	
	
	
	
	@Bean
	private RestTemplate restTemplate(){
		//Too simple-->RestTemplate restTemplate = new RestTemplate();
		
		//this factory is required in order not to lose the response body when getting a HttpClientErrorException on restTemplate (in an Interceptor)
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory());
    	RestTemplate restTemplate = new RestTemplate(factory);
    	restTemplate.setInterceptors(Collections.singletonList(new CommonClientHttpRequestInterceptor()));
		restTemplate.setErrorHandler(new CommonResponseErrorHandler());
		
		// Add the Jackson message converter for json payloads
		//restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		return restTemplate;  
		
	} 
	
	
}
