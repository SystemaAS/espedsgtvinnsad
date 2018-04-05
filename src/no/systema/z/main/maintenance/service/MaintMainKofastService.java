/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainChildWindowKofastContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 22, 2016
 * 
 *
 */
public interface MaintMainKofastService {
	/**
	 * Get JsonMaintMainChildWindowKofastContainer
	 * 
	 * @param utfPayload
	 * @return JsonMaintMainChildWindowsKodeContainer
	 */
	public JsonMaintMainChildWindowKofastContainer getContainer(String utfPayload);
	
}
