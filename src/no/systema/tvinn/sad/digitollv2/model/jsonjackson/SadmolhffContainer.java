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
public class SadmolhffContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<SadmolhffRecord> list = null;
	public void setList(Collection<SadmolhffRecord> value){ this.list = value;}
	public Collection<SadmolhffRecord> getList(){ return this.list; }
	
	
}
