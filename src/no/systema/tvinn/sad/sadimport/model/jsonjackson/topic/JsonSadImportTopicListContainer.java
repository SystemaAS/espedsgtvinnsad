/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 22, 2014
 *
 */
public class JsonSadImportTopicListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadImportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonSadImportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonSadImportTopicListRecord> getOrderList(){ return orderList; }
	
}
