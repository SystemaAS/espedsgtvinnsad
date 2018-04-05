/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsExportTopicListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String mrn = null;
	public void setMrn(String value) {  this.mrn = value; }
	public String getMrn() { return this.mrn;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadNctsExportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonSadNctsExportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonSadNctsExportTopicListRecord> getOrderList(){ return orderList; }
	
}
