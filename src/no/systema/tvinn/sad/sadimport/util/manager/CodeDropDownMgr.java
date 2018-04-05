	/**
 * 
 */
package no.systema.tvinn.sad.sadimport.util.manager;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;

import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCodeRecord;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCode2Container;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadCode2Record;

import no.systema.tvinn.sad.sadexport.util.SadExportConstants;
import no.systema.tvinn.sad.sadimport.util.SadImportConstants;
import no.systema.tvinn.sad.sadimport.util.manager.CodeDropDownMgr;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;


/**
 * The class handles general gui drop downs aspect population for TVINN-SAD IMPORT
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Maj 22, 2014
 * 
 * 	1=Ekspedisjonstyper(import)
 * 	2=Landkoder                     
	3=Transaksjonstyper     
	4=Transportmåter               
	5=Tollnedsettelser 
	6=Preferanser                 
	7=V.F. koder                 
	8=Avgiftkoder 
	8B=Avgiftkoder sekv.                   
	9=Ekspedisjonstyper(eksport)              
	
	A=Enhetskoder                          
	B=Dok./Sertifikat kode (TVINN-import)  
	C=Dok./sertifikat kode (TVINN-eksport) 
	D=lagringssted                         
	E=fylkeskoder                          
	O=Typetilfelle (omberegning)
	L=Incoterms
	V=Valutakoder
	
 * 
 */

public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	//
	
	public static final String CODE_1_EKSPEDISJONSTYPER_IMPORT = "1";
	public static final String CODE_2_COUNTRY = "2";
	public static final String CODE_3_TRANSAKSJONSTYPER = "3";
	public static final String CODE_4_TRANSPORTMATER = "4";
	public static final String CODE_5_TOLLNEDSETTELSER = "5";
	public static final String CODE_6_PREFERANSER = "6";
	public static final String CODE_7_VFKODER = "7";
	public static final String CODE_8_AVGIFTSKODER = "8";
	public static final String CODE_8B_AVGIFTSKODER_SEKV = "8B";
	public static final String CODE_9_EKSPEDISJONSTYPER_EXPORT = "9";
	//
	public static final String CODE_A_ENHETSKODER = "A";
	public static final String CODE_B_DOK_SERTIFIKAT_KODER_IMPORT = "B";
	public static final String CODE_C_DOK_SERTIFIKAT_KODER_EXPORT = "C";
	public static final String CODE_D_LAGRINGSSTED = "D";
	public static final String CODE_E_FYLKESKODERKODER = "E";
	public static final String CODE_O_TYPETILFELLE = "O";
	//
	public static final String CODE_V_CURRENCY = "V";
	public static final String CODE_L_LEVERINGSBETINGELSER = "L";
	
	
	
	/**
	 * 
	 * @param urlCgiProxyService
	 * @param skatDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
	 * @param paramKODE2
	 * @param paramKODE3
	 */
	public void populateCodesHtmlDropDownsFromJsonString(UrlCgiProxyService urlCgiProxyService, TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService,
														Map model, SystemaWebUser appUser, String paramTYP, String paramKODE2, String paramKODE3){
		//fill in html lists here
		try{
			
			String CODES_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			if(paramKODE2 !=null){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode2=" + paramKODE2);
			}
			if(paramKODE3 !=null){
				/* Obsolete since we should see all duplicates
				urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode3=" + paramKODE3);
				*/
			}
			//Now build the payload and send to the back end via the drop down service
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			
			
			JsonTvinnSadCodeContainer codeContainer = tvinnSadDropDownListPopulationService.getCodeContainer(utfPayload);
			List<JsonTvinnSadCodeRecord> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTvinnSadCodeRecord codeRecord: codeContainer.getKodlista()){
				//default
				list.add(codeRecord);
				//logger.info("CODE_RECORD: " + codeRecord.getZtxt());
			}
			
			
			if(this.CODE_1_EKSPEDISJONSTYPER_IMPORT.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST,list);
			
			}else if(this.CODE_2_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST,list);
				
			}else if(this.CODE_4_TRANSPORTMATER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST,list);
			
			}else if(this.CODE_5_TOLLNEDSETTELSER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST,list);
			
			}else if(this.CODE_6_PREFERANSER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST,list);
				
			}else if(this.CODE_7_VFKODER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST,list);
			
			}else if(this.CODE_8_AVGIFTSKODER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST,list);
			
			}else if(this.CODE_8B_AVGIFTSKODER_SEKV.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST,list);
			
			}else if(this.CODE_V_CURRENCY.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST,list);
			
			}else if(this.CODE_A_ENHETSKODER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST,list);
			
			}else if(this.CODE_B_DOK_SERTIFIKAT_KODER_IMPORT.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST,list);
			
			}else if(this.CODE_D_LAGRINGSSTED.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST,list);
			
			}else if(this.CODE_L_LEVERINGSBETINGELSER.equalsIgnoreCase(paramTYP)){
				model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST,list);
				
			}else if(this.CODE_O_TYPETILFELLE.equalsIgnoreCase(paramTYP)){
				model.put(SadExportConstants.RESOURCE_MODEL_KEY_CODE_O_TYPETILFELLE_OMBEREGNING_LIST,list);
			
			}
			
			//we put tolltariffen here since there is no other related list on ITEMS jsp
			model.put(SadImportConstants.URL_EXTERNAL_TOLLTARIFFEN, new UrlTvinnSadTolltariffenObject() );
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	

	/**
	 * 
	 * @param urlCgiProxyService
	 * @param tvinnSadDropDownListPopulationService
	 * @param model
	 * @param appUser
	 * @param paramTYP
	 */
	public void populateCodesHtmlDropDownsFromJsonString2(UrlCgiProxyService urlCgiProxyService, TvinnSadDropDownListPopulationService tvinnSadDropDownListPopulationService,
			Map model, SystemaWebUser appUser, String paramTYP){
			//fill in html lists here
			try{
			
			String CODES_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES2_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "type=" + paramTYP);
			//logger.info("CODES_URL:" + CODES_URL);
			//logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			//debug
			//logger.info(utfPayload);
			
			JsonTvinnSadCode2Container codeContainer = tvinnSadDropDownListPopulationService.getCodeContainer2(utfPayload);
			List<JsonTvinnSadCode2Record> list = new ArrayList();
			
			//Take some exception into consideration here or run the default to populate the final list
			for(JsonTvinnSadCode2Record codeRecord: codeContainer.getArkivkodelist()){
				//default
				list.add(codeRecord);
				//logger.info("CODE_RECORD: " + codeRecord.getZtxt());
			}
			model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_ARCHIVE_CODE_LIST,list);
			
			
			
			//we put tolltariffen here since there is no other related list on ITEMS jsp
			//model.put(SadImportConstants.URL_EXTERNAL_TOLLTARIFFEN, new UrlTvinnSadTolltariffenObject() );
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}

	
	
	
	
	
	
	
	/**
	 * 
	 * @param model
	 * @param session
	 */
	public void setCodeMgrListsInSession(Map model, HttpSession session){
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_3_TRANSAKSJONSTYPER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_3_TRANSAKSJONSTYPER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_9_EKSPEDISJONSTYPER_EXPORT_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_9_EKSPEDISJONSTYPER_EXPORT_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_C_DOK_SERTIFIKATKODER_EXPORT_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_C_DOK_SERTIFIKATKODER_EXPORT_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_E_FYLKESKODERKODER_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_E_FYLKESKODERKODER_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_F_TYPETILFELLE_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_F_TYPETILFELLE_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST) );
		session.setAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST, (List)model.get(SadImportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST) );
		 
	}
	
	/**
	 * 
	 * @param model
	 * @param session
	 */
	public void getCodeMgrListsFromSession(Map model, HttpSession session){
		List list = new ArrayList();
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_3_TRANSAKSJONSTYPER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_3_TRANSAKSJONSTYPER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_9_EKSPEDISJONSTYPER_EXPORT_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_9_EKSPEDISJONSTYPER_EXPORT_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_C_DOK_SERTIFIKATKODER_EXPORT_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_C_DOK_SERTIFIKATKODER_EXPORT_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_E_FYLKESKODERKODER_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_E_FYLKESKODERKODER_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_F_TYPETILFELLE_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_F_TYPETILFELLE_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST, list);
		list = (List)session.getAttribute("model." + SadImportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST);
		model.put(SadImportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST, list);
		
		
		
		
	}
	
	
	
	
}
