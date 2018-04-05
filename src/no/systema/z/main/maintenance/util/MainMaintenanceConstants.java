/**
 * 
 */
package no.systema.z.main.maintenance.util;


/**
 * All type of system constants for Maintenance in general
 * 
 * @author oscardelatorre
 * @date Apr 03, 2018
 * 
 *
 */
public final class MainMaintenanceConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_MAIN_MAINTENANCE = "activeUrlRPG_MainMaintenance";
	public static final String ACTIVE_URL_RPG_UPDATE_MAIN_MAINTENANCE = "activeUrlRPGUpdate_MainMaintenance";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_MAIN_MAINTENANCE = "activeUrlRPGFetchItem_MainMaintenance"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	
	//update modes
	public static final String MODE_UPDATE = "U";
	public static final String MODE_ADD = "A";
	public static final String MODE_DELETE = "D";
	public static final String MODE_SEND = "S";
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	//base path for resource files (for drop-downs or other convenient files
	
	public static final String RESOURCE_FILES_PATH = "/WEB-INF/resources/files/";
	public static final String RESOURCE_MODEL_KEY_HOURS_LIST = "hoursList";
	public static final String RESOURCE_MODEL_KEY_MINUTES_LIST = "minutesList";
	public static final String RESOURCE_MODEL_KEY_UOM_LIST = "uomList";
	
	//CODES
	public static final int ERROR_CODE = -1;
	
	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	
	/*
	public static final String DOMAIN_SEARCH_FILTER = "searchFilter";
	public static final String SESSION_SEARCH_FILTER = "searchFilter";
	//filter per module
	public static final String DOMAIN_SEARCH_FILTER_SADIMPORT = "searchFilterSadImport";
	public static final String DOMAIN_SEARCH_FILTER_SADEXPORT = "searchFilterSadExport";
	public static final String DOMAIN_SEARCH_FILTER_SADIMPORT_NCTS = "searchFilterSadImportNcts";
	public static final String DOMAIN_SEARCH_FILTER_SADEXPORT_NCTS = "searchFilterSadExportNcts";
	public static final String SESSION_SEARCH_FILTER_SADIMPORT = "searchFilterSadImport";
	public static final String SESSION_SEARCH_FILTER_SADEXPORT = "searchFilterSadExport";
	public static final String SESSION_SEARCH_FILTER_SADIMPORT_NCTS = "searchFilterSadImportNcts";
	public static final String SESSION_SEARCH_FILTER_SADEXPORT_NCTS = "searchFilterSadExportNcts";
	
	
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD = "recordTopicTvinnSad";
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD_UNLOADING = "recordTopicTvinnSadUnloading";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_FINANS_OPPLYSNINGER_TOPIC = "recordItemContainerFinansOpplysningerTopic";
	public static final String SESSION_LIST = "sessionList";
	public static final String ITEM_LIST = "itemList";
	*/
	
	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	//
	public static final String CODE_MGR_CURRENCY_LIST = "currencyCodeList";
	public static final String CODE_MGR_AVD_GENERAL_LIST = "avdGeneralList";
	public static final String CODE_MGR_OPP_TYPE_LIST = "oppTypeList";
	public static final String CODE_MGR_CODE_SAD_4_TRANSPORTMATER_LIST = "transportmaterCodeList";
	public static final String CODE_MGR_CODE_SAD_INCOTERMS_LIST = "incotermsCodeList";
	public static final String CODE_MGR_CODE_SAD_IMPORT_EKSPEDTYPER_LIST = "ekspedisjonstyperImportCodeList";
	public static final String CODE_MGR_CODE_SAD_EXPORT_EKSPEDTYPER_LIST = "ekspedisjonstyperExportCodeList";
	public static final String CODE_MGR_CODE_NCTS_EXPORT_DEKLARASJONS_TYPE_LIST = "deklarasjonsTypeCodeList";
	//SKAT
	public static final String CODE_MGR_CODE_SKAT_NCTS_EXPORT_108_TRANSPORTMATER_LIST = "transportmaterCodeList";
	
	
	public static final String CODE_MGR_CODE_NCTS_096_LIST = "ncts096_SpecOmst_CodeList";
	public static final String CODE_MGR_CODE_NCTS_116_LIST = "ncts116_BetalningTransport_CodeList";
	//CODE TABLE TRKODF (SAD-NCTS, SKAT-NCTS)
	public static final String CODE_NCTS_SIKKERHET_096_SPES_OMSTAND = "096";
	public static final String CODE_NCTS_SIKKERHET_116_TRANSP_KOST_BETAL_MATE = "116";
	public static final String CODE_SAD_4_TRANSPORTMATE = "4";
	public static final String CODE_NCTS_DEKLARASJONS_TYPE = "031";

	//SKAT
	public static final String CODE_SKAT_NCTS_EXPORT_108_TRANSPORTMATE = "108";
	
	
	
	/**
	 * Cast to {@link KundeSessionParams}
	 * 
	 */
	public static final String KUNDE_SESSION_PARAMS = "kundeSessionParams";	
	   
}
