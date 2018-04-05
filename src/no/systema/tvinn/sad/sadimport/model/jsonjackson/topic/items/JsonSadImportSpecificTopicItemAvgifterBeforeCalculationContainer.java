/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items;

import java.util.Collection;
import java.util.Locale;
import java.text.NumberFormat;
import no.systema.main.util.NumberFormatterLocaleAware;

/**
 * @author oscardelatorre
 * @date Mar 17, 2015
 * 
 * 
 */
public class JsonSadImportSpecificTopicItemAvgifterBeforeCalculationContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String svvnt = null;
	public void setSvvnt(String value) {  this.svvnt = value; }
	public String getSvvnt() { return this.svvnt;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> getavgifter;
	public void setGetavgifter(Collection<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> value){ this.getavgifter = value; }
	public Collection<JsonSadImportSpecificTopicItemAvgifterBeforeCalculationRecord> getGetavgifter(){ return getavgifter; }
	
	
	
}
