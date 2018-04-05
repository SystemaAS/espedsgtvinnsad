/**
 * 
 */
package no.systema.tvinn.sad.admin.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Maj 26, 2014
 * 
 * 
 */
public final class SadAdminUrlDataStore {
	
	//--------------------------------
	//[1] FETCH Transportuppdrag LIST
	//--------------------------------
	//http://gw.systema.no/sycgip/TNOG018R.pgm?user=OSCAR&avd=1&sign=CB&DATUM=20101001 
	static public String TVINN_SAD_ADMIN_BASE_TRANSPORT_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG018R.pgm";
	//---------------------------
	//[2] FETCH Avgiftsgrunnlag 
	//---------------------------	
	//http://gw.systema.no/sycgip/tnoi081r.pgm?user=OSCAR&siknk=0&datof=20000830&datot=20161231 
	static public String TVINN_SAD_ADMIN_BASE_AVGGRUNNLAG_LIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI081R.pgm";
	
}
