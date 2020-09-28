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
 * @date Sep 2020
 * 
 */
@Data
public class JsonTvinnSadManifestCargoLinesRecord extends JsonAbstractGrandFatherRecord {
	
	private String clst = ""; //TEGN (1) Status
	private String clavd = "0"; //SONET (4,0) Avdeling
	private String clpro = "0"; //SONET (8,0) Turnr
	private String cltdn = "0"; //SONET (8,0) Oppnr
	private String clrg = ""; //TEGN (11) Orgnr
	private String cl0068a = "0"; //SONET (8,0) Sendingsdato
	private String cl0068b = "0"; //SONET (6,0) Sendingssekv
	
	private String cltrnr = ""; //TEGN (18) MRN-nr
	private String clnas = ""; //TEGN (30) Avsender
	private String clnak = ""; //TEGN (30) Mottaker
	
	
	private String clntk = "0"; //SONET (7,0) Total kolli
	private String clvkb = "0"; //SONET (9,0) Total vekt
	private String clvt = ""; //TEGN (30) Varebeskrivelse
	
	private String cltrid = ""; //TEGN (17) Bilnr
	private String cl3039e = "0"; //SONET (6,0) Ekspedisjonsenhet
	private String cllkf = ""; //TEGN (2) Land of loading
	private String clsdf = ""; //TEGN (5) Place of loading
	private String clsdft = ""; //TEGN (30) Place of loading text
	private String cllkt = ""; //TEGN (2) Land of unloading
	private String clsdt = ""; //TEGN (5) Place of unloading
	private String clsdtt = ""; //TEGN (30) Place of loading text
	private String clpr = ""; //TEGN (2) Procedure
	private String clprt = ""; //TEGN (30) Procedure text
	
	private String cletyp = ""; //TEGN (2) Type
	private String cletypt = ""; //TEGN (30) Type text
	private String cleid = ""; //TEGN (18) Export id
	private String cleser = ""; //TEGN (1) Certified / Sertifisert
	
	
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
