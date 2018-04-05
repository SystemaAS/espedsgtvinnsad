/**
 * 
 */
package no.systema.main.util;

import java.util.ResourceBundle;

import no.systema.main.util.AppResources;
import no.systema.main.util.ApplicationPropertiesUtil;

/**
 * All type of system constants
 * @author oscardelatorre
 *
 */
public final class AppConstants {
	//static final ResourceBundle resources = AppResources.getBundle();
	
	//main http root for tds CGI-calls (it varies depending on the customer installation. Please change accordingly in your application.properties file)
	public static final String LOG4J_LOGGER_LEVEL = ApplicationPropertiesUtil.getProperty("log4j.logger.level");
	public static final String HTTP_ROOT_CGI =  ApplicationPropertiesUtil.getProperty("http.as400.root.cgi");  //resources.getString("http.as400.root.cgi");
	public static final String HTTP_ROOT_SERVLET_JSERVICES = ApplicationPropertiesUtil.getProperty("http.syjservices.root.servlet");
	public static final String HTTP_ROOT_JQUERY_DOCS_ROOT = ApplicationPropertiesUtil.getProperty("http.as400.root.jquery.docs.host");
	public static final String HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_1 = ApplicationPropertiesUtil.getProperty("https.toldtarif.skat.download.flatfile.1");
	public static final String HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_2 = ApplicationPropertiesUtil.getProperty("https.toldtarif.skat.download.flatfile.2");
	public static final String HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_3 = ApplicationPropertiesUtil.getProperty("https.toldtarif.skat.download.flatfile.3");
	public static final String HTTPS_SKAT_DOWNLOAD_TOLDTARIF_FLATFILE_4 = ApplicationPropertiesUtil.getProperty("https.toldtarif.skat.download.flatfile.4");
	public static final String HTTPS_SKAT_DOWNLOAD_TOLDTARIF_TARGETDIR = ApplicationPropertiesUtil.getProperty("https.toldtarif.skat.download.target.dir");
	//version
	public static final String VERSION_ESPSEDSG = ApplicationPropertiesUtil.getProperty("version.espedsg");
	public static final String VERSION_SPRING = ApplicationPropertiesUtil.getProperty("version.spring");
	//http protocol
	public static final String HTTP_PROTOCOL = ApplicationPropertiesUtil.getProperty("http.protocol");
	//css files
	public static final String CSS_ESPEDSG = ApplicationPropertiesUtil.getProperty("css.espedsg");
	public static final String CSS_ESPEDSG_MAINTENANCE = ApplicationPropertiesUtil.getProperty("css.espedsg.maintenance");
	//login firma code required
	public static final String LOGIN_FIRMA_CODE_REQUIRED = ApplicationPropertiesUtil.getProperty("login.firma.code.required");
	
	//session constants
	public static final String SYSTEMA_WEB_USER_KEY = "user";
	public static final String ACTIVE_URL_RPG = "activeUrlRPG";
	public static final String ACTIVE_URL_RPG_INITVALUE = "=)";
	
	//url
	public static final String URL_CHAR_DELIMETER_FOR_URL_WITH_HTML_REQUEST_GET = "?";
	public static final String URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST = "&"; //Used for GET and POST
	
	//base path for resource files (for drop-downs or other convenient files
	public static final String DOMAIN_MODEL = "model";
	public static final String DOMAIN_RECORD = "record";
	public static final String DOMAIN_LIST = "list";
	public static final String DOMAIN_CONTAINER_PARENT = "containerParent";
	
	
	//aspects in view (such as errors, success, logs, other)
	public static final String ASPECT_ERROR_MESSAGE = "errorMessage";
	public static final String ASPECT_SUCCESS_MESSAGE = "successMessage";
	public static final String ASPECT_ERROR_META_INFO = "errorInfo";
	//public static final String ASPECT_ERROR_MESSAGE_PASSWORD_TOO_OLD = "errorMessagePasswordToOld";
	
	
	
	//html content types
	public static final String HTML_CONTENTTYPE_TEXTPLAIN = "text/plain";
	public static final String HTML_CONTENTTYPE_TEXTHTML = "text/html";
	public static final String HTML_CONTENTTYPE_TEXTXML = "text/xml";
	public static final String HTML_CONTENTTYPE_PDF = "application/pdf";
	public static final String HTML_CONTENTTYPE_TIFF = "image/tiff";
	public static final String HTML_CONTENTTYPE_JPEG = "image/jpeg";
	public static final String HTML_CONTENTTYPE_WORD = "application/msword";
	public static final String HTML_CONTENTTYPE_EXCEL = "application/ms-excel";
	public static final String HTML_CONTENTTYPE_ZIP = "application/zip";
	
	public static final String DOCUMENTTYPE_TXT = "txt";
	public static final String DOCUMENTTYPE_LOG = "txt";
	public static final String DOCUMENTTYPE_PDF = "pdf";
	public static final String DOCUMENTTYPE_TIFF = "tiff";
	public static final String DOCUMENTTYPE_TIF = "tif";
	public static final String DOCUMENTTYPE_JPEG = "jpeg";
	public static final String DOCUMENTTYPE_JPG = "jpg";
	public static final String DOCUMENTTYPE_DOC = "doc";
	public static final String DOCUMENTTYPE_XLS = "xls";
	public static final String DOCUMENTTYPE_XLSX = "xlsx";
	public static final String DOCUMENTTYPE_CSV = "csv";
	
	
	//TDS-Login
	public static final String TDS_AUTHORIZATION_NUMBER_OF_TRIES = "tdsAuthorizationNumberOfTries";
		
	//-------------------------------------
	//TDS session specific (PKI-Signering)
	//-------------------------------------
	public static final String TDS_AUTHENTICATION_NUMBER_OF_TRIES = "tdsAuthenticationNumberOfTries";
	//Track time elapsed = 120 seconds = 120000 milliseconds (required)
	public static final String TDS_TIMESTAMP_BEFORE_SUBMITTING_VALID_SMSCODE = "tdsTimeStampBeforeSubmittingValidSmsCode"; 
	//Signering time out requirement = 30 minutes = 30x60 = 1800 seconds = 1800,000 milliseconds
	public static final String TDS_TIMESTAMP_AFTER_SUBMITTING_VALID_SMSCODE = "tdsTimeStampAfterSubmittingValidSmsCode"; //30 minutes (required)
	
	//PDF link
	//http://gw.systema.no/nas/arc/SI201300010000130ytOhsfRbjJ.pdf
	
	//General
	public static final String RESOURCE_FILES_PATH = "/WEB-INF/resources/files/";
	
	   
}
