/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Aug, 2023
 */
@Data
public class GeneralUpdateRecord <T> {
	public static final int LIMIT_SIZE_OF_MAIN_LIST_OF_TRANSPORTS = 300;
	
	private String status = null;
	private String status2 = null;
	private String status3 = null;
	private Integer id = null;
	private Integer id2 = null;
	private Integer id3 = null;
	private Integer id4 = null;
	//Routing Movement fields
	private String rid = "";
	private String fdate = "";
	
	
	
}
