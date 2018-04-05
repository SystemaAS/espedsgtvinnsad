/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.validation;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public class JsonSadNctsExportSpecificTopicGuaranteeValidatorContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String thgft1 = null;
	public void setThgft1(String value) {  this.thgft1 = value; }
	public String getThgft1() { return this.thgft1;}
	
	private String thgadk = null;
	public void setThgadk(String value) {  this.thgadk = value; }
	public String getThgadk() { return this.thgadk;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}

}
