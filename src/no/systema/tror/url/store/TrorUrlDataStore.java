package no.systema.tror.url.store;

import no.systema.main.util.AppConstants;

/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 * 
 */
public class TrorUrlDataStore {
	
	//----------------------------
	//[1] FETCH MAIN ORDER LIST
	//----------------------------
	//http://localhost:8080/syjservicestror/syjsHEADF_LITE.do?user=OSCAR&limit=100 (dftdg)
	static public String TROR_BASE_MAIN_ORDER_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF_LITE.do";
	//http://gw.systema.no/sycgip/syjsHEADF_LITE.do?user=OSCAR&heavd=1&heopd=11111 (mandatory fields)
	static public String TROR_BASE_FETCH_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF.do";
	//http://localhost:8080/syjservicesbcore/syjsSYHEDUMMY_URAR.do?user=OSCAR&heavd=1
	static public String TROR_BASE_FETCH_SPECIFIC_DEFAULT_BILIMPORT_VALUES_FROM_DBDUMMY_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYHEDUMMY_URAR.do";
	static public String TROR_BASE_FETCH_SPECIFIC_DEFAULT_BILEXPORT_VALUES_FROM_DBDUMMY_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYHEDUMMY_URBR.do";
	
	//http://localhost:8080/syjservicestror/syjsDOKUFE.do?user=OSCAR&fe_dfavd=1&fe_dfopd=52919&fe_dffbnr=1&fe_n3035
	static public String TROR_BASE_FETCH_SPECIFIC_CONTACT_INFORMATION_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKUFE.do";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOKUF.do?user=OSCAR&dfavd=1&dfopd=999&dffbnr=1
	static public String TROR_BASE_FETCH_DOKUF_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKUF.do?";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOKUFM.do?user=OSCAR&fmavd=1&fmopd=52919&fmfbnr=1&fmmknr=2
	static public String TROR_BASE_FETCH_DOKUFM_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKUFM.do?";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOKEFIM.do?user=OSCAR&imavd=1&imopd=999&imlop=1
	static public String TROR_BASE_FETCH_DOKEFIM_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKEFIM.do?";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOKEF.do?user=OSCAR&dfavd=1&dfopd=999&dflop=1
	static public String TROR_BASE_FETCH_DOKEF_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKEF.do?";
			
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOK29.do?user=OSCAR&d29avd=1&d29opd=52919&d29fnr=1
	static public String TROR_BASE_FETCH_DOK29_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOK29.do?";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOK36.do?user=OSCAR&d36avd=1&d36opd=52919&d36fnr=1
	static public String TROR_BASE_FETCH_DOK36_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOK36.do?";
		
	//http://gw.systema.no:8080/syjservicesbcore/syjsFAKTR.do?user=OSCAR&faavd=1&faopd=184&fafrbn=1
	static public String TROR_BASE_FETCH_FAKTR_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFAKTR.do?";
		
	/**
	 * @Example SELECT http://gw.systema.no:8080/syjservicestror/syjsKODTVA.do?user=OSCAR
	 */
	static public String TROR_BASE_KODTVA_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTVA.do";
	
	/**
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR&kftyp=PRODTYPE&kfkod=L
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR&kftyp=PRODTYPE
	 */
	static public String TROR_BASE_KUFAST_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKUFAST.do";

	
	//--------------------
	//[3] EDIT Order
	//mode=A  = Add
	//mode=U  = Update
	//mode=D  = Delete
	//OG et tillegg ...:
	//----------------------
	//http://gw.systema.no/syjservicestror/syjsHEADF_U.do?user=OSCAR&heavd=2&heopd=100&...
	static public String TROR_BASE_UPDATE_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF_U.do?";
	//http://gw.systema.no:8080/syjservicestror/syjsHEADF_STATUS_U.do?user=OSCAR&mode=U&heavd=1&heopd=184&hest=S
	static public String TROR_BASE_UPDATE_SPECIFIC_ORDER_STATUS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsHEADF_STATUS_U.do?";
		
	//http://gw.systema.no:8080/syjservicestror/syjsDOKUF_U.do?user=OSCAR&dfavd=2&dfopd=100&dfsg=JOV....and all the rest in DokufDao...&mode=U/A/D
	static public String TROR_BASE_DOKUF_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKUF_U.do?";
	//http://gw.systema.no:8080/syjservicestror/syjsDOKUFE_U.do?user=OSCAR&fe_dfavd=1&fe_dfopd=100&fe_dffbnr=1&fe_n3035=CN....and all the rest...&mode=U/A/D
	static public String TROR_BASE_DOKUFE_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKUFE_U.do?";
	//http://gw.systema.no:8080/syjservicestror/syjsDOK29_U.do?user=OSCAR&d29avd=1&d29opd=100&d29fnr=1....and all the rest...&mode=A/D
	static public String TROR_BASE_DOK29_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOK29_U.do?";
	//http://gw.systema.no:8080/syjservicestror/syjsDOK36_U.do?user=OSCAR&d36avd=1&d36opd=100&d36fnr=1....and all the rest...&mode=A/D
	static public String TROR_BASE_DOK36_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOK36_U.do?";
	
	//http://gw.systema.no:8080/syjservicestror/syjsDOKEFIM_U.do?user=OSCAR&imavd=2&imopd=100&imlop=1....and all the rest in DokufDao...&mode=U/A/D
	static public String TROR_BASE_DOKEFIM_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKEFIM_U.do?";
	//http://gw.systema.no:8080/syjservicestror/syjsDOKEF_U.do?user=OSCAR&dfavd=1&dfopd=100&dflop=1&....and all the rest...&mode=U/A/D
    static public String TROR_BASE_DOKEF_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsDOKEF_U.do?";
		

	/**
	//[2.1] Message Note management (Consignee, Carrier, Internal)
	static public String EBOOKING_BASE_WORKFLOW_FETCH_MAIN_ORDER_MESSAGE_NOTE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE08R.pgm";
	//http://gw.systema.no/sycgip/TJGE08R.pgm?user=OSCAR&unik=75&reff=11&part=R (R=Receiver, G=Carrier, Blank=internal melding)
	//[2.2] Fraktbrev section (order lines)
	static public String EBOOKING_BASE_WORKFLOW_FETCH_LIST_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE21R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE21R.pgm?user=OSCAR&unik=10001206&reff=TARZAN%20W&fbn=1
	static public String EBOOKING_BASE_WORKFLOW_FETCH_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE22R.pgm"; 	
	//http://gw.systema.no/sycgip/TJGE22R.pgm?user=OSCAR&unik=10001201&reff=TARZAN%20X&fbn=1&lin=1
	static public String EBOOKING_BASE_WORKFLOW_UPDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23R.pgm";
	//http://gw.systema.no/sycgip/TJGE23R.pgm?user=OSCAR&unik=10001206&reff=TARZAN W&fbn=1&lin=3&mode=A&...
	static public String EBOOKING_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV.pgm?user=JOVO&avd=75&opd=19&fmmrk1=&fvant=1&fvpakn=&fvvt=TEST&fvvkt=&fvvol=&fvlm=&fvlm2=&fvlen=&fvbrd=&fvhoy=&ffunnr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	static public String EBOOKING_BASE_WORKFLOW_VALIDATE_LINE_MAIN_ORDER_FRAKTBREV_2_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE23RV2.pgm";
	//http://gw.systema.no/sycgip/TJGE23RV2.pgm?user=JOVO&avd=75&opdtyp=OX&fmmrk1=&fvant=2&fvpakn=&fvvt=TEST&fvvkt=1000&fvlen=220&fvbrd=220&fvhoy=120&fvvol=&fvlm=&fvlm2=&ffunr=1234&ffemb=&ffantk=1&ffante=1&ffenh=KGM
	
	//------------
	//SEND ORDER
	//------------
	//http://gw.systema.no/sycgip/TBOK009R.pgm?user=OSCAR&HEUNIK=10001176
	static public String EBOOKING_BASE_SEND_SPECIFIC_ORDER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TBOK009R.pgm";
	**/
	
	//----------------------------------------------------------------
	//[2] FETCH/UPDATE/CREATE/DELETE Specific Invoice - Invoice lines
	// It might or might not have a trip cross reference
	// mode=A (add)
	// mode=U (update)
	// mode=D (delete)
	//----------------------------------------------------------
	//Lista -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=type=A
	//Linje -->http://gw.systema.no/sycgip/TJGE25R.pgm?user=JOVO&avd=80&opd=201523&lin=55&type=A
    static public String TROR_BASE_FETCH_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE25R.pgm";
	//Update -->http://gw.systema.no/sycgip/TJGE26R.pgm?user=JOVO&avd=80&opd=201523&lin=55&mode=(A)(U)(D)
    static public String TROR_BASE_UPDATE_MAIN_ORDER_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE26R.pgm";
	//http://gw.systema.no/sycgip/TSYFI10X.pgm?user=CB&avd=1&opd=184	
    static public String TROR_BASE_EXECUTE_RECALCULATE_INVOICE_LINES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSYFI10X.pgm";
	
	//----------------
	//Child window
	//----------------
	/**
	//(FRA)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=FRALK&VARKOD=FRA&SOKLK=NO&WSKUNPA=A (A, P eller blank) 
	//(TIL)-->http://gw.systema.no/sycgip/TJINQSTED.pgm?user=JOVO&varlk=TILLK&VARKOD=TIL&SOKLK=NO&
	static public String EBOOKING_BASE_CHILDWINDOW_POSTAL_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQSTED.pgm";
	
	
	//Fakturakunde - replaces the good old fashioned customer search(not applicable in eBooking)
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQFKU.pgm";
	//Fakturakunde adresser
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_DELIVERY_ADDRESS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQVADR.pgm";
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrnr=1 
	//http://gw.systema.no/sycgip/TJINQVADR.pgm?user=JOVO&wkundnr=7031&wvadrna=A (Alfa search exact)
	**/
	
	/**
	static public String EBOOKING_BASE_CHILDWINDOW_CUSTOMER_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQKUND.pgm";
	//http://gw.systema.no/sycgip/TJINQKUND.pgm?user=JOVO&sokknr=1 
	 **/
	
	//http://gw.systema.no:8080/syjservicesbcore/syjsTRAN.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_CARRIER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsTRAN.do";
	//http://gw.systema.no:8080/syjservicestror/syjsTRAN_U_COUNTER.do?user=OSCAR&vmtran=1000
	static public String TROR_BASE_UPDATE_TRAN_COUNTER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsTRAN_U_COUNTER.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsFIRFB_U_COUNTER.do?user=OSCAR&fifirm=SY
	static public String TROR_BASE_UPDATE_FIRFB_COUNTER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFIRFB_U_COUNTER.do";
	//http://gw.systema.no:8080/syjservicestror/syjsSTED2.do?user=OSCAR&st2kod=8000&st2lk=NO
	static public String TROR_BASE_CHILDWINDOW_POSTALCODE_STED2_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsSTED2.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTTST.do?user=OSCAR&ktskod=19
	static public String TROR_BASE_CHILDWINDOW_TOLLSTED_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTTST.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsKUNKO.do?user=OSCAR&kukun1=1
	static public String TROR_BASE_CHILDWINDOW_DELIVERY_ADDRESS_LANDIMPORT_SELLER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKUNKO.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsVADR.do?user=OSCAR&kundnr=4
	static public String TROR_BASE_CHILDWINDOW_DELIVERY_ADDRESS_LANDIMPORT_BUYER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsVADR.do";
	//http://localhost:8080/syjservicestror/syjsKODTERLF.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_LOAD_UNLOAD_PLACES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTERLF.do";
	//http://gw.systema.no/sycgip/TJINQGEB.pgm?user=JOVO&kode=&tekst=&fullinfo=J
	static public String TROR_BASE_CHILDWINDOW_GEBYR_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJINQGEB.pgm";
	//http://gw.systema.no:8080/syjservicestror/syjsPONRN.do?user=OSCAR&ponnr=0010
	static public String TROR_BASE_CHILDWINDOW_POSTALCODE_PONRN_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsPONRN.do";
	
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFLP.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_AIRPRODUCTS_KODTFLP_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTFLP.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFLP.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_AIRLINES_KODTFS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTFS.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFLP.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_CITIES_STED2_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsSTED2.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFR.do?user=OSCAR
	static public String TROR_BASE_CHILDWINDOW_INCOTERMS_KODTFR_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTFR.do";
						
	/**
	//---------------------------------------------------
	//[2] GENERAL CODES - for country (AS400 from TVINN) 
	//---------------------------------------------------
	static public String EBOOKING_CODES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOG005R.pgm"; 
	//http://gw.systema.no/sycgip/TNOG005R.pgm?user=OSCAR&typ=2 //country list
	**/
	
	//---------------------------------------------------
	//[3] GENERAL FUNCTIONS eg.(signature, other...) 
	//---------------------------------------------------
	//http://localhost:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=DELSYS
	static public String TROR_GENERAL_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKOFAST.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	static public String TROR_COUNTRY_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTLK.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTVA.do?user=OSCAR
	static public String TROR_CURRENCY_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTVA.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsKODTOTY.do?user=OSCAR
	static public String TROR_OPPDRAGSTYPE_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTOTY.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKODTFR.do?user=OSCAR
	static public String TROR_INCOTERMS_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTFR.do";
	//http://gw.systema.no:8080/syjservicestror/syjsKUFAST.do?user=OSCAR
	static public String TROR_PRODUCT_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKUFAST.do";
	//http://gw.systema.no:8080/syjservicesbcore/syjsTRKODFR.do?user=OSCAR&tkunik=017
	static public String TROR_ENHET_CODES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRKODFR.do";
	//http://gw.systema.no:8080/syjservicesbtror/syjsKODTSF.do?user=OSCAR
	static public String TROR_SIGNATURES_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestror/syjsKODTSF.do";
		
	//------------------------------------------
	//[4] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	
	//http://gw.systema.no/sycgip/TNOI015R.pgm?user=OSCAR&avd=1&opd=91152 
	//static public String TROR_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI015R.pgm";	
	static public String TROR_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE33R.pgm";	
	
	//http://gw.systema.no/sycgip/TNOI016R.pgm?user=OSCAR&fmn=84278
	//static public String TROR_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TNOI016R.pgm";	
	static public String TROR_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE34R.pgm";	
	
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
	static public String TROR_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules
	
	
	
		//----------------------------------------------------------------
		//[2] FETCH/UPDATE/CREATE/DELETE Specific Budget - Budget lines
		// It might or might not have a trip cross reference
		// mode=A (add)
		// mode=U (update)
		// mode=D (delete)
		//----------------------------------------------------------
		//http://gw.systema.no/sycgip/TJGE27RG.pgm?user=JOVO&avd=75&opd=&tur=75000038&bnr=1319143&type=A
		static public String TROR_BASE_FETCH_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RG.pgm";
		//http://gw.systema.no/sycgip/tjge27ru.pgm?user=JOVO&bnr=1318923&mode=U&bupCc=20&bupAr=15&bupMn
		static public String TROR_BASE_UPDATE_MAIN_ORDER_BUDGET_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE27RU.pgm";
		
		
		//----------------------------------------------------------------
		//[3] FETCH/UPDATE/CREATE/DELETE Frie søkeveier
		// It might or might not have a trip cross reference
		// mode=A (add)
		// mode=U (update)
		// mode=D (delete)
		// mode=I (inquiry)
		//----------------------------------------------------------
		//http://gw.systema.no/sycgip/TJGE28R.pgm?user=JOVO&avd=75&opd=155651
		static public String TROR_BASE_FETCH_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE28R.pgm";
		//http://gw.systema.no/sycgip/TJGE29R.pgm?user=JOVO&avd=75&opd=155651&mode=A&fskode=IFB&fssok=test&fsdokk=...	
		static public String TROR_BASE_UPDATE_MAIN_ORDER_FRISOKVEI_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE29R.pgm";
			
		//----------------------------------------------------------------
		//[3] FETCH/UPDATE/CREATE/DELETE Track & Trace general
		//----------------------------------------------------------------
		//http://localhost:8080/syjservicesbcore/syjsTRACKF.do?user=OSCAR&ttavd=1&ttopd=184...
		static public String TROR_BASE_FETCH_TRACK_AND_TRACE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRACKF.do";
		//http://gw.systema.no/sycgip/tjge27ru.pgm?user=JOVO&bnr=1318923&mode=U&bupCc=20&bupAr=15&bupMn
		static public String TROR_BASE_UPDATE_TRACK_AND_TRACE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRACKF_U.do";
		
		//----------------------------------------------------------------
		//[3] FETCH/UPDATE/CREATE/DELETE Fellesutskrift general
		//----------------------------------------------------------------
		//http://http://gw.systema.no/sycgip/TSYFAPR1.pgm?user=OSCAR&wsavd=1&wssg=OT ... etc
		static public String TROR_BASE_EXECUTE_FELLESUTSKRIFT_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSYFAPR1.pgm";
		
	
}
