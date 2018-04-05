package no.systema.tvinn.sad.nctsexport.validator;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicService;
import no.systema.tvinn.sad.nctsexport.service.SadNctsExportSpecificTopicServiceImpl;

import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.JsonSadNctsExportSpecificTopicRecord;
import no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation.JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer;

import no.systema.tvinn.sad.nctsexport.url.store.SadNctsExportUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;


/**
 * 
 * @author oscardelatorre
 * @date Sep 8, 2014
 * 
 *
 */
public class SadNctsExportHeaderValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadNctsExportHeaderValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SadNctsExportSpecificTopicService nctsExportSpecificTopicService = new SadNctsExportSpecificTopicServiceImpl();
	   
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadNctsExportSpecificTopicRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadNctsExportSpecificTopicRecord record = (JsonSadNctsExportSpecificTopicRecord)obj;
		logger.info("Inside SadNctsExportHeaderValidator");
		
		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdk", "systema.tvinn.sad.ncts.export.header.error.null.thdk"); 
		//Avsender
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnas", "systema.tvinn.sad.ncts.export.header.error.null.thnas"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtins", "systema.tvinn.sad.ncts.export.header.error.null.thtins"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thads1", "systema.tvinn.sad.ncts.export.header.error.null.thads1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thsks", "systema.tvinn.sad.ncts.export.header.error.null.thsks"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpns", "systema.tvinn.sad.ncts.export.header.error.null.thpns"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpss", "systema.tvinn.sad.ncts.export.header.error.null.thpss"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlks", "systema.tvinn.sad.ncts.export.header.error.null.thlks"); 
		//Mottaker
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnak", "systema.tvinn.sad.ncts.export.header.error.null.thnak"); 
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtins", "systema.tvinn.sad.ncts.export.header.error.null.thtink"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thadk1", "systema.tvinn.sad.ncts.export.header.error.null.thadk1"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thskk", "systema.tvinn.sad.ncts.export.header.error.null.thskk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpnk", "systema.tvinn.sad.ncts.export.header.error.null.thpnk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thpsk", "systema.tvinn.sad.ncts.export.header.error.null.thpsk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlkk", "systema.tvinn.sad.ncts.export.header.error.null.thlkk"); 
		
		//ansvarig
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thnaa", "systema.tvinn.sad.ncts.export.header.error.null.thnaa"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtina", "systema.tvinn.sad.ncts.export.header.error.null.thtina"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thlka", "systema.tvinn.sad.ncts.export.header.error.null.thlka"); 
		
		//header
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thalk", "systema.tvinn.sad.ncts.export.header.error.null.thalk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thblk", "systema.tvinn.sad.ncts.export.header.error.null.thblk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtaid", "systema.tvinn.sad.ncts.export.header.error.null.thtaid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtalk", "systema.tvinn.sad.ncts.export.header.error.null.thtalk");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtgid", "systema.tvinn.sad.ncts.export.header.error.null.thtgid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtglk", "systema.tvinn.sad.ncts.export.header.error.null.thtglk");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtrm", "systema.tvinn.sad.ncts.export.header.error.null.thtrm");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thcats", "systema.tvinn.sad.ncts.export.header.error.null.thcats");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thskfd", "systema.tvinn.sad.ncts.export.header.error.null.thskfd");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdst", "systema.tvinn.sad.ncts.export.header.error.null.thdst");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thdsk", "systema.tvinn.sad.ncts.export.header.error.null.thdsk");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtsd1", "systema.tvinn.sad.ncts.export.header.error.null.thtsd1");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thtsb", "systema.tvinn.sad.ncts.export.header.error.null.thtsb");
		//Garanti
		if(!"SS".equals(record.getThdk())){
			if(record.getThgft2()==null || "".equals (record.getThgft2())){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgkd", "systema.tvinn.sad.ncts.export.header.error.null.thgkd");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "thgft1", "systema.tvinn.sad.ncts.export.header.error.null.thgft1");
			}
		}
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				
				//-----------------
				//Date validations
				//-----------------
				if(record.getThtrdt()!=null && !"".equals(record.getThtrdt())){
					if(!this.isValidDate(record.getThtrdt())){
						errors.rejectValue("thtrdt", "systema.tvinn.sad.ncts.export.header.error.rule.thtrdt.invalid.date.format");
						logger.info("ERROR thtrdt");
					}
				}
				/*
				if(record.getThddtk()!=null && !"".equals(record.getThddtk())){
					if(!this.isValidDate(record.getThddtk())){
						errors.rejectValue("thddtk", "systema.tvinn.sad.ncts.export.header.error.rule.thddtk.invalid.date.format");
						logger.info("ERROR thddtk");

					}
				}*/
				if(record.getThddt()!=null && !"".equals(record.getThddt())){
					if(!this.isValidDate(record.getThddt())){
						errors.rejectValue("thddt", "systema.tvinn.sad.ncts.export.header.error.rule.thddt.invalid.date.format");
						logger.info("ERROR thddt");
					}
				}
				
				/*
				//------------------------------------------------------------------
				//Förseglingsrelaterade fält (all 3 fields must be filled or empty)
				//------------------------------------------------------------------				
				if(record.getThdfkd()!=null && !"".equals(record.getThdfkd())){
					if(record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdfsk()!=null && !"".equals(record.getThdfsk())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdant()==null || "".equals(record.getThdant())){
						errors.rejectValue("thdant", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdant");
					}
				}
				if(record.getThdant()!=null && !"".equals(record.getThdant())){
					if(record.getThdfkd()==null || "".equals(record.getThdfkd())){
						errors.rejectValue("thdfkd", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfkd");
					}else if (record.getThdfsk()==null || "".equals(record.getThdfsk())){
						errors.rejectValue("thdfsk", "systema.tvinn.sad.ncts.export.header.error.rule.thdfkd.threerelatedfields");
						logger.info("ERROR thdfsk");
					}
				}
				*/
				//-----------------
				//Kontrollresultat
				//-----------------
				if(record.getThenkl()!=null && "J".equals(record.getThenkl())){
					if(!this.isForhandsvarsel(record.getThdk())){
						if(!"A3".equals(record.getThdkr()) ){
							errors.rejectValue("thdkr", "systema.tvinn.sad.ncts.export.header.error.rule.thdkr.invalid.value.withforenkladprocedure");
						}
						if("".equals(record.getThddt()) ){
							errors.rejectValue("thddt", "systema.tvinn.sad.ncts.export.header.error.rule.thddt.invalid.value.withforenkladprocedure");
						}else{
							if(this.isValidFristDate(record.getThddt())){
								//OK
							}else{
								errors.rejectValue("thddt", "systema.tvinn.sad.ncts.export.header.error.rule.thddt.invalid.date.logical");
							}
						}
					}
				}
				
				/*
				//Transitkontor
				if(record.getThdkr()!=null && "A3".equals(record.getThdkr())){
					if( record.getThalk()!=null && record.getThblk()!=null){
						if(record.getThalk().equals(record.getThblk())){
							//nothing
						}else{
							if(record.getThtsd1()==null || "".equals (record.getThtsd1())){
								errors.rejectValue("thtsd1", "systema.tvinn.sad.ncts.export.header.error.rule.thtsd1.atleastone");
								logger.info("ERROR thtsd1");
							}
						}
					}
				}
				
				//Begränsnings land
				if("1".equals(record.getThgbgi())){
					if(record.getThgbgu()==null || "".equals(record.getThgbgu())){
						errors.rejectValue("thgbgu", "systema.tvinn.sad.ncts.export.header.error.rule.thgbgu.countrycode");
					}
				}
				*/
				
				//--------
				//Garanti
				//--------
				/*if(record.getThgft1()==null || "".equals(record.getThgft1())){
					if(record.getThgft2()==null || "".equals (record.getThgft2())){
						errors.rejectValue("thgft1", "systema.tvinn.sad.ncts.export.header.error.rule.thgft1.atleastone");
						logger.info("ERROR thgft1");
					}
				}*/
				//Garantikoder
				if(this.isForhandsvarsel(record.getThdk())){
					if(record.getThgft1()!=null && !"".equals (record.getThgft1())){
						errors.reject("thgft1", "Garantifejl: Forhåndsvarsl(SS) skal ikke ha garantikoder(Garantinr)" );
					}
					if(record.getThgadk()!=null && !"".equals (record.getThgadk())){
						errors.reject("thgft1", "Garantifejl: Forhåndsvarsl(SS) skal ikke ha garantikoder(Tilg.kode)" );
					}
				}else{
					//Only if annan garanti is empty
					if(record.getThgft2()==null || "".equals (record.getThgft2())){
						String errMsg = this.isValidGuarantee(record);
						if(errMsg!=null){
							errors.reject("thgft1", "Garantifel: " + errMsg);
						}
					}
				}
				//Mandatory lastested vid DeklTyp: SS
				if(this.isForhandsvarsel(record.getThdk())){
					if(!"".equals(record.getThlsd())){
						//OK
					}else{
						errors.rejectValue("thlsd", "systema.tvinn.sad.ncts.export.header.error.rule.thlsd.lastested.mandatory");
					}
					//Frister
					if(!"".equals(record.getThdkr())){
						errors.rejectValue("thdkr", "systema.tvinn.sad.ncts.export.header.error.rule.thdkr.invalid.value.withss.type");
					}else if(!"".equals(record.getThddt())){
						errors.rejectValue("thddt", "systema.tvinn.sad.ncts.export.header.error.rule.thddt.invalid.value.withss.type");
						
					}
				}
			}
		}
		
	}
	/**
	 * 
	 * @param code
	 * @return
	 */
	private boolean isForhandsvarsel(String code){
		boolean retval = false;
		
		if("SS".equals(code)){
			retval = true;
		}
		return retval;
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		//ISO->>SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy"); //NO
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidFristDate(String rawValue){
		boolean retval = false;
		DateTimeManager mgr = new DateTimeManager();
		retval = mgr.isValidForwardDateIncludingToday(rawValue, "ddMMyy");
		return retval;
	}
	
	/**
	 * Checks if the Guarantee number (thgft1) is ok together with its Guarantee code (thgadk)
	 * 
	 * @param record
	 * @return
	 */
	private String isValidGuarantee(JsonSadNctsExportSpecificTopicRecord record){
		String method = "isValidGuarantee";
	 	logger.info("Inside " + method);
	 	String retval = null;
	 	
	 	logger.info("VALIDATION of Guarantee...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = SadNctsExportUrlDataStore.NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL;
		//url params
		String urlRequestParamsKeys = this.getRequestUrlKeyParametersForGuaranteeValidation(record.getApplicationUser(),record.getThgft1(),record.getThgadk() );
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(method + " --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer container = this.nctsExportSpecificTopicService.getNctsExportSpecificTopicGuaranteeValidatorContainer(jsonPayload);
			if(container!=null){
				if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
					logger.info("######################################");
					logger.info("[ERROR] on Guarantee: " + container.getErrMsg());
					logger.info("######################################");
					retval = container.getErrMsg();
				}
			}
		}
		return retval;
	}
	/**
	   * Validate guarantee dependency fields
	   * 
	   * @param applicationUser
	   * @param guaranteeNumber
	   * @param guaranteeCode
	   * @return
	   */
	  private String getRequestUrlKeyParametersForGuaranteeValidation(String applicationUser,String guaranteeNumber,String guaranteeCode){
		  StringBuffer sb = new StringBuffer();
		  final String USER_TEST = "OSCAR"; //Special for test
		  if("Oscar".equalsIgnoreCase(applicationUser)){
			  sb.append("user=" + USER_TEST);
		  }else{
			  sb.append("user=" + applicationUser);
		  }
		  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgft1=" + guaranteeNumber );
		  sb.append( TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "thgadk=" + guaranteeCode );
		  return sb.toString();
	  }
	  
}
