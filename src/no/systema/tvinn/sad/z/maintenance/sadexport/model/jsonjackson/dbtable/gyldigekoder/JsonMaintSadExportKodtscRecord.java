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
 * @date Okt 26, 2016
 * 
 */
public class JsonMaintSadExportKodtscRecord extends JsonAbstractGrandFatherRecord  {

	private String ksckd = ""; 
	public String getKsckdPropertyName (){ return "ksckd"; }
	public void setKsckd (String value){ this.ksckd = value;   }   
	public String getKsckd (){ return this.ksckd;   }              

	private String kscft = ""; 
	public String getKscftPropertyName (){ return "kscft"; }
	public void setKscft (String value){ this.kscft = value;   }   
	public String getKscft (){ return this.kscft;   }              

	/**
	 * 
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}
