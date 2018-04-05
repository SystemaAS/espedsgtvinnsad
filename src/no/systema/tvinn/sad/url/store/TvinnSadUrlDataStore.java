/**
 * 
 */
package no.systema.tvinn.sad.url.store;
import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 * 
 */
public final class TvinnSadUrlDataStore {
	
	
	//----------------------------------
	//[1] FETCH HEADER FUNCTIONS GENERAL
	//----------------------------------
	
	//FETCH CUSTOMER(S)
	static public String TVINN_SAD_FETCH_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG003R.pgm";
	//http://gw.systema.no/sycgip/TNOG003R.pgm?user=OSCAR&knr=6
	
	//Fetch customer information (general)
	static public String TVINN_SAD_FETCH_CUSTOMER_INFO_FREETEXT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE007.pgm";
	//http://gw.systema.no/sycgip/TJGE007.pgm?user=OSCAR&kundn=1 
	
	//FETCH UTFARTSTULL KONTOR
	static public String TVINN_SAD_FETCH_UTFARTS_TULLKONTOR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG004R.pgm";//?user=OSCAR&sonavn=SVINESUND&kod=SE...";
	//http://gw.systema.no/sycgip/TNOG004R.pgm?user=OSCAR&sonavn=NO&avg=J
	
	// GENERAL CODES - TVINN SAD 
	static public String TVINN_SAD_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNOG005R.pgm?user=OSCAR&typ=2
	static public String TVINN_SAD_CODES2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC001R.pgm"; 
	//http://gw.systema.no/sycgip/TARC001R.pgm?user=OSCAR&type=Z..
	
	
	//FETCH VARUKODER-TOLLTARIFF (ITEM LINES)
	static public String TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG006R.pgm";
	//http://gw.systema.no/sycgip/TNOG006R.pgm?user=OSCAR&ie=I&kod=8514 (alt. ie=E)
	static public String TVINN_SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_FROM_DESC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG023R.pgm";
	//http://gw.systema.no/sycgip/TNOG023R.pgm?user=OSCAR&ie=I&sok=Beskrivelse (alt. ie=E)
	
	//CURRENCY RATES
	static public String TVINN_SAD_FETCH_CURRENCY_RATE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG007R.pgm"; //?user=OSCAR&kod=SEK...		
	
	//GENERAL CODES - TVINN NCTS 
	static public String TVINN_SAD_NCTS_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTCG005R.pgm";
	//http://gw.systema.no/sycgip/TTCG005R.pgm?user=OSCAR&typ=012 
		
	
	//----------------------------------
	//FETCH AVDELNING AND SIGNATURE
	//----------------------------------
    //ie=E	SAD export
    //ie=I	SAD import
    //ie=A	Transportuppdrag
	static public String TVINN_SAD_FETCH_AVDELNINGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG008R.pgm"; //?user=OSCAR&ie=E
	//http://gw.systema.no/sycgip/TNOG008R.pgm?user=OSCAR&ie=I
	
	//ie=F	SAD import/export
	static public String TVINN_SAD_FETCH_SIGNATURE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG009R.pgm"; //?user=OSCAR&ie=F
	
	//--------------------------------------
	//FETCH AVDELNING AND SIGNATURE (NCTS)
	//--------------------------------------
	//ie=X	NCTS export
    //ie=N	NCTS import
    static public String TVINN_SAD_FETCH_AVDELNINGAR_NCTS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTNG008R.pgm"; 
	//http://gw.systema.no/sycgip/TTNG008R.pgm?user=OSCAR&ie=N
	static public String TVINN_SAD_FETCH_SIGNATURE_NCTS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TTNG009R.pgm"; 
	//http://gw.systema.no/sycgip/TTNG009R.pgm?user=OSCAR&ie=N	
	
	//-------------------------
	// Authorization on SAD
	//-------------------------
	static public String TVINN_SAD_GET_AUTHORIZATION_CODE = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG010R.pgm"; //user=OSCAR	
	
	//-----------------------------
	//Validation routines (general)
	//-----------------------------
	//lk = landkod (Opp.land), kod=Varukod 
	static public String TVINN_SAD_CHECK_EXTRA_MANGDENHET = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG016R.pgm"; 
	//http://gw.systema.no/sycgip/TSVG016R.pgm?user=OSCAR&ie=I&kod=9404300000&lk=CA 
	
	//-------------------------------
	//Name for a given SIGN (general)
	//-------------------------------
	//http://gw.systema.no/sycgip/TSVG017R.pgm?user=OSCAR&avd=1&sign=OT
	//static public String SKAT_FETCH_SIGNATURE_NAME_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVG017R.pgm"; 
	
	
	/**
	 * Return invalid kunder for firma where mismatch against data.brreg.no. <br><br>
	 * 
	 * /syjservicestn/brregKundeDataKontroll.do
	 */
	public static final String TVINN_SAD_BRREG_GET_KUNDEDATA_KONTROLL_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/brregKundeDataKontroll.do";
	
	
}
