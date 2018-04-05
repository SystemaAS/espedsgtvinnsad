/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.items.validation;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 */
public class JsonSadNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String tftanr = null;
	public void setTftanr(String value) {  this.tftanr = value; }
	public String getTftanr() { return this.tftanr;}
	
	private String tfkode = null;
	public void setTfkode(String value) {  this.tfkode = value; }
	public String getTfkode() { return this.tfkode;}
	
	private String exists = null;
	public void setExists(String value) {  this.exists = value; }
	public String getExists() { return this.exists;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}

}
