/**
 * 
 */
package no.systema.main.model.jsonjackson.general;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Feb 24, 2017
 *
 */
public class JsonFileUploadToArchiveValidationContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String wsdokn = null;
	public void setWsdokn(String value) {  this.wsdokn = value; }
	public String getWsdokn() { return this.wsdokn;}
	
	private String valids = null;
	public void setValids(String value) {  this.valids = value; }
	public String getValids() { return this.valids;}
	
	private String tmpdir = null;
	public void setTmpdir(String value) {  this.tmpdir = value; }
	public String getTmpdir() { return this.tmpdir;}
	
	private String wstur = null;
	public void setWstur(String value) {  this.wstur = value; }
	public String getWstur() { return this.wstur;}
	
	private String wsavd = null;
	public void setWsavd(String value) {  this.wsavd = value; }
	public String getWsavd() { return this.wsavd;}
	
	private String wsopd = null;
	public void setWsopd(String value) {  this.wsopd = value; }
	public String getWsopd() { return this.wsopd;}
	
	private String wstype = null;
	public void setWstype(String value) {  this.wstype = value; }
	public String getWstype() { return this.wstype;}
	
	private String wsalias = null;
	public void setWsalias(String value) {  this.wsalias = value; }
	public String getWsalias() { return this.wsalias;}
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
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
