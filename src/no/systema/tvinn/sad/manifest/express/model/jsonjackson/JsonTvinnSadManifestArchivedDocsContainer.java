/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Oct 2020
 *
 */
public class JsonTvinnSadManifestArchivedDocsContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	                  
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonTvinnSadManifestArchivedDocsRecord> getdoc;
	public void setGetdoc(Collection<JsonTvinnSadManifestArchivedDocsRecord> value){ this.getdoc = value; }
	public Collection<JsonTvinnSadManifestArchivedDocsRecord> getGetdoc(){ return getdoc; }
	
	
}
