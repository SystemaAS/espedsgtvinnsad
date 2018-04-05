/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 *
 */
public class JsonSadExportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadExportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonSadExportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonSadExportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
