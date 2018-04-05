/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Mar 20, 2016
 * 
 * 
 */
public final class TvinnSadMaintenanceFellesUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//SYFT10R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R.do?user=OSCAR&klikod=D
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SYFT10R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT10R.do";
	
	//SAD010R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R.do?user=OSCAR&tatanr=2042100
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD010R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD010R.do";
	
	//SAD012R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R.do?user=OSCAR&klbkod=D
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD012R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R.do";
	
	//SAD062R
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR
	//ONE --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R.do?user=OSCAR&tariff=2042100
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD062R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD062R.do";
	
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	
	//SYFT10R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSYFT10R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SYFT10R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSYFT10R_U.do";
	
	//SAD010R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD010R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD010R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD010R_U.do";
	
	//SAD012R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD012R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD012R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD012R_U.do";
	

	//SAD062R_U
	//ALL --> http://gw.systema.no:8080/syjservicestn/syjsSAD062R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String TVINN_SAD_MAINTENANCE_FELLES_BASE_SAD062R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn/syjsSAD062R_U.do";
	
	
}
