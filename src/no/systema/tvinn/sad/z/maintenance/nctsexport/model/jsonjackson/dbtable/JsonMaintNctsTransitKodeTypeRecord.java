/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * Holding tkunik with description
 * 
 * Primary used in dropdown.
 * 
 * @author Fredrik Möller
 * @date Okt 17, 2016
 *
 */
public class JsonMaintNctsTransitKodeTypeRecord extends JsonAbstractGrandFatherRecord {

	private String tkunik = "";
	private String description = "";

	public String getTkunik() {
		return tkunik;
	}

	public void setTkunik(String tkunik) {
		this.tkunik = tkunik;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception {
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);

		return list;
	}

}
