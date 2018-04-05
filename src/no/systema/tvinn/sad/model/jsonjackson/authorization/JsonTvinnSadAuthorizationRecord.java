/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.authorization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 * 
 */
public class JsonTvinnSadAuthorizationRecord extends JsonAbstractGrandFatherRecord  {
	
	private String ok = null;
	public void setOk(String value){ this.ok = value;}
	public String getOk(){ return this.ok; }
	
	private String sign = null;
	public void setSign(String value){ this.sign = value;}
	public String getSign(){ return this.sign; }
	
	
	/**
	 * Used for java reflection in other classes
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
