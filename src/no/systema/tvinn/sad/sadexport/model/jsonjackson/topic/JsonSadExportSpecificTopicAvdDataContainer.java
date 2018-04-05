/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;
import java.util.Collection;
/**
 * 
 * @author oscardelatorre
 * @date Nov 27, 2014
 * 
 */
public class JsonSadExportSpecificTopicAvdDataContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() {return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() {return this.avd;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() {return this.errMsg;}
	
	private Collection<JsonSadExportSpecificTopicAvdDataRecord> getGetdepinf = null;
	public void setGetdepinf(Collection<JsonSadExportSpecificTopicAvdDataRecord> value) {  this.getGetdepinf = value; }
	public Collection<JsonSadExportSpecificTopicAvdDataRecord> getGetdepinf() {return this.getGetdepinf;}
	
	
}
