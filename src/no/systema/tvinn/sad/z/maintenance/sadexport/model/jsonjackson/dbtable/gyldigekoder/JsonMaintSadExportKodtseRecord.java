package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable.gyldigekoder;
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
public class JsonMaintSadExportKodtseRecord extends JsonAbstractGrandFatherRecord  {
	
	private String ksefyl = null; 
	public void setKsefyl (String value){ this.ksefyl = value;   }   
	public String getKsefyl (){ return this.ksefyl;   }              

	private String ksetxt = null; 
	public void setKsetxt (String value){ this.ksetxt = value;   }   
	public String getKsetxt (){ return this.ksetxt;   } 
	
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
