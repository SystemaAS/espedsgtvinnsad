/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.MaintMainGenericMapper;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainChildWindowKofastContainer;

/**
 * 
 * @author Fredrik Möller
 * @date Nov 22, 2016
 * 
 * 
 */
public class MaintMainKofastServiceImpl implements MaintMainKofastService {
	
	@Override
	public JsonMaintMainChildWindowKofastContainer getContainer(String utfPayload) {
		JsonMaintMainChildWindowKofastContainer container = null;
		try {
			MaintMainGenericMapper mapper = new MaintMainGenericMapper(new JsonMaintMainChildWindowKofastContainer());
			container = (JsonMaintMainChildWindowKofastContainer)mapper.getContainer(utfPayload);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return container;

	}

}
