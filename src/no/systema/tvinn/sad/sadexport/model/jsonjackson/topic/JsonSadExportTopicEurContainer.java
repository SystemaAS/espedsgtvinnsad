/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import java.util.Collection;

import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Feb, 2019
 *
 */
public class JsonSadExportTopicEurContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	/**
	 * 
	 */
	private Collection<JsonSadExportTopicEurRecord> geteur1;
	public void setGeteur1(Collection<JsonSadExportTopicEurRecord> value){ this.geteur1 = value; }
	public Collection<JsonSadExportTopicEurRecord> getGeteur1(){ return geteur1; }
	
	
}
