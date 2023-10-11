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
public class SadTurContainer {
	
	private String user = null;
	private String wsstur = null;
	private String errMsg = null;
	private Collection<SadTurRecord> wrktriplist = null;
	
	
}
