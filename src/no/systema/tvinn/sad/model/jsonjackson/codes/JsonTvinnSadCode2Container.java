/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.codes;

import java.util.Collection;


/**
 * General Code Container for Tvinn general codes
 * 
 * 
 *
 * @author oscardelatorre
 * @date Feb 10, 2017
 *
 */
public class JsonTvinnSadCode2Container {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String type = null;
	public void setType(String value){ this.type = value;}
	public String getType(){ return this.type; }
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonTvinnSadCode2Record> arkivkodelist = null;
	public void setArkivkodelist(Collection<JsonTvinnSadCode2Record> value){ this.arkivkodelist = value;}
	public Collection<JsonTvinnSadCode2Record> getArkivkodelist(){ return this.arkivkodelist; }
	
}
