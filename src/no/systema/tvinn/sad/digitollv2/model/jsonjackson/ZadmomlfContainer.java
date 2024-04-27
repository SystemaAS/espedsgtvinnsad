/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Oct, 2023
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZadmomlfContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<ZadmomlfRecord> list = null;
	public void setList(Collection<ZadmomlfRecord> value){ this.list = value;}
	public Collection<ZadmomlfRecord> getList(){ return this.list; }
	
	
}
