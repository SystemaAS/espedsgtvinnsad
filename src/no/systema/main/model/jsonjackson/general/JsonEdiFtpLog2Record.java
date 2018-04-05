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
public class JsonEdiFtpLog2Record extends JsonAbstractGrandFatherRecord {
	
	private String ssssn = null;                                
	public void setSsssn (String value){ this.ssssn = value;   }   
	public String getSsssn (){ return this.ssssn;   }              

	private String sssdt = null;                                
	public void setSssdt (String value){ this.sssdt = value;   }   
	public String getSssdt (){ return this.sssdt;   }              

	private String ssstm = null;                                
	public void setSsstm (String value){ this.ssstm = value;   }   
	public String getSsstm (){ return this.ssstm;   }              

	private String sssdata = null;                                
	public void setSssdata (String value){ this.sssdata = value;   }   
	public String getSssdata (){ return this.sssdata;   }              

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
