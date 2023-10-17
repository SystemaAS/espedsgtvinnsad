/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Oct, 2023
 */
@Data
public class SadmoafContainer {
	
	private String user = null;
	private String errMsg = null;
	private Collection<SadmoafRecord> list = null;
	
	
}
