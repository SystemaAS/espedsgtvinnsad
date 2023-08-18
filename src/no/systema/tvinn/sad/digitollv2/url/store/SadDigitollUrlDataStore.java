package no.systema.tvinn.sad.digitollv2.url.store;

import no.systema.main.util.AppConstants;

public final class SadDigitollUrlDataStore {

		//FETCH Transport list
		//http://localhost:8080/syjservicestn/syjsSADMOTFR.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_TRANSPORT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOTF.do";
		
		//Manifest info from toll.no (API)
		//http://gw.systema.no:8080/syjservicestn-expft/getManifestRaw.do?user=SYSTEMA&id=e35a52a6-18ae-4746-a4b4-9e3f0edbacc6
		//static public String SAD_CHILDWINDOW_TRANSP_MASTER_HOUSE_RAW_INFO_TOLL_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn-expft/getManifestRaw.do";
		//http://gw.systema.no:8080/syjservicestn-expft/getManifest.do?user=SYSTEMA&id=e35a52a6-18ae-4746-a4b4-9e3f0edbacc6
		static public String SAD_DIGITOLL_MANIFEST_ROOT_API_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn-expft/digitollv2/";
		
}
