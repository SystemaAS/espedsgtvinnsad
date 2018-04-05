/**
 * 
 */
package no.systema.skat.nctsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public final class SkatNctsExportUrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String NCTS_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE000R.pgm"; 
	//http://gw.systema.no/sycgip/TDCE000R.pgm?user=OSCAR&avd=1&sign=CB";
	
	//---------------------------------------------------------------
	//[2] FETCH A SPECIFIC ARENDE or Default values for a NEW ARENDE
	//---------------------------------------------------------------
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE001R.pgm";
	//http://gw.systema.no/sycgip/TDCE001R.pgm?user=OSCAR&avd=1&opd=50013
	//http://gw.systema.no/sycgip/TDCE001R.pgm?user=OSCAR&avd=1 (for default values with CREATE NEW)
	//2.1 FETCH Sikkerhet (extra information)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE021R.pgm";
	//http://gw.systema.no/sycgip/tdce021r.pgm?user=OSCAR&avd=1&opd=50187
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy existing topic) from Norskimport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//mode=GS (Clone from SKAT Export)
	//------------------------------		
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE002R.pgm";
	//http://gw.systema.no/sycgip/TDCE002R.pgm?user=OSCAR&thavd=1&sign=CB&mode=A
	//http://gw.systema.no/sycgip/TDCE002R.pgm?user=OSCAR&thavd=1&newavd=1&thtdn=50013&mode=C&newsign=OT
	
	//Validate Guarantee
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE003R.pgm";
	//http://gw.systema.no/sycgip/TDCE003R.pgm?user=YBC&thgft1=09SE00005000000W7&thgadk=2222
	
	//Validate Sensitive goods
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_ITEM_SENSITIVE_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE017R.pgm";
	//http://gw.systema.no/sycgip/TDCE017R.pgm?user=OSCAR&tftanr=170199(valid varukod) --->010290 (valid varukod)
	
	
	//Refresh ARENDE
	static public String NCTS_EXPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE004R.pgm";
	
	//-----------------------------------------
	//[3.1] EDIT A SPECIFIC ARENDE- SIKKERHET
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//-----------------------------------------		
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE022R.pgm";
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE010R.pgm";
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE011R.pgm";
	//[5.2] FETCH SPECIFIC ITEM (for Sikkerhet)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE025R.pgm";
	//http://gw.systema.no/sycgip/tdce025r.pgm?user=OSCAR&avd=1&opd=50187&lin=1
	
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE012R.pgm";	
	//http://gw.systema.no/sycgip/TDCE012R.pgm?user=OSCAR&avd=1&opd=50079&lin=3&mode=D	
	//[6.1] EDIT a SPECIFIC SIKKERHET ITEM RECORD
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE026R.pgm";	
	//http://gw.systema.no/sycgip/tdce026r.pgm?user=OSCAR&mode=U&tvavd=1&tvtdn=50187&tvli=1&tvtkbm=A&tvkref=Derivat&tvnass=Tarzan
	
	//---------------------------------------------
	//[6.1] IMPORT into item lines
	//avd = avdelningen till NCTS export
	//opd = angivelsenr till NCTS export
	//dkeh_syav = avdelningen till exportangivelsen
	//dkeh_syop = angivelsenr till exportangivelsen
	//----------------------------------------------
	static public String NCTS_EXPORT_BASE_IMPORT_EXPORT_AS_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE091R.pgm";	
	//http://gw.systema.no/sycgip/tdce091r.pgm?user=OSCAR&avd=1&opd=188888&dkeh_syav=2&dkeh_syop=152221
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within SKAT-NCTS EXPORT	
	static public String NCTS_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE015R.pgm";	
	//http://gw.systema.no/sycgip/TDCE015R.pgm?user=OSCAR&avd=1&opd=50113&typ=5
	/* Typ kan vara:
		0 = Norsk SAD Import  
		1 = Norsk SAD Eksport
		2 = Norsk NCTS Eksport      
		3 = Norsk NCTS Import      
		4 = All edifact
		5 = Svensk NCTS Export  
		6 = Svensk NCTS Import */	
	
	static public String NCTS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE016R.pgm";	
	//http://gw.systema.no/sycgip/TDCE016R.pgm?user=OSCAR&fmn=85847 
	
	static public String NCTS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	 
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=???ß
	
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE014R.pgm";	
	//http://gw.systema.no/sycgip/TDCE014R.pgm?user=OSCAR&avd=1&opd=218	
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE051R.pgm";
	
	//-----------------------------
	// Cancellation SKAT
	//-----------------------------
	//http://gw.systema.no/sycgip/tdce005r.pgm?user=CB&tkavd=1&tktdn=9000019&tkft1=tarzan&tkft2=jane&tksk=DA
	static public String NCTS_EXPORT_BASE_UPDATE_CANCEL_SKAT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDCE005R.pgm";
}
