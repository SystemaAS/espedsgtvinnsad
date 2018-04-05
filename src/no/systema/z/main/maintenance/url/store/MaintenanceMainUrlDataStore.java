/**
 * 
 */
package no.systema.z.main.maintenance.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 * 
 */
public final class MaintenanceMainUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R.do?user=OSCAR...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA26R.do?user=OSCAR&teavd=1
	static public String MAINTENANCE_MAIN_BASE_SYFA26R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA26R.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28R.do?user=OSCAR&kovavd=333
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA63R.do?user=OSCAR&koaavd=2
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R.do";
	//ALL --> http://localhost:8080/syjservicesbcore/syjsSYFA68R.do?user=OSCAR&kohavd=1
	static public String MAINTENANCE_MAIN_BASE_SYFA68R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA68R.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFIRMR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMR.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFIRMONLY.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFIRMONLY_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFIRMONLY.do";
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYKS01R.do?user=OSCAR (drop down on cost(kostt) for firmaopplysningar)
	static public String MAINTENANCE_MAIN_BASE_SYKS01R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYKS01R.do";
		
	//SIGNATURER-->ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA60R.do?user=OSCAR
	//SIGNATURER-->ONE --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA60R.do?user=OSCAR&kosfsi=OT
	static public String MAINTENANCE_MAIN_BASE_SYFA60R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA60R.do";
	
	
	
	//AVD SAD IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAR.do";
	//AVD SAD EXPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSYFTAAAER.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAER_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAER.do";
	//AVD SAD NCTS IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsTR053R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_TR053R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR053R.do";
	//AVD SAD NCTS EKSPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsTR003R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_TR003R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003R.do";
	//CHILD RECORD - Sikkerhet -->http://gw.systema.no:8080/syjservicesbcore/syjsTR003fvR.do?user=OSCAR&thavd=1
	static public String MAINTENANCE_MAIN_BASE_TR003fvR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003fvR.do";
	
	
	
	//AVD SKAT EXPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsDKE051R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_DKE051R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKE051R.do";
	//AVD SKAT IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsDKI051R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_DKI051R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKI051R.do";	
	//AVD SKAT NCTS EKSPORT ON GENERAL MAINT	
	//http://gw.systema.no:8080/syjservicesbcore/syjsDKX003R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_DKX003R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX003R.do";
	//CHILD RECORD - Sikkerhet -->http://gw.systema.no:8080/syjservicesbcore/syjsDKX003fvR.do?user=OSCAR&thavd=1
	static public String MAINTENANCE_MAIN_BASE_DKX003fvR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX003fvR.do";
	//AVD SKAT NCTS IMPORT ON GENERAL MAINT	
	//http://gw.systema.no:8080/syjservicesbcore/syjsDKX053R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_DKX053R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX053R.do";
	
	//AVD TDS EXPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSVE051R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SVE051R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVE051R.do";
	//AVD TDS IMPORT ON GENERAL MAINT
	//http://gw.systema.no:8080/syjservicesbcore/syjsSVI051R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SVI051R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVI051R.do";	
	//AVD TDS NCTS EKSPORT ON GENERAL MAINT	
	//http://gw.systema.no:8080/syjservicesbcore/syjsSVX003R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SVX003R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX003R.do";
	//CHILD RECORD - Sikkerhet -->http://gw.systema.no:8080/syjservicesbcore/syjsSVX003fvR.do?user=OSCAR&thavd=1
	static public String MAINTENANCE_MAIN_BASE_SVX003fvR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX003fvR.do";
	//AVD TDS NCTS IMPORT ON GENERAL MAINT	
	//http://gw.systema.no:8080/syjservicesbcore/syjsSVX053R.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SVX053R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX053R.do";
	
	
	//CHILDREN List on SYFA28 --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333
	//ONE CHILD on SYFA28 --> http://localhost:8080/syjservicesbcore/syjsSYFA28ChildR.do?user=OSCAR&kopavd=333&koplnr=4
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_GET_CHILDREN_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR.do";
	
	//GET Customer (cundf)
	/**
	 * ALL --> http://http://gw.systema.no:8080/syjservicesbcore/syjsSYCUNDFR.do?user=OSCAR&kundnr=1&firma=SY
	 */
	static public String MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYCUNDFR.do";
	
	
	//GET Kontaktpersoner (cundc)
	/**
	 * ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC.do?user=OSCAR&cfirma=SY&ccompn=1
	 * 
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_CUNDC_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsCUNDC.do";
	
	//GET Default Emma Xml (cundc)
	/**
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsCUNDC_LATEST_EMMA_XML_INFO.do?user=OSCAR&cfirma=SY
	 * 
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_CUNDC_GET_LATEST_EMMA_XML_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsCUNDC_LATEST_EMMA_XML_INFO.do";

	/**
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=FUNKSJON&kfkod=ad
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKOFAST.do?user=OSCAR&kftyp=FUNKSJON
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KOFAST_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKOFAST.do";	

	/**
	 * /syjservicesbcore/syjsCUNDC_C.do
	 */
	static public String MAINTENANCE_MAIN_BASE_CUNDC_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsCUNDC_U.do";
	
	/**
	 * Adding/deleting/updating in CUNDF
	 * 
	 * /syjservicesbcore/syjsSYCUNDFR_U.do
	 */
	static public String MAINTENANCE_MAIN_BASE_SYCUNDFR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYCUNDFR_U.do";
	
	
	/**
	 * Retrieve a Enhet on provided orgnr from data.brreg.no. <br><br>
	 * 
	 * /syjservicestn/brreg.do
	 * 
	 * @Example http://gw.systema.no:8080/syjservicestn/brreg.do?user=OSCAR&orgnr=936809219
	 */
	static public String BRREG_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/brreg.do";
	
	
	/**
	 * Retrieve a List of SadvareDao
	 * 
	 * @Example SELECT specific:http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1&varenr=9004901000
	 * @Example SELECT list: 	http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE.do?user=OSCAR&levenr=1
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SADVARE_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSADVARE.do";	

	
	/**
	 * DML of SadvareDao
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSADVARE_U.do?user=OSCAR&levenr=24&varenr=59&varebe=alfaupdate...and many more...&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SADVARE_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSADVARE_U.do";	
	
	
	/**
	 * Retrieve a List of SyparfDao
	 * 
	 * @Example SELECT specific:http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1&syrecn=15
	 * @Example SELECT list: 	http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF.do?user=OSCAR&sykunr=1
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SYPARF_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYPARF.do";
	//Users on Syparf
	//SELECT list: 	http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF2.do?user=OSCAR&syuser=OSCAR
	static public String MAINTENANCE_MAIN_BASE_SYPARF2_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYPARF2.do";


	/**
	 * DML of SyparfDao
	 * 
	 * @Example http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF_U.do?user=OSCAR&sykunr=24&syrecn=59&sysort=15&syvrdn=1,5&syvrda=alfaupdate&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SYPARF_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYPARF_U.do";
	//Users on Syparf
	//SELECT list: 	http://gw.systema.no:8080/syjservicesbcore/syjsSYPARF2_U.do?user=OSCAR...etc
	static public String MAINTENANCE_MAIN_BASE_SYPARF2_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYPARF2_U.do";			
	
	/**
	 * Retrieve a List of ValufDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsVALUF.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_VALUF_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsVALUF.do";
	
	
	/**
	 * Retrieve a List of KodtlkDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTLK.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTLK_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTLK.do";
		
	/**
	 * Retrieve a List of KodtotyDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTOTY.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTOTY_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTOTY.do";
	
	/**
	 * Retrieve a List of Kodts7Dao, where ks7uni = S7
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTS7.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTS7_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTS7.do";

	/**
	 * Retrieve a List of Kodts8Dao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTS8.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTS8_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTS8.do";	
	
	
	/**
	 * Retrieve a List of Kodts2Dao, where ks2uni = S2
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTS2.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTS2_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTS2.do";
	
	/**
	 * Retrieve a List of TariDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsTARI.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_TARI_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTARI.do";
	
	/**
	 * Retrieve a List of Kodts5Dao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTS5.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTS5_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTS5.do";

	/**
	 * Retrieve a List of Kodts6Dao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTS6.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTS6_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTS6.do";
	
	/**
	 * Retrieve a List of KodtsaDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTSA.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTSA_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTSA.do";	
	
	/**
	 * Retrieve a List of KodtsbDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTSB.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTSB_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTSB.do";	
	
	/**
	 * Retrieve a List of KodtvalfDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTVALF.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTVALF_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTVALF.do";	
	
	/**
	 * Retrieve a List of KodtftDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsKODTFT.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_KODTFT_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsKODTFT.do";		
	
	/**
	 * Retrieve a List of FratxtDao
	 * 
	 * @Example SELECT specific: http://gw.systema.no:8080/syjservicesbcore/syjsFRATXT.do?user=OSCAR&fxknr=24&delsys=A
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_FRATXT_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFRATXT.do";	
	
	/**
	 * DML of FratxtDao
	 * 
	 * @Example http://gw.systema.no:8080/syjservicesbcore/syjsFRATXT_U.do?user=OSCAR&fxknr=24&fxlnr=20&delsys=A&fxtxt=updatedtext&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_FRATXT_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFRATXT_U.do";		
	
	/**
	 * Retrieve a List of ArktxtDao
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT.do?user=OSCAR&artype=fa
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_ARKTXT_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsARKTXT.do";


	/**
	 * DML of ArktxtDao
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKTXT_U.do?user=OSCAR&artype=ZH&artxt=kalle&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_ARKTXT_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsARKTXT_U.do";				
	
	/**
	 * Retrieve a List of ArkextDao
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsARKEXT.do?user=OSCAR&arcext=E1
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsARKEXT.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_ARKEXT_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsARKEXT.do";
	

	/**
	 * Retrieve a List of SvewDao
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSVEW.do?user=OSCAR&svew_knnr=1&svew_knso=Tarzan
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVEW.do?user=OSCAR&svew_knnr=1
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVEW_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVEW.do";	
	
	/**
	 * DML of SvewDao
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVEW_U.do?user=OSCAR&svew_knnr=1&svew_knso=Tarzan&...and many more...&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVEW_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVEW_U.do";	
	

	/**
	 * Retrieve a List of SviwDao
	 * 
	 * @Example SELECT
	 *          specific:http://gw.systema.no:8080/syjservicesbcore/syjsSVIW.do?user=OSCAR&sviw_knnr=1&sviw_knso=Jane
	 * @Example SELECT list:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVIW.do?user=OSCAR&svew_knnr=1
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVIW_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVIW.do";	
	
	/**
	 * 
	 * @Example UPDATE:
	 *          http://gw.systema.no:8080/syjservicesbcore/syjsSVEW_U.do?user=OSCAR&svew_knnr=1&svew_knso=Jane&...and many more...&mode=U/A/D
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVIW_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVIW_U.do";	
	
	
	/**
	 * Retrieve a List of Svtx03fDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsSVTX03F.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVTX03F_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVTX03F.do";		


	/**
	 * Retrieve a List of Svtx10fDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsSVTX10F.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVTX10F_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVTX10F.do";		

	/**
	 * Retrieve a List of Svtx03fDao
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsSVTPRO.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_SVTPRO_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVTPRO.do";			

	/**
	 * Retrieve a List of SingleValueObject, placeholder of available years in HEADF
	 * 
	 * @Example SELECT list: http://gw.systema.no:8080/syjservicesbcore/syjsFAKT_DB_DISTINCT_YEAR.do?user=OSCAR
	 * 
	 */
	static public String MAINTENANCE_MAIN_BASE_HEADF_YEARS_GET_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsFAKT_DB_DISTINCT_YEAR.do";		
	
	//OS-Users (general)
	//http://gw.systema.no:8080/syjservicesbcore/syjsSYQAOKP08AR.do?user=OSCAR&wos8dden==ROGER
	static public String MAINTENANCE_MAIN_BASE_SYQAOKP08A_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYQAOKP08AR.do";	
	//GET EDI (edii)
	//ALL --> http://http://gw.systema.no:8080/syjservicesbcore/syjsSYEDIIR.do?user=OSCAR&inid=1&inna=TOLD
	static public String MAINTENANCE_MAIN_BASE_SYEDIIR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYEDIIR.do";
	
	//SAD GENERAL CODES (Language, Country, Tollsted (tullkontor))
	//http://gw.systema.no:8080/syjservicesbcore/syjsTRKODFR.do?user=OSCAR
	static public String MAINTENANCE_MAIN_BASE_CODES_TRKODFR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRKODFR.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT04R_U
	//ALL --> http://gw.systema.no:8080/syjservicesbcore/syjsSYFA14R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_MAIN_BASE_SYFA14R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA14R_U.do";
	//SYFA26R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA26R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA26R_U.do";
	
	//SYFA28R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28R_U.do";
	//SYFA28R_U CHILD (Del-2)
	static public String MAINTENANCE_MAIN_BASE_SYFA28R_DML_UPDATE_CHILD_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28ChildR_U.do";
	//SYFA28DPTAvdR_U /Dupliser (Kopiera)
	//http://localhost:8080/syjservicesbcore/syjsSYFA28DPTAvdR_U.do?user=OSCAR&originalAvd=1&originalLnr=2&fromAvd=333&toAvd=334&mode=U
	static public String MAINTENANCE_MAIN_BASE_SYFA28DPTAvdR_DML_UPDATE_CHILD_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA28DPTAvdR_U.do";
	//SYFA63R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA63R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA63R_U.do";
	//SYFA68R_U
	static public String MAINTENANCE_MAIN_BASE_SYFA68R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA68R_U.do";
	//SYFA60R_U (SAKSBEHANDLERE)
	static public String MAINTENANCE_MAIN_BASE_SYFA60R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA60R_U.do";
	//syjsSYFIRMR_U.do (FIRM)
	static public String MAINTENANCE_MAIN_BASE_SYFIRMR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFIRMR_U.do";
	
	
	
	//AVD SAD IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAR_U.do";
	//AVD SAD EXPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SYFTAAAER_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFTAAAER_U.do";
	//AVD SAD NCTS IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_TR053R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR053R_U.do";
	//AVD SAD NCTS EKSPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_TR003R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003R_U.do";
	static public String MAINTENANCE_MAIN_BASE_TR003fvR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTR003fvR_U.do";
	
	//AVD SKAT EXPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_DKE051R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKE051R_U.do";
	//AVD SKAT IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_DKI051R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKI051R_U.do";
	//AVD SKAT NCTS EKSPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_DKX003R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX003R_U.do";
	static public String MAINTENANCE_MAIN_BASE_DKX003fvR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX003fvR_U.do";
	//AVD SKAT NCTS IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_DKX053R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsDKX053R_U.do";
	
	//AVD TDS EXPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SVE051R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVE051R_U.do";
	//AVD TDS IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SVI051R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVI051R_U.do";
	//AVD TDS NCTS EKSPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SVX003R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX003R_U.do";
	static public String MAINTENANCE_MAIN_BASE_SVX003fvR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX003fvR_U.do";
	//AVD TDS NCTS IMPORT ON GENERAL MAINT
	static public String MAINTENANCE_MAIN_BASE_SVX053R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSVX053R_U.do";
		
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFT02R_GET_CURRENCY_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT02R.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SYFA61R_GET_OPPTYPE_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsSYFA61R.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_GET_CODES_SAD_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesbcore/syjsTRKODFR.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_GET_CODES_SAD_SERVICESTN_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS4R.do";
	//DK
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_DKT057R_GET_CURRENCY_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKT057R.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_GET_CODES_SKAT_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX001R.do";
	//TDS
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_SVT057R_GET_CURRENCY_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVT057R.do";
	static public String MAINTENANCE_MAIN_BASE_DROPDOWN_GET_CODES_TDS_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservices/syjsSVX001R.do";
	
	 
	
}
