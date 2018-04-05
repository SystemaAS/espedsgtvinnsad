/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.sadexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

/**
 * @author Fredrik Möller
 * @date Aug 16, 2016
 *
 */
public class JsonMaintSadExportSadavgeRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();

	private String agtanr = null;                            
	public void setAgtanr (String value){ this.agtanr = value;   }   
	public String getAgtanr (){ return this.agtanr;   }  

	private String taalfa = null;                            
	public void setTaalfa (String value){ this.taalfa = value;   }   
	public String getTaalfa (){ return this.taalfa;   }  
		
	private String agakd = null;                                
	public void setAgakd (String value){ this.agakd = value;   }   
	public String getAgakd (){ return this.agakd;   }  	
	
	private String agskv = null;                                
	public void setAgskv (String value){ this.agskv = value;   }   
	public String getAgskv (){ return this.agskv;   }  
	
	private String agdtf = null;                                
	public void setAgdtf(String value) {this.agdtf = value;}
	public String getAgdtf() {return this.agdtf;}
	
	private String agdtfNO = null;
	public void setAgdtfNO(String value) {this.agdtfNO = value;}
	public String getAgdtfNO() {
		if (agdtfNO != null) {  //from UI
			return agdtfNO;
		} else {				//from DB
			return dateFormatter.convertToDate_NO(this.agdtf);
		}
	}
	
	private String agdtt = null;                                
	public void setAgdtt (String value){ this.agdtt = value;   }   
	public String getAgdtt (){ return this.agdtt;   }  

	private String agdttNO = null;
	public void setAgdttNO (String value){ this.agdttNO = value;   }   

	public String getAgdttNO() {
		if (agdttNO != null) { // from UI
			return agdttNO;
		} else { // from DB
			return dateFormatter.convertToDate_NO(this.agdtt);
		}
	}
	
	private String agkd = null;                                
	public void setAgkd (String value){ this.agkd = value;   }   
	public String getAgkd (){ return this.agkd;   }  
	 
	private String agpp = null;                                
	public void setAgpp (String value){ this.agpp = value;   }   
	public String getAgpp (){ return this.agpp;   }  
	 	
	private String agsats = null;                                
	public void setAgsats (String value){ this.agsats = value;   }   
	public String getAgsats (){ return this.agsats;   }  

	private String agaktk = null;                                
	public void setAgaktk (String value){ this.agaktk = value;   }   
	public String getAgaktk (){ return this.agaktk;   }  	
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
	
	@Override
	public String toString()  {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName() + " [ ");
		try {
			for (Field f : getFields()) {
				String fName = f.getName();
				sb.append("(" + f.getType() + ") " + fName + " = " + f.get(this) + ", ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
}
