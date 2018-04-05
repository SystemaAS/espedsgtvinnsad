/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author Fredrik Möller
 * @date Sep 19, 2016
 * 
 * 
 */
public final class TvinnNctsMaintenanceExportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	/**
	 * TR030R
	 * 
	 * /syjservicestn/syjsTR030R.do
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR030R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTR030R.do";

	
	/**
	 * TR030R
	 * 
	 * 
	 *syjservicestn/syjsCustomerRecord.do
	 * 
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR030R_GET_CUSTOMER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsCustomerRecord.do";
	
	
	/**
	 * TR001R
	 * 
	 * /syjservicestn/syjsTR001R.do
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTR001R.do";
	
	/**
	 * TR001R
	 * 
	 * Kodetyper
	 * 
	 * /syjservicestn/syjsTR001R_Kodetyper.do
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_GET_KODE_TYPER_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTR001R_Kodetyper.do";

	
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	
	/**
	 * TR030R_U
	 * 
	 * /syjservicestn/syjsTR030R_U.do
	 * 
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR030R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTR030R_U.do";

	/**
	 * TR001R_U
	 * 
	 * /syjservicestn/syjsTR001R_U.do
	 * 
	 */
	static public final String TVINN_NCTS_MAINTENANCE_EXPORT_BASE_TR001R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsTR001R_U.do";

	
}
