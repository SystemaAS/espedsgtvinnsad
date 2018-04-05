/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 *
 */
public class JsonSadImportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonSadImportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonSadImportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
