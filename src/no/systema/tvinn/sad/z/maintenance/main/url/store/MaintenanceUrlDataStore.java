/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jun 7, 2016
 * 
 * 
 */
public final class MaintenanceUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	
	//SYFT02R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT02R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT02R.do?user=OSCAR&kvakod=USD
	static public String MAINTENANCE_BASE_SYFT02R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT02R.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//SYFT02R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT02R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_SYFT02R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT02R_U.do";
	
	
	
}
