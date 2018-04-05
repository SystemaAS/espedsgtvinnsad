/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic;

import java.util.Collection;


/**
 * @author oscardelatorre
 * @date Mar 6, 2015
 *
 */
public class JsonSadNctsImportTopicListContainer {
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
	
	private String godsNr = null;
	public void setGodsNr(String value) {  this.godsNr = value; }
	public String getGodsNr() { return this.godsNr;}
	
	private String mrn = null;
	public void setMrn(String value) {  this.mrn = value; }
	public String getMrn() { return this.mrn;}
	
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String forenklad = null;
	public void setForenklad(String value) {  this.forenklad = value; }
	public String getForenklad() { return this.forenklad;}
	
	private String ansNavn = null;
	public void setAnsNavn(String value) {  this.ansNavn = value; }
	public String getAnsNavn() { return this.ansNavn;}
	
	private String datumFr = null;
	public void setDatumFr(String value) {  this.datumFr = value; }
	public String getDatumFr() { return this.datumFr;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSadNctsImportTopicListRecord> orderList;
	public void setOrderList(Collection<JsonSadNctsImportTopicListRecord> value){ this.orderList = value; }
	public Collection<JsonSadNctsImportTopicListRecord> getOrderList(){ return orderList; }
	
}
