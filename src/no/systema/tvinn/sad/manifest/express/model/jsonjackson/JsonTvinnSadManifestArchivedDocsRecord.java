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
 * @date Oct 2020
 * 
 *
 */
@Data
public class JsonTvinnSadManifestArchivedDocsRecord extends JsonAbstractGrandFatherRecord{

	private String doctxt = null;
	private String doclnk = null;
	private String doctyp = null;
	private String docdat = null;
	private String doctim = null;
	
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
