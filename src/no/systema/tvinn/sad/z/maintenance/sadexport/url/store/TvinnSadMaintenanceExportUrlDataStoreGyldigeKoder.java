/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 20, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceExportUrlDataStoreGyldigeKoder {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	//SAD002_KODTS9R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS1R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS1R.do?user=OSCAR&ks1...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS9R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS9R.do";
	
	//SAD002_KODTSCR
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSCR.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSCR.do?user=OSCAR&ksckd...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSCR_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTSCR.do";
	
	//SAD002_KODTSER
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSER.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSER.do?user=OSCAR&ksefyl=...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSER_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTSER.do";

	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	
	//SAD002R_KODTS9R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTS1R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTS9R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTS9R_U.do";
	//SAD002_KODTSCR_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSCR_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSCR_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTSCR_U.do";
	//SAD002R_KODTSER_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD002_KODTSBR_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_EXPORT_BASE_SAD002_KODTSER_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD002_KODTSER_U.do";
	
	
}
