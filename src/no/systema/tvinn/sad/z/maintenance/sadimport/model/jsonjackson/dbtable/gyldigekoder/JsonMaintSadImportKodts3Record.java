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
 * @date May 20, 2016
 * 
 */
public class JsonMaintSadImportKodts3Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks3sta = null; 
	public void setKs3sta (String value){ this.ks3sta = value;   }   
	public String getKs3sta (){ return this.ks3sta;   }              

	private String ks3uni = null; 
	public void setKs3uni (String value){ this.ks3uni = value;   }   
	public String getKs3uni (){ return this.ks3uni;   }              

	private String ks3trt = null; 
	public void setKs3trt (String value){ this.ks3trt = value;   }   
	public String getKs3trt(){ return this.ks3trt;   }              

	private String ks3ftx = null;
	public void setKs3ftx (String value){ this.ks3ftx = value;   }   
	public String getKs3ftx (){ return this.ks3ftx;   }              

	
	
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
