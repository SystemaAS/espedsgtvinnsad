/**
 * 
 */
package no.systema.tvinn.sad.sadimport.util;

/**
 * All type of system constants for TVINN-SAD Import in general
 * 
 * @author oscardelatorre
 * @date Jun 2, 2014
 * 
 *
 */
public final class SadImportConstants {
	/**
		1=Ekspedisjonstyper(import)
	 * 	2=Landkoder                     
		3=Transaksjonstyper     
		4=Transportmåter               
		5=Tollnedsettelser 
		6=Preferanser                 
		7=V.F. koder                 
		8=Avgiftkoder                    
		9=Ekspedisjonstyper(eksport)              
		
		A=Enhetskoder                          
		B=Dok./Sertifikat kode (TVINN-import)  
		C=Dok./sertifikat kode (TVINN-eksport) 
		D=lagringssted                         
		E=fylkeskoder                          
		O=Typetilfelle (omberegning)           
		//
		?TODO CB-COVI 32=Valutakurser (och koder)
	*/
	
	public static final String RESOURCE_MODEL_KEY_CODE_1_EKSPEDISJONSTYPER_IMPORT_LIST = "ekspedisjonstyperImportCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST = "countryCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_3_TRANSAKSJONSTYPER_LIST = "transaksjonstyperCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST = "transportmaterCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_5_TOLLNEDSETTELSER_LIST = "tollnedsettelserCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_6_PREFERANSER_LIST = "preferanserCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_7_VFKODER_LIST = "vfCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_8_AVGIFTSKODER_LIST = "avgiftsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_8B_AVGIFTSKODER_SEKV_LIST = "avgiftsCodeListB";
	public static final String RESOURCE_MODEL_KEY_CODE_9_EKSPEDISJONSTYPER_EXPORT_LIST = "ekspedisjonstyperExportCodeList";
	//
	public static final String RESOURCE_MODEL_KEY_CODE_A_ENHETSKODER_LIST = "enhetsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_B_DOK_SERTIFIKATKODER_IMPORT_LIST = "docSertImportCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_C_DOK_SERTIFIKATKODER_EXPORT_LIST = "docSertExportCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_D_LAGRINGSSTED_LIST = "lagringsstedCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_E_FYLKESKODERKODER_LIST = "fylkesCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_F_TYPETILFELLE_LIST = "typetilfelleCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_L_INCOTERMS_LIST = "incotermsCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST = "currencyCodeList";
	//
	public static final String RESOURCE_MODEL_KEY_CODE_O_TYPETILFELLE_OMBEREGNING_LIST = "typetilfelleOmbCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_ARCHIVE_CODE_LIST = "typeArchiveCodeList";
	
	public static final String URL_EXTERNAL_TOLLTARIFFEN = "tolltariffenURL";
	
}
