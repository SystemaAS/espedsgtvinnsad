/**
 * 
 */
package no.systema.tvinn.sad.sadimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Maj 26, 2014
 * 
 * 
 */
public final class SadImportUrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String SAD_IMPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI000R.pgm";
	//http://gw.systema.no/sycgip/TNOI000R.pgm?user=OSCAR&avd=1
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI001R.pgm";
	//http://gw.systema.no/sycgip/TNOI001R.pgm?user=OSCAR&avd=1&opd=91132
	
	static public String SAD_IMPORT_BASE_CREATE_OMBEREGNING_FROM_ORIG_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI003R.pgm";
	//http://gw.systema.no/sycgip/TNOI003R.pgm?user=OSCAR&avd=1&opd=900041&sesg=OT&OMTYPE=NYO(NYS,NYA)
	
	//[2.1] FETCH Fakt.total from Invoices
	//http://gw.systema.no/sycgip/tnoi033r.pgm?user=OSCAR&avd=1&opd=215
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from the original: transportuppdrag (mode=GS)
	//mode=S (Send topic)
	//------------------------------		
	static public String SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI002R.pgm";
	
	//[3.01]Fetch the Send parameters
	//http://gw.systema.no/sycgip/tnoi004r.pgm?user=CB&avd=1&opd=0091333
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_SEND_PARAMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI004R.pgm";
		
	//--------------------------------------------------
	//[3.1] Fetch LIST Topic-financial extra information
	//--------------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI030R.pgm";	
	//http://gw.systema.no/sycgip/TNOI030R.pgm?user=OSCAR&avd=1&opd=91091 
	//----------------------------------------------------------------
	//[3.2] Fetch Specific-record Topic-financial extra information
	//----------------------------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI031R.pgm";	
	//http://gw.systema.no/sycgip/TNOI031R.pgm?user=OSCAR&avd=1&opd=91091&fak=FAKTURA123 
	
	//----------------------------------------------------------------
	//[3.3] EDIT Topic-financial extra information (Update)
	//mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//----------------------------------------------------------------
	static public String SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI032R.pgm";	
	//http://gw.systema.no/sycgip/TNOI032R.pgm?user=OSCAR&avd=1&opd=91091&fak=FAKTURA123&mode=U&sfdt=20140924.........o.s.v. som vanligt ang mode
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String SAD_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI010R.pgm";
	//http://gw.systema.no/sycgip/TNOI010R.pgm?user=OSCAR&avd=1&opd=91028
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI011R.pgm";
	
	//[5.2] Ångra Omberegning på alla item lines (same as fetch BUT with mode=R)
	static public String SAD_IMPORT_BASE_ANGRE_OMB_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI010R.pgm";
	//http://gw.systema.no/sycgip/TNOI010R.pgm?user=OSCAR&avd=1&opd=91132&mode=R
	
	//FETCH VARUKODER-KUNDENS Vareregister 
	static public String SAD_IMPORT_FETCH_TOLLTARIFF_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI041R.pgm";
	//http://gw.systema.no/sycgip/tnoi041r.pgm?user=OSCAR&levenr=1
	static public String SAD_IMPORT_BASE_UPDATE_TOLLTARIFF_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI042R.pgm";
	//http://gw.systema.no/sycgip/tnoi042r.pgm?user=OSCAR&varenr=koste&varebe=solsommar&levenr=1&etc...
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI012R.pgm";
	//http://gw.systema.no/sycgip/TNOI012R.pgm?user=OSCAR&avd=1&opd=80001&lin=2&mode=A
	
	//[6.1 Get Preference code]
	static public String SAD_IMPORT_BASE_GET_SPECIFIC_TOPIC_ITEM_PREFERENCE_CODE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI062R.pgm";
	//http://gw.systema.no/sycgip/TNOI062R.pgm?user=OSCAR&sidp=40&sitst=1&svvnt=33073000&svlk=NU&svtn= 
	//[6.2 Get PVA code]
	static public String SAD_IMPORT_BASE_GET_SPECIFIC_TOPIC_ITEM_PVA_CODE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI065R.pgm";
	//http://gw.systema.no/sycgip/TNOI065R.pgm?user=OSCAR&SVVNT=21069098&SVLK=SE&SVPRE=A
	
	//--------------------------------------
	//[6.1.1] Update AutoControl error line  
	//--------------------------------------
	static public String SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI022R.pgm";
	//http://gw.systema.no/sycgip/tnoi022r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&sverr=X
	
	//------------------------------------------
	//[6.3] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI070R.pgm";
	//http://gw.systema.no/sycgip/tnoi070r.pgm?user=OSCAR
	static public String SAD_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI071R.pgm";	
	//http://gw.systema.no/sycgip/tnoi071r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String SAD_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI072R.pgm";	
	//http://gw.systema.no/sycgip/tnoi072r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	static public String SAD_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI015R.pgm";	
	//http://gw.systema.no/sycgip/TNOI015R.pgm?user=OSCAR&avd=1&opd=91152 
	
	static public String SAD_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI016R.pgm";	
	//http://gw.systema.no/sycgip/TNOI016R.pgm?user=OSCAR&fmn=84278
	
	static public String SAD_IMPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String SAD_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI014R.pgm";	
	//http://gw.systema.no/sycgip/TNOI014R.pgm?user=OSCAR&avd=1&opd=218	
	static public String SAD_IMPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC000R.pgm";	
	//http://gw.systema.no/sycgip/TNOI014R.pgm?user=OSCAR&avd=1&opd=218	

	//----------------------------------------------
	//[9] Avd default values for every new record
	//----------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_AVDDATA_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI018R.pgm";	
	//http://gw.systema.no/sycgip/TNOI018R.pgm?user=OSCAR&avd=1
	
	//----------------------------------------------
	//[10] INCOTERMS attributes when validating
	//----------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_INCOTERMS_ATTRIBUTES_FOR_VALIDATION_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI061R.pgm";	
	//http://gw.systema.no/sycgip/TNOI018R.pgm?user=OSCAR&avd=1
	
	//----------------------------------------------
	//[11] Avgifter - Calculation
	//----------------------------------------------
	static public String SAD_IMPORT_BASE_FETCH_AVGIFTER_BEFORE_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI063R.pgm";	
	//http://gw.systema.no/sycgip/TNOI063R.pgm?user=JOVO&svvnt=22030040 
	static public String SAD_IMPORT_BASE_FETCH_AVGIFTER_AFTER_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI064R.pgm";	
	//http://gw.systema.no/sycgip/TNOI064R.pgm?user=JOVO...
		
	
	
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String SAD_IMPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI051R.pgm";
		
	
}
