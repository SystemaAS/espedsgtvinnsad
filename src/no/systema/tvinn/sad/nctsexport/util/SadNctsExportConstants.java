/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.util;

/**
 * All type of system constants for TVINN-NCTS Export
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 *
 */
public final class SadNctsExportConstants {
   /** 
	012= KOD_SPRAK
	013= KOD_DOK
	014= KOD_TIDIGARE_DOK
	017= KOD_KOLLI_TYP
	031= KOD_DEKL_TYP
	039= KOD_TILLAGSUPP
	047= KOD_KONTROLLRESULTAT
	064= KOD_KANSLIGVARA
	096= KOD_SPEC_OMST
	105= KOD_TILLGANGASKOD_GARANTI
	106= KOD_TULLKONTOR_REF
	116= KOD_BETALNINGSSATT_TRANSPORTKOSTNAD
	
	=========================
	BORROWED from SAD 
	=========================
	2= Landekoder, R15
	V= Valutakoder
	4= Transportmater
	*/
	
	//code lists (Kolli(017), Dokumentkod(013), etc.
	public static final String RESOURCE_MODEL_KEY_CODE_2_COUNTRY_LIST = "countryCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_V_CURRENCY_LIST = "currencyCodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_4_TRANSPORTMATER_LIST = "transportmaterCodeList";
	
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_012_SPRAK_LIST = "ncts012_Sprak_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_013_DOKTYPE_LIST = "ncts013_DocType_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_014_TIDIGAREDOKS_LIST = "ncts014_TidigareDocs_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_017_KOLLI_LIST = "ncts017_Kolli_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_031_DEKLTYPE_LIST = "ncts031_DeklType_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_039_TILLAGSUPP_LIST = "ncts039_TillagsUppgifter_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_047_KONTROLL_RESULTAT_LIST = "ncts047_KontrollResultat_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_064_KANSLIGVARA_LIST = "ncts064_KansligVara_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_096_SPEC_OMST_LIST = "ncts096_SpecOmst_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_105_TILLGANGASKOD_GARANTI_LIST = "ncts105_TillgangskodGaranti_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_106_TULLKONTOR_REF_LIST = "ncts106_TullkontorRef_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_116_BETALNINGSSATT_TRANSPORTKOSTNAD_LIST = "ncts116_BetalningTransport_CodeList";
	public static final String RESOURCE_MODEL_KEY_CODE_NCTSEX_302_STATUS_KODER_LIST = "statusCodeList";
	
	//external URLs (if applicable)
	public static final String URL_EXTERNAL_TOLLTARIFFEN = "tolltariffenURL";
	
	//TODO
	public static final String URL_EXTERNAL_TOLDSTEDXXX_CODE = "toldstedXXXCodesURL";
	public static final String URL_EXTERNAL_LANDCODES_SKAT_CODE = "skatLandCodesURL";
	public static final String URL_EXTERNAL_CURRENCYCODES_SKAT_CODE = "skatCurrencyCodesURL";
	public static final String URL_EXTERNAL_LANGUAGECODES_ISO_CODE = "isoLanguageCodesURL";
	//public static final String URL_EXTERNAL_UOM_SKAT_CODE = "skatUnitOfMeasureCodesURL";
	//public static final String URL_EXTERNAL_KOLLIARTCODES_31_SKAT_CODE = "skatKolliart31CodesURL";
	//public static final String URL_EXTERNAL_TRANSPORT_SUMMARISKA_DOKUMENTCODES_40_SKAT_CODE = "skatTransportSummariskaDokument40CodesURL";
	//public static final String URL_EXTERNAL_CERTIFIKATCODES_R44_2_SKAT_CODE = "skatCertifikatk44_2CodesURL";
	//public static final String URL_EXTERNAL_VAB_CERTIFIKATCODES_R44_3_SKAT_CODE = "skatVabCertifikatk44_3CodesURL";
	//public static final String URL_EXTERNAL_STAMDATALISTE_SKAT_CODE = "skatStamdataCodesURL";
	//public static final String URL_EXTERNAL_EXPORTARTER_CODE = "exportArterCodesURL";
	   
}
