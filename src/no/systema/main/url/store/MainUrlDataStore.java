/**
 * 
 */
package no.systema.main.url.store;
import no.systema.main.util.AppConstants;
/**
 * Static URLs
 * @author oscardelatorre
 *
 */
public final class MainUrlDataStore {
	//------------------------------
	//[1] LOGIN - SYSTEMA WEB eSped
	//------------------------------
	static public String SYSTEMA_WEB_LOGIN_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/symn0J.pgm";
	//Special firm code service in order to login above
	static public String SYSTEMA_WEB_FIRMLOGIN_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMLOGIN.do";
	
	
	//---------------
	//[2] Notisblock 
	//---------------
	static public String SYSTEMA_NOTIS_BLOCK_FETCH_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE08R.pgm"; 
	static public String SYSTEMA_NOTIS_BLOCK_FETCH_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE09R.pgm"; 
	static public String SYSTEMA_NOTIS_BLOCK_UPDATE_ITEMLINE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE10R.pgm"; 
		
	//---------------
	//[3] Edi ftp log 
	//---------------
	static public String SYSTEMA_EDI_FTP_LOG_EDI42R_FETCH_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsEDI42R.do";
	static public String SYSTEMA_EDI_FTP_LOG_EDI43R_FETCH_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsEDI43R.do";
	
	
	//-----------------
	//Upload to Archive
	//-----------------
	static public String SYSTEMA_UPLOAD_FILE_VALIDATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07A.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07A.pgm?user=OSCAR&wsdokn=tarzan.jpg
	//{ "user": "OSCAR", "wsdokn": "tarzan.jpg","valids": "Y", "tmpdir": "/pdf/tmp/", "errMsg": "", "chksuffix": [] } 
	static public String SYSTEMA_UPLOAD_FILE_AFTER_VALIDATION_APPROVAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJETUR07B.pgm";	
	//http://gw.systema.no/sycgip/TJETUR07B.pgm?user=JOVO&wstur=75000002&wsdokn=/pdf/tmp/ukkulele.jpg&wsalias=trumpet.jpg 
	
}

