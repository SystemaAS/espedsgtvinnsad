/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Aug 2020
 * 
 */
@Data
public class JsonTvinnSadManifestRecord extends JsonAbstractGrandFatherRecord {
	
	private String efst = ""; //TEGN (1) Status
	private String efuuid = ""; //TEGN (36) ManifestID (uuid)
 	private String efavd = "0"; //SONET (4,0) Avdeling
	private String efpro = "0"; //SONET (8,0) Turnr
	private String efdtr = "0"; //SONET (8,0) Reg.dato
	private String efsg = ""; //TEGN (3) Signatur
	private String efst2 = ""; //TEGN (1) Status om manifest
	private String eftsd = "0"; //SONET (4,0) Passeringstollsted
	private String efst3 = ""; //TEGN (1) Status om inpassering
	private String efdtin = "0"; //SONET (8,0) Innsendingsdato
	private String efeta = "0"; //SONET (8,0) ETA-dato
	private String efetm = "0"; //SONET (6,0) ETA-tid
	private String efata = "0"; //SONET (8,0) ATA-dato
	private String efatm = "0"; //SONET (6,0) ATA-tid
	//
	private String ef3039e = "0"; //SONET (6,0) Ekspedisjonsenhet
	private String efeid = ""; //TEGN (18) Ekportid
	//
	private String efknd = "0"; //SONET (8,0) Deklarant/Tr.sportør
	private String efrgd = ""; //TEGN (9) Org.nr Deklarant
	private String eftm = ""; //TEGN (3) Transportmåte
	private String eftmt = ""; //TEGN (50) Transportmåte tekst
	private String efktyp = ""; //TEGN (2) Kjøretøy type kode
	private String efktypt = ""; //TEGN (50) Kjøretøy type tekst
	private String efklk = ""; //TEGN (2) Kjøretøynasjonalitet
	private String efkmrk = ""; //TEGN (30) Kjennemerke
	private String efplk = ""; //TEGN (2) Kjøretøynasjonalitet
	private String efpmrk = ""; //TEGN (30) Kjennemerke
	private String efsjaf = ""; //TEGN (30) Sjåfør fornavn
	private String efsjae = ""; //TEGN (30) Sjåfør ettenavn
	private String efsjalk = ""; //TEGN (30) Sjåfør nasjonalitet
	private String efsjadt = "0"; //SONET (8,0) Sjåfør Sjåfør fødselsdato
	private String efbekr = ""; //TEGN (1) Sjåfør bekreftelse
	private String eferr = ""; //TEGN (50) Feilmelding ved SND
	//
	private int own_valid = 1;
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
