/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author Fredrik Möller
 * @date Aug 9, 2016
 *
 */
public class JsonMaintSadExportTvineRecord extends JsonAbstractGrandFatherRecord {

	private String e9705 = null;
	public void setE9705(String value) {  this.e9705 = value; }
	public String getE9705() { return this.e9705;}
	
	private String e4440 = null;
	public void setE4440(String value) {  this.e4440 = value; }
	public String getE4440() { return this.e4440;}
		
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
