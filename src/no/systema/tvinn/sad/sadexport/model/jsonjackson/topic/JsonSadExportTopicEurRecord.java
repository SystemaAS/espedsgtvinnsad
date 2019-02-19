/**
 * 
 */
package no.systema.tvinn.sad.sadexport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;
import lombok.Data;
/**
 * @author oscardelatorre
 * @date Feb, 2019
 *
 */
@Data
public class JsonSadExportTopicEurRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String euravd;
	private String eurtdn;
	private String eur01a;
	private String eur01b;
	private String eur01c;
	private String eur01d;
	//
	private String eur03a;
	private String eur03b;
	private String eur03c;
	private String eur03d;
	//
	private String eur04;
	private String eur05;
	
	private String eur06a;
	private String eur06b;
	private String eur06c;
	
	private String eur07a;
	private String eur07b;
	private String eur07c;
	private String eur07d;
	
	private String eur12a;
	private String eur12b;
	private String eur12c;
	
	private String eur081a;
	private String eur081b;
	private String eur081c;
	private String eur081d;
	private String eur081e;
	private String eur081f;
	private String eur081g;
	private String eur081h;
	private String eur081i;
	//
	private String eur082a;
	private String eur082b;
	private String eur082c;
	private String eur082d;
	private String eur082e;
	private String eur082f;
	private String eur082g;
	private String eur082h;
	private String eur082i;
	//
	private String eur083a;
	private String eur083b;
	private String eur083c;
	private String eur083d;
	private String eur083e;
	private String eur083f;
	private String eur083g;
	private String eur083h;
	private String eur083i;
	//
	private String eur084a;
	private String eur084b;
	private String eur084c;
	private String eur084d;
	private String eur084e;
	private String eur084f;
	private String eur084g;
	private String eur084h;
	private String eur084i;
	//
	private String eur09a;
	private String eur09b;
	private String eur09c;
	private String eur09d;
	private String eur09e;
	private String eur09f;
	private String eur09g;
	private String eur09h;
	private String eur09i;
	//
	private String eur010a;
	private String eur010b;
	private String eur010c;
	private String eur010d;
	private String eur010e;
	private String eur010f;
	private String eur010g;
	private String eur010h;
	private String eur010i;
	
	
	
	
	
	private String errMsg = "";
	
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
