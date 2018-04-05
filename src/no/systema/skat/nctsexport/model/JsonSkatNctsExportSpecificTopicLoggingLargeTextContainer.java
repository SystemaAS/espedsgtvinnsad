/**
 * 
 */
package no.systema.skat.nctsexport.model;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Apr 14, 2014
 *
 */
public class JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String fmn = null;
	public void setFmn(String value) {  this.fmn = value; }
	public String getFmn() { return this.fmn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord> loggtext;
	public void setLoggtext(Collection<JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord> value){ this.loggtext = value; }
	public Collection<JsonSkatNctsExportSpecificTopicLoggingLargeTextRecord> getLoggtext(){ return loggtext; }
	
	
}
