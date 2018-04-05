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
public class JsonMaintSadImportKodts6Record extends JsonAbstractGrandFatherRecord  {
	
	private String ks6sta = null; 
	public void setKs6sta (String value){ this.ks6sta = value;   }   
	public String getKs6sta (){ return this.ks6sta;   }              

	private String ks6uni = null; 
	public void setKs6uni (String value){ this.ks6uni = value;   }   
	public String getKs6uni (){ return this.ks6uni;   }              

	private String ks6pre = null; 
	public void setKs6pre (String value){ this.ks6pre = value;   }   
	public String getKs6pre(){ return this.ks6pre;   }              

	private String ks6ftx = null;
	public void setKs6ftx (String value){ this.ks6ftx = value;   }   
	public String getKs6ftx (){ return this.ks6ftx;   }              

	private String ks6trn = null; 
	public void setKs6trn (String value){ this.ks6trn = value;   }   
	public String getKs6trn(){ return this.ks6trn;   }              

	
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
