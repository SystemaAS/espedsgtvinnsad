/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Mar, 2025
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZadmoattfContainer {
	
	private String user = null;
	private String avd = null;
	private String errMsg = null;
	private Collection<ZadmoattfRecord> list = null;
	public void setList(Collection<ZadmoattfRecord> value){ this.list = value;}
	public Collection<ZadmoattfRecord> getList(){ return this.list; }
	
	
}
