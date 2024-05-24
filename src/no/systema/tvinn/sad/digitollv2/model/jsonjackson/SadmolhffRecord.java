package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

//log data to FTP for främmande houses: 
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmolhffRecord extends JsonAbstractGrandFatherRecord {

	private String status = ""; //TEGN 20 status (send, confirmed, dialog, receipt, ok) 
	private String statustxt = ""; //TEGN 70 status txt (usually when getting a receipt)
	private String uuid = ""; //TEGN 36 messageId
	private String ehdkh = ""; //TEGN 50 master doc id
	private String ehlnrt  = "" ;  //TEGN   7 
	private String avsid = ""; //TEGN 20 sender Orgnr
	private String motid = ""; //TEGN 20 receiver Orgnr		
	
	private String date = ""; //TEGN 8 
	private String time = ""; //TEGN 6 
	
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
