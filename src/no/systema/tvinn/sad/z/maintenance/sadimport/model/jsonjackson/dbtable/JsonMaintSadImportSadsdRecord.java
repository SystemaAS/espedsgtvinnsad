/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 12, 2016
 *
 */
public class JsonMaintSadImportSadsdRecord extends JsonAbstractGrandFatherRecord {
	
	private String sdtnrf = null;                                
	public void setSdtnrf (String value){ this.sdtnrf = value;   }   
	public String getSdtnrf (){ return this.sdtnrf;   }  
	
	private String taalfa = null; 
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }              
	
	private String taalfaOrig = null; 
	public void setTaalfaOrig (String value){ this.taalfaOrig = value;   }   
	public String getTaalfaOrig (){ return this.taalfaOrig;   }              

	private String sdkdae = null;
	public void setSdkdae (String value){ this.sdkdae = value;   }   
	public String getSdkdae (){ return this.sdkdae;   }              

	private String sdkdse = null; 
	public void setSdkdse (String value){ this.sdkdse = value;   }   
	public String getSdkdse (){ return this.sdkdse;   }              

	private String sddtf = null; 
	public void setSddtf (String value){ this.sddtf = value;   }   
	public String getSddtf (){ return this.sddtf;   }              

	private String sddtfNO = null; 
	public void setSddtfNO (String value){ this.sddtfNO = value;   }   
	public String getSddtfNO() {
		if (sddtfNO != null) { // from UI
			return sddtfNO;
		} else { 				// from DB
			return dateFormatter.convertToDate_NO(this.sddtf);
		}
	}

	private String sddtfOrig = null; 
	public void setSddtfOrig (String value){ this.sddtfOrig = value;   }   
	public String getSddtfOrig (){ return this.sddtfOrig;   }              

	private String sddtt = null; 
	public void setSddtt (String value){ this.sddtt = value;   }   
	public String getSddtt (){ return this.sddtt;   }              

	private String sddttNO = null; 
	public void setSddttNO (String value){ this.sddttNO = value;   }   
	public String getSddttNO() {
		if (sddttNO != null) { // from UI
			return sddttNO;
		} else { // from DB
			return dateFormatter.convertToDate_NO(this.sddtt);
		}
	}

	private String sddttOrig = null; 
	public void setSddttOrig (String value){ this.sddttOrig = value;   }   
	public String getSddttOrig (){ return this.sddttOrig;   }              

	private String sdblse = null; 
	public void setSdblse (String value){ this.sdblse = value;   }   
	public String getSdblse (){ return this.sdblse;   }              
	
	private String sdaktk = null; 
	public void setSdaktk (String value){ this.sdaktk = value;   }   
	public String getSdaktk (){ return this.sdaktk;   }              
	
	
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
