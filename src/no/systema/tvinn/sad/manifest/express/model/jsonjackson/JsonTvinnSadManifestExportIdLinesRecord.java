/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
@Data
public class JsonTvinnSadManifestExportIdLinesRecord extends JsonAbstractGrandFatherRecord {
	
	private String cmst = ""; //tegn            1       1         1        begge    status    
	private String cmavd = "0"; //sonet        4  0       4         2        begge    avdeling  
	private String cmtdn = "0"; //sonet        7  0       7         6        begge    oppdragsnr
	private String cmli = "0"; //sonet        2  0       2        13        begge    linjenr   
	private String cmavde = "0";//sonet        4  0       4        15        begge    svensk eksp.avdeling
	private String cmtdne = "0";//sonet        7  0       7        19        begge    svensk eksp.oppd.nr 
	private String cmetyp = ""; //tegn            2       2        26        begge    type        
	private String cmetypt = ""; //tegn           30      30        28        begge    type tekst  
	private String cmeid = ""; 	//tegn           18      18        58        begge    eksport id  
	private String cmeser = ""; //tegn            1       1        76        begge    sertifisert 
	
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
