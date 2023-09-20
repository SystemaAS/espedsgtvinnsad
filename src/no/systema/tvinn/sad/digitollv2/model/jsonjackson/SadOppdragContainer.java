/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Sep, 2023
 */
@Data
public class SadOppdragContainer {
	
	private String user = null;
	private String tur = null;
	private String errMsg = null;
	private Collection<SadOppdragRecord> orderList = null;
	
	
}
