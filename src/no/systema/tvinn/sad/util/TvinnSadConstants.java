/**
 * 
 */
package no.systema.tvinn.sad.util;

/**
 * All type of system constants for TVINN-SAD in general
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public final class TvinnSadConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_TVINN_SAD = "activeUrlRPG_TvinnSad";
	public static final String ACTIVE_URL_RPG_UPDATE_TVINN_SAD = "activeUrlRPGUpdate_TvinnSad";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_TVINN_SAD = "activeUrlRPGFetchItem_TvinnSad"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	public static final String ACTION_REVERSE = "doReverse";
	
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
	public static final String RESOURCE_MODEL_KEY_CURRENCY_LIST = "currencyList";
	public static final String RESOURCE_MODEL_KEY_COUNTRY_LIST = "countryList";
	public static final String RESOURCE_MODEL_KEY_LANGUAGE_LIST = "languageList";
	public static final String RESOURCE_MODEL_KEY_BERAKNINGSENHET_LIST = "berakningsEnhetList";
	
	
	public static final String RESOURCE_MODEL_KEY_HOURS_LIST = "hoursList";
	public static final String RESOURCE_MODEL_KEY_MINUTES_LIST = "minutesList";
	public static final String RESOURCE_MODEL_KEY_UOM_LIST = "uomList";
	
	//avd and signature lists
	public static final String RESOURCE_MODEL_KEY_AVD_LIST = "avdList";
	public static final String RESOURCE_MODEL_KEY_SIGN_LIST = "signList";
	public static final String RESOURCE_MODEL_KEY_AVD_LIST_SESSION_TEST_FLAG = "avdListSessionTestFlag";
	
	public static final String RESOURCE_MODEL_KEY_CODE_FORMAN_LIST = "formanCodeList"; //förmånskoder
	public static final String RESOURCE_MODEL_KEY_CODE_FORFARANDE01_LIST = "forfarande01CodeList"; //förfarande 1
	public static final String RESOURCE_MODEL_KEY_CODE_FORFARANDE02_LIST = "forfarande02CodeList"; //förfarande 1
	
	//external URLs (if applicable)
	public static final String URL_EXTERNAL_LANGUAGECODES_TARIC_CODE = "isoLanguageCodesURL";
	/*public static final String URL_EXTERNAL_LANDCODES_TARIC_CODE = "taricLandCodesURL";
	public static final String URL_EXTERNAL_CURRENCYCODES_TARIC_CODE = "taricCurrencyCodesURL";
	public static final String URL_EXTERNAL_KOLLISLAGCODES_TARIC_CODE = "taricKollislagCodesURL";
	public static final String URL_EXTERNAL_FORMANSKODCODES_TARIC_CODE = "taricFormanskodCodesURL";
	public static final String URL_EXTERNAL_FORFARANDE01_CODES_TARIC_CODE = "taricForfarande01CodesURL";
	public static final String URL_EXTERNAL_FORFARANDE02_CODES_TARIC_CODE = "taricForfarande02CodesURL";
	public static final String URL_EXTERNAL_AVGIFTSBERAKNINGAR_CODES_TARIC_CODE = "taricAvgiftsberakningarCodesURL";
	public static final String URL_EXTERNAL_MCFCODES_TARIC_CODE = "taricBilagdaHandlingarCodesURL";
	public static final String URL_EXTERNAL_SALCODES_TARIC_CODE = "taricSarskildaUpplysningarCodesURL";
	public static final String URL_EXTERNAL_FRAGA_TULLID_TARIC_CODE = "taricFragaTullidURL";
	*/

	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
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
	//
	public static final String SESSION_CODE_MANAGER_EXISTS_SADIMPORT = "codeMgrExistsSadImport";
	public static final String SESSION_CODE_MANAGER_EXISTS_SADEXPORT = "codeMgrExistsSadExport";
	public static final String SESSION_CODE_MANAGER_EXISTS_SADEXPORT_NCTS = "codeMgrExistsSadImportNcts";
	public static final String SESSION_CODE_MANAGER_EXISTS_SADIMPORT_NCTS = "codeMgrExistsSadExportNcts";
	
	
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD = "recordTopicTvinnSad";
	public static final String DOMAIN_RECORD_TOPIC_TVINN_SAD_UNLOADING = "recordTopicTvinnSadUnloading";
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_SIZE = "listSize";
	
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_FINANS_OPPLYSNINGER_TOPIC = "recordItemContainerFinansOpplysningerTopic";
	public static final String SESSION_LIST = "sessionList";
	public static final String ITEM_LIST = "itemList";

	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	   
}
