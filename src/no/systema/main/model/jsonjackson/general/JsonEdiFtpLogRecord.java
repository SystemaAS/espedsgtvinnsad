package no.systema.main.model.jsonjackson.general;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class JsonEdiFtpLogRecord extends JsonAbstractGrandFatherRecord {
	
	private String sssn = null;                                
	public void setSssn (String value){ this.sssn = value;   }   
	public String getSssn (){ return this.sssn;   }              

	private String ssdt = null;                                
	public void setSsdt (String value){ this.ssdt = value;   }   
	public String getSsdt (){ return this.ssdt;   }              

	private String sstm = null;                                
	public void setSstm (String value){ this.sstm = value;   }   
	public String getSstm (){ return this.sstm;   }              

	private String ssst = null;                                
	public void setSsst (String value){ this.ssst = value;   }   
	public String getSsst (){ return this.ssst;   }              

	private String sstext = null;                                
	public void setSstext (String value){ this.sstext = value;   }   
	public String getSstext (){ return this.sstext;   }              

	/**
	 * Required for java reflection in other classes
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
