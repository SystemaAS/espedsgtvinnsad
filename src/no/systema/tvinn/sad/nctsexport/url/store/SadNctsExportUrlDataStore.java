/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public final class SadNctsExportUrlDataStore {
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	static public String NCTS_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE000R.pgm"; 
	//http://gw.systema.no/sycgip/ttce000r.pgm?user=YBC&datum=20140101;
	
	//---------------------------------------------------------------
	//[2] FETCH A SPECIFIC ARENDE or Default values for a NEW ARENDE
	//---------------------------------------------------------------
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE001R.pgm";
	//http://gw.systema.no/sycgip/TTCE001R.pgm?user=OSCAR&avd=1&opd=50013
	//http://gw.systema.no/sycgip/TTCE001R.pgm?user=OSCAR&avd=1 (for default values with CREATE NEW)
	//2.1 FETCH Sikkerhet (extra information)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE021R.pgm";
	//http://gw.systema.no/sycgip/ttce021r.pgm?user=OSCAR&avd=1&opd=900040
		
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy existing topic) from Norskimport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE002R.pgm";
	//http://gw.systema.no/sycgip/TDCE002R.pgm?user=OSCAR&thavd=1&sign=CB&mode=A
	//http://gw.systema.no/sycgip/TDCE002R.pgm?user=OSCAR&thavd=1&newavd=1&thtdn=50013&mode=C&newsign=OT
	
	//Validate Guarantee
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_GUARRANTEE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE003R.pgm";
	//http://gw.systema.no/sycgip/TTCE003R.pgm?user=YBC&thgft1=09SE00005000000W7&thgadk=2222
	
	//Validate Sensitive goods
	static public String NCTS_EXPORT_BASE_VALIDATE_SPECIFIC_TOPIC_ITEM_SENSITIVE_GOODS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE017R.pgm";
	//http://gw.systema.no/sycgip/TTCE017R.pgm?user=OSCAR&tftanr=170199(valid varukod) --->010290 (valid varukod)
	
	//Refresh ARENDE
	static public String NCTS_EXPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE004R.pgm";
	
	//-----------------------------------------
	//[3.1] EDIT A SPECIFIC ARENDE- SIKKERHET
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//-----------------------------------------		
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE022R.pgm";
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE010R.pgm";
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE011R.pgm";
	//[5.2] FETCH SPECIFIC ITEM (for Sikkerhet)
	static public String NCTS_EXPORT_BASE_FETCH_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE025R.pgm";
	//http://gw.systema.no/sycgip/ttce025r.pgm?user=OSCAR&avd=1&opd=1&lin=1
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE012R.pgm";	
	//http://gw.systema.no/sycgip/TTCE012R.pgm?user=OSCAR&avd=1&opd=50079&lin=3&mode=D	
	//[6.1] EDIT a SPECIFIC SIKKERHET ITEM RECORD
	static public String NCTS_EXPORT_BASE_UPDATE_SPECIFIC_SIKKERHET_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE026R.pgm";	
	//http://gw.systema.no/sycgip/ttce026r.pgm?user=OSCAR&mode=U&tvavd=1&tvtdn=1&tvli=1&tvnass=Tarzan
	
	//---------------------------------------------
	//[6.1] IMPORT into item lines
	//avd = avdelningen till NCTS export
	//opd = angivelsenr till NCTS export
	//TODOsveh_syav = avdelningen till exportangivelsen
	//TODOsveh_syop = angivelsenr till exportangivelsen
	//----------------------------------------------
	static public String NCTS_EXPORT_BASE_IMPORT_EXPORT_AS_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE091R.pgm";	
	//http://gw.systema.no/sycgip/ttce091r.pgm?user=OSCAR&avd=1&opd=188888&TODOsveh_syav=2&TODOsveh_syop=152221
	
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE, etc, for a specific topic within SKAT-NCTS EXPORT	
	static public String NCTS_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE015R.pgm";	
	//http://gw.systema.no/sycgip/TTCE015R.pgm?user=OSCAR&avd=1&opd=50113
	/* Typ kan vara:
		0 = Norsk SAD Import  
		1 = Norsk SAD Eksport
		2 = Norsk NCTS Eksport      
		3 = Norsk NCTS Import      
		4 = All edifact
		5 = Svensk NCTS Export  
		6 = Svensk NCTS Import */	
	
	static public String NCTS_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE016R.pgm";	
	//http://gw.systema.no/sycgip/TTCE016R.pgm?user=OSCAR&fmn=85847 
	
	static public String NCTS_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	 
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=???
	
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	static public String NCTS_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE014R.pgm";	
	//http://gw.systema.no/sycgip/TTCE014R.pgm?user=OSCAR&avd=1&opd=218	
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	static public String NCTS_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCE051R.pgm";
	
	
}
