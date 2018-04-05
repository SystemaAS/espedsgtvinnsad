package no.systema.tror.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

public class JsonTrorOrderListRecord extends JsonAbstractGrandFatherRecord {

	/* Avdeling */
	private int heavd; 
	/* Ordernr */
	private int heopd;
	/* Signatur */
	private String hesg;
	/*  Oppdragsdato  */
	private String hedtop;
	/* Avsender */
	private String henas;
	/* Mottaker */
	private String henak;
	/* Antall */
	private int hent;
	/* Vekt */
	private int hevkt;
	/* M3 */
	private BigDecimal hem3;
	/* Landkode selger */
	private String helks;
	/* Postnr selger */
	private String hepns;
	/* Landkode kjøper */
	private String helkk;
	/* Postnr kjøper */
	private String hepnk;
	
	private String heur;
	private String hepro;
	private String hegn;
	private String hest;
	private String hepk3;
	private String hepk4;
	

	public String getHest() {
		return hest;
	}

	public void setHest(String hest) {
		this.hest = hest;
	}
	
	public String getHeur() {
		return heur;
	}

	public void setHeur(String heur) {
		this.heur = heur;
	}

	public String getHepro() {
		return hepro;
	}

	public void setHepro(String hepro) {
		this.hepro = hepro;
	}

	public String getHegn() {
		return hegn;
	}

	public void setHegn(String hegn) {
		this.hegn = hegn;
	}

	public int getHeopd() {
		return heopd;
	}

	public void setHeopd(int heopd) {
		this.heopd = heopd;
	}

	public String getHedtop() {
		return hedtop;
	}

	public void setHedtop(String hedtop) {
		this.hedtop = hedtop;
	}
	
	public String getHesg() {
		return hesg;
	}

	public void setHesg(String hesg) {
		this.hesg = hesg;
	}

	
	public String getHenas() {
		return henas;
	}

	public void setHenas(String henas) {
		this.henas = henas;
	}

	public String getHenak() {
		return henak;
	}

	public void setHenak(String henak) {
		this.henak = henak;
	}

	public int getHent() {
		return hent;
	}

	public void setHent(int hent) {
		this.hent = hent;
	}

	public int getHevkt() {
		return hevkt;
	}

	public void setHevkt(int hevkt) {
		this.hevkt = hevkt;
	}

	public BigDecimal getHem3() {
		return hem3;
	}

	public void setHem3(BigDecimal hem3) {
		this.hem3 = hem3;
	}

	public String getHelks() {
		return helks;
	}

	public void setHelks(String helks) {
		this.helks = helks;
	}

	public String getHepns() {
		return hepns;
	}

	public void setHepns(String hepns) {
		this.hepns = hepns;
	}

	public String getHelkk() {
		return helkk;
	}

	public void setHelkk(String helkk) {
		this.helkk = helkk;
	}

	public String getHepnk() {
		return hepnk;
	}

	public void setHepnk(String hepnk) {
		this.hepnk = hepnk;
	}

	public int getHeavd() {
		return heavd;
	}

	public void setHeavd(int heavd) {
		this.heavd = heavd;
	}
	
	public String getHepk3() {
		return hepk3;
	}

	public void setHepk3(String hepk3) {
		this.hepk3 = hepk3;
	}
	public String getHepk4() {
		return hepk4;
	}
	public void setHepk4(String hepk4) {
		this.hepk4 = hepk4;
	}
	
	
	
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
