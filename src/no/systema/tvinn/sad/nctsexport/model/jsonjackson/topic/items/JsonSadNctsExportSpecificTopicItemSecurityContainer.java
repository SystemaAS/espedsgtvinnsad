/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsExportSpecificTopicItemSecurityContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String lin = null;
	public void setLin(String value) {  this.lin = value; }
	public String getLin() { return this.lin;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonSadNctsExportSpecificTopicItemSecurityRecord> securityline;
	public void setSecurityline(Collection<JsonSadNctsExportSpecificTopicItemSecurityRecord> value){ this.securityline = value; }
	public Collection<JsonSadNctsExportSpecificTopicItemSecurityRecord> getSecurityline(){ return securityline; }
	
}
