/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import java.util.Collection;

import lombok.Data;
/**
 * @author oscardelatorre
 * @date Sep , 2020
 */
public class JsonTvinnSadManifestPostalCodeContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ctype = null;
	public void setCtype(String value){ this.ctype = value;}
	public String getCtype(){ return this.ctype; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadManifestPostalCodeRecord> dtoList = null;
	public void setDtoList(Collection<JsonTvinnSadManifestPostalCodeRecord> value){ this.dtoList = value;}
	public Collection<JsonTvinnSadManifestPostalCodeRecord> getDtoList(){ return this.dtoList; }
	
}
