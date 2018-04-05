package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable.gyldigekoder;
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
public class JsonMaintSadImportKodtsbRecord extends JsonAbstractGrandFatherRecord  {
	
	private String ksbkd = null; 
	public void setKsbkd (String value){ this.ksbkd = value;   }   
	public String getKsbkd (){ return this.ksbkd;   }              

	private String ksbft = null; 
	public void setKsbft (String value){ this.ksbft = value;   }   
	public String getKsbft (){ return this.ksbft;   } 
	
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
