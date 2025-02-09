/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Feb, 2025
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmodoclgContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<SadmodoclgRecord> list = null;
	public void setList(Collection<SadmodoclgRecord> value){ this.list = value;}
	public Collection<SadmodoclgRecord> getList(){ return this.list; }
	
	
}
