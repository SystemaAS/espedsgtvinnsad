/**
 * 
 */
package no.systema.tvinn.sad.admin.model.jsonjackson.topic;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Sep 21, 2014
 *
 */
public class JsonSadAdminAvggrunnlagListContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	
	private String siknk = null;
	public void setSiknk(String value) {  this.siknk = value; }
	public String getSiknk() { return this.siknk;}
	
	private String datof = null;
	public void setDatof(String value) {  this.datof = value; }
	public String getDatof() { return this.datof;}
	
	private String datot = null;
	public void setDatot(String value) {  this.datot = value; }
	public String getDatot() { return this.datot;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonSadAdminAvggrunnlagListRecord> orderMVAreport;
	public void setOrderMVAreport(Collection<JsonSadAdminAvggrunnlagListRecord> value){ this.orderMVAreport = value; }
	public Collection<JsonSadAdminAvggrunnlagListRecord> getOrderMVAreport(){ return orderMVAreport; }
	
}
