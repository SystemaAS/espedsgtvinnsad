/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Sep, 2024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmobuplogContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<SadmobuplogRecord> list = null;
	public void setList(Collection<SadmobuplogRecord> value){ this.list = value;}
	public Collection<SadmobuplogRecord> getList(){ return this.list; }
	
	
}
