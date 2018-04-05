/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;
import java.util.Collection;

/**
 * 
 * @author oscardelatorre
 * @date Sep 11, 2014
 * 
 */
public class JsonSadImportSpecificTopicAvdDataContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonSadImportSpecificTopicRecord> getGetdepinf = null;
	public void setGetdepinf(Collection<JsonSadImportSpecificTopicRecord> value) {  this.getGetdepinf = value; }
	public Collection<JsonSadImportSpecificTopicRecord> getGetdepinf() {return this.getGetdepinf;}
	
	
}
