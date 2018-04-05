/**
 * 
 */
package no.systema.main.mapper.jsonjackson.general;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper; 

/**
 * All mapper subclasses should implement this method in order to inherit jacskon api ojects
 * 
 * @author oscardelatorre
 * @date Nov 7, 2017
 * 
 */
public abstract class ObjectMapperAbstractGrandFather {
	
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}
	
	
}
