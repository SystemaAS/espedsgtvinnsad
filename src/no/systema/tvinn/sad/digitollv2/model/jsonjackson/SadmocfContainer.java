/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Dec, 2023
 */
@Data
public class SadmocfContainer <T> {
	
	private String user = null;
	private String errMsg = null;
	
	private Collection<SadmocfRecord> list = null;
	
	
}
