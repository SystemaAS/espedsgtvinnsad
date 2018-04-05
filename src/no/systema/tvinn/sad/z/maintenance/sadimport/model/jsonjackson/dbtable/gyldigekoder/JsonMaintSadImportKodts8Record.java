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
 * @date May 13, 2016
 * 
 */
public class JsonMaintSadImportKodts8Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks8avg = null;                                
	public void setKs8avg (String value){ this.ks8avg = value;   }   
	public String getKs8avg (){ return this.ks8avg;   }  
	
	private String ks8skv = null; 
	public void setKs8skv (String value){ this.ks8skv = value;   }   
	public String getKs8skv (){ return this.ks8skv;   }              

	private String ks8ftx = null; 
	public void setKs8ftx (String value){ this.ks8ftx = value;   }   
	public String getKs8ftx(){ return this.ks8ftx;   }              

	private String ks8sat = null;
	public void setKs8sat (String value){ this.ks8sat = value;   }   
	public String getKs8sat (){ return this.ks8sat;   }              

	private String ks8sty = null; 
	public void setKs8sty (String value){ this.ks8sty = value;   }   
	public String getKs8sty (){ return this.ks8sty;   }              

	
	private String ore = null; 
	public void setOre (String value){ this.ore = value;   }   
	public String getOre(){ return this.ore;   }              

	private String mil = null; 
	public void setMil (String value){ this.mil = value;   }   
	public String getMil(){ return this.mil;   }              

	
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
