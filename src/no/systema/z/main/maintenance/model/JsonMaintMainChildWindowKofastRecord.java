package no.systema.z.main.maintenance.model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Apr 3, 2018
 *
 */
public class JsonMaintMainChildWindowKofastRecord extends JsonAbstractGrandFatherRecord {

	private String kftyp = null;
	private String kfkod = null;
	private String kftxt = null;
	private String kftxte = null;

	public String getKftyp() {
		return kftyp;
	}

	public void setKftyp(String kftyp) {
		this.kftyp = kftyp;
	}

	public String getKfkod() {
		return kfkod;
	}

	public void setKfkod(String kfkod) {
		this.kfkod = kfkod;
	}

	public String getKftxt() {
		return kftxt;
	}

	public void setKftxt(String kftxt) {
		this.kftxt = kftxt;
	}

	public String getKftxte() {
		return kftxte;
	}

	public void setKftxte(String kftxte) {
		this.kftxte = kftxte;
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
