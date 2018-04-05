/**
 * 
 */
package no.systema.tvinn.sad.admin.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oscardelatorre
 * @date Sep 21, 2014
 *
 */
public class JsonSadAdminAvggrunnlagListRecord extends JsonAbstractGrandFatherRecord {
	private String filnam = null;
	public void setFilnam(String value) {  this.filnam = value; }
	public String getFilnam() { return this.filnam;}
	
	/**
	 * 
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
