/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author Fredrik Möller
 * @date Aug 10, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceExportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	

	/**
	 * TVI99D
	 * 
	 * /syjservicestn/syjsTVI99D.do
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTVI99D.do";
	
	/**
	 * SAD015
	 * 
	 * /syjservicestn/syjsSAD015.do
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD015_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD015.do";


	/**
	 * SAD024
	 * 
	 * /syjservicestn/syjsSAD024.do
	 */	
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD024_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD024.do";

	
	/**
	 * SAD004
	 * 
	 * /syjservicestn/syjsSAD004R.do
	 * 
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD004R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R.do";

	/**
	 * KODTS6
	 * 
	 * /syjservicestn/syjsSAD002_KODTS6R.do
	 * 
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS6R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS6R.do";
	

	/**
	 * KODTSE
	 * 
	 * /syjservicestn/syjsSAD002_KODTSER.do
	 * 
	 * 
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSER_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTSER.do";

	
	
	/**
	 * SAD999 (table: sadsd)
	 * 
	 * /syjservicestn/syjservicestn/syjsSAD999R.do
	 * 
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD999_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD999R.do";
					
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	
	/**
	 * TVI99D_U
	 * 
	 * /syjservicestn/syjsTVI99D_U.do
	 * 
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_TVI99D_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTVI99D_U.do";

	/**
	 * SAD015_U
	 * 
	 * /syjservicestn/syjsSAD015_U.do
	 * 
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD015_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD015_U.do";
	
	/**
	 * SAD024_U
	 * 
	 * /syjservicestn/syjsSAD024_U.do
	 * 
	 */
	static public final String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD024_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD024_U.do";

	/**
	 * SAD004R_U
	 * 
	 * /syjservicestn/syjsSAD004R_U.do
	 * 
	 */
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD004R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD004R_U.do";

	
	//--------------------------
	//[3] GUI lists drop downs
	//--------------------------
	static public String TVINN_SAD_MAINTENANCE_IMPORT_BASE_DROPDOWN_SYFT04R_GET_POSTNR_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT04R_guipostnr.do";
	
	
}
