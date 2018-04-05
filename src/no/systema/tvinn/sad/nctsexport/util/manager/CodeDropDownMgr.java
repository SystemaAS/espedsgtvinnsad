/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.util.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.model.external.url.UrlISOLanguageObject;
import no.systema.tvinn.sad.model.external.url.UrlTvinnSadCountryObject;
import no.systema.tvinn.sad.model.external.url.UrlTvinnSadCurrencyObject;
import no.systema.tvinn.sad.model.external.url.UrlTvinnSadTolltariffenObject;

import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeContainer;
import no.systema.tvinn.sad.model.jsonjackson.codes.JsonTvinnSadNctsCodeRecord;
import no.systema.tvinn.sad.service.html.dropdown.TvinnSadDropDownListPopulationService;
import no.systema.tvinn.sad.url.store.TvinnSadUrlDataStore;
import no.systema.tvinn.sad.util.TvinnSadConstants;
import no.systema.tvinn.sad.nctsexport.util.SadNctsExportConstants;
import no.systema.tvinn.sad.nctsimport.util.SadNctsImportConstants;

/**
 * The class handles general gui drop downs aspect population
 *
 * This Manager is not instantiated by the Spring Container at start up. 
 * Instead, it is instantiated by a controller when needed.
 * 
 * 
 *  
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class CodeDropDownMgr {
	private static final Logger logger = Logger.getLogger(CodeDropDownMgr.class.getName());
	/**
		012= KOD_SPRAK
		013= KOD_DOK
		014= KOD_TIDIGARE_DOK
		017= KOD_KOLLI_TYP
		031= KOD_DEKL_TYP
		039= KOD_TILLAGSUPP
		047= KOD_KONTROLLRESULTAT
		064= KOD_KANSLIGVARA
		096= KOD_SPEC_OMST
		105= KOD_TILLGANGASKOD_GARANTI
		106= KOD_TULLKONTOR_REF
		116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
		302= KOD_STATUS_KODER_NCTS_EXPORT
		
		BORROWED FROM SAD EXPORT/IMPORT
		2= Country code
		V= Currency code
		4 = KOD_TRANSPORTMÅTE
		
		
	*/
	public static final String CODE_2_COUNTRY = "2";
	public static final String CODE_V_CURRENCY = "V";
	public static final String CODE_4_TRANSPORTMATER = "4";
	
	public static final String CODE_012_SPRAK = "012";
	public static final String CODE_013_DOKTYPE = "013";
	public static final String CODE_014_TIDIGARE_DOK = "104";
	public static final String CODE_017_KOLLI = "017";
	public static final String CODE_031_DEKLTYPE = "031";
	public static final String CODE_039_TILLAGSUPP = "039";
	public static final String CODE_047_KONTROLL_RESULTAT = "047";
	public static final String CODE_064_KANSLIGVARA = "064";
	public static final String CODE_096_SPEC_OMST = "096";
	public static final String CODE_105_TILLGANGSKOD_GARANTI = "105";
	public static final String CODE_106_TULLKONTOR_REF = "106";
	
	public static final String CODE_116_BETALNING_TRANSPORT = "116";
	public static final String CODE_302_STATUS_KODER = "302";
	
	
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
			//default url store (NCTS)
			String CODES_URL = TvinnSadUrlDataStore.TVINN_SAD_NCTS_CODES_URL;
			
			//Exception for CODE_URL (MUST be borrowed from SAD EKS/IMP
			if(CodeDropDownMgr.CODE_2_COUNTRY.equalsIgnoreCase(paramTYP) || CodeDropDownMgr.CODE_V_CURRENCY.equalsIgnoreCase(paramTYP) ||
					CodeDropDownMgr.CODE_4_TRANSPORTMATER.equalsIgnoreCase(paramTYP) ){
					
				CODES_URL = TvinnSadUrlDataStore.TVINN_SAD_CODES_URL;
			}
			
			
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + paramTYP);
			if(paramKODE2 !=null){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode2=" + paramKODE2);
			}
			if(paramKODE3 !=null){
				urlRequestParamsKeys.append(TvinnSadConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "kode3=" + paramKODE3);
			}
			//Now build the payload and send to the back end via the drop down service
			String utfPayload = urlCgiProxyService.getJsonContent(CODES_URL, urlRequestParamsKeys.toString());
			/*
			logger.info("CODES PARAMS:" + urlRequestParamsKeys.toString());
			logger.info(utfPayload);
			*/
			
			JsonTvinnSadNctsCodeContainer codeContainer = tvinnSadDropDownListPopulationService.getNctsCodeContainer(utfPayload);
			List<JsonTvinnSadNctsCodeRecord> list = new ArrayList();
			for(JsonTvinnSadNctsCodeRecord codeRecord: codeContainer.getKodlista()){
				//default
				list.add(codeRecord);
				 
			}
			if(CodeDropDownMgr.CODE_2_COUNTRY.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST,list);
				//model.put(SadNctsExportConstants.URL_EXTERNAL_LANDCODES_SKAT_CODE, new UrlTvinnSadCountryObject() );
			
			}else if(CodeDropDownMgr.CODE_V_CURRENCY.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST,list);
				//model.put(SadNctsExportConstants.URL_EXTERNAL_CURRENCYCODES_SKAT_CODE, new UrlTvinnSadCurrencyObject() );
			
			}else if(CodeDropDownMgr.CODE_012_SPRAK.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_012_SPRAK_LIST, list);
				model.put(SadNctsExportConstants.URL_EXTERNAL_LANGUAGECODES_ISO_CODE, new UrlISOLanguageObject() );
			
			}else if(CodeDropDownMgr.CODE_013_DOKTYPE.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_013_DOKTYPE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_014_TIDIGARE_DOK.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_014_TIDIGAREDOKS_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_017_KOLLI.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_017_KOLLI_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_031_DEKLTYPE.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_031_DEKLTYPE_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_039_TILLAGSUPP.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_039_TILLAGSUPP_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_047_KONTROLL_RESULTAT.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_047_KONTROLL_RESULTAT_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_064_KANSLIGVARA.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_064_KANSLIGVARA_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_096_SPEC_OMST.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_096_SPEC_OMST_LIST, list);
				
			}else if(CodeDropDownMgr.CODE_105_TILLGANGSKOD_GARANTI.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_105_TILLGANGASKOD_GARANTI_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_106_TULLKONTOR_REF.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_106_TULLKONTOR_REF_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_4_TRANSPORTMATER.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_116_BETALNING_TRANSPORT.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_116_BETALNINGSSATT_TRANSPORTKOSTNAD_LIST, list);
			
			}else if(CodeDropDownMgr.CODE_302_STATUS_KODER.equalsIgnoreCase(paramTYP)){
				model.put(SadNctsExportConstants.RESOURCE_MODEL_KEY_CODE_NCTSEX_302_STATUS_KODER_LIST, list);
			}
		
			//we put tolltariffen here there is no other related list on ITEMS jsp
			model.put(SadNctsExportConstants.URL_EXTERNAL_TOLLTARIFFEN, new UrlTvinnSadTolltariffenObject() ); 

		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
