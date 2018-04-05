/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadNctsImportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonSadNctsImportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonSadNctsImportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
