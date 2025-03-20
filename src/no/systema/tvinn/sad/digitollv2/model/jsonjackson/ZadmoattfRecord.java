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
public class ZadmoattfRecord extends JsonAbstractGrandFatherRecord {

	private String id = ""; //TEGN 100 emdkm or ehdkm or other significant
	private String avsid = ""; //TEGN 50 sender Orgnr
	private String motid = ""; //TEGN 20 receiver Orgnr		
	
	private Integer date = 0; //TEGN 8 
	private Integer time = 0; //TEGN 6 
	
	private String docname = ""; //TEGN 100 file name	
	private String typref = ""; //TEGN 50 file type (business doc.type: invoice or docX)	
	private String docref = ""; //TEGN 300 file absolute name including path... 
	
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
