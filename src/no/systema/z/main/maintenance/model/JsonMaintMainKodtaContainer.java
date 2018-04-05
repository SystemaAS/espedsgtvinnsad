/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Aug 3, 2016
 *
 */
public class JsonMaintMainKodtaContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainKodtaRecord> list;
	public void setList(Collection<JsonMaintMainKodtaRecord> value){ this.list = value; }
	public Collection<JsonMaintMainKodtaRecord> getList(){ return list; }
	
}
