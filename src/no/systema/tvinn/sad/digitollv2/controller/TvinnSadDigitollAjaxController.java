package no.systema.tvinn.sad.digitollv2.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.digitollv2.controller.service.HouseControllerService;
import no.systema.tvinn.sad.digitollv2.controller.service.MasterControllerService;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmohfStatus2;
import no.systema.tvinn.sad.digitollv2.enums.EnumSadmomfStatus2;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.EoriValidationContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.EoriValidationDto;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadOppdragRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadTurRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoafRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmohfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmomfRecord;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfRecord;
import no.systema.tvinn.sad.digitollv2.service.GeneralUpdateService;
import no.systema.tvinn.sad.digitollv2.service.SadOppdragService;
import no.systema.tvinn.sad.digitollv2.service.SadTurService;
import no.systema.tvinn.sad.digitollv2.service.SadmoafListService;
import no.systema.tvinn.sad.digitollv2.service.SadmohfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.service.SadmomfListService;
import no.systema.tvinn.sad.digitollv2.service.SadmotfListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.mapper.url.request.UrlRequestParameterMapper;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerContainer;
import no.systema.tvinn.sad.model.jsonjackson.customer.JsonTvinnSadCustomerRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.service.TvinnSadCustomerService;
import no.systema.tvinn.sad.util.TvinnSadConstants;


@RestController
public class TvinnSadDigitollAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollAjaxController.class.getName());
	private DateTimeManager dateMgr = new DateTimeManager();
	private String HYPHEN = "-";
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param applicationUser
	 * @param eili
	 * @param eilnrt
	 * @param eilnrm
	 * @param eilnrh
	 * @return
	 */
	@RequestMapping(value = "searchCustomer_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTvinnSadCustomerRecord> searchCustomer(@RequestParam String applicationUser, @RequestParam String customerName, @RequestParam String customerNumber) {
		  logger.info("Inside searchCustomer");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_CUSTOMER_URL;
		  String urlRequestParamsKeys = this.getRequestUrlKeyParametersForSearchCustomer(applicationUser, customerName, customerNumber);
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		  //Should be removed as soon as RPG return the correct container name = customerlist (not capitalized in the first letter)
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	    		JsonTvinnSadCustomerContainer container = this.tvinnSadCustomerService.getTvinnSadCustomerContainer(jsonPayload);
	    		if(container!=null){
	    			for(JsonTvinnSadCustomerRecord  record : container.getCustomerlist()){
	    				logger.info("CUSTOMER via AJAX: " + record.getKnavn() + " NUMBER:" + record.getKundnr());
	    				logger.info("KPERS: " + record.getKpers() + " TLF:" + record.getTlf());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificGoodsItemVoec_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadImportTopicFinansOpplysningerRecord> getSpecificGoodsItemVoec_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String eili, 
	  						 @RequestParam String eilnrt, @RequestParam String eilnrm , @RequestParam String eilnrh) {
		 
		 final String METHOD = "[DEBUG] getSpecificGoodsItemVoec_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		 String urlRequestParams = "user=" + applicationUser + "&eili=" + eili + "&eilnrt=" + eilnrt + "&eilnrm=" + eilnrm + "&eilnrh=" + eilnrh;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
	    		List<SadmoifRecord> list = (List)jsonContainer.getList();
	    		for(SadmoifRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param eori
	 * @return
	 */
	@RequestMapping(value = "getEORIValidation_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<EoriValidationDto> getEORIValidation_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String eori) {
		 
		 final String METHOD = "[DEBUG] getEORIValidation_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL + "getEORIValidation.do";
		 String urlRequestParams = "user=" + applicationUser + "&eori=" + eori;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		EoriValidationContainer jsonContainer = this.sadmotfListService.getListContainerEORIValidation(jsonPayload);
	    		List<EoriValidationDto> list = (List)jsonContainer.getList();
	    		for(EoriValidationDto record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param targetTransportId
	 * @param fromEmlnrt
	 * @param fromEmlnrm
	 * @param fromEtktyp
	 * @return
	 */
	@RequestMapping(value = "changeTransport_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmotfRecord> changeTransport_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String targetTransportId, 
	  						 @RequestParam String fromEmlnrt, @RequestParam String fromEmlnrm , @RequestParam String fromEtktyp) {
		 
		 final String METHOD = "[DEBUG] changeTransport_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_CHANGE_TRANSPORT_URL;
		 String urlRequestParams = "user=" + applicationUser + "&mode=U" + "&emlnrt=" + fromEmlnrt + "&emlnrm=" + fromEmlnrm + "&toEmlnrt=" + targetTransportId;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		result.add(jsonContainer);
	    		/*List<SadmotfRecord> list = (List)jsonContainer.getList();
	    		for(SadmotfRecord fakeRecord : list) {
	    			result.add(fakeRecord);
	    		}*/
	    		logger.info("result Set:" + result.toString());
	    		
	    	}
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param tur
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificOppdrag_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> getSpecificOppdrag_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String tur, 
	  						 @RequestParam String avd, @RequestParam String opd) {
		 
		final String METHOD = "[DEBUG] getSpecificOppdrag_Digitoll ";
		logger.info(METHOD + "Inside");
		Set result = new HashSet();
		SadOppdragRecord record = this.getOppdrag(applicationUser, tur, opd);
		if(record!=null) {
			result.add(record);
		}
		
    	return result;
    	
	 }
	
	@RequestMapping(value = "getSpecificSadi_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> getSpecificSadi_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String dato, 
	  						 @RequestParam String avd, @RequestParam String opd, @RequestParam String bilnr) {
		 
		final String METHOD = "[DEBUG] getSpecificSadi_Digitoll";
		logger.info(METHOD + "Inside");
		Set result = new HashSet();
		//convert NO-dato to ISO
		String datoISO = dateMgr.getDateFormatted_ISO(String.valueOf(dato), DateTimeManager.NO_FORMAT);
		SadOppdragRecord record = this.getSadi(applicationUser, datoISO, avd, opd, bilnr); 
		if(record!=null) {
			result.add(record);
		}
		
    	return result;
    	
	 }
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param etlnrt
	 * @return
	 * 
	 * 			opener.jq('#ehavd').val("");opener.jq('#ehavd').val(data[i].siavd); //Avd
				opener.jq('#ehtdn').val("");opener.jq('#ehtdn').val(data[i].sitdn); //Opp
				opener.jq('#ehvkb').val("");opener.jq('#ehvkb').val(data[i].sivkb); //bruttovikt
				opener.jq('#ehntk').val("");opener.jq('#ehntk').val(data[i].sintk); //Kolli
				opener.jq('#ehcnin').val("");opener.jq('#ehcnin').val(data[i].sikdc); //Container
				//Previous Docs
				opener.jq('#ehrg').val("");opener.jq('#ehrg').val(data[i].wehrg); //Dekl.nr
				opener.jq('#eh0068a').val("");opener.jq('#eh0068a').val(data[i].weh0068a); //Dekl.dato
				opener.jq('#eh0068b').val("");opener.jq('#eh0068b').val(data[i].weh0068b); //Dekl.sekv
				//Sender
				opener.jq('#ehkns').val("");opener.jq('#ehkns').val(data[i].sikns); //Kundnr
				opener.jq('#ehnas').val("");opener.jq('#ehnas').val(data[i].sinas); //Namn
				opener.jq('#ehrgs').val("");opener.jq('#ehrgs').val(data[i].ehrgs); //Orgnr
				opener.jq('#ehad1s').val("");opener.jq('#ehad1s').val(data[i].siads1); //Adress
				var ad2Avs = data[i].siads2 + " " + data[i].siads3; 
				opener.jq('#ehpbs').val("");opener.jq('#ehpbs').val(ad2Avs); //Adress2+3
				if(data[i].ehems != ""){ opener.jq('#own_ehems_email').val(data[i].ehems) } //email
				//Postnr and City
				var postnrAvs = ""; 
				var cityAvs = "";
				var landAvs = "";
				if(data[i].ehpns != ""){ postnrAvs = data[i].ehpns;}
				if(data[i].ehpss != ""){ cityAvs = data[i].ehpss;}
				if(data[i].ehlks != ""){ landAvs = data[i].ehlks;}
				opener.jq('#ehpns').val(postnrAvs);
				opener.jq('#ehpss').val(cityAvs);
				opener.jq('#ehlks').val(landAvs);
				
				
				//Receiver
				opener.jq('#ehknm').val("");opener.jq('#ehknm').val(data[i].siknk); //Kundnr
				opener.jq('#ehnam').val("");opener.jq('#ehnam').val(data[i].sinak); //Namn
				opener.jq('#ehrgm').val("");opener.jq('#ehrgm').val(data[i].ehrgm); //Orgnr
				opener.jq('#ehad1m').val("");opener.jq('#ehad1m').val(data[i].siadk1); //Adress
				var ad2Mot = data[i].siadk2 + " " + data[i].siadk3; 
				opener.jq('#ehpbm').val("");opener.jq('#ehpbm').val(ad2Mot); //Adress2+3
				if(data[i].ehemm != ""){ opener.jq('#own_ehemm_email').val(data[i].ehemm) } //email
				//Postnr and City
				var postnrMot = ""; 
				var cityMot = "";
				var landMot = "";
				if(data[i].ehpnm != ""){ postnrMot = data[i].ehpnm;}
				if(data[i].ehpsm != ""){ cityMot = data[i].ehpsm;}
				if(data[i].ehlkm != ""){ landMot = data[i].ehlkm;}
				opener.jq('#ehpnm').val(postnrMot);
				opener.jq('#ehpsm').val(cityMot);
				opener.jq('#ehlkm').val(landMot);
				
				
	 * 
	 */
	@RequestMapping(value = "createHousesFromOppdrag_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> createHousesFromOppdrag_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String params, @RequestParam String tur, 
	  						 @RequestParam Integer lnrt, @RequestParam Integer lnrm,  @RequestParam String mode ) {
		
		 Set result = new HashSet();
		 
		 logger.info("lnrt:" + lnrt);
		 logger.info("lnrm:" + lnrm);
		 logger.info("tur:" + tur);
		 logger.info("mode:" + mode);
		 logger.info(params);
		 List<String> mainList = new ArrayList<String>();
		 if (StringUtils.isNotEmpty(params)) {
			 String [] recordAvdOpd = params.split("#");
			 mainList = Arrays.asList(recordAvdOpd);
		 }
		 
		 
		 if(!mainList.isEmpty()) {
			 
			 for (String recordAvdOpd: mainList) {
				 String[] avdOpd = recordAvdOpd.split("_");
				 String avd = avdOpd[0].replace("avd", "");
				 String opd = avdOpd[1].replace("opd", "");
				 logger.info("avd:" + avd);
				 logger.info("opd:" + opd);
				 
				 //(2) now go on with the real issue (create the house(s)
				 SadOppdragRecord record = this.getOppdrag(applicationUser, tur, opd);
				 if(record!=null) {
					//hand-over 
					SadmohfRecord sadmohfRecord = new SadmohfRecord();
					sadmohfRecord.setEhlnrt(lnrt);
					sadmohfRecord.setEhlnrm(lnrm);
					sadmohfRecord.setEhavd(Integer.valueOf(avd));
					sadmohfRecord.setEhpro(Integer.valueOf(tur));
					sadmohfRecord.setEhtdn(Integer.valueOf(opd));
					sadmohfRecord.setEhvkb(record.getSivkb()); //bruttovikt
					sadmohfRecord.setEhdkht("N730");
					
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEhntk(Integer.valueOf(record.getSintk())); //Kolli
					}
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEhcnin(Integer.valueOf(record.getSikdc()));//Container
					}
					//Previous Docs
					sadmohfRecord.setEhrg(record.getWehrg());//Dekl.nr
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEh0068a(Integer.valueOf(record.getWeh0068a()));//Dekl.dato
					}
					if(StringUtils.isNotEmpty(record.getWeh0068b())) {
						sadmohfRecord.setEh0068b(Integer.valueOf(record.getWeh0068b()));//Dekl.sekv
					}
					if(StringUtils.isNotEmpty(record.getWfssokmrn())) {
						sadmohfRecord.setEhtrnr(record.getWfssokmrn());//Mrn.Transit
					}
					if(StringUtils.isNotEmpty(record.getWfssokexp())) {
						sadmohfRecord.setEheid(record.getWfssokexp());//Eksp.id
					}
					
					//set ehprt and ehupr depending on combinations (this is also made on JSP/JS when the user does not use the Auto(checkbox)
					this.setProcedures(sadmohfRecord);
					
					//Sender
					if(StringUtils.isNotEmpty(record.getSikns())) { sadmohfRecord.setEhkns(Integer.valueOf(record.getSikns())); } //Kundnr
					sadmohfRecord.setEhnas(record.getSinas());//Namn
					sadmohfRecord.setEhrgs(record.getEhrgs());//Orgnr
					sadmohfRecord.setEhad1s(record.getSiads1());//Adress
					String ad2Avs = record.getSiads2() + " " + record.getSiads3(); 
					sadmohfRecord.setEhpbs(ad2Avs);//
					if(record.getEhems() != ""){ 
						sadmohfRecord.setOwn_ehems_email(record.getEhems());//email
					}
					//Sender Postnr and City
					logger.info("Send-pnr:" + record.getEhpns());
					logger.info("Send-sted:" + record.getEhpss());
					logger.info("Send-land:" + record.getEhlks());
					String postnrAvs = ""; String cityAvs = ""; String landAvs = "";
					if(record.getEhpns() != ""){ postnrAvs = record.getEhpns();}
					if(record.getEhpss() != ""){ cityAvs = record.getEhpss();}
					if(record.getEhlks() != ""){ landAvs = record.getEhlks();}
					sadmohfRecord.setEhpns(postnrAvs);
					sadmohfRecord.setEhpss(cityAvs);
					sadmohfRecord.setEhlks(landAvs);
					
					//Receiver
					if(StringUtils.isNotEmpty(record.getSiknk())) { sadmohfRecord.setEhknm(Integer.valueOf(record.getSiknk())); } //Kundnr
					sadmohfRecord.setEhnam(record.getSinak());//Namn
					sadmohfRecord.setEhrgm(record.getEhrgm());//Orgnr
					sadmohfRecord.setEhad1m(record.getSiadk1());//Adress
					String ad2Mot = record.getSiadk2() + " " + record.getSiadk3(); 
					sadmohfRecord.setEhpbm(ad2Mot);//
					if(record.getEhemm() != ""){ 
						sadmohfRecord.setOwn_ehemm_email(record.getEhemm());//email
					}
					//Receiver Postnr and City
					logger.info("Mott-pnr:" + record.getEhpnm());
					logger.info("Mott-sted:" + record.getEhpsm());
					logger.info("Mott-land:" + record.getEhlkm());
					String postnrMot = ""; String cityMot = ""; String landMot = "";
					if(record.getEhpnm() != ""){ postnrMot = record.getEhpnm();}
					if(record.getEhpsm() != ""){ cityMot = record.getEhpsm();}
					if(record.getEhlkm() != ""){ landMot = record.getEhlkm();}
					sadmohfRecord.setEhpnm(postnrMot);
					sadmohfRecord.setEhpsm(cityMot);
					sadmohfRecord.setEhlkm(landMot);
					
					//adjust other fields
					this.adjustFieldsForUpdateHouse(applicationUser, sadmohfRecord);
					
					
					//create new
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.houseControllerService.updateRecord(applicationUser, sadmohfRecord, mode, errMsg);
					
		   		 }else {
		   			 logger.warn("no record on <getOppdrag>... ?");
		   		 }
			 }
			 
		 }
		 
		 SadOppdragRecord fejk = new SadOppdragRecord();
		 fejk.setSiavd("1");
		 //(1) just to satisfy the ajax-return-requirement of data
		 result.add(fejk);
		 
		 return result;
	 }
	
	/**
	 * 
	 * @param applicationUser
	 * @param params
	 * @param tur
	 * @param lnrt
	 * @param lnrm
	 * @param mode
	 * @return
	 */
	@RequestMapping(value = "createHousesFromSadi_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> createHousesFromSadi_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String params, @RequestParam String tur, 
	  						 @RequestParam Integer lnrt, @RequestParam Integer lnrm,  @RequestParam String mode ) {
		
		 Set result = new HashSet();
		 
		 logger.info("lnrt:" + lnrt);
		 logger.info("lnrm:" + lnrm);
		 logger.info("tur:" + tur);
		 logger.info("mode:" + mode);
		 logger.info(params);
		 
		 List<String> mainList = new ArrayList<String>();
		 if (StringUtils.isNotEmpty(params)) {
			 String [] recordAvdOpdDatoBilList = params.split("#");
			 mainList = Arrays.asList(recordAvdOpdDatoBilList);
		 }
		 
		 
		 if(!mainList.isEmpty()) {
			 
			 for (String recordAvdOpdDatoBil: mainList) {
				 String[] avdOpd = recordAvdOpdDatoBil.split("_");
				 String avd = avdOpd[0].replace("avd", "");
				 String opd = avdOpd[1].replace("opd", "");
				 String dato = avdOpd[2].replace("dato", "");
				 String bilnr = avdOpd[3].replace("bil", "");
				 //new fields
				 
				 logger.info("avd:" + avd);
				 logger.info("opd:" + opd);
				 logger.info("dato:" + dato);
				 logger.info("bilnr:" + bilnr);
				 //convert NO-dato to ISO
				 String datoISO = dateMgr.getDateFormatted_ISO(String.valueOf(dato), DateTimeManager.NO_FORMAT);
				 
				 //(2) now go on with the real issue (create the house(s)
				 SadOppdragRecord record = this.getSadi(applicationUser, datoISO, avd, opd, bilnr);
				 if(record!=null) {
					//hand-over 
					SadmohfRecord sadmohfRecord = new SadmohfRecord();
					sadmohfRecord.setEhlnrt(lnrt);
					sadmohfRecord.setEhlnrm(lnrm);
					sadmohfRecord.setEhavd(Integer.valueOf(avd));
					sadmohfRecord.setEhpro(Integer.valueOf(tur));
					sadmohfRecord.setEhtdn(Integer.valueOf(opd));
					sadmohfRecord.setEhvkb(record.getSivkb()); //bruttovikt
					sadmohfRecord.setEhdkht("N730");
					
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEhntk(Integer.valueOf(record.getSintk())); //Kolli
					}
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEhcnin(Integer.valueOf(record.getSikdc()));//Container
					}
					//Previous Docs
					sadmohfRecord.setEhrg(record.getWehrg());//Dekl.nr
					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
						sadmohfRecord.setEh0068a(Integer.valueOf(record.getWeh0068a()));//Dekl.dato
					}
					if(StringUtils.isNotEmpty(record.getWeh0068b())) {
						sadmohfRecord.setEh0068b(Integer.valueOf(record.getWeh0068b()));//Dekl.sekv
					}
					if(StringUtils.isNotEmpty(record.getWfssokmrn())) {
						sadmohfRecord.setEhtrnr(record.getWfssokmrn());//Mrn.Transit
					}
					if(StringUtils.isNotEmpty(record.getWfssokexp())) {
						sadmohfRecord.setEheid(record.getWfssokexp());//Eksp.id
					}
					
					//set ehprt and ehupr depending on combinations (this is also made on JSP/JS when the user does not use the Auto(checkbox)
					this.setProcedures(sadmohfRecord);
					
					//Sender
					if(StringUtils.isNotEmpty(record.getSikns())) { sadmohfRecord.setEhkns(Integer.valueOf(record.getSikns())); } //Kundnr
					sadmohfRecord.setEhnas(record.getSinas());//Namn
					sadmohfRecord.setEhrgs(record.getEhrgs());//Orgnr
					sadmohfRecord.setEhad1s(record.getSiads1());//Adress
					String ad2Avs = record.getSiads2() + " " + record.getSiads3(); 
					sadmohfRecord.setEhpbs(ad2Avs);//
					if(record.getEhems() != ""){ 
						sadmohfRecord.setOwn_ehems_email(record.getEhems());//email
					}
					//Sender Postnr and City
					logger.info("Send-pnr:" + record.getEhpns());
					logger.info("Send-sted:" + record.getEhpss());
					logger.info("Send-land:" + record.getEhlks());
					String postnrAvs = ""; String cityAvs = ""; String landAvs = "";
					if(record.getEhpns() != ""){ postnrAvs = record.getEhpns();}
					if(record.getEhpss() != ""){ cityAvs = record.getEhpss();}
					if(record.getEhlks() != ""){ landAvs = record.getEhlks();}
					sadmohfRecord.setEhpns(postnrAvs);
					sadmohfRecord.setEhpss(cityAvs);
					sadmohfRecord.setEhlks(landAvs);
					
					//Receiver
					if(StringUtils.isNotEmpty(record.getSiknk())) { sadmohfRecord.setEhknm(Integer.valueOf(record.getSiknk())); } //Kundnr
					sadmohfRecord.setEhnam(record.getSinak());//Namn
					sadmohfRecord.setEhrgm(record.getEhrgm());//Orgnr
					sadmohfRecord.setEhad1m(record.getSiadk1());//Adress
					String ad2Mot = record.getSiadk2() + " " + record.getSiadk3(); 
					sadmohfRecord.setEhpbm(ad2Mot);//
					if(record.getEhemm() != ""){ 
						sadmohfRecord.setOwn_ehemm_email(record.getEhemm());//email
					}
					//Receiver Postnr and City
					logger.info("Mott-pnr:" + record.getEhpnm());
					logger.info("Mott-sted:" + record.getEhpsm());
					logger.info("Mott-land:" + record.getEhlkm());
					String postnrMot = ""; String cityMot = ""; String landMot = "";
					if(record.getEhpnm() != ""){ postnrMot = record.getEhpnm();}
					if(record.getEhpsm() != ""){ cityMot = record.getEhpsm();}
					if(record.getEhlkm() != ""){ landMot = record.getEhlkm();}
					sadmohfRecord.setEhpnm(postnrMot);
					sadmohfRecord.setEhpsm(cityMot);
					sadmohfRecord.setEhlkm(landMot);
					
					//adjust other fields
					this.adjustFieldsForUpdateHouse(applicationUser, sadmohfRecord);
					
					
					//create new
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.houseControllerService.updateRecord(applicationUser, sadmohfRecord, mode, errMsg);
					
		   		 }else {
		   			 logger.warn("no record on <getSadi>... ?");
		   		 }
			 }
			 
		 }
		 
		 SadOppdragRecord fejk = new SadOppdragRecord();
		 fejk.setSiavd("1");
		 //(1) just to satisfy the ajax-return-requirement of data
		 result.add(fejk);
		 
		 return result;
	 }
	
	
	@RequestMapping(value = "createHousesFromOppdrag_DigitollOrig.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> createHousesFromOppdrag_DigitollOrig
	  						(@RequestParam String applicationUser, @RequestParam String avd, @RequestParam String opd,@RequestParam String tur,  
	  						 @RequestParam Integer lnrt, @RequestParam Integer lnrm,  @RequestParam String mode ) {

		 logger.info("avd:" + avd);
		 logger.info("tur:" + tur);
		 logger.info("opd:" + opd);
		 logger.info("lnrt:" + lnrt);
		 logger.info("lnrm:" + lnrm);
		 logger.info("mode:" + mode);
		 
		 Set result = new HashSet();
		 SadOppdragRecord fejk = new SadOppdragRecord();
		 fejk.setSiavd(avd);
		 //(1) just to satisfy the ajax-return-requirement of data
		 result.add(fejk);
		 
		 //(2) now go on with the real issue (create the house(s)
		 SadOppdragRecord record = this.getOppdrag(applicationUser, tur, opd);
		 if(record!=null) {
			//hand-over 
			SadmohfRecord sadmohfRecord = new SadmohfRecord();
			sadmohfRecord.setEhlnrt(lnrt);
			sadmohfRecord.setEhlnrm(lnrm);
			sadmohfRecord.setEhavd(Integer.valueOf(avd));
			sadmohfRecord.setEhpro(Integer.valueOf(tur));
			sadmohfRecord.setEhtdn(Integer.valueOf(opd));
			sadmohfRecord.setEhvkb(record.getSivkb()); //bruttovikt
			sadmohfRecord.setEhdkht("N730");
			
			if(StringUtils.isNotEmpty(record.getWeh0068a())) {
				sadmohfRecord.setEhntk(Integer.valueOf(record.getSintk())); //Kolli
			}
			if(StringUtils.isNotEmpty(record.getWeh0068a())) {
				sadmohfRecord.setEhcnin(Integer.valueOf(record.getSikdc()));//Container
			}
			//Previous Docs
			sadmohfRecord.setEhrg(record.getWehrg());//Dekl.nr
			if(StringUtils.isNotEmpty(record.getWeh0068a())) {
				sadmohfRecord.setEh0068a(Integer.valueOf(record.getWeh0068a()));//Dekl.dato
			}
			if(StringUtils.isNotEmpty(record.getWeh0068b())) {
				sadmohfRecord.setEh0068b(Integer.valueOf(record.getWeh0068b()));//Dekl.sekv
			}
			if(StringUtils.isNotEmpty(record.getWfssokmrn())) {
				sadmohfRecord.setEhtrnr(record.getWfssokmrn());//Mrn.Transit
			}
			if(StringUtils.isNotEmpty(record.getWfssokexp())) {
				sadmohfRecord.setEheid(record.getWfssokexp());//Eksp.id
			}
			
			//set ehprt and ehupr depending on combinations (this is also made on JSP/JS when the user does not use the Auto(checkbox)
			this.setProcedures(sadmohfRecord);
			
			
			//Sender
			if(StringUtils.isNotEmpty(record.getSikns())) { sadmohfRecord.setEhkns(Integer.valueOf(record.getSikns())); } //Kundnr
			sadmohfRecord.setEhnas(record.getSinas());//Namn
			sadmohfRecord.setEhrgs(record.getEhrgs());//Orgnr
			sadmohfRecord.setEhad1s(record.getSiads1());//Adress
			String ad2Avs = record.getSiads2() + " " + record.getSiads3(); 
			sadmohfRecord.setEhpbs(ad2Avs);//
			if(record.getEhems() != ""){ 
				sadmohfRecord.setOwn_ehems_email(record.getEhems());//email
			}
			//Sender Postnr and City
			logger.info("Send-pnr:" + record.getEhpns());
			logger.info("Send-sted:" + record.getEhpss());
			logger.info("Send-land:" + record.getEhlks());
			String postnrAvs = ""; String cityAvs = ""; String landAvs = "";
			if(record.getEhpns() != ""){ postnrAvs = record.getEhpns();}
			if(record.getEhpss() != ""){ cityAvs = record.getEhpss();}
			if(record.getEhlks() != ""){ landAvs = record.getEhlks();}
			sadmohfRecord.setEhpns(postnrAvs);
			sadmohfRecord.setEhpss(cityAvs);
			sadmohfRecord.setEhlks(landAvs);
			
			//Receiver
			if(StringUtils.isNotEmpty(record.getSiknk())) { sadmohfRecord.setEhknm(Integer.valueOf(record.getSiknk())); } //Kundnr
			sadmohfRecord.setEhnam(record.getSinak());//Namn
			sadmohfRecord.setEhrgm(record.getEhrgm());//Orgnr
			sadmohfRecord.setEhad1m(record.getSiadk1());//Adress
			String ad2Mot = record.getSiadk2() + " " + record.getSiadk3(); 
			sadmohfRecord.setEhpbm(ad2Mot);//
			if(record.getEhemm() != ""){ 
				sadmohfRecord.setOwn_ehemm_email(record.getEhemm());//email
			}
			//Receiver Postnr and City
			logger.info("Mott-pnr:" + record.getEhpnm());
			logger.info("Mott-sted:" + record.getEhpsm());
			logger.info("Mott-land:" + record.getEhlkm());
			String postnrMot = ""; String cityMot = ""; String landMot = "";
			if(record.getEhpnm() != ""){ postnrMot = record.getEhpnm();}
			if(record.getEhpsm() != ""){ cityMot = record.getEhpsm();}
			if(record.getEhlkm() != ""){ landMot = record.getEhlkm();}
			sadmohfRecord.setEhpnm(postnrMot);
			sadmohfRecord.setEhpsm(cityMot);
			sadmohfRecord.setEhlkm(landMot);
			
			//adjust other fields
			this.adjustFieldsForUpdateHouse(applicationUser, sadmohfRecord);
			
			
			//create new
			StringBuffer errMsg = new StringBuffer();
			int dmlRetval = 0;
			dmlRetval = this.houseControllerService.updateRecord(applicationUser, sadmohfRecord, mode, errMsg);
			
   		 }else {
   			 logger.warn("no record on <getOppdrag>... ?");
   		 }
		 
		 
		 return result;
	 }
	
	@RequestMapping(value = "createMasterFromZadmomlf_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmomfRecord> createMasterFromZadmomlf_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String params, 
	  						 @RequestParam Integer lnrt, @RequestParam String mode ) {
		
		 Set result = new HashSet();
		 logger.info("Inside createMasterFromZadmomlf_Digitoll.do:" + lnrt);
		 logger.info("lnrt:" + lnrt);
		 logger.info("mode:" + mode);
		 logger.info("params:" + params);
		 
		 List<String> mainList = new ArrayList<String>();
		 if (StringUtils.isNotEmpty(params)) {
			 String [] recordList = params.split("#");
			 mainList = Arrays.asList(recordList);
		 }
		 
		 
		 if(!mainList.isEmpty()) {
			 
			 for (String recordParent: mainList) {
				 	String[] items = recordParent.split("_");
				 	String emdkm = items[0].replace("emdkm", "");
				 	String emdkmt = items[1].replace("emdkmt", "");
				 	//new fields
					 
				 	logger.info("emdkm:" + emdkm);
				 	logger.info("emdmt:" + emdkmt);
					 
				 	//convert NO-dato to ISO
					//String datoISO = dateMgr.getDateFormatted_ISO(String.valueOf(dato), DateTimeManager.NO_FORMAT);
					 
				 	//(2) now go on with the real issue (create the master)
				 	SadmomfRecord sadmomfRecord = new SadmomfRecord();
				
				 	sadmomfRecord.setEmlnrt(lnrt);
				 	sadmomfRecord.setEmdkm(emdkm);
				 	sadmomfRecord.setEmdkmt(emdkmt);
				 	
					/*sadmohfRecord.setEhlnrm(lnrm);
					sadmohfRecord.setEhavd(Integer.valueOf(avd));
					sadmohfRecord.setEhpro(Integer.valueOf(tur));
					sadmohfRecord.setEhtdn(Integer.valueOf(opd));
					sadmohfRecord.setEhvkb(record.getSivkb()); //bruttovikt
					sadmohfRecord.setEhdkht("N730");
					//Sender
					
					if(StringUtils.isNotEmpty(record.getSikns())) { sadmohfRecord.setEhkns(Integer.valueOf(record.getSikns())); } //Kundnr
					sadmohfRecord.setEhnas(record.getSinas());//Namn
					sadmohfRecord.setEhrgs(record.getEhrgs());//Orgnr
					sadmohfRecord.setEhad1s(record.getSiads1());//Adress
					String ad2Avs = record.getSiads2() + " " + record.getSiads3(); 
					sadmohfRecord.setEhpbs(ad2Avs);//
					if(record.getEhems() != ""){ 
						sadmohfRecord.setOwn_ehems_email(record.getEhems());//email
					}
					//Sender Postnr and City
					logger.info("Send-pnr:" + record.getEhpns());
					logger.info("Send-sted:" + record.getEhpss());
					logger.info("Send-land:" + record.getEhlks());
					String postnrAvs = ""; String cityAvs = ""; String landAvs = "";
					if(record.getEhpns() != ""){ postnrAvs = record.getEhpns();}
					if(record.getEhpss() != ""){ cityAvs = record.getEhpss();}
					if(record.getEhlks() != ""){ landAvs = record.getEhlks();}
					sadmohfRecord.setEhpns(postnrAvs);
					sadmohfRecord.setEhpss(cityAvs);
					sadmohfRecord.setEhlks(landAvs);
					
					//Receiver
					if(StringUtils.isNotEmpty(record.getSiknk())) { sadmohfRecord.setEhknm(Integer.valueOf(record.getSiknk())); } //Kundnr
					sadmohfRecord.setEhnam(record.getSinak());//Namn
					sadmohfRecord.setEhrgm(record.getEhrgm());//Orgnr
					sadmohfRecord.setEhad1m(record.getSiadk1());//Adress
					String ad2Mot = record.getSiadk2() + " " + record.getSiadk3(); 
					sadmohfRecord.setEhpbm(ad2Mot);//
					if(record.getEhemm() != ""){ 
						sadmohfRecord.setOwn_ehemm_email(record.getEhemm());//email
					}
					//Receiver Postnr and City
					logger.info("Mott-pnr:" + record.getEhpnm());
					logger.info("Mott-sted:" + record.getEhpsm());
					logger.info("Mott-land:" + record.getEhlkm());
					String postnrMot = ""; String cityMot = ""; String landMot = "";
					if(record.getEhpnm() != ""){ postnrMot = record.getEhpnm();}
					if(record.getEhpsm() != ""){ cityMot = record.getEhpsm();}
					if(record.getEhlkm() != ""){ landMot = record.getEhlkm();}
					sadmohfRecord.setEhpnm(postnrMot);
					sadmohfRecord.setEhpsm(cityMot);
					sadmohfRecord.setEhlkm(landMot);
					
					//adjust other fields
					this.adjustFieldsForUpdateHouse(applicationUser, sadmohfRecord);
					*/
					
					//create new
					StringBuffer errMsg = new StringBuffer();
					int dmlRetval = 0;
					dmlRetval = this.masterControllerService.updateRecord(applicationUser, sadmomfRecord, mode, errMsg);
					logger.info("dmlRetval:" + dmlRetval);
			 }
			 
		 }
		 
		 SadmomfRecord fejk = new SadmomfRecord();
		 fejk.setEmlnrt(lnrt);
		 //(1) just to satisfy the ajax-return-requirement of data
		 result.add(fejk);
		 logger.info(result.toString());
		 return result;
	 }
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param ehlnrt
	 * @param ehlnrm
	 * @param ehlnrh
	 * @return
	 */
	@RequestMapping(value = "getDocumentNrHouse_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadmotfRecord> updateDocumentNrHouse_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String etlnrt) {
		 
		 final String METHOD = "[DEBUG] updateDocumentNrHouse_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		 String urlRequestParams = "user=" + applicationUser + "&etlnrt=" + etlnrt;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
	    		List<SadmotfRecord> list = (List)jsonContainer.getList();
	    		for(SadmotfRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
		
	/**
	 * 
	 * @param request
	 * @param applicationUser
	 * @param turNr
	 * @param fromDate
	 * @return
	 */
	@RequestMapping(value = "searchTur_Digitoll.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadTurRecord> searchTur(HttpServletRequest request, 	@RequestParam String applicationUser, @RequestParam(value = "avd", required = true) String avd, 
			  																	   	@RequestParam(value = "turNr", required = true) String turNr, 
			  																		@RequestParam(value = "fromDate", required = true) String fromDate) {

		  logger.info("Inside searchTur");
		  Set result = new HashSet();
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TUR_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser);
		  urlRequestParamsKeys.append("&wtudt=" + fromDate);
		  if(StringUtils.isNotEmpty(turNr)) {
			  urlRequestParamsKeys.append("&wsstur=" + turNr);
		  }
		  if(StringUtils.isNotEmpty(avd)) {
			  urlRequestParamsKeys.append("&wssavd=" + avd);
		  }
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());

		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadTurContainer container = this.sadTurService.getListContainer(jsonPayload);
	    		if(container!=null){
	    			if(StringUtils.isEmpty(container.getErrMsg())){
		    			for(SadTurRecord  record : container.getWrktriplist()){
		    				logger.info("Bilnr: " + record.getTubiln());
		    				logger.info("Tollsted(a): " + record.getTuto1a());
		    				logger.info("Fører: " + record.getTusjn1());
		    				//transport måte
		    				if(StringUtils.isNotEmpty(record.getTutrma())) { this.washTranspMate(record); }
		    				//eta (NO format)
		    				if(StringUtils.isNotEmpty(record.getTueta()) && record.getTueta().equals("0") && record.getTueta().length()==8 ){
		    					record.setTueta(this.dateMgr.getDateFormatted_NO(record.getTueta(), DateTimeManager.ISO_FORMAT));
		    				}
		    				//
		    				result.add(record);
		    			}
	    			}else {
	    				SadTurRecord record = new SadTurRecord();
	    				record.setOwn_ErrMsg(container.getErrMsg());
	    				result.add(record);
	    			}
	    		}
	    	}
		  return result;
		  
	  }
	
	/**
	 * 
	 * @param applicationUser
	 * @param params
	 * @param lnrt
	 * @param tur
	 * @return
	 */
	@RequestMapping(value = "createHousesFromTransportConsolidation_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<SadOppdragRecord> createHousesFromTransportConsolidation_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String params, 
	  						 @RequestParam Integer lnrt, @RequestParam Integer tur ) {
		
		 Set result = new HashSet();
		 
		 logger.info("lnrt:" + lnrt);
		 logger.info("tur:" + tur);
		 logger.info(params);
		 
		 List<String> mainList = new ArrayList<String>();
		 if (StringUtils.isNotEmpty(params)) {
			 String [] recordsEtlnrt = params.split("#");
			 mainList = Arrays.asList(recordsEtlnrt);
		 }
		 int maxHouseTargetCounter = this.getMaxHouseCounter(applicationUser, lnrt);
		 logger.info("maxHouseTargetCounter:" + maxHouseTargetCounter);
		 
		 //
		 if(!mainList.isEmpty()) {
			 //(1) iterate through the list of GUI-chosen params-transports to consolidate into the parent transport (lnrt)
			 for (String param: mainList) {
				 logger.info("etlnrt to move:" + param);
				 if(param!=null) {
					 SadmotfRecord sourceTransport = this.getSourceTransportToHandoverHouses(applicationUser, param);
					 if(sourceTransport!=null && sourceTransport.getListMasters()!=null) {
						 for(SadmomfRecord master : sourceTransport.getListMasters()) {
							 if(master!=null && master.getListHouses()!=null) {
								 for(SadmohfRecord house : master.getListHouses()) {
									 //consolidate
									 //this.consolidateUpdate(applicationUser, lnrt, ++maxHouseTargetCounter, tur, house);//in case the TUR must be set as the parent transport
									 this.consolidateUpdate(applicationUser, lnrt, ++maxHouseTargetCounter, tur, house);//in case the TUR must be set as the parent transport
								 }
							 }
						 }
						 //delete source transport
						 int dmlRetval = 0;
						 StringBuffer errMsg = new StringBuffer();
						 dmlRetval = this.deleteTransportConsolidated(applicationUser, sourceTransport, "DC", errMsg);
						 if(dmlRetval >= 0) {
							 logger.debug("Transport lnrt:" + sourceTransport.getEtlnrt() + " has been DELETED");
						 }else {
							 logger.error("ERROR on deleteTranport:" + errMsg.toString());
						 }
				 	 }
					
		   		 }else {
		   			 logger.warn("no record to update... ?");
		   		 }
			 }
			 
		 }
		 
		 SadOppdragRecord fejk = new SadOppdragRecord();
		 fejk.setSiavd("1");
		 //(1) just to satisfy the ajax-return-requirement of data
		 result.add(fejk);
		 
		 return result;
	 }
	/**
	 * 
	 * @param applicationUser
	 * @param sourceTransport
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int deleteTransportConsolidated(String applicationUser, SadmotfRecord sourceTransport, String mode, StringBuffer errMsg) {
		int retval = 0;
		
		
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + applicationUser + "&mode=" + mode);
		urlRequestParams.append(this.urlRequestParameterMapper.getUrlParameterValidString((sourceTransport)));
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		GeneralUpdateContainer container = this.generalUpdateService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
    		Collection<GeneralUpdateRecord> outputList = container.getList();	
			if(outputList!=null && outputList.size()>0){
				for(GeneralUpdateRecord record : outputList ){
					logger.info(record.toString());
					if(StringUtils.isNotEmpty(container.getErrMsg())){
						errMsg.append(record.getStatus());
						errMsg.append(" -->detail:" + container.getErrMsg());
						retval = -1;
						break;
					}
				}
			}
    	}
    	
    	return retval;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param param
	 * @return
	 */
	private SadmotfRecord getSourceTransportToHandoverHouses(String applicationUser, String param) {
		//get masters and houses of the transport in turn
		SadmotfRecord recordTmp = new SadmotfRecord();
		recordTmp.setEtlnrt(Integer.valueOf(param));
		SadmotfRecord record = this.getMasters(applicationUser, recordTmp);
		
		for (SadmomfRecord master : record.getListMasters()) {
			this.getHouses(applicationUser, master);
		}
		
		//at this point we must get the highest ehlnrh-nr of the target transport in order to put the new ones whith this new counter (ehlnrh)
		logger.info("consolidate houses OK");
		//logger.info(record.toString());
		return record;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param lnrt
	 * @param ehlnrh
	 * @param ehpro
	 * @param record
	 */
	private void consolidateUpdate(String applicationUser, Integer lnrt, Integer ehlnrh, Integer ehpro, SadmohfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_CONSOLIDATE_URL;
		//add URL-parameters
		//http://localhost:8080/syjservicestn/syjsSADMOHF_U_CONS.do?user=OSCAR&ehlnrt=35&ehlnrm=1&ehlnrh=1&ehpro=4444&ehlnrt_w=31&ehlnrm_w=1&ehlnrh_w=1
		StringBuilder urlRequestParams = new StringBuilder();
		//urlRequestParams.append("user=" + applicationUser + "&ehlnrt=" + lnrt + "&ehlnrm=1" + "&ehlnrh=" + ehlnrh + "&ehpro=" + ehpro); ??? ehpro (not likely???)
		urlRequestParams.append("user=" + applicationUser + "&ehlnrt=" + lnrt + "&ehlnrm=1" + "&ehlnrh=" + ehlnrh + "&ehpro=" + record.getEhpro());
		urlRequestParams.append("&ehlnrt_w=" + record.getEhlnrt() + "&ehlnrm_w=1" + "&ehlnrh_w=" + record.getEhlnrh());
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		//
    		if(StringUtils.isNotEmpty(jsonContainer.getErrMsg())){
    			logger.error("##################" + jsonContainer.getErrMsg() + "#######################");
    		}else {
    			//OK with the UPDATE
    			logger.info("SUCCESS on update...");
    		}
    		
    	}
    	
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param lnrt
	 * @return
	 */
	private int getMaxHouseCounter(String applicationUser, Integer lnrt) {
		int retval = -1;
		int counter = -1;
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&ehlnrt=" + lnrt + "&ehlnrm=1"; //always first Master
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		List<SadmohfRecord> list = (List)jsonContainer.getList();
    		for(SadmohfRecord house : list) {
    			if(house.getEhlnrh()> counter) {
    				counter = house.getEhlnrh();
    			}
    		}
    		retval = counter;
    	}
    	
    	return retval;
	}
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @return
	 */
	private SadmotfRecord getMasters(String applicationUser, SadmotfRecord record) {
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&emlnrt=" + record.getEtlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmomfContainer jsonContainer = this.sadmomfListService.getListContainer(jsonPayload);
    		//get houses also
    		List<SadmomfRecord> tmpMasterList = (List)jsonContainer.getList();
    		record.setListMasters(tmpMasterList);
    		
    	}
    	SadmotfRecord retval = record;
    	
    	return retval;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 */
	private void getHouses(String applicationUser, SadmomfRecord record) {
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&ehlnrt=" + record.getEmlnrt() + "&ehlnrm=" + record.getEmlnrm();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadmohfContainer jsonContainer = this.sadmohfListService.getListContainer(jsonPayload);
    		record.setListHouses(jsonContainer.getList());
    	}
	}
	

	
	/**
	 * fetch default values from SADMOAF
	 * @param request
	 * @param applicationUser
	 * @return
	 */
	@RequestMapping(value = "searchDefaultValuesTransport_Digitoll.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadmoafRecord> searchDefaultValues(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam String avd) {
		  String DEFAULT_AVD = "0";
		  
		  logger.info("Inside searchDefaultValues (SADMOAF)");
		  logger.info("avd:" + avd);
		  Set result = new HashSet();
		  //with some etavd
		  SadmoafRecord record = this.getDefaultValues(applicationUser, avd);
		  if(record!=null) {
			  result.add(record);
		  }else {
			  //if the avd above does not exist take the default etavd = 0
			  record = this.getDefaultValues(applicationUser, DEFAULT_AVD);
			  if(record!=null) {
				  result.add(record);
			  }
		  }
	    	
		  return result;
		  
	  }
	/**
	 * This method is just at proxy to the target: .../syjservicestn-expft/digitollv2/send_masterId_toExternalParty.do
	 * 
	 * The reason for that is the design issue on whether the external party is going to receive the payload (FTP, web-service, other)
	 * The communication issue will be the responsibility for the syjservices-expft subsystem.
	 * 
	 * @param request
	 * @param applicationUser
	 * @param emlnrt
	 * @param emlnrm
	 * @param orgNr
	 * @return
	 */
	@RequestMapping(value = "tvinnsaddigitollv2_send_masterId_toExternalParty.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadmomfRecord> sendMasterIdToExternalParty(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam String emlnrt,
			  												@RequestParam String emlnrm, @RequestParam String receiverName, @RequestParam String receiverOrgnr ) {
		  Set result = new HashSet();
		  logger.info("Inside sendMasterIdToExternalParty");
		  logger.info("emlnrt:" + emlnrt);
		  logger.info("emlnrm:" + emlnrm);
		  logger.info("file-receiver name:" + receiverName);
		  logger.info("file-receiver orgNr:" + receiverOrgnr);
		  
		  try {
			  if(StringUtils.isNotEmpty(receiverName) && StringUtils.isNotEmpty(receiverOrgnr) && StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm)) {
				  //get BASE URL
				  final String BASE_URL = SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL + "send_masterId_toExternalParty.do" ;
				  //add URL-parameters
				  StringBuffer urlRequestParams = new StringBuffer();
				  urlRequestParams.append("user=" + applicationUser + "&emlnrt=" + emlnrt + "&emlnrm=" + emlnrm + "&receiverName=" + receiverName + "&receiverOrgnr=" + receiverOrgnr);
				  logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				  logger.warn("URL: " + BASE_URL);
				  logger.warn("URL PARAMS: " + urlRequestParams);
			    	
				  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				  //Debug --> 
				  logger.info(jsonPayload);
				  //return to jquery
				  if("OK".equalsIgnoreCase(jsonPayload)) {
					  SadmomfRecord record = new SadmomfRecord();
					  record.setOwn_resultAjaxText(jsonPayload);
					  result.add(record);
				  }else {
					  SadmomfRecord record = new SadmomfRecord();
					  record.setOwn_resultAjaxText(jsonPayload);
					  result.add(record);
				  }
				
			  	  
			 }
		  }catch(Exception e) {
			  logger.error(e.toString());
		  }
	      
		  return result;
		  
	  }
	
	/**
	 * This method is just at proxy to the target: .../syjservicestn-expft/digitollv2/send_masterId_toExternalParty.do
	 * This method will act in a multi-party fashion. As the single method but within a list
	 * 
	 * The reason for that is the design issue on whether the external party is going to receive the payload (FTP, web-service, other)
	 * The communication issue will be the responsibility for the syjservices-expft subsystem.
	 * 
	 * @param request
	 * @param applicationUser
	 * @param emlnrt
	 * @param emlnrm
	 * @param orgNr
	 * @return
	 */
	@RequestMapping(value = "tvinnsaddigitollv2_send_masterId_toExternalParties.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadmomfRecord> sendMasterIdToExternalParties(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam String params,  
			  																							@RequestParam String emlnrt,@RequestParam String emlnrm ) {
		
		 //TODO
		 List<String> partyList = new ArrayList<String>();
		 if (StringUtils.isNotEmpty(params)) {
			 String [] recordsParties = params.split("#");
			 partyList = Arrays.asList(recordsParties);
		 }	
		 logger.info("paramsRaw:" + params);
		 
		
		
		  Set result = new HashSet();
		  logger.info("Inside sendMasterIdToExternalParties");
		  logger.info("emlnrt:" + emlnrt);
		  logger.info("emlnrm:" + emlnrm);
		  
		  
		  try {
			  if(partyList != null && !partyList.isEmpty()) {
				//(1) iterate through the list of GUI-chosen checkboxes 
				for (String party: partyList) {
					  logger.trace("partyRaw:" + party);	
					  String [] partyRecord = party.split("_");
					  if(partyRecord.length>=2) {
						  String receiverOrgnr = partyRecord[0].replace("orgnr", ""); //from the tvinnsaddigitollv2_childwindow_external_houses.js file
						  String receiverName = partyRecord[1].replace("name", "");  //from the tvinnsaddigitollv2_childwindow_external_houses.js file
						  logger.info("file-receiver name:" + receiverName);
						  logger.info("file-receiver orgNr:" + receiverOrgnr);
						  
						  if(StringUtils.isNotEmpty(receiverName) && StringUtils.isNotEmpty(receiverOrgnr) && StringUtils.isNotEmpty(emlnrt) && StringUtils.isNotEmpty(emlnrm)) {
							  //get BASE URL
							  final String BASE_URL = SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL + "send_masterId_toExternalParty.do" ;
							  //add URL-parameters
							  StringBuffer urlRequestParams = new StringBuffer();
							  urlRequestParams.append("user=" + applicationUser + "&emlnrt=" + emlnrt + "&emlnrm=" + emlnrm + "&receiverName=" + receiverName + "&receiverOrgnr=" + receiverOrgnr);
							  logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
							  logger.warn("URL: " + BASE_URL);
							  logger.warn("URL PARAMS: " + urlRequestParams);
						    	
							  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
							  //Debug --> 
							  logger.info(jsonPayload);
							  //return to jquery
							  if("OK".equalsIgnoreCase(jsonPayload)) {
								  SadmomfRecord record = new SadmomfRecord();
								  record.setOwn_resultAjaxText(jsonPayload);
								  result.add(record);
							  }else {
								  SadmomfRecord record = new SadmomfRecord();
								  record.setOwn_resultAjaxText(jsonPayload);
								  result.add(record);
							  }
							
						  	  
						 }
					  }
				}
			  }
		  }catch(Exception e) {
			  logger.error(e.toString());
		  }
	      
		  return result;
		  
	  }
	
	
	/**
	 * Send the local house back to the MasterId sender Party
	 * @param request
	 * @param applicationUser
	 * @param emlnrt
	 * @param emlnrm
	 * @param receiverName
	 * @param receiverOrgnr
	 * @return
	 */
	@RequestMapping(value = "tvinnsaddigitollv2_send_externalHouse_toExternalParty.do", method = RequestMethod.GET)
	  public @ResponseBody Set<SadmomfRecord> sendExternalHouseBackToExternalParty(HttpServletRequest request, @RequestParam String applicationUser, @RequestParam String ehlnrt,
			  																		   @RequestParam String ehlnrm, @RequestParam String ehlnrh, 
			  																		   @RequestParam String receiverName, @RequestParam String receiverOrgnr ) {
		  Set result = new HashSet();
		  logger.info("Inside sendExternalHouseToExternalParty");
		  logger.info("ehlnrt:" + ehlnrt);
		  logger.info("ehlnrm:" + ehlnrm);
		  logger.info("ehlnrh:" + ehlnrh);
		  logger.info("file-receiver name:" + receiverName);
		  logger.info("file-receiver orgNr:" + receiverOrgnr);
		  
		  try {
			  if(StringUtils.isNotEmpty(receiverName) && StringUtils.isNotEmpty(receiverOrgnr) && StringUtils.isNotEmpty(ehlnrt) 
				  && StringUtils.isNotEmpty(ehlnrm) && StringUtils.isNotEmpty(ehlnrh)) {
				  //get BASE URL
				  final String BASE_URL = SadDigitollUrlDataStore.SAD_DIGITOLL_MANIFEST_ROOT_API_URL + "send_externalHouse_toExternalParty.do" ;
				  //add URL-parameters
				  StringBuffer urlRequestParams = new StringBuffer();
				  urlRequestParams.append("user=" + applicationUser + "&ehlnrt=" + ehlnrt + "&ehlnrm=" + ehlnrm +  "&ehlnrh=" + ehlnrh + "&receiverName=" + receiverName + "&receiverOrgnr=" + receiverOrgnr);
				  logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				  logger.warn("URL: " + BASE_URL);
				  logger.warn("URL PARAMS: " + urlRequestParams);
			    	
				  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
				  //Debug --> 
				  logger.info(jsonPayload);
				  //return to jquery
				  if("OK".equalsIgnoreCase(jsonPayload)) {
					  SadmohfRecord record = new SadmohfRecord();
					  record.setOwn_resultAjaxText(jsonPayload);
					  result.add(record);
				  }else {
					  SadmohfRecord record = new SadmohfRecord();
					  record.setOwn_resultAjaxText(jsonPayload);
					  result.add(record);
				  }
				
			  	  
			 }
		  }catch(Exception e) {
			  logger.error(e.toString());
		  }
	      
		  return result;
		  
	  }
	
	/**
	 * This saves a file payload temporarily before we do something with it ...
	 * @param applicationUser
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="tvinnsaddigitollv2_saveAttachmentTempOnMaster.do", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody Set<String> saveAttachmentTempOnMaster (@RequestParam ("applicationUser") String applicationUser, @RequestParam ("file") MultipartFile file) {
		    logger.info("Inside:  saveAttachmentTempOnMaster");
		    Set result = new HashSet();
		    
	        if (!file.isEmpty()) {
        		String fileName = file.getOriginalFilename();
        		logger.info("FILE NAME:" + fileName);
                
        		String rootPath	= "/Users/oscardelatorre/";
        	    File dir = new File(rootPath);
        	    
        	    try {
	                byte[] bytes = file.getBytes();
	                // Create the file on server
	                File serverFile = new File(dir.getAbsolutePath() + File.separator +  fileName);
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                logger.info("Server File Location=" + serverFile.getAbsolutePath());
	                
        	    } catch (Exception e) {
            		//run time upload error
            		String absoluteFileName = rootPath + File.separator + fileName;
            		//return "You failed to upload to:" + fileName + " runtime error:" + e.getMessage();
        	    }

                
	        } else {
	            //return "You failed to upload an empty file.";
	        	
	        }
		    result.add("OK");
			
    	return result;
    }

	
	
	/**
	 * 
	 * @param sadmohfRecord
	 */
	private void setProcedures(SadmohfRecord sadmohfRecord) {
		//set ehprt and ehupr depending on combinations (this is also made on JSP/JS when the user does not use the Auto(checkbox)
		if(StringUtils.isEmpty(sadmohfRecord.getEhtrnr())){
			//No transit-MRN means: Eksempel 1 – Tolldeklarasjon for overgang til fri disponering og svensk/EU eksport (kun relevant på vei)
			sadmohfRecord.setEhprt("IMMEDIATE_RELEASE_IMPORT");
			sadmohfRecord.setEhupr("EXP");
			logger.info("A");
		}else {
			//transit-MRN exists only as clean transit: Eksempel 3 – Transittering som er startet opp utenfor Norge og som bare skal grensepasseres ved ankomst til grensen
			if(sadmohfRecord.getEh0068a()==0 && sadmohfRecord.getEh0068b()==0) {
				sadmohfRecord.setEhprt("TRANSIT_IMPORT");
				sadmohfRecord.setEhupr("TRA");
				logger.info("B");
			}else {
				//MRN exists and CUDE also: Eksempel 2 – Tolldeklarasjon for overgang til fri disponering og transittering som skal fullføres ved grensepassering
				sadmohfRecord.setEhprt("IMMEDIATE_RELEASE_IMPORT");
				sadmohfRecord.setEhupr("TRA");
				logger.info("C");
				if(StringUtils.isNotEmpty(sadmohfRecord.getEheid())){
					sadmohfRecord.setEhupr("TRE");
					logger.info("D");
				}
			}
		}
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param tur
	 * @param opd
	 */
	private SadOppdragRecord getOppdrag(String applicationUser, String tur, String opd) {
		SadOppdragRecord retval = null;
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_OPPDRAG_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + applicationUser + "&tur=" + tur;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && !container.getOrderList().isEmpty()) {
    			for(SadOppdragRecord record: container.getOrderList()) {
    				if(record.getSitdn().equals(opd)) {
    					//Dekl.dato format to NO
    					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
							if (record.getWeh0068a().length()==8) {
								record.setWeh0068a(this.dateMgr.getDateFormatted_NO(record.getWeh0068a(), DateTimeManager.ISO_FORMAT));
							}
						}
    					logger.info(record.getWeh0068a());
    					logger.info("Mrn:" + record.getWfssokmrn());
    					retval = record;
    					break;
    				}
    			}
    		}
    		
		}
    	
    	return retval;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param dato
	 * @param avd
	 * @param opd
	 * @return
	 */
	private SadOppdragRecord getSadi(String applicationUser, String dato, String avd, String opd, String bilnr) {
		SadOppdragRecord retval = null;
		
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_SADI_URL;
		//add URL-parameters
		StringBuilder urlRequestParams =  new StringBuilder();
		urlRequestParams.append("user=" + applicationUser);
		if(StringUtils.isNotEmpty(bilnr)){
			urlRequestParams.append("&bil=" + bilnr);
		}
		if(StringUtils.isNotEmpty(dato)){
			urlRequestParams.append("&dato=" + dato);
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		SadOppdragContainer container = this.sadOppdragService.getListContainer(jsonPayload);
    		if(container!=null && !container.getList().isEmpty()) {
    			for(SadOppdragRecord record: container.getList()) {
    				if(record.getSitdn().equals(opd) && record.getSiavd().equals(avd)) {
    					logger.info(record.getSitdn());
    					//Dekl.dato format to NO
    					if(StringUtils.isNotEmpty(record.getWeh0068a())) {
							if (record.getWeh0068a().length()==8) {
								record.setWeh0068a(this.dateMgr.getDateFormatted_NO(record.getWeh0068a(), DateTimeManager.ISO_FORMAT));
							}
						}
    					logger.info(record.getWeh0068a());
    					retval = record;
    					break;
    				}
    			}
    		}
    		
		}
    	
    	return retval;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param etavd
	 * @return
	 */
	private SadmoafRecord getDefaultValues(String applicationUser, String etavd) {
		  SadmoafRecord result = null;
		  //prepare the access CGI with RPG back-end
		  String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL;
		  StringBuffer urlRequestParamsKeys = new StringBuffer();
		  urlRequestParamsKeys.append("user=" + applicationUser + "&etavd=" + etavd);
		  
		  		  
		  logger.info("URL: " + BASE_URL);
		  logger.info("PARAMS: " + urlRequestParamsKeys);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		  String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
		
		  logger.info(jsonPayload);
		  logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		  if(jsonPayload!=null){
			SadmoafContainer container = this.sadmoafListService.getListContainer(jsonPayload);
			if(container!=null){
				for(SadmoafRecord  record : container.getList()){
					logger.info("Ombud navn:" + record.getEtnar());
					result = record;
				}
			}
		  }
	    	
		  return result ;
	}
	/**
	 * 
	 * @param record
	 */
	private void washTranspMate(SadTurRecord record) {
		//supported by Digitoll
		if(record.getTutrma().equals("10") || record.getTutrma().equals("20") || record.getTutrma().equals("21") || record.getTutrma().equals("30") ||
				record.getTutrma().equals("31") || record.getTutrma().equals("41") || record.getTutrma().equals("80") ){
		}else {
			record.setTutrma("");
		}
	}
	/**
	 * 
	 * @param applicationUser
	 * @param customerName
	 * @param customerNumber
	 * @return
	 */
	private String getRequestUrlKeyParametersForSearchCustomer(String applicationUser, String customerName, String customerNumber){
		  StringBuffer sb = new StringBuffer();
		  sb.append("user=" + applicationUser);
		  if(customerName!=null && !"".equals(customerName) && customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }else if (customerName!=null && !"".equals(customerName)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sonavn=" + customerName );
		  }else if (customerNumber!=null && !"".equals(customerNumber)){
			  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "knr=" + customerNumber );
		  }
		  
		  return sb.toString();
	  }
	/**
	 * 
	 * @param user
	 * @param recordToValidate
	 */
	private void getTransportDto(String user, SadmohfRecord recordToValidate) {
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + user + "&etlnrt=" + recordToValidate.getEhlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
			List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null && outputList.size() > 0){
				for (SadmotfRecord record : outputList) {
					recordToValidate.setTransportDto(record);
				}
			}
			
    	}	
	}
	/**
	 * 
	 * @param user
	 * @param recordToValidate
	 */
	private void getTransportDto(String user, SadmomfRecord masterRecord) {
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_TRANSPORT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + user + "&etlnrt=" + masterRecord.getEmlnrt();
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmotfContainer jsonContainer = this.sadmotfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
			List<SadmotfRecord> outputList = (List)jsonContainer.getList();
			if(outputList!=null && outputList.size() > 0){
				for (SadmotfRecord record : outputList) {
					masterRecord.setTransportDto(record);
				}
			}
			
    	}	
	}
	
	/**
	 * 
	 * @param user
	 * @param emlnrt
	 * @param emlnrm
	 * @return
	 */
	private SadmomfRecord getMasterDto(String user, String emlnrt, String emlnrm) {
		SadmomfRecord result = null;
		//get BASE URL
		final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL;
		//add URL-parameters
		String urlRequestParams = "user=" + user + "&emlnrt=" + emlnrt + "&emlnrm=" + emlnrm;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + BASE_URL);
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		
    		SadmomfContainer container = this.sadmomfListService.getListContainer(jsonPayload);
    		//----------------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//----------------------------------------------------------------
			List<SadmomfRecord> outputList = (List)container.getList();
			if(outputList!=null && outputList.size() > 0){
				for (SadmomfRecord record : outputList) {
					result = record;
				}
			}
			
    	}
    	
    	return result;
	}
	/**
	 * 
	 * @param user
	 * @param sadmohfRecord
	 */
	private void adjustFieldsForUpdateHouse(String user, SadmohfRecord sadmohfRecord){
		
		this.getTransportDto(user, sadmohfRecord);
		logger.info("!!!!!!!! getting orgNr for EHDKH from Transport DTO:" + sadmohfRecord.getTransportDto().toString());
		//get the first part of the string, namely the OrnNr from the representative
		String orgNr = sadmohfRecord.getTransportDto().getEtrgr();
		if(StringUtils.isEmpty(orgNr)) {
			orgNr = sadmohfRecord.getTransportDto().getEtrgt(); //Carrier's OrgNr
		}
		sadmohfRecord.setEhdkh(this.houseControllerService.getRandomDocumentId(orgNr, sadmohfRecord));
		
		
		
	}
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private SadmoifListService sadmoifListService;
	@Autowired
	private SadmotfListService sadmotfListService;
	@Autowired
	private SadmomfListService sadmomfListService;
	@Autowired
	private SadmohfListService sadmohfListService;
	@Autowired
	private SadmoafListService sadmoafListService;
	@Autowired
	private SadOppdragService sadOppdragService;
	@Autowired
	private SadTurService sadTurService;
	
	@Autowired
	private GeneralUpdateService generalUpdateService;
	
	@Autowired
	private HouseControllerService houseControllerService;
	@Autowired
	private MasterControllerService masterControllerService;
	
	@Autowired
	private TvinnSadCustomerService tvinnSadCustomerService;
}
