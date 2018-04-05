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
 * @date May 25, 2016
 * 
 */
public class JsonMaintSadImportKodts2Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks2sta = null; 
	public void setKs2sta (String value){ this.ks2sta = value;   }   
	public String getKs2sta (){ return this.ks2sta;   }              

	private String ks2uni = null; 
	public void setKs2uni (String value){ this.ks2uni = value;   }   
	public String getKs2uni (){ return this.ks2uni;   }              

	private String ks2lk = null; 
	public void setKs2lk (String value){ this.ks2lk = value;   }   
	public String getKs2lk(){ return this.ks2lk;   }              

	private String ks2ftx = null;
	public void setKs2ftx (String value){ this.ks2ftx = value;   }   
	public String getKs2ftx (){ return this.ks2ftx;   }              

	private String ks2nas = null;
	public void setKs2nas (String value){ this.ks2nas = value;   }   
	public String getKs2nas (){ return this.ks2nas;   }              

	private String ks2mo = null;
	public void setKs2mo (String value){ this.ks2mo = value;   }   
	public String getKs2mo (){ return this.ks2mo;   }              

	private String ks2pre = null;
	public void setKs2pre (String value){ this.ks2pre = value;   }   
	public String getKs2pre (){ return this.ks2pre;   }              

	
	
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
