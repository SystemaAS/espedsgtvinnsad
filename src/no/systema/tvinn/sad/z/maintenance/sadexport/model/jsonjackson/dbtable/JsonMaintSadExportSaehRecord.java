/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Fredrik Möller
 * @date Aug 30, 2016
 *
 */
public class JsonMaintSadExportSaehRecord extends JsonAbstractGrandFatherRecord {
	
	private String seavd = null;                                
	public void setSeavd (String value){ this.seavd = value;   }   
	public String getSeavd (){ return this.seavd;   }  
	
	private String setdn = null;
	public void setSetdn (String value){ this.setdn = value;   }   
	public String getSetdn (){ return this.setdn;   }              

	private String senas = null;
	public void setSenas (String value){ this.senas = value;   }   
	public String getSenas (){ return this.senas;   }              

	private String setll = null;
	public void setSetll (String value){ this.setll = value;   }   
	public String getSetll (){ return this.setll;   }              

	private String setle = null; 
	public void setSetle (String value){ this.setle = value;   }   
	public String getSetle (){ return this.setle;   }              

	private String sedtg = null; 
	public void setSedtg (String value){ this.sedtg = value;   }   
	public String getSedtg (){ return this.sedtg;   }              

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
