/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Nov, 2024
 */
public class EoriValidationContainer <T> {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<EoriValidationDto> list = null;
	public void setList(Collection<EoriValidationDto> value){ this.list = value;}
	public Collection<EoriValidationDto> getList(){ return this.list; }
	
	
}
