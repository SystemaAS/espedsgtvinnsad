/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep, 2018
 */
public class JsonTvinnSadManifestContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadManifestRecord> list = null;
	public void setList(Collection<JsonTvinnSadManifestRecord> value){ this.list = value;}
	public Collection<JsonTvinnSadManifestRecord> getList(){ return this.list; }
}
