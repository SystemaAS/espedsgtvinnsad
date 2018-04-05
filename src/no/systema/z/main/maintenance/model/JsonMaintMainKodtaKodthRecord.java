package no.systema.z.main.maintenance.model;
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
 * @date Aug 19, 2016
 * 
 */
public class JsonMaintMainKodtaKodthRecord extends JsonAbstractGrandFatherRecord  {
	
	//KODTH table
	private String kohuni = "H";                                
	public void setKohuni (String value){ this.kohuni = value;   }   
	public String getKohuni (){ return this.kohuni;   }  
	
	private String kohavd = null;                                
	public void setKohavd (String value){ this.kohavd = value;   }   
	public String getKohavd (){ return this.kohavd;   }  
	
	private String kohfak = null;                                
	public void setKohfak (String value){ this.kohfak = value;   }   
	public String getKohfak (){ return this.kohfak;   }  
	
	private String kohlas = null;                                
	public void setKohlas (String value){ this.kohlas = value;   }   
	public String getKohlas (){ return this.kohlas;   }  
	
	private String kohgod = null;                                
	public void setKohgod (String value){ this.kohgod = value;   }   
	public String getKohgod (){ return this.kohgod;   }  
	
	private String kohbou = null;                                
	public void setKohbou (String value){ this.kohbou = value;   }   
	public String getKohbou (){ return this.kohbou;   }  
	
	private String kohkk = null;                                
	public void setKohkk (String value){ this.kohkk = value;   }   
	public String getKohkk (){ return this.kohkk;   }  
	
	private String kohlos = null;                                
	public String getKohlosPropertyName (){ return "kohlos"; }
	public void setKohlos (String value){ this.kohlos = value;   }   
	public String getKohlos (){ return this.kohlos;   }  
	
	private String kohman = null;                                
	public void setKohman (String value){ this.kohman = value;   }   
	public String getKohman (){ return this.kohman;   }  
	
	private String kohxx1 = null;                                
	public void setKohxx1 (String value){ this.kohxx1 = value;   }   
	public String getKohxx1 (){ return this.kohxx1;   }  
	
	private String kohls1 = null;                                
	public void setKohls1 (String value){ this.kohls1 = value;   }   
	public String getKohls1 (){ return this.kohls1;   }  
	
	private String koh421 = null;                                
	public void setKoh421 (String value){ this.koh421 = value;   }   
	public String getKoh421 (){ return this.koh421;   }  
	
	private String kohls2 = null;                                
	public void setKohls2 (String value){ this.kohls2 = value;   }   
	public String getKohls2 (){ return this.kohls2;   }  
	
	private String koh422 = null;                                
	public void setKoh422 (String value){ this.koh422 = value;   }   
	public String getKoh422 (){ return this.koh422;   }  
	
	private String kohls3 = null;                                
	public void setKohls3 (String value){ this.kohls3 = value;   }   
	public String getKohls3 (){ return this.kohls3;   }  
	
	private String koh423 = null;                                
	public void setKoh423 (String value){ this.koh423 = value;   }   
	public String getKoh423 (){ return this.koh423;   }  
	
	private String kohxx2 = null;                                
	public void setKohxx2 (String value){ this.kohxx2 = value;   }   
	public String getKohxx2 (){ return this.kohxx2;   }  
	
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
