/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder;

import java.util.Collection;

/**
 * @author oscardelatorre
 * @date Okt 25, 2016
 *
 */
public class JsonMaintSadExportKodts9Container {
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	private Collection<JsonMaintSadExportKodts9Record> list;
	public void setList(Collection<JsonMaintSadExportKodts9Record> value){ this.list = value; }
	public Collection<JsonMaintSadExportKodts9Record> getList(){ return list; }
	
}
