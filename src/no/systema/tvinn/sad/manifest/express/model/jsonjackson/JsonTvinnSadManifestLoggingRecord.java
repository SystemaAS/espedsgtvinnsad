/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.util.*;
import java.lang.reflect.Field;
import lombok.Data;
/**
 * @author oscardelatorre
 * @date Nov 2020
 * 
 *
 */
@Data
public class JsonTvinnSadManifestLoggingRecord extends JsonAbstractGrandFatherRecord{

	private String ssn = null;
	private String ssr = null;
	private String s0004 = null;
	private String s0010 = null;
	private String s0026 = null;
	private String s0036 = null;
	private String sst = null;
	private String sdt = null;
	private String stm = null;
	private String wurl = null;
	private String own_fileName = null;
	
	/**
	 * Used for java reflection in other classes
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
