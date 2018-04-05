package no.systema.z.main.maintenance.model;
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
 * @date Aug 22, 2016
 * 
 */
public class JsonMaintMainKodtaTellRecord extends JsonAbstractGrandFatherRecord   {
	
	//TELL table
	
	private String teavd = null;                                
	public void setTeavd (String value){ this.teavd = value;   }   
	public String getTeavd (){ return this.teavd;   }  
	
	private String teopdn = null;                                
	public void setTeopdn (String value){ this.teopdn = value;   }   
	public String getTeopdn (){ return this.teopdn;   }  
	
	private String teturn = null;                                
	public void setTeturn (String value){ this.teturn = value;   }   
	public String getTeturn (){ return this.teturn;   }  
	
	private String tetmin = null;                                
	public void setTetmin (String value){ this.tetmin = value;   }   
	public String getTetmin (){ return this.tetmin;   }  
	
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
