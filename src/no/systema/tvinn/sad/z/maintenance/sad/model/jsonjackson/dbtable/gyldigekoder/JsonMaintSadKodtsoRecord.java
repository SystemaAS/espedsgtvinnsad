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
public class JsonMaintSadKodtsoRecord extends JsonAbstractGrandFatherRecord  {
	
	private String ksokd = null; 
	public void setKsokd (String value){ this.ksokd = value;   }   
	public String getKsokd (){ return this.ksokd;   }              

	private String ksoft = null; 
	public void setKsoft (String value){ this.ksoft = value;   }   
	public String getKsoft (){ return this.ksoft;   } 
	
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
