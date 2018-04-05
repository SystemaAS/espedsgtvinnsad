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
 * @date May 23, 2016
 * 
 */
public class JsonMaintSadImportKodts5Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks5sta = null; 
	public void setKs5sta (String value){ this.ks5sta = value;   }   
	public String getKs5sta (){ return this.ks5sta;   }              

	private String ks5uni = null; 
	public void setKs5uni (String value){ this.ks5uni = value;   }   
	public String getKs5uni (){ return this.ks5uni;   }              

	private String ks5tln = null; 
	public void setKs5tln (String value){ this.ks5tln = value;   }   
	public String getKs5tln(){ return this.ks5tln;   }              

	private String ks5ftx = null;
	public void setKs5ftx (String value){ this.ks5ftx = value;   }   
	public String getKs5ftx (){ return this.ks5ftx;   }              

	
	
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
