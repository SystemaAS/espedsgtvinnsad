package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Sep , 2020
 */
@Data
public class JsonTvinnSadManifestPostalCodeRecord extends JsonAbstractGrandFatherRecord {

	private String st2sta;
	private String st2kod; // key
	private String st2nvn;
	private String st2lk; // key
	private String stxxx;
	private int st2son;
	private String st2ko2;
	private String st2sol;
	private int st2pnr;
	private int st2soi;
	private String st2uni;
	

	/**
	 * User for java reflection in other classes
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
