/**
 * 
 */
package no.systema.tvinn.sad.sadimport.model.jsonjackson.topic;

import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import no.systema.tvinn.sad.util.TvinnSadDateFormatter;

import java.lang.reflect.Field;
import java.util.*;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Feb, 2024
 *
 */
@Data
public class SadImpDigRecord extends JsonAbstractGrandFatherRecord {
	private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private Integer siavd = 0;
	private String sisg = "";
	private String sist = "";
	private Integer sitdn = 0;
	
	//date
	private String sidt = "";
	/*public void setSidt(String value) { this.sidt = value; }
	public String getSidt() {
		if(this.sidt != null && !"".equals(this.sidt)){
			return dateFormatter.convertToDate_NO(this.sidt);
		}else{
			return this.sidt;
		}
	}*/
	private String sidtno = "";
	private String sidt_to = ""; //only for date purposes in the select
	private String sidtg = "";
	private String sitrid = ""; //bilregnr
	private String sitle = ""; //Exped.enhet
	private String sitll = ""; //Løpenr
	private String h_xref = ""; //Ext.ref
	private String sinas = ""; //Avs
	private String sinak = ""; //Mott
	private String sivkb = ""; //Brutto vekt
	private String sign = ""; //Godsnr
	
	//Digitoll fields
	private Integer etlnrt = 0; 
	private Integer etpro = 0;
	private String etkmrk = "";
	private String etetad = "";
	private String etetadno = "";
	private String emdkm = ""; 
	private String emvkb = "";
	private String ehrgm = "";
	private Integer ehpro = 0;
	private Integer ehtdn = 0;
	private String ehdkh = "";
	private String ehvkb = "";
	private String ehvt = "";
	
	
	
	/*
	private String avd = "";
	private String opd = "";
	private String refnr = "";
	//Godsnr = sign
	private String sign = "";
	private String sg = "";
	private String datum = "";
	private String status = "";
	private String avsNavn = "";
	private String motNavn = "";
	private String aart = "";
	private String sitll = "";
	private String sitle = "";
	private String sivkb = "";
	private String simi = "";
	private String h_xref = "";
	private String o2_simf = "";
	private String o2_sist = "";
	private String o2_sidt = "";
	private String o2_sitll = "";
	private String epjn = "";
	private String detaout= "";
	*/

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
