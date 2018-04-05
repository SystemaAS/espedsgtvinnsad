/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.authorization;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadAuthorizationContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String usrAS400 = null;
	public void setUsrAS400(String value){ this.usrAS400 = value;}
	public String getUsrAS400(){ return this.usrAS400; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadAuthorizationRecord> sadtillatelse = null;
	public void setSadtillatelse(Collection<JsonTvinnSadAuthorizationRecord> value){ this.sadtillatelse = value;}
	public Collection<JsonTvinnSadAuthorizationRecord> getSadtillatelse(){ return this.sadtillatelse; }
	
}
