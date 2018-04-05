package no.systema.tvinn.sad.kundekontroll.brreg.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author Fredrik Möller
 * @date Sep 26, 2016
 *
 */
public class JsonEnhetsRegisteretDataCheckRecord extends JsonAbstractGrandFatherRecord {

	private String firmakode = null;
	private String kundenr = null;
	private String kundenavn = null;
	private String orgnr = null;
	private String konkurs = null;
	private String registrertimvaregisteret = null;
	private String underavvikling = null;
	private String undertvangsavviklingellertvangsopplosning = null;
	private String existsashovedenhet = null;
	private String existsasunderenhet = null;
	private String overordnetenhetorgnr = null;
	private String organisasjonsform = null;
	

	public String getFirmakode() {
		return firmakode;
	}

	public void setFirmakode(String firmakode) {
		this.firmakode = firmakode;
	}

	public String getKundenr() {
		return kundenr;
	}

	public void setKundenr(String kundenr) {
		this.kundenr = kundenr;
	}

	public String getKundenavn() {
		return kundenavn;
	}

	public void setKundenavn(String kundenavn) {
		this.kundenavn = kundenavn;
	}

	public String getOrgnr() {
		return orgnr;
	}

	public void setOrgnr(String orgnr) {
		this.orgnr = orgnr;
	}

	public String getKonkurs() {
		return konkurs;
	}

	public void setKonkurs(String konkurs) {
		this.konkurs = konkurs;
	}

	public String getRegistrertimvaregisteret() {
		return registrertimvaregisteret;
	}

	public void setRegistrertimvaregisteret(String registrertimvaregisteret) {
		this.registrertimvaregisteret = registrertimvaregisteret;
	}

	public String getUnderavvikling() {
		return underavvikling;
	}

	public void setUnderavvikling(String underavvikling) {
		this.underavvikling = underavvikling;
	}

	public String getUndertvangsavviklingellertvangsopplosning() {
		return undertvangsavviklingellertvangsopplosning;
	}

	public void setUndertvangsavviklingellertvangsopplosning(String undertvangsavviklingellertvangsopplosning) {
		this.undertvangsavviklingellertvangsopplosning = undertvangsavviklingellertvangsopplosning;
	}


	public String getExistsashovedenhet() {
		return existsashovedenhet;
	}

	public void setExistsashovedenhet(String existsashovedenhet) {
		this.existsashovedenhet = existsashovedenhet;
	}

	public String getExistsasunderenhet() {
		return existsasunderenhet;
	}

	public void setExistsasunderenhet(String existsasunderenhet) {
		this.existsasunderenhet = existsasunderenhet;
	}

	public String getOverordnetenhetorgnr() {
		return overordnetenhetorgnr;
	}

	public void setOverordnetenhetorgnr(String overordnetenhetorgnr) {
		this.overordnetenhetorgnr = overordnetenhetorgnr;
	}

	public String getOrganisasjonsform() {
		return organisasjonsform;
	}

	public void setOrganisasjonsform(String organisasjonsform) {
		this.organisasjonsform = organisasjonsform;
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
