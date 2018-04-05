/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * tkunik,tkkode, tktxtn, tktxte, tkavg, tkank, tktrs
 * @author Fredrik Möller
 * @date Okt 17, 2016
 *
 */
public class JsonMaintNctsTrkodfRecord extends JsonAbstractGrandFatherRecord {

	private String tkunik = "";
	private String tkkode = "";
	private String tktxtn = "";
	private String tktxte = "";
	private String tkavg = "";
	private String tkank = "";
	private String tktrs = "";

	public String getTkunik() {
		return tkunik;
	}

	public void setTkunik(String tkunik) {
		this.tkunik = tkunik;
	}

	public String getTkkode() {
		return tkkode;
	}

	public void setTkkode(String tkkode) {
		this.tkkode = tkkode;
	}

	public String getTktxtn() {
		return tktxtn;
	}

	public void setTktxtn(String tktxtn) {
		this.tktxtn = tktxtn;
	}

	public String getTktxte() {
		return tktxte;
	}

	public void setTktxte(String tktxte) {
		this.tktxte = tktxte;
	}

	public String getTkavg() {
		return tkavg;
	}

	public void setTkavg(String tkavg) {
		this.tkavg = tkavg;
	}

	public String getTkank() {
		return tkank;
	}

	public void setTkank(String tkank) {
		this.tkank = tkank;
	}

	public String getTktrs() {
		return tktrs;
	}

	public void setTktrs(String tktrs) {
		this.tktrs = tktrs;
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
