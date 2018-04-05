/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.util;


/**
 * All type of system constants for TVINN-SAD in general
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 *
 */
public final class TvinnSadMaintenanceConstants {
	
	
	//session constants
	public static final String ACTIVE_URL_RPG_TVINN_SAD_MAINTENANCE = "activeUrlRPG_TvinnSadMaintenance";
	public static final String ACTIVE_URL_RPG_UPDATE_TVINN_SAD_MAINTENANCE = "activeUrlRPGUpdate_TvinnSadMaintenance";
	public static final String ACTIVE_URL_RPG_FETCH_ITEM_TVINN_SAD_MAINTENANCE = "activeUrlRPGFetchItem_TvinnSadMaintenance"; //Ajax
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//actions
	public static final String EDIT_ACTION_ON_TOPIC = "editActionOnTopic";
	public static final String EDIT_ACTION_ON_TOPIC_ITEM = "editActionOnTopicItem";
	
	/**
	 * doFetch
	 */
	public static final String ACTION_FETCH = "doFetch";
	/**
	 * doUpdate
	 */
	public static final String ACTION_UPDATE = "doUpdate";
	/**
	 * doCreate
	 */
	public static final String ACTION_CREATE = "doCreate";
	/**
	 * doDelete
	 */
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
	/**
	 * list
	 * 
	 */
	public static final String DOMAIN_LIST = "list";
	public static final String SESSION_LIST = "sessionList";

	public static final String DOMAIN_LIST_SIZE = "listSize";
	/**
	 * searchFilterNctsExportKoderegister
	 * 
	 */
	public static final String DOMAIN_SEARCH_FILTER_NCTS_EXPORT_KODEREGISTER = "searchFilterNctsExportKoderegister";
	
	//aspects in view (sucha as errors, logs, other
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	

	   
}
