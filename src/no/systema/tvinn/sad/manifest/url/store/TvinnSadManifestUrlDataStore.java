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
		
		
}