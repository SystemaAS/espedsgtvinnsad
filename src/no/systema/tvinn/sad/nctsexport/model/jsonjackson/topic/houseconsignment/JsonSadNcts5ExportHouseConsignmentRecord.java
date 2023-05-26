/**
 * 
 */
package no.systema.tvinn.sad.nctsexport.model.jsonjackson.topic.houseconsignment;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date May 25, 2023
 *
 */
@Data
public class JsonSadNcts5ExportHouseConsignmentRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String avd = null;
	private String opd = null;
	private String sign = null;
	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() {
		if(this.datum != null && !"".equals(this.datum)){
			return dateFormatter.convertToDate_NO(this.datum);
		}else{
			return this.datum;
		}
	}
	
	private String tcavd = null;
	private String tctdn = null;
	private String tcli = null;
	private String tcln = null;
	private String tcdk = null; //5 char DEKLARASJONSTYPE
	private String tcalk = null;//2 char AVSENDERSLAND
	private String tcblk = null;//2 char BESTEMMELSESLAND
	private String tcvktb = null;//13.3 double Bruttovekt
	private String tcucr = null; //35 char REF.NR UCR
 	private String tcavd2 = null;//4 int AVD. FOR HENTET OPD
 	private String tctdn2 = null;//7 int OPD. FOR HENTET OPD
 	private String tcxext = null;//35 char EKSTERN REF
 	private String tcrole = null;//3 char LEVERANDØR ROLLE
 	private String tcidr = null;//17 char LEVERANDØR ID
 	private String tctaty = null;//2 char TYPE IDENTIFIKASJON
 	private String tctaid = null;//35 char IDENTITET
 	private String tctalk = null;//2 char Landk Tr.middel
 	private String tcpdty = null;//4 char DOKUMENT TYPE
 	private String tcpdrf = null;//70 char DOKUMENT REF.
 	private String tcpdin = null;//35 char INFORMASJON
 	
 	private String tcsdty = null;//4 char DOKUMENT TYPE
 	private String tcsdrf = null;//70 char DOKUMENT REF.
 	private String tcsdln = null;//5 char LINE ITEM NUMBER
 	private String tcsdin = null;//35 INFORMASJON
 	private String tctdty = null;//4 char DOKUMENT TYPE
 	private String tctdrf = null;//70 char DOKUMENT REF.
 	
 	private String tcadty = null;//4 char REFERENSE TYPE
 	private String tcadrf = null;//70 char REFERANSE
 	private String tcaicd = null;//5 INFORMASJON KODE
 	private String tcaitx = null;//120 char TEKST
 	private String tctrch = null;//1 char BETALINGSMÅTE
 	
 	
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
