/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * 
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 */
public class JsonTvinnSadSignatureContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadSignatureRecord> signaturer = null;
	public void setSignaturer(Collection<JsonTvinnSadSignatureRecord> value){ this.signaturer = value;}
	public Collection<JsonTvinnSadSignatureRecord> getSignaturer(){ return this.signaturer; }
}
