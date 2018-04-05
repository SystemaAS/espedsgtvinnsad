/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * Populating Drop down 
 * 
 * 
 * @author Fredrik Möller
 * @date Sep 7, 2016
 *
 */
public class JsonMaintSadExportKodts6Record extends JsonAbstractGrandFatherRecord {

	private String ks6pre = null;                            
	public void setKs6pre (String value){ this.ks6pre = value;   }   
	public String getKs6pre (){ return this.ks6pre;   }  

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
