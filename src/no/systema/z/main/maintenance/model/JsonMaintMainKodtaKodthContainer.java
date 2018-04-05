/**
 * 
 */
package no.systema.z.main.maintenance.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Aug 19, 2016
 *
 */
public class JsonMaintMainKodtaKodthContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonMaintMainKodtaKodthRecord> list;
	public void setList(Collection<JsonMaintMainKodtaKodthRecord> value){ this.list = value; }
	public Collection<JsonMaintMainKodtaKodthRecord> getList(){ return list; }
	
	
}
