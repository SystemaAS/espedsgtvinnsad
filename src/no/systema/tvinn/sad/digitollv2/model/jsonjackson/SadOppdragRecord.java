package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
public class SadOppdragRecord extends JsonAbstractGrandFatherRecord  {
	
	
	private String siavd;
    private String sitdn;
    private String sisg;
    private String sidt;
    private String sikns;
    private String sinas;
    private String siads1;
    private String siads2;
    private String siads3;
    private String sintk;
    private String sivkb;
    private String sikdc;
    private String siknk;
    private String sirg;
    private String sinak;
    private String siadk1;
    private String siadk2;
    private String siadk3;
    private String silka;
    private String sitlf;
    private String sinad;
    private String silv;
    private String silvt;
    private String sign;
    private String wehrg;
    private String weh0068a;
    private String weh0068b;
    //Cundf
    private String ehrgs;  //": "SE789431221123333",
    private String ehnas;  //": "TRUCKING INTERNATIONAL CORP.",
    private String ehad1s; //": "EGENSEVEI 24 - 32",
    private String ehpns;  //": "2270",
    private String ehpss;  //": "Kastrup",
    private String ehlks;  //": "DK",
    private String ehems;  //": "efraim.barkbit@skogen.se",
    private String ehemst; //": "EM",
    private String ehrgm;  //": "10067911119",
    private String ehnam;  //": "SYSTEMA A/S TEST",
    private String ehad1m; //": "St. Halvardsgt. 33 A",
    private String ehpnm;  //": "0193",
    private String ehpsm;  //": "OSLO",
    private String ehlkm;  //": "NO",
    private String ehemm;  //": "trond@systema.no",
    private String ehemmt; //": "EM",
    
    private String stiarf;
    private String fssok;
    private String sitrid;
    
    private String wfssok_cr; 
    private String wfssokmrn; //Mrn nummer
    private String wfssokexp; //Ekspid
    private String wfssokexp2; //Ekspid2
    private String wfssokexp3; //Ekspid3
    private String wfssokexp4; //Ekspid4
    private String wfssokexp5; //Ekspid5
    private String wfssokexp6; //Ekspid6
    private String wfssokexp7; //Ekspid7
    private String wfssokexp8; //Ekspid8
    private String wfssokexp9; //Ekspid9
    
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



/*
{
  "user": "OSCAR",
  "tur": "501954",
  "errMsg": "",
  "orderList": [
    {
      "siavd": "1",
      "sitdn": "155973",
      "sisg": "CB",
      "sidt": "20230920",
      "sikns": "10008568",
      "sinas": "TRUCKING INTERNATIONAL CORP.",
      "siads1": "EGENSEVEI 24 - 32",
      "siads2": "DK-2270 KASTRUP",
      "siads3": "DANMARK",
      "sintk": "1",
      "sivkb": "100",
      "sikdc": "0",
      "siknk": "1",
      "sirg": "10067911119",
      "sinak": "SYSTEMA A/S TEST",
      "siadk1": "St. Halvardsgt. 33 A ÆØÅ",
      "siadk2": "P.B 1234",
      "siadk3": "6001 ÅLESUND",
      "silka": "AA",
      "sitlf": "22 00 00 00",
      "sinad": "CARGONET",
      "silv": "FOB",
      "silvt": "DANMARK",
      "sign": "2013",
      "wehrg": "936809219",
      "weh0068a": "20230920",
      "weh0068b": "3",
      "ehrgs": "SE789431221123333",
      "ehnas": "TRUCKING INTERNATIONAL CORP.",
      "ehad1s": "EGENSEVEI 24 - 32",
      "ehpns": "2270",
      "ehpss": "Kastrup",
      "ehlks": "DK",
      "ehems": "efraim.barkbit@skogen.se",
      "ehemst": "EM",
      "ehrgm": "10067911119",
      "ehnam": "SYSTEMA A/S TEST",
      "ehad1m": "St. Halvardsgt. 33 A",
      "ehpnm": "0193",
      "ehpsm": "OSLO",
      "ehlkm": "NO",
      "ehemm": "trond@systema.no",
      "ehemmt": "EM",
      "sitarf": "TROND HYTTAN"
    }
*/