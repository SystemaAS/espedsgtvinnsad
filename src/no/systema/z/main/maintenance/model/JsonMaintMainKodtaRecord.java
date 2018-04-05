package no.systema.z.main.maintenance.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Aug 1, 2016
 * 
 */
public class JsonMaintMainKodtaRecord extends JsonAbstractGrandFatherRecord {
	
	private String koauni = null;                             
	public void setKoauni (String value){ this.koauni = value;   }   
	public String getKoauni (){ return this.koauni;   }  
	
	private String koaavd = null;                                 
	public void setKoaavd (String value){ this.koaavd = value;   }   
	public String getKoaavd (){ return this.koaavd;   }  
	
	private String koaknr = null;  
	public void setKoaknr (String value){ this.koaknr = value;   }   
	public String getKoaknr (){ return this.koaknr;   }              

	private String koabaer = null;  
	public void setKoabaer (String value){ this.koabaer = value;   }   
	public String getKoabaer (){ return this.koabaer;   }              

	private String koakon = null;  
	public void setKoakon (String value){ this.koakon = value;   }   
	public String getKoakon (){ return this.koakon;   }              

	private String koafir = null;  
	public void setKoafir (String value){ this.koafir = value;   }   
	public String getKoafir (){ return this.koafir;   }              

	private String koanvn = null;  
	public void setKoanvn (String value){ this.koanvn = value;   }   
	public String getKoanvn (){ return this.koanvn;   }              

	private String koaiat = null;  
	public void setKoaiat (String value){ this.koaiat = value;   }   
	public String getKoaiat (){ return this.koaiat;   }              

	private String koaia2 = null;  
	public void setKoaia2 (String value){ this.koaia2 = value;   }   
	public String getKoaia2 (){ return this.koaia2;   }              

	private String koaie = null;  
	public void setKoaie (String value){ this.koaie = value;   }   
	public String getKoaie (){ return this.koaie;   }              

	private String koapos = null;  
	public void setKoapos (String value){ this.koapos = value;   }   
	public String getKoapos (){ return this.koapos;   }              

	private String koalk = null;  
	public void setKoalk (String value){ this.koalk = value;   }   
	public String getKoalk (){ return this.koalk;   }              

	
	//external fields from joins
	private String navsg = null;  
	public void setNavsg (String value){ this.navsg = value;   }   
	public String getNavsg (){ return this.navsg;   }              

	private String ksidnr = null;  
	public void setKsidnr (String value){ this.ksidnr = value;   }   
	public String getKsidnr (){ return this.ksidnr;   }              

	//DUP
	private String kodus1 = null; 
	public void setKodus1 (String value){ this.kodus1 = value;   }   
	public String getKodus1 (){ return this.kodus1;   }              
    
	private String kodus2 = null; 
	public void setKodus2 (String value){ this.kodus2 = value;   }   
	public String getKodus2 (){ return this.kodus2;   }              
    
	private String kodus3 = null; 
	public void setKodus3 (String value){ this.kodus3 = value;   }   
	public String getKodus3 (){ return this.kodus3;   }              
    
	private String kodus4 = null; 
	public void setKodus4 (String value){ this.kodus4 = value;   }   
	public String getKodus4 (){ return this.kodus4;   }              
    
	private String kodus5 = null; 
	public void setKodus5 (String value){ this.kodus5 = value;   }   
	public String getKodus5 (){ return this.kodus5;   }              
    
	private String kodus6 = null; 
	public void setKodus6 (String value){ this.kodus6 = value;   }   
	public String getKodus6 (){ return this.kodus6;   }              
    
	public boolean dup = false;
	public void setDup (boolean value){ this.dup = value;   }
	public boolean isDup(){
		if(this.koanvn!=null && !"".equals(koanvn)){ 
			if(this.koanvn.toUpperCase().contains("DUP")){
				dup = true;
			}
		}
		return this.dup;
	}
	
	public boolean fasteDataExists = true;
	public void setFasteDataExists (boolean value){ this.fasteDataExists = value;   }   
	public boolean isFasteDataExists(){ return this.fasteDataExists; } 
	
	//Listehode-record
	private JsonMaintMainKodtaKodthRecord listeHodeRecord;
	public void setListeHodeRecord(JsonMaintMainKodtaKodthRecord value){ this.listeHodeRecord = value; }
	public JsonMaintMainKodtaKodthRecord getListeHodeRecord(){ return listeHodeRecord; }
	
	//Oppd.nr og tur - record
	private JsonMaintMainKodtaTellRecord oppnrTurRecord;
	public void setOppnrTurRecord(JsonMaintMainKodtaTellRecord value){ this.oppnrTurRecord = value; }
	public JsonMaintMainKodtaTellRecord getOppnrTurRecord(){ return oppnrTurRecord; }
	
	//these fields are ONLY for validation purposes. Refer to the child record otherwise
	private String teopdn = null;                                
	public void setTeopdn (String value){ this.teopdn = value;   }   
	public String getTeopdn (){ return this.teopdn;   }  
	
	private String teturn = null;                                
	public void setTeturn (String value){ this.teturn = value;   }   
	public String getTeturn (){ return this.teturn;   }  
	
	private String tetmin = null;                                
	public void setTetmin (String value){ this.tetmin = value;   }   
	public String getTetmin (){ return this.tetmin;   }  
	
	
	
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
