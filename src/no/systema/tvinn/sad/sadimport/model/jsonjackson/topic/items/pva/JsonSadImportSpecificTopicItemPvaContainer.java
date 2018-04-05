/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.pva;
import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Jun 16, 2015
 * 
 */
public class JsonSadImportSpecificTopicItemPvaContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String svvnt = null;
	public void setSvvnt(String value) {  this.svvnt = value; }
	public String getSvvnt() {return this.svvnt;}

	private String svlk = null;
	public void setSvlk(String value) {  this.svlk = value; }
	public String getSvlk() {return this.svlk;}

	private String svpre = null;
	public void setSvpre(String value) {  this.svpre = value; }
	public String getSvpre() {return this.svpre;}
	  
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicItemPvaRecord> getPVA = null;
	public void setGetPVA(Collection<JsonSadImportSpecificTopicItemPvaRecord> value) {  this.getPVA = value; }
	public Collection<JsonSadImportSpecificTopicItemPvaRecord> getGetPVA() {return this.getPVA;}
	
	
}
