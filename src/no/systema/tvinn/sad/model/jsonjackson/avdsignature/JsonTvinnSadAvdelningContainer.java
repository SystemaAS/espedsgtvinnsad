/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.avdsignature;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadAvdelningContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String ie = null;
	public void setIe(String value){ this.ie = value;}
	public String getIe(){ return this.ie; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadAvdelningRecord> avdelningar = null;
	public void setAvdelningar(Collection<JsonTvinnSadAvdelningRecord> value){ this.avdelningar = value;}
	public Collection<JsonTvinnSadAvdelningRecord> getAvdelningar(){ return this.avdelningar; }
}
