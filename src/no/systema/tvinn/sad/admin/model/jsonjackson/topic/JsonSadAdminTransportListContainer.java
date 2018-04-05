/**
 * 
 */
package no.systema.tvinn.sad.admin.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Maj 26, 2014
 *
 */
public class JsonSadAdminTransportListContainer {
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
	
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}

	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}

	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadAdminTransportListRecord> orderList;
	public void setOrderList(Collection<JsonSadAdminTransportListRecord> value){ this.orderList = value; }
	public Collection<JsonSadAdminTransportListRecord> getOrderList(){ return orderList; }
	
}
