/**
 * 
 */
package no.systema.z.main.maintenance.mapper;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.z.main.maintenance.model.IJsonMaintMainContainer;

/**
 * This class maps an json payload on delivered {@link IJsonMaintMainContainer}
 * 
 * @author Fredrik Möller
 * @date Nov 22, 2016
 * 
 */
public class MaintMainGenericMapper extends ObjectMapperAbstractGrandFather {
	private String className = null;

	public MaintMainGenericMapper(IJsonMaintMainContainer container) {
		this.className = container.getClass().getName();
	}

	public Object getContainer(String utfPayload) throws Exception {
		
		Class<?> clazz = Class.forName(className);
		return super.getObjectMapper().readValue(utfPayload.getBytes(), clazz);
	}
}
