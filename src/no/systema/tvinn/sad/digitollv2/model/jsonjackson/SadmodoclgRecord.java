package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmodoclgRecord extends JsonAbstractGrandFatherRecord   {

	private String docId = "";	//char(100),          
	private String deklid = ""; //char (100) NOT NULL
	private String doctyp = ""; //char(30) NOT NULL, 
	private String doclnk = ""; //char(200) NOT NULL,
	private String deklnr = ""; //char(35) NOT NULL, 
	private String dekldate = ""; //char(8) NOT NULL,
	private String deklsekv = ""; //char(8) NOT NULL,
	private String senddate = ""; //char(8) NOT NULL,
	private String sendtime = ""; //char(6) NOT NULL, 
	private String resultapi = ""; //char(200) NOT NULL
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
