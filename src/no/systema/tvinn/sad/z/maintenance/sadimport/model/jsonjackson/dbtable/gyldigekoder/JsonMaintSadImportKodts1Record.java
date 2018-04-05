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
public class JsonMaintSadImportKodts1Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks1sta = null; 
	public void setKs1sta (String value){ this.ks1sta = value;   }   
	public String getKs1sta (){ return this.ks1sta;   }              

	private String ks1uni = null; 
	public void setKs1uni (String value){ this.ks1uni = value;   }   
	public String getKs1uni (){ return this.ks1uni;   }              

	private String ks1typ = null; 
	public void setKs1typ (String value){ this.ks1typ = value;   }   
	public String getKs1typ(){ return this.ks1typ;   }              

	private String ks1ftx = null;
	public void setKs1ftx (String value){ this.ks1ftx = value;   }   
	public String getKs1ftx (){ return this.ks1ftx;   }              

	
	
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
