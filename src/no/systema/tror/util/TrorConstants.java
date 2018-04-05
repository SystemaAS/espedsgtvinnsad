/**
 * 
 */
package no.systema.tror.util;

/**
 * 
 * All type of system constants for TROR in general
 * 
 * @author oscardelatorre
 * @date Jul 04, 2017
 * 
 *
 */
public final class TrorConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_TROR = "activeUrlRPG_Tror";
	public static final String ACTIVE_URL_RPG_UPDATE_TROR = "activeUrlRPGUpdate_Tror";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_TROR = "activeUrlRPGFetchItem_Tror"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	public static final String ACTION_FETCH = "doFetch";
	public static final String ACTION_UPDATE = "doUpdate";
	public static final String ACTION_CREATE = "doCreate";
	public static final String ACTION_DELETE = "doDelete";
	public static final String ACTION_SEND = "doSend";
	public static final String ACTION_EXECUTE = "doExecute";
	
	
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
	public static final String RESOURCE_MODEL_KEY_YEAR_LIST = "yearList";
	public static final String RESOURCE_MODEL_KEY_MONTH_LIST = "monthList";
	public static final String RESOURCE_MODEL_KEY_CURRENCY_CODE_LIST = "currencyCodeList";
	public static final String RESOURCE_MODEL_KEY_COUNTRY_CODE_LIST = "countryCodeList";
	public static final String RESOURCE_MODEL_KEY_SIGN_LIST = "signList";
	public static final String RESOURCE_MODEL_KEY_INCOTERMS_LIST = "incotermsList";
	public static final String RESOURCE_MODEL_KEY_OPPDRAGSTYPE_LIST = "oppdragstypeList";
	public static final String RESOURCE_MODEL_KEY_GEBYRCODES_LIST = "gebyrCodesList";
	public static final String RESOURCE_MODEL_KEY_DELSYSTEM_CODE_LIST = "delsystemList";
	public static final String RESOURCE_MODEL_KEY_PRODUCT_CODE_LIST = "produktList";
	public static final String RESOURCE_MODEL_KEY_ENHET_CODE_LIST = "enhetList";
	public static final String RESOURCE_MODEL_KEY_TRANSPORTTYPE_CODE_LIST = "transporttypeList";
	public static final String RESOURCE_MODEL_KEY_LOADUNLOAD_CODE_LIST = "loadUnloadList";
	public static final String RESOURCE_MODEL_KEY_MLAPKOD_CODE_LIST = "merkelappList";
	public static final String RESOURCE_MODEL_KEY_GENERIC_CODE_LIST = "genericList";
	public static final String RESOURCE_MODEL_KEY_RATE_CLASS_CODE_LIST = "rateClassCodeList";
	
	
	
	//Sig/Avd
	public static final String RESOURCE_MODEL_KEY_SIGNATURES_LIST = "signatureList";
	public static final String RESOURCE_MODEL_KEY_AVD_LIST = "avdList";
	
	
	
	//CODES
	public static final int ERROR_CODE = -1;
		
	
	
	public static final Integer CONSTANT_TOTAL_NUMBER_OF_ORDER_LINES = 4;
	/*N/A at the moment
	public static final String RESOURCE_MODEL_KEY_LANGUAGE_LIST = "languageList";
	public static final String RESOURCE_MODEL_KEY_HOURS_LIST = "hoursList";
	public static final String RESOURCE_MODEL_KEY_MINUTES_LIST = "minutesList";
	public static final String RESOURCE_MODEL_KEY_UOM_LIST = "uomList";
	*/
	
	

	//domain objects for model-view passing values
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	
	public static final String DOMAIN_CONTAINER = "container";
	public static final String DOMAIN_CONTAINER_VALIDATION_BACKEND = "containerValidationBackend";
	public static final String DOMAIN_CONTAINER_OPEN_ORDERS = "containerOpenOrders";
	public static final String DOMAIN_CONTAINER_CURRENT_ORDERS = "containerCurrentOrders";
	public static final String DOMAIN_CONTAINER_TRIP_LIST = "containerTripList";
	
	public static final String DOMAIN_RECORD_ORDER_TROR = "recordOrderTror";
	public static final String SESSION_RECORD_ORDER_TROR_LANDIMPORT = "recordOrderTrorLandImport";
	public static final String SESSION_RECORD_ORDER_TROR_LANDEXPORT = "recordOrderTrorLandExport";
	public static final String SESSION_RECORD_ORDER_TROR_LAND = "recordOrderTrorLand";
	public static final String SESSION_RECORD_ORDER_TROR_FLY = "recordOrderTrorFly";
	public static final String SESSION_RECORD_FLYFRAKTBREV_IMPORT_HEADER_TROR_FLY = "recordFlyfraktbrevImportHeaderTrorFly";
	public static final String SESSION_RECORD_FLYFRAKTBREV_IMPORT_HEADER_TROR_FLY_OWNSENDERNAME = "ownSenderName";
		
	
	public static final String SESSION_SUBSYSTEM_ORDER_TROR = "subSystem";
	public static final String SESSION_SUBSYSTEM_ORDER_TROR_LANDIMPORT = "tror_li";
	public static final String SESSION_SUBSYSTEM_ORDER_TROR_LANDEXPORT = "tror_le";
	public static final String SESSION_SUBSYSTEM_ORDER_TROR_FLYIMPORT = "tror_fi";
	public static final String SESSION_SUBSYSTEM_ORDER_TROR_FLYEXPORT = "tror_fe";
	//public static final String SESSION_SUBSYSTEM_MULTI_FRAKTBREV_TROR = "mulitFraktbrev";
	//public static final String SESSION_SUBSYSTEM_MULTI_FRAKTBREV_TROR_EXIST = "1";
	
	
	
	
	
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_LIST_CURRENT_ORDERS = "listCurrentOrders";
	public static final String DOMAIN_LIST_OPEN_ORDERS = "listOpenOrders";
	public static final String DOMAIN_MAX_WARNING_OPEN_ORDERS = "maxWarningOpenOrders";
	public static final String DOMAIN_MAX_WARNING_CURRENT_ORDERS = "maxWarningCurrentOrders";
	
	public static final String DOMAIN_RECORD_ITEM_CONTAINER_TOPIC = "recordItemContainerTopic";
	public static final String ITEM_LIST = "itemList";
	public static final String SESSION_LIST = "sessionList";
	public static final String SESSION_SEARCH_FILTER = "searchFilter";
	public static final String SESSION_CHILDWINDOW_FLAG = "cw";
	
	public static final String SESSION_SEARCH_FILTER_TROR = "searchFilterTror";
	public static final String DOMAIN_SEARCH_FILTER_TROR = "searchFilterTror";

	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	   
}
