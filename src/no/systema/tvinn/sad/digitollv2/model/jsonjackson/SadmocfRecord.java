package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
public class SadmocfRecord extends JsonAbstractGrandFatherRecord  {

	private String orgnr  = "";//      tegn      30     
	private String name  = "";//      tegn      30  
	private String commtype  = ""; //  tegn      10 (ftp, email, webserv)   
	
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
