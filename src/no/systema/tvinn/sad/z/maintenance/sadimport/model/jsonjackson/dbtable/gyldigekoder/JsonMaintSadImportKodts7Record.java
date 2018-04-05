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
 * @date May 23, 2017
 * 
 */
public class JsonMaintSadImportKodts7Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks7sta = null; 
	public void setKs7sta (String value){ this.ks7sta = value;   }   
	public String getKs7sta (){ return this.ks7sta;   }              

	private String ks7uni = null; 
	public void setKs7uni (String value){ this.ks7uni = value;   }   
	public String getKs7uni (){ return this.ks7uni;   }              

	private String ks7vf = null; 
	public void setKs7vf (String value){ this.ks7vf = value;   }   
	public String getKs7vf(){ return this.ks7vf;   }              

	private String ks7ftx = null;
	public void setKs7ftx (String value){ this.ks7ftx = value;   }   
	public String getKs7ftx (){ return this.ks7ftx;   }              

	
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
