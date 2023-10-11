package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
public class SadTurRecord extends JsonAbstractGrandFatherRecord  {
	
	private String xturtype;
	private String tuavd;
	private String tupro;
	private String tupro2;
	private String tustef;
	private String tustet;
	private String tuetd;
	private String tueta;
	private String tuatd;
	private String tucon1;
	private String tubiln;//": "AA25507",
	private String tudt;  //": "20200528",
	private String tudtt; //": "20200528",
	private String tutm;
	private String tutmt;
	private String tulkf; //": "NO",
	private String tulkt; //": "NO",
	private String tusg; //": "JOV",
	private String turund; //": "1042",
	private String tutst1; //": "",
	private String tust;
	private String tuopdt;
	private String tuao; //": "0",
	private String tuts; //": "0",
	private String tutvkt; // ": "0",
	private String tutm3; //": "0,000",
	private String tutlm2; //": "0,00",
	private String tupoen; //": "",
	private String turclose; //": "close"
	
	
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



