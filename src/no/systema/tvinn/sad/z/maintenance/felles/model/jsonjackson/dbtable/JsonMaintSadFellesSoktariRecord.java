/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 26, 2016
 *
 */
public class JsonMaintSadFellesSoktariRecord extends JsonAbstractGrandFatherRecord {
	
	private String tariff = null;                                
	public void setTariff (String value){ this.tariff = value;   }   
	public String getTariff (){ return this.tariff;   }  
	
	private String beskr1 = null; 
	public void setBeskr1 (String value){ this.beskr1 = value;   }   
	public String getBeskr1 (){ return this.beskr1;   }              
	
	private String fill1 = null; 
	public void setFill1 (String value){ this.fill1 = value;   }   
	public String getFill1 (){ return this.fill1;   }              
	
	private String beskr1Orig = null; 
	public void setBeskr1Orig (String value){ this.beskr1Orig = value;   }   
	public String getBeskr1Orig (){ return this.beskr1Orig;   }              

	
	
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
