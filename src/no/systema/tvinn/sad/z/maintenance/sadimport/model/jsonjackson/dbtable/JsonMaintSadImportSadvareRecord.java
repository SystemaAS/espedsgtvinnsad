/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadimport.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 27, 2016
 *
 */
public class JsonMaintSadImportSadvareRecord extends JsonAbstractGrandFatherRecord {
	
	private String varenr = null;                                
	public void setVarenr (String value){ this.varenr = value;   }   
	public String getVarenr (){ return this.varenr;   }  
	
	private String varebe = null; 
	public void setVarebe (String value){ this.varebe = value;   }   
	public String getVarebe (){ return this.varebe;   }              
	
	private String levenr = null; 
	public void setLevenr (String value){ this.levenr = value;   }   
	public String getLevenr (){ return this.levenr;   }              

	private String w2vf = null; 
	public void setW2vf (String value){ this.w2vf = value;   }   
	public String getW2vf (){ return this.w2vf;   }              

	private String w2lk = null; 
	public void setW2lk (String value){ this.w2lk = value;   }   
	public String getW2lk (){ return this.w2lk;   }              

	private String w2vnti = null; 
	public void setW2vnti (String value){ this.w2vnti = value;   }   
	public String getW2vnti (){ return this.w2vnti;   }              


	private String w2tn = null; 
	public void setW2tn (String value){ this.w2tn = value;   }   
	public String getW2tn (){ return this.w2tn;   }              

	private String w2pre = null; 
	public void setW2pre (String value){ this.w2pre = value;   }   
	public String getW2pre (){ return this.w2pre;   }              

	private String w2pva = null; 
	public void setW2pva (String value){ this.w2pva = value;   }   
	public String getW2pva (){ return this.w2pva;   }              

	private String w2as = null; 
	public void setW2as (String value){ this.w2as = value;   }   
	public String getW2as (){ return this.w2as;   }              

	private String w2mfr = null; 
	public void setW2mfr (String value){ this.w2mfr = value;   }   
	public String getW2mfr (){ return this.w2mfr;   }              

	
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
