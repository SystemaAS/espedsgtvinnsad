/**
 * 
 */
package no.systema.tvinn.sad.sadimport.util.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/*
import no.systema.tds.tdsimport.controller.TdsImportItemsController;
import no.systema.tds.tdsimport.model.jsonjackson.topic.JsonTdsImportSpecificTopicRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemContainer;
import no.systema.tds.tdsimport.service.TdsImportSpecificTopicItemService;
import no.systema.tds.tdsimport.url.store.TdsImportUrlDataStore;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterContainer;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemAvgifterRecord;
import no.systema.tds.tdsimport.model.jsonjackson.topic.items.JsonTdsImportSpecificTopicItemRecord;
import no.systema.tds.util.TdsConstants;
*/
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.model.topic.items.SadImportSpecificTopicItemAvgiftDynamicObject;
import no.systema.tvinn.sad.sadimport.service.SadImportSpecificTopicItemService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.JsonDebugger;




/**
 * This class calculates the charges (Avgifter) in a SAD-Import item scenario.
 * 
 * @author oscardelatorre
 * @date Dec 11, 2013
 * 
 */
public class SadImportAvgiftsberakningenMgr {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(1000);
	private static final Logger logger = Logger.getLogger(SadImportAvgiftsberakningenMgr.class.getName());
	private SadImportSpecificTopicItemService sadImportSpecificTopicItemService;
	private UrlCgiProxyService urlCgiProxyService;
	
	/**
	 * Constructor
	 * @param itemService
	 * @param urlProxyService
	 */
	public SadImportAvgiftsberakningenMgr(SadImportSpecificTopicItemService itemService, UrlCgiProxyService urlProxyService){
		this.sadImportSpecificTopicItemService = itemService;
		this.urlCgiProxyService = urlProxyService;
		
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param svvnt
	 * @return
	 */
	public List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> getAvgiftDataBeforeCalculation(String applicationUser, String svvnt, String siknk_receiverId) {
		  final String METHOD = "[DEBUG] getAvgiftDataBeforeCalculation ";
		  logger.info(METHOD + "Inside...");
		  List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> result = new ArrayList<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord>();
		  try{
			  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_AVGIFTER_BEFORE_CALCULATION_URL;
			  String urlRequestParamsKeys = "user=" + applicationUser + "&svvnt=" + svvnt + "&siknk=" + siknk_receiverId;
			  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
			  //logger.info(METHOD + "URL:" + BASE_URL);
		  	  //logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
			  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			  //logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			  
			  JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemAvgifterBeforeCalculationContainer(jsonPayload);
			  //we are returning a list instead of a Set since we must have an ordered list. TreeSet did not work...
			  for(JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord record : container.getGetavgifter()){
				  //logger.info("awa:" + record.getAwa());
				  result.add(record);
			  }	
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	public boolean isMultipleChoiceAvgiftMatrix(List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> avgList) {
		boolean retval = false;
		for(JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord record:avgList){
			//It's enough to check if there is a second choice after Awc1.
			//The choices could be between: awc2-awc9
			if(record.getAwc2()!=null && !"".equals(record.getAwc2())){
				retval = true;
				break;
			}
		}
		
		return retval;
	}
	/**
	 * 
	 * @param recordToValidate
	 * @return
	 */
	public boolean avgiftValuesExist(JsonSadImportSpecificTopicItemRecord recordToValidate){
		boolean retval = false;
		//Check only Level 1
		if(recordToValidate.getWg1()!=null && !"".equals(recordToValidate.getWg1()) ){ 
			if(recordToValidate.getWh1()!=null && !"".equals(recordToValidate.getWh1()) ){
				if(recordToValidate.getWk1()!=null && !"".equals(recordToValidate.getWk1()) ){
					if(recordToValidate.getWj1()!=null && !"".equals(recordToValidate.getWj1()) ){
						if(recordToValidate.getWi1()!=null && !"".equals(recordToValidate.getWi1()) ){
							retval = true;
						}
					}
				}
			}
		}
		
		return retval;
	}
	
	
	/**
	 * 
	 * @param avgList
	 * @param recordToValidate
	 */
	
	public void calculateSingleAvgifter(String applicationUser, List<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> avgList, JsonSadImportSpecificTopicRecord headerRecord, JsonSadImportSpecificTopicItemRecord recordToValidate){
		int counter = 0;
		String kode0=""; String sekvensList_0="";  String sats0="";  String kode1="";  String sekvensList_1="";  String sats1="";  String kode2="";  String sekvensList_2="";  String sats2=""; 
		String kode3="";  String sekvensList_3="";  String sats3=""; String kode4="";  String sekvensList_4="";  String sats4="";  String kode5="";  String sekvensList_5="";  String sats5=""; 
		String kode6="";  String sekvensList_6="";  String sats6=""; String kode7="";  String sekvensList_7="";  String sats7="";  String kode8="";  String sekvensList_8="";  String sats8="";
		String kode9="";  String sekvensList_9="";  String sats9=""; String kode10="";  String sekvensList_10="";  String sats10=""; String kode11="";  String sekvensList_11="";  String sats11="";
		String kode12="";  String sekvensList_12="";  String sats12="";
		
		for(JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord avgRecord : avgList){
			if(counter==0){
				kode0 = avgRecord.getAwa(); sekvensList_0 = avgRecord.getAwc1(); sats0 = avgRecord.getAwd1();
			}else if (counter==1){
				kode1 = avgRecord.getAwa(); sekvensList_1 = avgRecord.getAwc1(); sats1 = avgRecord.getAwd1();
			}else if (counter==2){
				kode2 = avgRecord.getAwa(); sekvensList_2 = avgRecord.getAwc1(); sats2 = avgRecord.getAwd1();
			}else if (counter==3){
				kode3 = avgRecord.getAwa(); sekvensList_3 = avgRecord.getAwc1(); sats3 = avgRecord.getAwd1();
			}else if (counter==4){
				kode4 = avgRecord.getAwa(); sekvensList_4 = avgRecord.getAwc1(); sats4 = avgRecord.getAwd1();
			}else if (counter==5){
				kode5 = avgRecord.getAwa(); sekvensList_5 = avgRecord.getAwc1(); sats5 = avgRecord.getAwd1();
			}else if (counter==6){
				kode6 = avgRecord.getAwa(); sekvensList_6 = avgRecord.getAwc1(); sats6 = avgRecord.getAwd1();
			}else if (counter==7){
				kode7 = avgRecord.getAwa(); sekvensList_7 = avgRecord.getAwc1(); sats7 = avgRecord.getAwd1();
			}else if (counter==8){
				kode8 = avgRecord.getAwa(); sekvensList_8 = avgRecord.getAwc1(); sats8 = avgRecord.getAwd1();
			}else if (counter==9){
				kode9 = avgRecord.getAwa(); sekvensList_9 = avgRecord.getAwc1(); sats9 = avgRecord.getAwd1();
			}else if (counter==10){
				kode10 = avgRecord.getAwa(); sekvensList_10 = avgRecord.getAwc1(); sats10 = avgRecord.getAwd1();
			}else if (counter==11){
				kode11 = avgRecord.getAwa(); sekvensList_11 = avgRecord.getAwc1(); sats11 = avgRecord.getAwd1();
			}else if (counter==12){
				kode12 = avgRecord.getAwa(); sekvensList_12 = avgRecord.getAwc1(); sats12 = avgRecord.getAwd1();
			}
			counter++;
		}
		
		//calculate
		List<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> listAfterCalculation = this.setAvgiftDataAfterCalculation(applicationUser, recordToValidate.getSvvnt(), headerRecord.getInsibvnv(), headerRecord.getSibel3(), headerRecord.getSibel4(), headerRecord.getSibelr(), headerRecord.getSibels(), 
										recordToValidate.getSvbelt(), recordToValidate.getSvvktb(), recordToValidate.getSvvktn(), recordToValidate.getSvntm(), 
										kode0, sekvensList_0, sats0, kode1, sekvensList_1, sats1, kode2, sekvensList_2, sats2, kode3, sekvensList_3, sats3, kode4, sekvensList_4, sats4, 
										kode5, sekvensList_5, sats5, kode6, sekvensList_6, sats6, kode7, sekvensList_7, sats7, kode8, sekvensList_8, sats8, kode9, sekvensList_9, sats9, 
										kode10, sekvensList_10, sats10, kode11, sekvensList_11, sats11, kode12, sekvensList_12, sats12);
		//hand over final values
		this.handoverAvgifterAfterCalculationToRecord(listAfterCalculation, recordToValidate);
		
	}
	
	/**
	 * 
	 * @param listAfterCalculation
	 * @param recordToValidate
	 */
	private void handoverAvgifterAfterCalculationToRecord(List<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> listAfterCalculation, JsonSadImportSpecificTopicItemRecord recordToValidate){
		int counter = 0;
		
		for(JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord record : listAfterCalculation){
			if( (record.getWh()!=null && !"".equals(record.getWh()) && (record.getWk()!=null) && !"".equals(record.getWk())) ){
				if(counter==0){
					if( (recordToValidate.getWh1()!=null && !"".equals(recordToValidate.getWh1()) && (recordToValidate.getWk1()!=null) && !"".equals(recordToValidate.getWk1())) ){
						//already values in place
					}else{
						//logger.info("KODE:" + record.getWg());
						//logger.info("SEKV:" + record.getWh());
						recordToValidate.setWg1(record.getWg()); recordToValidate.setWh1(record.getWh()); recordToValidate.setWk1(record.getWk()); 
						recordToValidate.setWj1(record.getWj()); recordToValidate.setWi1(record.getWi());
					}
				}else if(counter==1){
					if( (recordToValidate.getWh2()!=null && !"".equals(recordToValidate.getWh2()) && (recordToValidate.getWk2()!=null) && !"".equals(recordToValidate.getWk2())) ){
						//already values in place
					}else{
						//logger.info("KODE:" + record.getWg());
						//logger.info("SEKV:" + record.getWh());
						recordToValidate.setWg2(record.getWg()); recordToValidate.setWh2(record.getWh()); recordToValidate.setWk2(record.getWk()); 
						recordToValidate.setWj2(record.getWj()); recordToValidate.setWi2(record.getWi());
					}
				}else if(counter==2){
					if( (recordToValidate.getWh3()!=null && !"".equals(recordToValidate.getWh3()) && (recordToValidate.getWk3()!=null) && !"".equals(recordToValidate.getWk3())) ){
						//already values in place
					}else{
						recordToValidate.setWg3(record.getWg()); recordToValidate.setWh3(record.getWh()); recordToValidate.setWk3(record.getWk()); 
						recordToValidate.setWj3(record.getWj()); recordToValidate.setWi3(record.getWi());
					}
				}else if(counter==3){
					if( (recordToValidate.getWh4()!=null && !"".equals(recordToValidate.getWh4()) && (recordToValidate.getWk4()!=null) && !"".equals(recordToValidate.getWk4())) ){
						//already values in place
					}else{
						recordToValidate.setWg4(record.getWg()); recordToValidate.setWh4(record.getWh()); recordToValidate.setWk4(record.getWk()); 
						recordToValidate.setWj4(record.getWj()); recordToValidate.setWi4(record.getWi());
					}
				}else if(counter==4){
					if( (recordToValidate.getWh5()!=null && !"".equals(recordToValidate.getWh5()) && (recordToValidate.getWk5()!=null) && !"".equals(recordToValidate.getWk5())) ){
						//already values in place
					}else{
						recordToValidate.setWg5(record.getWg()); recordToValidate.setWh5(record.getWh()); recordToValidate.setWk5(record.getWk()); 
						recordToValidate.setWj5(record.getWj()); recordToValidate.setWi5(record.getWi());
					}
				}else if(counter==5){
					if( (recordToValidate.getWh6()!=null && !"".equals(recordToValidate.getWh6()) && (recordToValidate.getWk6()!=null) && !"".equals(recordToValidate.getWk6())) ){
						//already values in place
					}else{
						recordToValidate.setWg6(record.getWg()); recordToValidate.setWh6(record.getWh()); recordToValidate.setWk6(record.getWk()); 
						recordToValidate.setWj6(record.getWj()); recordToValidate.setWi6(record.getWi());
					}
				}else if(counter==6){
					if( (recordToValidate.getWh7()!=null && !"".equals(recordToValidate.getWh7()) && (recordToValidate.getWk7()!=null) && !"".equals(recordToValidate.getWk7())) ){
						//already values in place
					}else{
						recordToValidate.setWg7(record.getWg()); recordToValidate.setWh7(record.getWh()); recordToValidate.setWk7(record.getWk()); 
						recordToValidate.setWj7(record.getWj()); recordToValidate.setWi7(record.getWi());
					}
				}else if(counter==7){
					if( (recordToValidate.getWh8()!=null && !"".equals(recordToValidate.getWh8()) && (recordToValidate.getWk8()!=null) && !"".equals(recordToValidate.getWk8())) ){
						//already values in place
					}else{
						recordToValidate.setWg8(record.getWg()); recordToValidate.setWh8(record.getWh()); recordToValidate.setWk8(record.getWk()); 
						recordToValidate.setWj8(record.getWj()); recordToValidate.setWi8(record.getWi());
					}
				}
			}
			
			counter++;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param svvnt
	 * @param omrakningsFaktor
	 * @param fakturaSum
	 * @param belCifSum
	 * @param bearbKost
	 * @param cifsum
	 * @param tollvardi
	 * @param bruttoVekt
	 * @param nettoVekt
	 * @param mengde
	 * @param kode0
	 * @param sekvensList_0
	 * @param sats0
	 * @param kode1
	 * @param sekvensList_1
	 * @param sats1
	 * @param kode2
	 * @param sekvensList_2
	 * @param sats2
	 * @param kode3
	 * @param sekvensList_3
	 * @param sats3
	 * @param kode4
	 * @param sekvensList_4
	 * @param sats4
	 * @param kode5
	 * @param sekvensList_5
	 * @param sats5
	 * @param kode6
	 * @param sekvensList_6
	 * @param sats6
	 * @param kode7
	 * @param sekvensList_7
	 * @param sats7
	 * @param kode8
	 * @param sekvensList_8
	 * @param sats8
	 * @param kode9
	 * @param sekvensList_9
	 * @param sats9
	 * @param kode10
	 * @param sekvensList_10
	 * @param sats10
	 * @param kode11
	 * @param sekvensList_11
	 * @param sats11
	 * @param kode12
	 * @param sekvensList_12
	 * @param sats12
	 * @return
	 */
	private List<JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord> setAvgiftDataAfterCalculation(String applicationUser, String svvnt,
			  String omrakningsFaktor, String fakturaSum, String belCifSum, String bearbKost, String cifsum, String tollvardi, String bruttoVekt, String nettoVekt, String mengde, 
			  String kode0, String sekvensList_0, String sats0, String kode1, String sekvensList_1, String sats1, String kode2, String sekvensList_2, String sats2, 
			  String kode3, String sekvensList_3, String sats3, String kode4, String sekvensList_4, String sats4, String kode5, String sekvensList_5, String sats5, 
			  String kode6, String sekvensList_6, String sats6, String kode7, String sekvensList_7, String sats7, String kode8, String sekvensList_8, String sats8,
			  String kode9, String sekvensList_9, String sats9, String kode10, String sekvensList_10, String sats10, String kode11, String sekvensList_11, String sats11,
			  String kode12, String sekvensList_12, String sats12) {

		 final Integer NUMBER_OF_FIELDS = 13;
		 final String METHOD = "[DEBUG] setAvgiftDataAfterCalculation";
		  logger.info(METHOD + " Inside...");
		  List result = new ArrayList();
		  try{
			  Map<Integer, SadImportSpecificTopicItemAvgiftDynamicObject> recordDynObj = this.getDynamicObject(
					   kode0, sekvensList_0,  sats0, kode1, sekvensList_1,  sats1, kode2, sekvensList_2,  sats2,kode3, sekvensList_3,  sats3, 
					   kode4, sekvensList_4,  sats4, kode5, sekvensList_5,  sats5, kode6, sekvensList_6,  sats6,kode7, sekvensList_7,  sats7,
					   kode8, sekvensList_8,  sats8, kode9, sekvensList_9,  sats9, kode10, sekvensList_10,  sats10, kode11, sekvensList_11,  sats11,
					   kode12, sekvensList_12,  sats12);
			  
			  String urlRequestHeaderParamsKeys=null;
			  String urlRequestItemLineParamsKeys=null;
			  String urlRequestParamsKeys=null;
			  
	
			  for(int i=0;i<NUMBER_OF_FIELDS;i++){
				  
				  SadImportSpecificTopicItemAvgiftDynamicObject obj = (SadImportSpecificTopicItemAvgiftDynamicObject)recordDynObj.get(i);
				  if(this.isValidItemAvgift(obj)){
					  urlRequestHeaderParamsKeys = this.getRequestUrlHeaderKeyParametersForAvgifterAfterCalculation(applicationUser,omrakningsFaktor,fakturaSum,belCifSum,bearbKost,cifsum);
					  urlRequestItemLineParamsKeys = this.getRequestUrlItemKeyParametersForAvgifterAfterCalculation(obj.getKode(), obj.getSekvens(), obj.getSats(), tollvardi,bruttoVekt,nettoVekt,mengde );
					  urlRequestParamsKeys = urlRequestHeaderParamsKeys + urlRequestItemLineParamsKeys;
					  String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_FETCH_AVGIFTER_AFTER_CALCULATION_URL;
					  UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
					  //logger.info(METHOD + "URL:" + BASE_URL);
				  	  //logger.info(METHOD + "PARAMS:" + urlRequestParamsKeys);
				  	  
				  	  String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				  	  //logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
					  
				  	  JsonSadImportSpecificTopicItemAvgifterAfterCalculationContainer container = this.sadImportSpecificTopicItemService.getSadImportSpecificTopicItemAvgifterAfterCalculationContainer(jsonPayload);
					  //we are returning a list instead of a Set since we must have an ordered list. TreeSet did not work...
					  for(JsonSadImportSpecificTopicItemAvgifterAfterCalculationRecord record : container.getCalcavgifter()){
						  //hand over from container to record;
						  record.setWg(container.getWg());record.setWh(container.getWh());record.setWk(container.getWk());
						  result.add(record);
					  }
					  
				  }
			  }
			  
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  return result;
	  }
	  
	  
	  /**
	   * 
	   * @param applicationUser
	   * @param omrakningsFaktor
	   * @param fakturaSum
	   * @param belCifSum
	   * @param bearbKost
	   * @param cifsum
	   * @return
	   */
	  private String getRequestUrlHeaderKeyParametersForAvgifterAfterCalculation(String applicationUser,
				 String omrakningsFaktor, String fakturaSum, String belCifSum, String bearbKost, String cifsum ){
			  
			 StringBuffer sb = new StringBuffer();
			 sb.append("user=" + applicationUser);
			 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ubvnv=" + omrakningsFaktor);
			 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibel3=" + fakturaSum);
			 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibel4=" + belCifSum);
			 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibelr=" + bearbKost);
			 sb.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sibels=" + cifsum);
			  
			 return sb.toString();
		  }
	  
	  /**
	   * 
	   * @param kode
	   * @param sekvensList
	   * @param sats
	   * @param tollvardi
	   * @param bruttoVekt
	   * @param nettoVekt
	   * @param mengde
	   * @return
	   */
	  private String getRequestUrlItemKeyParametersForAvgifterAfterCalculation(String kode, String sekvensList, String sats, 
				 String tollvardi, String bruttoVekt, String nettoVekt, String mengde ){
			 
			 StringBuffer sb = new StringBuffer();
			 if( (sekvensList!=null && !"".equals(sekvensList)) && (sats!=null && !"".equals(sats)) ){
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wg=" + kode );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wh=" + sekvensList );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "wk=" + sats );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svbelt=" + tollvardi );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svvktb=" + bruttoVekt );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svvktn=" + nettoVekt );
				 sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svntm=" + mengde );
			 }
			 return sb.toString();
	  }  
	
	  /**
	   * 
	   * @param kode0
	   * @param sekvensList_0
	   * @param sats0
	   * @param kode1
	   * @param sekvensList_1
	   * @param sats1
	   * @param kode2
	   * @param sekvensList_2
	   * @param sats2
	   * @param kode3
	   * @param sekvensList_3
	   * @param sats3
	   * @param kode4
	   * @param sekvensList_4
	   * @param sats4
	   * @param kode5
	   * @param sekvensList_5
	   * @param sats5
	   * @param kode6
	   * @param sekvensList_6
	   * @param sats6
	   * @param kode7
	   * @param sekvensList_7
	   * @param sats7
	   * @param kode8
	   * @param sekvensList_8
	   * @param sats8
	   * @param kode9
	   * @param sekvensList_9
	   * @param sats9
	   * @param kode10
	   * @param sekvensList_10
	   * @param sats10
	   * @param kode11
	   * @param sekvensList_11
	   * @param sats11
	   * @param kode12
	   * @param sekvensList_12
	   * @param sats12
	   * @return
	   */
	  private Map<Integer, SadImportSpecificTopicItemAvgiftDynamicObject>getDynamicObject(
				 String kode0, String sekvensList_0, String sats0, String kode1, String sekvensList_1, String sats1,
				 String kode2, String sekvensList_2, String sats2, String kode3, String sekvensList_3, String sats3,
				 String kode4, String sekvensList_4, String sats4, String kode5, String sekvensList_5, String sats5,
				 String kode6, String sekvensList_6, String sats6, String kode7, String sekvensList_7, String sats7,
				 String kode8, String sekvensList_8, String sats8, String kode9, String sekvensList_9, String sats9,
				 String kode10, String sekvensList_10, String sats10, String kode11, String sekvensList_11, String sats11,
				 String kode12, String sekvensList_12, String sats12){
			 
			 Map retval = new HashMap();
			 
			 SadImportSpecificTopicItemAvgiftDynamicObject obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode0);obj.setSats(sats0);obj.setSekvens(sekvensList_0);
			 retval.put(0, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode1);obj.setSats(sats1);obj.setSekvens(sekvensList_1);
			 retval.put(1, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode2);obj.setSats(sats2);obj.setSekvens(sekvensList_2);
			 retval.put(2, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode3);obj.setSats(sats3);obj.setSekvens(sekvensList_3);
			 retval.put(3, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode4);obj.setSats(sats4);obj.setSekvens(sekvensList_4);
			 retval.put(4, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode5);obj.setSats(sats5);obj.setSekvens(sekvensList_5);
			 retval.put(5, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode6);obj.setSats(sats6);obj.setSekvens(sekvensList_6);
			 retval.put(6, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode7);obj.setSats(sats7);obj.setSekvens(sekvensList_7);
			 retval.put(7, obj);
			 
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode8);obj.setSats(sats8);obj.setSekvens(sekvensList_8);
			 retval.put(8, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode9);obj.setSats(sats9);obj.setSekvens(sekvensList_9);
			 retval.put(9, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode10);obj.setSats(sats10);obj.setSekvens(sekvensList_10);
			 retval.put(10, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode11);obj.setSats(sats11);obj.setSekvens(sekvensList_11);
			 retval.put(11, obj);
			 obj = new SadImportSpecificTopicItemAvgiftDynamicObject();
			 obj.setKode(kode12);obj.setSats(sats12);obj.setSekvens(sekvensList_12);
			 retval.put(12, obj);
			 
			 
			 return retval;
	 }
	  
	 /**
	  * 
	  * @param obj
	  * @return
	  */
	  private boolean isValidItemAvgift(SadImportSpecificTopicItemAvgiftDynamicObject obj){
			 boolean retval = false;
			 if( (obj.getSekvens()!=null && !"".equals(obj.getSekvens())) && (obj.getSats()!=null && !"".equals(obj.getSats()))){
				 retval = true;
			 }
			 
			 return retval;
	 }
	
}
