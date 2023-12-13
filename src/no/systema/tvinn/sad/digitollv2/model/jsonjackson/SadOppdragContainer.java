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
	private String bil = null;
	private String dato = null;
	private String errMsg = null;
	private Collection<SadOppdragRecord> orderList = null;
	private Collection<SadOppdragRecord> list = null;
	private Collection<SadOppdragRecord> sadimpList = null;
	
	
}
