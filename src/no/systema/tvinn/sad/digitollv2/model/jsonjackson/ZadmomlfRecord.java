package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ZadmomlfRecord extends JsonAbstractGrandFatherRecord {

	private String emdkm = ""; //TEGN 50 master doc id
	private String empro  = "" ;  //TEGN 20 (including AVD-TUR)
	
	private String avsna = ""; //TEGN 50 sender name
	private String avsid = ""; //TEGN 20 sender Orgnr
	private String motna = ""; //TEGN 50 receiver name
	private String motid = ""; //TEGN 20 receiver Orgnr		
	
	private String trreforg = ""; //TEGN 50 carrier Orgnr	
	private String trrefreg = ""; //TEGN 50 carrier regnr	
	private String date = ""; //TEGN 8 
	private String time = ""; //TEGN 6 
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
