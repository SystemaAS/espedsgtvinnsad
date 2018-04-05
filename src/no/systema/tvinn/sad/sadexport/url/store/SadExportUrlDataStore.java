/**
 * 
 */
package no.systema.tvinn.sad.sadexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Sep 4, 2014
 * 
 * 
 */
public final class SadExportUrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String SAD_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE000R.pgm";
	//http://gw.systema.no/sycgip/TNOE000R.pgm?user=OSCAR&avd=1
	
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE001R.pgm";
	//http://gw.systema.no/sycgip/TNOE001R.pgm?user=OSCAR&avd=1&opd=91132
	
	static public String SAD_EXPORT_BASE_CREATE_OMBEREGNING_FROM_ORIG_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE003R.pgm";
	//http://gw.systema.no/sycgip/TNOE003R.pgm?user=OSCAR&avd=1&opd=900041&sesg=OT&OMTYPE=NYO(NYS,NYA)
	
	//[2.1] FETCH Fakt.total from Invoices
	//http://gw.systema.no/sycgip/tnoe033r.pgm?user=OSCAR&avd=1&opd=155272
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE033R.pgm";
	
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from Norskexport or fallback to the origin: transportuppdrag (mode=GS)
	//mode=S (Send topic)
	//------------------------------		
	static public String SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE002R.pgm";
	
	
	//--------------------------------------------------
	//[3.1] Fetch LIST Topic-financial extra information
	//--------------------------------------------------
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_LIST_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE030R.pgm";	
	//http://gw.systema.no/sycgip/TNOE030R.pgm?user=OSCAR&avd=1&opd=91091 
	//----------------------------------------------------------------
	//[3.2] Fetch Specific-record Topic-financial extra information
	//----------------------------------------------------------------
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE031R.pgm";	
	//http://gw.systema.no/sycgip/TNOE031R.pgm?user=OSCAR&avd=1&opd=91091&fak=FAKTURA123 
	
	//----------------------------------------------------------------
	//[3.3] EDIT Topic-financial extra information (Update)
	//mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//----------------------------------------------------------------
	static public String SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_FINANS_OPPLYS_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE032R.pgm";	
	//http://gw.systema.no/sycgip/TNOE032R.pgm?user=OSCAR&avd=1&opd=91091&fak=FAKTURA123&mode=U&sfdt=20140924.........o.s.v. som vanligt ang mode
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String SAD_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE010R.pgm";
	//http://gw.systema.no/sycgip/TNOE010R.pgm?user=OSCAR&avd=1&opd=91132
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE011R.pgm";
	
	//FETCH VARUKODER-KUNDENS Vareregister 
	static public String SAD_EXPORT_FETCH_TOLLTARIFF_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE041R.pgm";
	//http://gw.systema.no/sycgip/tnoe041r.pgm?user=OSCAR&levenr=1
	static public String SAD_EXPORT_BASE_UPDATE_TOLLTARIFF_KUNDENSVAREREG_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE042R.pgm";
	//http://gw.systema.no/sycgip/tnoe042r.pgm?user=OSCAR&slknr=1&slalfa=FXR123&sltxt=gashindrare&sltanr=12345678
	
	//[5.2] Ångra Omberegning på alla item lines (same as fetch BUT with mode=R
	static public String SAD_EXPORT_BASE_ANGRE_OMB_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE010R.pgm";
	//http://gw.systema.no/sycgip/TNOE010R.pgm?user=OSCAR&avd=1&opd=91132&mode=R
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE012R.pgm";
	//http://gw.systema.no/sycgip/TNOE012R.pgm?user=OSCAR&avd=1&opd=80001&lin=2&mode=A
	
	//--------------------------------------
	//[6.1.1] Update AutoControl error line  
	//--------------------------------------
	static public String SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE022R.pgm";
	//http://gw.systema.no/sycgip/tnoe022r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&sverr=X
	
	//-------------------------------------------------------------------------------------------------------
	//[6.1] EDIT/SAVE Avgiftsberäkningen (SKAT är bara read-only men Statistiskt værdi MÅSTE kalkyleras ändå 
	//OBSOLETE!! - This function has been replaced by TNOI022R.pgm
	//-------------------------------------------------------------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI017R.pgm?user=OSCAR&svih_vufr=123&svih_vuva=USD&svih_vuku=8&svih_vufo=123&svih_ovko=123&svih_kara=123&svih_anra=123&svih_lekd=DDP&svih_vakd=USD&svih_vaku=8&svih_fabl=123&sviv_vata=4601219000&sviv_ulkd=US&sviv_fokd=100&sviv_eup1=4000&sviv_ankv=1&sviv_stva=0&sviv_stva2=0&sviv_fabl=100 
	//static public String SKAT_IMPORT_BASE_AVGIFTS_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI017R.pgm";	
	
	//------------------------------------------
	//[6.3] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	static public String SAD_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE070R.pgm";
	//http://gw.systema.no/sycgip/tnoe070r.pgm?user=OSCAR
	static public String SAD_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE071R.pgm";	
	//http://gw.systema.no/sycgip/tnoe071r.pgm?user=OSCAR&reff=6441&unik=4009282
	static public String SAD_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE072R.pgm";	
	//http://gw.systema.no/sycgip/tnoe072r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	static public String SAD_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE015R.pgm";	
	//http://gw.systema.no/sycgip/TNOE015R.pgm?user=OSCAR&avd=1&opd=218&typ=NOE 
	
	static public String SAD_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE016R.pgm";	
	//http://gw.systema.no/sycgip/TNOE016R.pgm?user=OSCAR&fmn=84278
	
	static public String SAD_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String SAD_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE014R.pgm";	
	//http://gw.systema.no/sycgip/TNOI014R.pgm?user=OSCAR&avd=1&opd=218	
	static public String SAD_EXPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC000R.pgm";	
	//http://gw.systema.no/sycgip/TARC000R.pgm?user=OSCAR&avd=1&opd=218&type=Z

	
	//----------------------------------------------
	//[9] Avd default values for every new record
	//----------------------------------------------
	static public String SAD_EXPORT_BASE_FETCH_AVDDATA_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE018R.pgm";	
	//http://gw.systema.no/sycgip/TNOE018R.pgm?user=OSCAR&avd=1
	
		
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String SAD_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOE051R.pgm";
		
		
	
}
