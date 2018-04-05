/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sad.model.jsonjackson.dbtable;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date May 27, 2016
 *
 */
public class JsonMaintSadSadlRecord extends JsonAbstractGrandFatherRecord implements Serializable{
	
	private static final long serialVersionUID = -2102828510223358217L;
		
	private String slstat = null;                                
	public void setSlstat (String value){ this.slstat = value;   }   
	public String getSlstat (){ return this.slstat;   }  
	
	private String slknr = null; 
	public void setSlknr (String value){ this.slknr = value;   }   
	public String getSlknr (){ return this.slknr;   }              
	
	private String slalfa = null; 
	public void setSlalfa (String value){ this.slalfa = value;   }   
	public String getSlalfa (){ return this.slalfa;   }              

	private String sltxt = null; 
	public void setSltxt (String value){ this.sltxt = value;   }   
	public String getSltxt (){ return this.sltxt;   }              

	private String sloppl = null; 
	public void setSloppl (String value){ this.sloppl = value;   }   
	public String getSloppl (){ return this.sloppl;   }              

	private String slvekt = null; 
	public void setSlvekt (String value){ this.slvekt = value;   }   
	public String getSlvekt (){ return this.slvekt;   }              


	private String sltanr = null; 
	public void setSltanr (String value){ this.sltanr = value;   }   
	public String getSltanr (){ return this.sltanr;   }              

	private String sltar = null; 
	public void setSltar (String value){ this.sltar = value;   }   
	public String getSltar (){ return this.sltar;   }              

	private String slpva = null; 
	public void setSlpva (String value){ this.slpva = value;   }   
	public String getSlpva (){ return this.slpva;   }              

	private String slsats = null; 
	public void setSlsats (String value){ this.slsats = value;   }   
	public String getSlsats (){ return this.slsats;   }              

	private String sltn = null; 
	public void setSltn (String value){ this.sltn = value;   }   
	public String getSltn (){ return this.sltn;   }              

	private String slkdae = null; 
	public void setSlkdae (String value){ this.slkdae = value;   }   
	public String getSlkdae (){ return this.slkdae;   }              

	private String slkdse = null; 
	public void setSlkdse (String value){ this.slkdse = value;   }   
	public String getSlkdse (){ return this.slkdse;   }              

	private String slto = null; 
	public void setSlto (String value){ this.slto = value;   }   
	public String getSlto (){ return this.slto;   }              

	private String slcref = null; 
	public void setSlcref (String value){ this.slcref = value;   }   
	public String getSlcref (){ return this.slcref;   }              

	private String r31 = null; 
	public void setR31 (String value){ this.r31 = value;   }   
	public String getR31 (){ return this.r31;   }              

	private String pref = null; 
	public void setPref (String value){ this.pref = value;   }   
	public String getPref (){ return this.pref;   }              

	private String mf = null; 
	public void setMf (String value){ this.mf = value;   }   
	public String getMf (){ return this.mf;   }              

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
