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
public class JsonMaintSadImportKodttsRecord extends JsonAbstractGrandFatherRecord {
	
	private String ktsuni = null;                                
	public void setKtsuni (String value){ this.ktsuni = value;   }   
	public String getKtsuni (){ return this.ktsuni;   }  
	
	private String ktskod = null;
	public void setKtskod (String value){ this.ktskod = value;   }   
	public String getKtskod (){ return this.ktskod;   }              

	private String ktsnav = null;
	public void setKtsnav (String value){ this.ktsnav = value;   }   
	public String getKtsnav (){ return this.ktsnav;   }              

	private String ktspnr = null;
	public void setKtspnr (String value){ this.ktspnr = value;   }   
	public String getKtspnr (){ return this.ktspnr;   }              

	private String ktstrt = null; 
	public void setKtstrt (String value){ this.ktstrt = value;   }   
	public String getKtstrt (){ return this.ktstrt;   }              

	private String ktssat = null; 
	public void setKtssat (String value){ this.ktssat = value;   }   
	public String getKtssat (){ return this.ktssat;   }              

	private String ktsxxx = null; 
	public void setKtsxxx (String value){ this.ktsxxx = value;   }   
	public String getKtsxxx (){ return this.ktsxxx;   }              

	//Postnr table KODTTSX
	private String ktxpnr = "0000"; //Default
	public void setKtxpnr (String value){ this.ktxpnr = value;   }   
	public String getKtxpnr (){ return this.ktxpnr;   }              

	private String ktxkod = null; 
	public void setKtxkod (String value){ this.ktxkod = value;   }   
	public String getKtxkod (){ return this.ktxkod;   }              
       

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
