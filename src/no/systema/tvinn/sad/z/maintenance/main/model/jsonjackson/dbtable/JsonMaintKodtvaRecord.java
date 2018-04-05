package no.systema.tvinn.sad.z.maintenance.main.model.jsonjackson.dbtable;
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
 * @date Jun 7, 2016
 * 
 */
public class JsonMaintKodtvaRecord extends JsonAbstractGrandFatherRecord {

	private String kvasta = null;                                
	public void setKvasta (String value){ this.kvasta = value;   }   
	public String getKvasta (){ return this.kvasta;   }  
	
	private String kvauni = "VA"; 
	public void setKvauni (String value){ this.kvauni = value;   }   
	public String getKvauni (){ return this.kvauni;   }              

	private String kvakod = null;
	public void setKvakod (String value){ this.kvakod = value;   }   
	public String getKvakod (){ return this.kvakod;   }              

	private String kvakrs = null;
	public void setKvakrs (String value){ this.kvakrs = value;   }   
	public String getKvakrs (){ return this.kvakrs;   }              

	private String kvaomr = null; 
	public void setKvaomr (String value){ this.kvaomr = value;   }   
	public String getKvaomr (){ return this.kvaomr;   }              

	private String kvadt = null; 
	public void setKvadt (String value){ this.kvadt = value;   }   
	public String getKvadt (){ return this.kvadt;   }              

	private String kvadtNO = null; 
	public void setKvadtNO (String value){ this.kvadtNO = value;   }   
	public String getKvadtNO() {
		if (kvadtNO != null) { // from UI
			return kvadtNO;
		} else { 				// from DB
			return dateFormatter.convertToDate_NO(this.kvadt);
		}
	}	
	
	private String kvagkr = null; 
	public void setKvagkr (String value){ this.kvagkr = value;   }   
	public String getKvagkr (){ return this.kvagkr;   }              

	private String kvaxxx = null; 
	public void setKvaxxx (String value){ this.kvaxxx = value;   }   
	public String getKvaxxx (){ return this.kvaxxx;   }              

	private String kvagv = null; 
	public void setKvagv (String value){ this.kvagv = value;   }   
	public String getKvagv (){ return this.kvagv;   }              
	
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
