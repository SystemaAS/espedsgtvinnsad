/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.preference;
import java.util.Collection;


/**
 * 
 * @author oscardelatorre
 * @date Mar 16, 2015
 * 
 */
public class JsonSadImportSpecificTopicItemPreferenceContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String sidp = null;
	public void setSidp(String value) {  this.sidp = value; }
	public String getSidp() {return this.sidp;}
	
	private String sitst = null;
	public void setSitst(String value) {  this.sitst = value; }
	public String getSitst() {return this.sitst;}
	
	private String svvnt = null;
	public void setSvvnt(String value) {  this.svvnt = value; }
	public String getSvvnt() {return this.svvnt;}
	
	private String svlk = null;
	public void setSvlk(String value) {  this.svlk = value; }
	public String getSvlk() {return this.svlk;}
	
	private String svtn = null;
	public void setSvtn(String value) {  this.svtn = value; }
	public String getSvtn() {return this.svtn;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicItemPreferenceRecord> getpreferanse = null;
	public void setGetpreferanse(Collection<JsonSadImportSpecificTopicItemPreferenceRecord> value) {  this.getpreferanse = value; }
	public Collection<JsonSadImportSpecificTopicItemPreferenceRecord> getGetpreferanse() {return this.getpreferanse;}
	
	
}
