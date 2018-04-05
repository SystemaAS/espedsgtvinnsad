/**
 * 
 */
package no.systema.main.model.jsonjackson.general.notisblock;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
/**
 * 
 * @author oscardelatorre
 * @date Jan 17, 2015
 * 
 * 
 */
public class JsonNotisblockContainer {
	
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	private String avd = null;
	public void setAvd(String value){ this.avd = value;}
	public String getAvd(){ return this.avd; }
	
	private String opd = null;
	public void setOpd(String value){ this.opd = value;}
	public String getOpd(){ return this.opd; }
	
	private String ceilingLineNumber = null;
	public void setCeilingLineNumber(String value){ this.ceilingLineNumber = value;}
	public String getCeilingLineNumber(){ return this.ceilingLineNumber; }
	
	private String part = null;
	public void setPart(String value) {  this.part = value; }
	public String getPart() { return this.part;}
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	private Collection<JsonNotisblockRecord> freetextlist = null;
	public void setFreetextlist(Collection<JsonNotisblockRecord> value){ this.freetextlist = value;}
	public Collection<JsonNotisblockRecord> getFreetextlist(){ return this.freetextlist; }
	
	private Collection<JsonNotisblockRecord> freetxtUpdate = null;
	public void setFreetxtUpdate(Collection<JsonNotisblockRecord> value){ this.freetxtUpdate = value;}
	public Collection<JsonNotisblockRecord> getFreetxtUpdate(){ return this.freetxtUpdate; }
	
	private Collection<JsonNotisblockRecord> freetxtGet = null;
	public void setFreetxtGet(Collection<JsonNotisblockRecord> value){ this.freetxtGet = value;}
	public Collection<JsonNotisblockRecord> getFreetxtGet(){ return this.freetxtGet; }
	
	
	
	/**
	 * User for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
