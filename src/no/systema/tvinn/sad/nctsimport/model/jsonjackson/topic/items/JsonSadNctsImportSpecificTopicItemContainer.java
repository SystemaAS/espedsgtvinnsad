/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.items;

import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Mar 6, 2015
 * 
 *
 */
public class JsonSadNctsImportSpecificTopicItemContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tvavd = null;
	public void setTvavd(String value) {  this.tvavd = value; }
	public String getTvavd() { return this.tvavd;}
	
	private String tvtdn = null;
	public void setTvtdn(String value) {  this.tvtdn = value; }
	public String getTvtdn() { return this.tvtdn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
		
	private Collection<JsonSadNctsImportSpecificTopicItemRecord> orderList;
	public void setOrderList(Collection<JsonSadNctsImportSpecificTopicItemRecord> value){ this.orderList = value; }
	public Collection<JsonSadNctsImportSpecificTopicItemRecord> getOrderList(){ return orderList; }
	
}
