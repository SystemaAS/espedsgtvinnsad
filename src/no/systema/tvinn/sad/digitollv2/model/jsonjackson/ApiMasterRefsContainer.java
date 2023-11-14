/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Nov, 2023
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiMasterRefsContainer {
	
	private String user = null;
	private String mrn = null;
	private String errMsg = null;
	
	private Collection<ApiMasterRefsRecord> list = null;
	public void setList(Collection<ApiMasterRefsRecord> value){ this.list = value;}
	public Collection<ApiMasterRefsRecord> getList(){ return this.list; }
	
	
}
