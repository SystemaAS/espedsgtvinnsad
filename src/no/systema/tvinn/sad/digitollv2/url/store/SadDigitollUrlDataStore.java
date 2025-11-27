package no.systema.tvinn.sad.digitollv2.url.store;

import no.systema.main.util.AppConstants;

public final class SadDigitollUrlDataStore {

		//FETCH Transport list
		//http://localhost:8080/syjservicestn/syjsSADMOTF.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_TRANSPORT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOTF.do";
		//UPDATE/INSERT/DELETE Transport
		//http://localhost:8080/syjservicestn/syjsSADMOTF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_TRANSPORT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOTF_U.do";
		
		//UPDATE AUTO-GEN Children
		//http://localhost:8080/syjservicestn/syjsSADMOTF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_AUTOGEN_CHILDREN_DIGITOLL_TRANSPORT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOTF_U_AUTOGEN_CHIL.do";
		
		//UPDATE Transp.on master
		//http://localhost:8080/syjservicestn/syjsSADMOTF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_TRANSP_ORGNR_ON_MASTER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOMF_U_ADJ_MASTER.do";
		
		//Gets the routingId needed for each 1000-record on the Movement-Routing API (complement to the ENTRY (completed/denied) API
		//http://localhost:8080/syjservicestn/syjsSADMOROID.do?user=OSCAR
		static public String SAD_FETCH_ROUTINGID_MOVEMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOROID.do";
		static public String SAD_UPDATE_ROUTINGID_MOVEMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOROID_U.do";
		
		
		//FETCH Master Consignment list
		//http://localhost:8080/syjservicestn/syjsSADMOMFR.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_MASTERCONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOMF.do";
		//UPDATE/INSERT/DELETE Master
		//http://localhost:8080/syjservicestn/syjsSADMOMF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOMF_U.do";
		//...
		//http://localhost:8080/syjservicestn/syjsSADMOMF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_MASTERCONSIGNMENT_EXTERNAL_MASTER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOMF_U_EXTERNAL.do";
		
		
		//http://localhost:8080/syjservicestn/syjsSADMOMF_U_CT.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_CHANGE_TRANSPORT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOMF_U_CT.do";

		//FETCH House Consignment list
		//http://localhost:8080/syjservicestn/syjsSADMOHF.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOHF.do";
		//UPDATE/INSERT/DELETE House
		//http://localhost:8080/syjservicestn/syjsSADMOMF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOHF_U.do";
		//http://localhost:8080/syjservicestn/syjsSADMOHF_U_CONS.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_HOUSECONSIGNMENT_CONSOLIDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOHF_U_CONS.do";
		//http://localhost:8080/syjservicestn/syjsSADMODOCLG_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_DOC_API_LOG_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMODOCLG_U.do";
		//http://localhost:8080/syjservicestn/syjsSADMODOCLG_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_DIGITOLL_DOC_API_LOG_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMODOCLG.do";
				
				
		//FETCH External - Främmande House Consignment list
		//http://localhost:8080/syjservicestn/syjsZADMOHF.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOHF.do";
		//UPDATE/INSERT/DELETE House
		//http://localhost:8080/syjservicestn/syjsZADMOMF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_EXTERNAL_HOUSECONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOHF_U.do";
		//http://localhost:8080/syjservicestn/syjsZADMOMLF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_EXTERNAL_MASTERCONSIGNMENT_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOMLF_U.do";
		//http://localhost:8080/syjservicestn/syjsZADMOMLF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_EXTERNAL_ATTACHMENTS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOATTF_U.do";
				
		
		
		
		
		//FETCH Item line list
		//http://localhost:8080/syjservicestn/syjsSADMOIFR.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_ITEMLINES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOIF.do";
		//UPDATE/INSERT/DELETE House
		//http://localhost:8080/syjservicestn/syjsSADMOIF_U.do?user=OSCAR and/or user=OSCAR...
		static public String SAD_UPDATE_DIGITOLL_ITEMLINES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOIF_U.do";

		//FETCH LOG 
		static public String SAD_FETCH_DIGITOLL_LOG_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOLOG.do";
		
		//FETCH DEFAULT VALUES SADMOAF 
		static public String SAD_FETCH_DIGITOLL_DEFAULT_VALUES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOAF.do";
		//UPDATE DEFAULT VALUES SADMOAF 
		static public String SAD_UPDATE_DIGITOLL_DEFAULT_VALUES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOAF_U.do";
				
		
		//Manifest info from toll.no (API)
		//http://gw.systema.no:8080/syjservicestn-expft/getManifestRaw.do?user=SYSTEMA&id=e35a52a6-18ae-4746-a4b4-9e3f0edbacc6
		//static public String SAD_CHILDWINDOW_TRANSP_MASTER_HOUSE_RAW_INFO_TOLL_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn-expft/getManifestRaw.do";
		//http://gw.systema.no:8080/syjservicestn-expft/getManifest.do?user=SYSTEMA&id=e35a52a6-18ae-4746-a4b4-9e3f0edbacc6
		static public String SAD_DIGITOLL_MANIFEST_ROOT_API_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn-expft/digitollv2/";
		
		
		//AS400 services CGI
		//FETCH CUSTOMER(S)
		static public String SAD_FETCH_DIGITOLL_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG003R.pgm";
		//http://gw.systema.no/sycgip/TNOG003R.pgm?user=OSCAR&knr=6
		//FETCH UTFARTSTULL KONTOR
		static public String SAD_FETCH_DIGITOLL_UTFARTS_TULLKONTOR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG004R.pgm";//?user=OSCAR&sonavn=SVINESUND&kod=SE...";
		//FETCH tolltariff
		//http://gw.systema.no/sycgip/TNOG006R.pgm?user=OSCAR&ie=I&kod=8514 (alt. ie=E)
		static public String SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG006R.pgm";
		//http://gw.systema.no/sycgip/TNOG023R.pgm?user=OSCAR&ie=I&sok=Beskrivelse (alt. ie=E)
		static public String SAD_FETCH_TOLLTARIFF_VARUKODER_ITEMS_FROM_DESC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG023R.pgm";
		
		
		//FETCH Oppdrag list
		//http://10.11.47.61/sycgip/TDIG000R2.pgm?user=OSCAR&tur=501954
		static public String SAD_FETCH_DIGITOLL_OPPDRAG_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG000R2.pgm";
		//FETCH Sadi
		//http://10.11.47.61/sycgip/TDIG000.pgm?user=OSCAR&bil=xxx&dato=20231201
		static public String SAD_FETCH_DIGITOLL_SADI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG000R.pgm";
		//FETCH Tur
		//http://10.11.47.61/sycgip/TDIG001R.pgm?user=OSCAR&wsstur=81000009&wtudt=20200101
		static public String SAD_FETCH_DIGITOLL_TUR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG001R.pgm";
		//AVD 
		//..."avdelningar":[ { "avd":"1", "namn":"AIRFREIGHT/IMPORT DEPARTMENTSSø'" } ....
		//http://10.11.47.61/sycgip/TDIG002R.pgm?user=OSCAR
		static public String SAD_FETCH_DIGITOLL_AVD_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG002R.pgm";
		//SIGN 
		//..."signaturer": { "sign":"A01", "namn":"A2001" 
		//http://10.11.47.61/sycgip/TDIG003R.pgm?user=OSCAR
		static public String SAD_FETCH_DIGITOLL_SIGN_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG003R.pgm";
		//Automation from SYSPED (nortrail and kingsrød)
		static public String SAD_AUTOMATION_SYSPED_DIGITOLL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDIG004R.pgm";
		
		//Främmande houses 
		//http://10.11.47.61/syjservicestn/syjsSADMOCF.do?user=OSCAR&orgnr
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOCF.do";
		//http://10.11.47.61/syjservicestn/syjsSADMOCF_U.do?user=OSCAR&orgnr
		static public String SAD_UPDATE_DIGITOLL_EXTERNAL_HOUSES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOCF_U.do";
		//http://10.11.47.61/syjservicestn/syjsSADMOLFF.do?user=OSCAR&orgnr
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_FTP_LOG_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOLFF.do";
		//http://10.11.47.61/syjservicestn/syjsSADMOLHFF.do?user=OSCAR&orgnr
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_BACKTO_OMBUD_FTP_LOG_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOLHFF.do";
		//http://10.11.47.61/syjservicestn/syjsSADMOBUPLOG.do?user=OSCAR&orgnr
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSES_BUPFILES_LOG_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSADMOBUPLG.do";
		
		//Främmande master
		//http://10.11.47.61/syjservicestn/syjsZADMOMLF.do?user=OSCAR...
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_MASTER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOMLF.do";
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_MASTER_ATTACHMENTS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOATTF.do";
		//External Houses
		static public String SAD_FETCH_DIGITOLL_EXTERNAL_HOUSE_ATTACHMENTS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsZADMOATTF.do";
		
		
		
		
}
