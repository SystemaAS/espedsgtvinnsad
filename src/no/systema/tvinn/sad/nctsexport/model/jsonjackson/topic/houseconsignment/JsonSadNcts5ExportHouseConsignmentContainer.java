/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment;

import java.util.Collection;

import lombok.Data;



/**
 * @author oscardelatorre
 * @date Maj 25, 2023
 * 
 * 
 */
@Data
public class JsonSadNcts5ExportHouseConsignmentContainer {
	private String user = null;
	private String errMsg = null;
	private Collection<JsonSadNcts5ExportHouseConsignmentRecord> list;
	
	
	
	
}
