/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Nov 17, 2015
 */
public class JsonSadAutoControlErrorContainer {
	private String user = null;
	public void setUser(String value){ this.user = value;}
	public String getUser(){ return this.user; }
	
	//Export
	private String svev_syav = null;
	public void setSvev_syav(String value){ this.svev_syav = value;}
	public String getSvev_syav(){ return this.svev_syav; }
	
	private String svev_syop = null;
	public void setSvev_syop(String value){ this.svev_syop = value;}
	public String getSvev_syop(){ return this.svev_syop; }
	
	private String svev_syli = null;
	public void setSvev_syli(String value){ this.svev_syli = value;}
	public String getSvev_syli(){ return this.svev_syli; }
	
	//Import
	private String svavd = null;
	public void setSvavd(String value){ this.svavd = value;}
	public String getSvavd(){ return this.svavd; }
	
	private String svtdn = null;
	public void setSvtdn(String value){ this.svtdn = value;}
	public String getSvtdn(){ return this.svtdn; }
	
	private String svli = null;
	public void setSvli(String value){ this.svli = value;}
	public String getSvli(){ return this.svli; }
	
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg = value;}
	public String getErrMsg(){ return this.errMsg; }
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
