/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Maj 2, 2014
 *
 */
public class JsonSadExportTopicListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sg = null;
	public void setSg(String value) {  this.sg = value; }
	public String getSg() { return this.sg;}
	
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadExportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonSadExportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonSadExportTopicListRecord> getOrderList(){ return orderList; }
	
}
