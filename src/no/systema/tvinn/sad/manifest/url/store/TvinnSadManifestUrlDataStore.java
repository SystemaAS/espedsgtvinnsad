/**
 * 
 */
package no.systema.tvinn.sad.manifest.url.store;
import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Aug 2020
 * 
 * 
 */
public final class TvinnSadManifestUrlDataStore {
	
	
	//----------------------------------------------------------
	//[1] FETCH EKSPRESS MANIFEST MAIN LIST or Specific MANIFEST
	//----------------------------------------------------------
	
	//FETCH MANIFEST list
	//http://localhost:8080/syjservicestn/syjsSADEFFR.do?user=OSCAR and/or user=OSCAR&efuuid=2350cab2-98f0-4b54-a4f7-a2ae453e61bd
	static public String TVINN_SAD_FETCH_MANIFEST_EXPRESS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADEFFR.do";
	
	//FETCH codes from KOFAST
	//http://localhost:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=SADEFBKODE
	static public String TVINN_SAD_FETCH_KOFAST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKOFAST.do";
	
	//FETCH UUID
	//http://localhost:8080/syjservicesbcore/syjsuuid.do
	static public String TVINN_SAD_FETCH_MANIFEST_UUID_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsuuid.do";
		
	//----------------------------
	//[1.1] UPDATE EKSPRESS MANIFEST
	//----------------------------
	//http://localhost:8080/syjservicestn/syjsSADEFFR_U.do?user=OSCAR&mode=U/A/D and/or
	static public String TVINN_SAD_UPDATE_MANIFEST_EXPRESS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADEFFR_U.do";
		
	//Child windows
	//http://gw.systema.no:8080/syjservicestn/syjsSTED2.do?user=OSCAR&st2lk=NL&st2kod=AMS
	static public String TVINN_SAD_CHILDWINDOW_POSTALCODE_STED2_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSTED2.do";
		
	
	//----------------------------------------------------
	//[2] FETCH CARGO LINES' LIST of a specific MANIFEST
	//----------------------------------------------------
	//FETCH CARGO LINE list
	//http://localhost:8080/syjservicestn/syjsSADEFCFR.do?user=OSCAR and/or user=OSCAR&clpro=turNr
	static public String TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADEFCFR.do";
		
	//http://localhost:8080/syjservicestn/syjsSADEFCFR_U.do?user=OSCAR&mode=U/A/D and/or
	static public String TVINN_SAD_UPDATE_MANIFEST_EXPRESS_CARGOLINES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADEFCFR_U.do";
		
}