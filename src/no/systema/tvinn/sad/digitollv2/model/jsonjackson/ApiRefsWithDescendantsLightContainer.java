/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Maj, 2024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRefsWithDescendantsLightContainer {
	
	private String user = null;
	private String errMsg = null;
	private ApiMrnStatusWithDescendantsLightRecordDto object = null;
	
	
	
}
