/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.service;

import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTransitKodeTypeContainer;
import no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable.JsonMaintNctsTrkodfContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 * 
 *
 */
public interface MaintNctsExportTrkodfService {
	public JsonMaintNctsTrkodfContainer getList(String utfPayload);
	public JsonMaintNctsTrkodfContainer doUpdate(String utfPayload);
	
	/**
	 * Retrieving TransitKoder for dropdown
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintNctsTransitKodeTypeContainer getTransitKoderList(String utfPayload);

}
