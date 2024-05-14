/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Maj, 2024
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZadmohfContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<ZadmohfRecord> list = null;
	public void setList(Collection<ZadmohfRecord> value){ this.list = value;}
	public Collection<ZadmohfRecord> getList(){ return this.list; }
	
	
}
