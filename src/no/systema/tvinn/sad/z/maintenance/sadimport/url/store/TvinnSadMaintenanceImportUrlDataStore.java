/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 20, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceImportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	//SYFT04R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT04R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT04R.do?user=OSCAR...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT04R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT04R.do";
	
	//SYFT18R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R.do?user=OSCAR&kundnr=1
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT18R.do";
	
	//SYFT19R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R.do?user=OSCAR&klikod=D
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT19R.do";
	
	//SAD001AR
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR.do?user=OSCAR&levenr=1
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR.do?user=OSCAR&levenr=1&varenr=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD001AR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD001AR.do";
	
	//SAD004R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R.do?user=OSCAR&slknr=1&slalfa=2042100
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R.do";
	
	//SAD006R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD006R.do?user=OSCAR...
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD006R.do?user=OSCAR...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD006R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD006R.do";
	
	
	//SAD999R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R.do?user=OSCAR&sdtnrf=98
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD999R.do";
	
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT04R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT04R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT04R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT04R_U.do";
	
	//SYFT10R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT10R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT10R_U.do";
	
	//SYFT18R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT18R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT18R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT18R_U.do";
	
	//SYFT19R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT19R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SYFT19R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT19R_U.do";
	
	//SAD001AR_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD001AR_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD001AR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD001AR_U.do";
	
	//SAD004R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD004R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R_U.do";
	
	//SAD006R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD004R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD006R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD006R_U.do";
	
	//SAD999R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD999R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_SAD999R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD999R_U.do";
	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_DROPDOWN_SYFT04R_GET_POSTNR_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT04R_guipostnr.do";
	
	
}
