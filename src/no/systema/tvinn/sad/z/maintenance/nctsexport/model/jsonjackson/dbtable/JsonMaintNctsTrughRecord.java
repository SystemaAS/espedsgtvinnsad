/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.nctsexport.model.jsonjackson.dbtable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author Fredrik Möller
 * @date Sep 19, 2016
 *
 */
public class JsonMaintNctsTrughRecord extends JsonAbstractGrandFatherRecord {

	private String tgst = null;
	private String tggnr = null;
	private String tggty = null;
	private String tggvk = null;
	private String tggbl = null;
	private String tggblb = null;
	private String tgkna = null;
	private String tgtina = null;
	private String tgnaa = null;
	private String tgada1 = null;
	private String tgpna = null;
	private String tgpsa = null;
	private String tglka = null;
	private String tgtsd = null;
	private String tgakny = null;
	private String tgakgm = null;
	private String tgusr = null;
	private String tgdt = null;
	private String tgdtNO = null;
	private String tgdtr = null;
	private String tgprm = null;
	private String tgrn = null;
	private String tggfv = null;

	public String getTgst() {
		return tgst;
	}

	public void setTgst(String tgst) {
		this.tgst = tgst;
	}

	public String getTggnr() {
		return tggnr;
	}

	public void setTggnr(String tggnr) {
		this.tggnr = tggnr;
	}

	public String getTggty() {
		return tggty;
	}

	public void setTggty(String tggty) {
		this.tggty = tggty;
	}

	public String getTggvk() {
		return tggvk;
	}

	public void setTggvk(String tggvk) {
		this.tggvk = tggvk;
	}

	public String getTggbl() {
		return tggbl;
	}

	public void setTggbl(String tggbl) {
		this.tggbl = tggbl;
	}

	public String getTggblb() {
		return tggblb;
	}

	public void setTggblb(String tggblb) {
		this.tggblb = tggblb;
	}

	public String getTgkna() {
		return tgkna;
	}

	public void setTgkna(String tgkna) {
		this.tgkna = tgkna;
	}

	public String getTgtina() {
		return tgtina;
	}

	public void setTgtina(String tgtina) {
		this.tgtina = tgtina;
	}

	public String getTgnaa() {
		return tgnaa;
	}

	public void setTgnaa(String tgnaa) {
		this.tgnaa = tgnaa;
	}

	public String getTgada1() {
		return tgada1;
	}

	public void setTgada1(String tgada1) {
		this.tgada1 = tgada1;
	}

	public String getTgpna() {
		return tgpna;
	}

	public void setTgpna(String tgpna) {
		this.tgpna = tgpna;
	}

	public String getTgpsa() {
		return tgpsa;
	}

	public void setTgpsa(String tgpsa) {
		this.tgpsa = tgpsa;
	}

	public String getTglka() {
		return tglka;
	}

	public void setTglka(String tglka) {
		this.tglka = tglka;
	}

	public String getTgtsd() {
		return tgtsd;
	}

	public void setTgtsd(String tgtsd) {
		this.tgtsd = tgtsd;
	}

	public String getTgakny() {
		return tgakny;
	}

	public void setTgakny(String tgakny) {
		this.tgakny = tgakny;
	}

	public String getTgakgm() {
		return tgakgm;
	}

	public void setTgakgm(String tgakgm) {
		this.tgakgm = tgakgm;
	}

	public String getTgusr() {
		return tgusr;
	}

	public void setTgusr(String tgusr) {
		this.tgusr = tgusr;
	}

	public String getTgdt() {
		return tgdt;
	}

	public void setTgdt(String tgdt) {
		this.tgdt = tgdt;
	}

	public void setTgdtNO(String value) {
		this.tgdtNO = value;
	}

	public String getTgdtNO() {
		if (tgdtNO != null) {  //from UI
			return tgdtNO;
		} else {				//from DB
			return dateFormatter.convertToDate_NO(this.tgdt);
		}
	}
	
	public String getTgdtr() {
		return tgdtr;
	}

	public void setTgdtr(String tgdtr) {
		this.tgdtr = tgdtr;
	}

	public String getTgprm() {
		return tgprm;
	}

	public void setTgprm(String tgprm) {
		this.tgprm = tgprm;
	}

	public String getTgrn() {
		return tgrn;
	}

	public void setTgrn(String tgrn) {
		this.tgrn = tgrn;
	}

	public String getTggfv() {
		return tggfv;
	}

	public void setTggfv(String tggfv) {
		this.tggfv = tggfv;
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
