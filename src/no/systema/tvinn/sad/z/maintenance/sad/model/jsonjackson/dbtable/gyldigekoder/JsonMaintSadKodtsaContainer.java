/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date May 24, 2016
 *
 */
public class JsonMaintSadKodtsaContainer {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadKodtsaRecord> list;
	public void setList(Collection<JsonMaintSadKodtsaRecord> value){ this.list = value; }
	public Collection<JsonMaintSadKodtsaRecord> getList(){ return list; }
	
}
