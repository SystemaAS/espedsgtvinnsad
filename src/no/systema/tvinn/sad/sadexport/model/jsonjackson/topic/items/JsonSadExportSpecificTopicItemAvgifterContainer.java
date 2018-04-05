/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class JsonSadExportSpecificTopicItemAvgifterContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadExportSpecificTopicItemAvgifterRecord> statvaluecalc;
	public void setStatvaluecalc(Collection<JsonSadExportSpecificTopicItemAvgifterRecord> value){ this.statvaluecalc = value; }
	public Collection<JsonSadExportSpecificTopicItemAvgifterRecord> getStatvaluecalc(){ return statvaluecalc; }
	
	
	
}
