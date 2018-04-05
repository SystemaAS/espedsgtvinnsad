/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.tullkontor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadTullkontorRecord {
	
	private String tkkode = null;
	public void setTkkode(String value){ this.tkkode = value;}
	public String getTkkode(){ return this.tkkode; }
	
	private String tktxtn = null;
	public void setTktxtn(String value){ this.tktxtn = value;}
	public String getTktxtn(){ return this.tktxtn; }
	
	private String tkavg = null;
	public void setTkavg(String value){ this.tkavg = value;}
	public String getTkavg(){ return this.tkavg; }
	
	private String tkank = null;
	public void setTkank(String value){ this.tkank = value;}
	public String getTkank(){ return this.tkank; }
	
	private String tktrs = null;
	public void setTktrs(String value){ this.tktrs = value;}
	public String getTktrs(){ return this.tktrs; }
	
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
