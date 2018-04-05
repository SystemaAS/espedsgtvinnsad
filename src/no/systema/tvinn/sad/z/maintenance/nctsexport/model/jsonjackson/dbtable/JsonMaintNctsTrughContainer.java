/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable;

import java.util.Collection;

/**
 * @author Fredrik Möller
 * @date Sep 19, 2016
 *
 */
public class JsonMaintNctsTrughContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintNctsTrughRecord> list;
	public void setList(Collection<JsonMaintNctsTrughRecord> value){ this.list = value; }
	public Collection<JsonMaintNctsTrughRecord> getList(){ return list; }
	
}
