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
public class JsonMaintSadImportKodts4Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks4sta = null; 
	public void setKs4sta (String value){ this.ks4sta = value;   }   
	public String getKs4sta (){ return this.ks4sta;   }              

	private String ks4uni = null; 
	public void setKs4uni (String value){ this.ks4uni = value;   }   
	public String getKs4uni (){ return this.ks4uni;   }              

	private String ks4trm = null; 
	public void setKs4trm (String value){ this.ks4trm = value;   }   
	public String getKs4trm(){ return this.ks4trm;   }              

	private String ks4ftx = null;
	public void setKs4ftx (String value){ this.ks4ftx = value;   }   
	public String getKs4ftx (){ return this.ks4ftx;   }              

	
	
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
