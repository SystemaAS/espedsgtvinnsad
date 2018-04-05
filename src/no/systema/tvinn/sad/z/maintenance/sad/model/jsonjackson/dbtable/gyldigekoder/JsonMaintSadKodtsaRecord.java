package no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable.gyldigekoder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date May 24, 2017
 * 
 */
public class JsonMaintSadKodtsaRecord extends JsonAbstractGrandFatherRecord  {
	
	private String ksakd = null; 
	public void setKsakd (String value){ this.ksakd = value;   }   
	public String getKsakd (){ return this.ksakd;   }              

	private String ksaft = null; 
	public void setKsaft (String value){ this.ksaft = value;   }   
	public String getKsaft (){ return this.ksaft;   }              

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
