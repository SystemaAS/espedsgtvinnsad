/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 20, 2016
 *
 */
public class JsonMaintSadImportSadhHeadfRecord extends JsonAbstractGrandFatherRecord {
	
	private String siavd = null;                                
	public void setSiavd (String value){ this.siavd = value;   }   
	public String getSiavd (){ return this.siavd;   }  
	
	private String sitdn = null;
	public void setSitdn (String value){ this.sitdn = value;   }   
	public String getSitdn (){ return this.sitdn;   }              

	private String sinak = null;
	public void setSinak (String value){ this.sinak = value;   }   
	public String getSinak (){ return this.sinak;   }              

	private String sitll = null;
	public void setSitll (String value){ this.sitll = value;   }   
	public String getSitll (){ return this.sitll;   }              

	private String sitle = null; 
	public void setSitle (String value){ this.sitle = value;   }   
	public String getSitle (){ return this.sitle;   }              

	private String sidtg = null; 
	public void setSidtg (String value){ this.sidtg = value;   }   
	public String getSidtg (){ return this.sidtg;   }              

	private String heavd = null; 
	public void setHeavd (String value){ this.heavd = value;   }   
	public String getHeavd (){ return this.heavd;   }              

	private String heopd = null; 
	public void setHeopd (String value){ this.heopd = value;   }   
	public String getHeopd (){ return this.heopd;   }              
	
	private String hetll = null; 
	public void setHetll (String value){ this.hetll = value;   }   
	public String getHetll (){ return this.hetll;   }              
	
	private String hetle = null; 
	public void setHetle (String value){ this.hetle = value;   }   
	public String getHetle (){ return this.hetle;   }              
	            

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
