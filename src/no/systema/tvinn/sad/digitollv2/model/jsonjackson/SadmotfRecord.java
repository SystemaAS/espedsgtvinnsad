package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmotfRecord extends JsonAbstractGrandFatherRecord  {
	
	private String etst = ""; // varchar(1) , 
	private Integer etavd = 0; // numeric(4) default 0,
	private Integer etpro = 0; // numeric(8) default 0, 
	private Integer etlnrt = 0; // numeric(7) default 0, Løpenummer
	private Integer etdtr = 0; // numeric(8) default 0, Reg.dato
	private String etdtrStr = "";
	public String getEtdtrStr() {
		if(this.etdtr!=null && this.etdtr > 0 ){
			String tmp = String.valueOf(this.etdtr);
			if (tmp.length()< 6) {
				this.etdtrStr = "0" + tmp;
			}else {
				this.etdtrStr = tmp;
			}
		}
		return this.etdtrStr;
	}
	private String etsg = ""; // varchar(3)
	private String etst2 = ""; // varchar(1) , Status om manifest
	private String etuuid = ""; // varchar(36), LRN
	private String etuuid_own = ""; // varchar(36), LRN
	private String etmid = ""; // varchar(18),  MRN
	private String etmid_own = ""; // varchar(18),  MRN back up
	
	private String etentval = "0";
	private String etentoff = "";
	private String etenttim = "0";
	
	private String etst3 = ""; // varchar(1) , Status om inpassering
	private Integer etdtin = 0; // numeric(8) , Innsendingsdato
	private Integer etetad = 0; // numeric(8) default 0, ETA date
	
	private String etetadStr = "";
	public String getEtetadStr() {
		if(this.etetad > 0 ){
			String tmp = String.valueOf(this.etetad);
			if (tmp.length()< 6) {
				this.etetadStr = "0" + tmp;
			}else {
				this.etetadStr = tmp;
			}
		}
		return this.etetadStr;
	}
	
	private Integer etetat = 0; // numeric(6) default 0, ETA tid 
	private String etetatStr = "";
	public String getEtetatStr() {
		if(this.etetat > 0 ){
			String tmp = String.valueOf(this.etetat);
			if (tmp.length()< 4) {
				this.etetatStr = "0" + tmp;
			}else {
				this.etetatStr = tmp;
			}
		}
		return this.etetatStr;
	}
	
	private Integer etshed = 0; // numeric(8) default 0, Sheduled avg-dt
	private String etshedStr = "";
	public String getEtshedStr() {
		if(this.etshed!=null && this.etshed > 0 ){
			String tmp = String.valueOf(this.etshed);
			if (tmp.length()< 6) {
				this.etshedStr = "0" + tmp;
			}else {
				this.etshedStr = tmp;
			}
		}
		return this.etshedStr;
	}
	

	private Integer etshet = 0; // numeric(6) default 0, Sheduled avg-tid
	private String etshetStr = "";
	public String getEtshetStr() {
		if(this.etshet!=null && this.etshet > 0 ){
			String tmp = String.valueOf(this.etshet);
			if (tmp.length()< 4) {
				this.etshetStr = "0" + tmp;
			}else {
				this.etshetStr = tmp;
			}
		}
		return this.etshetStr;
	}
	
	
	
	private Integer etknr = 0; // numeric(8),  Representative
	private String etrgr = ""; // varchar(17), Orgnr Repres.
	private String etnar = ""; // varchar(30), Navn
	private String etna2r = ""; // varchar(30), SubDivvision
	private String etad1r = ""; // varchar(30), Gateadr.
	private String etnrr = ""; // varchar(15), Husnr.
	private String etpnr = ""; // varchar(9),  Postnr.
	private String etpsr = ""; // varchar(24), Poststed
	private String etlkr = ""; // varchar(2) , Landkod
	private String etpbr = ""; // varchar(15) , Postbox
	
	private String etemr = ""; // varchar(50), Epostadr/tlf  
	private String etemrt = ""; // varchar(2),   Epostadr/tlf typ (TE-EM)
	private String own_etemr_telephone = ""; // 
	private String own_etemr_email = ""; // 
	private String etkmrk = ""; // varchar(30), Kjennemerke (reg.nr)
	private String etktm= ""; // varchar(4), Transportmiddel type (typeOfMeansOfTransport)
	private String etktyp = ""; // varchar(2), Kjøretøy type (typeOfIdentification)
	private String etklk = ""; // varchar(2), Kjøretøynasjonalitet
	private String etcref = ""; // varchar(17), Convay/turRef/flight
	private String etktkd = ""; // varchar(1), Mode av transportKD (modeOfTransportCode)
	
	private String etsjaf = ""; // varchar(50), Sjåfør navn
	private String etems = ""; // varchar(50), Epostadr/tlf
	private String etemst = ""; // varchar(2), Epostadr/tlf typ (TE-EM) 
	
	private Integer etknt = 0; // numeric(8),  Transportör
	private String etrgt = ""; // varchar(17), Orgnr Repres.
	private String etnat = ""; // varchar(30), Navn
	private String etna2t = ""; // varchar(30), SubDivvision
	private String etad1t = ""; // varchar(30), Gateadr.
	private String etnrt = ""; // varchar(15), Husnr.
	private String etpnt = ""; // varchar(9),  Postnr.
	private String etpst = ""; // varchar(24), Poststed
	private String etlkt = ""; // varchar(2) , Landkod
	private String etpbt = ""; // varchar(15) , Postbox
	private String etemt = ""; // varchar(50), Epostadr/tlf 
	private String etemtt = ""; // varchar(2), Epostadr/tlf typ (TE-EM)
	private String own_etemt_telephone = ""; // 
	private String own_etemt_email = ""; // 
	
	private String etdkm = ""; // varchar(50), Master Dokumentnr 
	private String etdkmt = ""; // varchar(4), Master Dokumenttype
	
	private String ettsd = ""; // varchar(8), Passeringstollsted
	private String emerr = ""; // varchar(50), Feilmelding ved SND
	
	private Boolean own_okToSend = true;
	private Boolean own_okToDelete = true;
	private Boolean own_invalidMastersExist = false;
	private Boolean own_invalidHousesExist = false;
	private Boolean own_unsentMastersExist = false;
	private Boolean own_unsentHousesExist = false;
	private Boolean own_invalidSekvnrOnHouse = false;
	private Boolean isDuplicateTurnr = false;
	
	private Collection<SadmomfRecord> listMasters = null;
	public void setListMasters(Collection<SadmomfRecord> value){ this.listMasters = value;}
	public Collection<SadmomfRecord> getListMasters(){ return this.listMasters; }
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@JsonIgnore
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
	
}



/*
DROP TABLE SYSPEDF.OSCDIT 

CREATE TABLE SYSPEDF.OSCDIT (
  ETST VARCHAR(1) , ETUUID VARCHAR(36), 
  ETMID VARCHAR(18), ETAVD NUMERIC(4) DEFAULT 0,
  ETPRO NUMERIC(8) DEFAULT 0, 
  ETDTR NUMERIC(8) DEFAULT 0, ETSG VARCHAR(8),
  ETNAR VARCHAR(30), ETRGR VARCHAR(17), 
  ETSTR VARCHAR(3), ETPSR VARCHAR(24),
  ETLKR VARCHAR(2) , ETAD1R VARCHAR(30), 
  ETPNR VARCHAR(9), ETEMR VARCHAR(50),
  ETEMRT VARCHAR(2), ETKMRK VARCHAR(30) , 
  ETKTYP VARCHAR(2), ETKTM VARCHAR(4) ,
  ETKLK VARCHAR(2), ETKTKD VARCHAR(1), 
  ETSJAF VARCHAR(50), ETSJAEMR VARCHAR(50), 
  ETSJAEMRT VARCHAR(2), ETNAT VARCHAR(30) , 
  ETRGT VARCHAR(17), ETPST VARCHAR(24), 
  ETLKT VARCHAR(2), ETAD1T VARCHAR(30), 
  ETPNT VARCHAR(9), ETEMT VARCHAR(50), 
  ETEMTT VARCHAR(2),ETTSD VARCHAR(8), 
  ETETAD NUMERIC(8) DEFAULT 0, 
  ETETAT NUMERIC(6) DEFAULT 0, 
  ETSTAD NUMERIC(8) DEFAULT 0, 
  ETSTAT NUMERIC(6) DEFAULT 0, 
  ETDKM VARCHAR(50), ETDKMT VARCHAR(4)
  )





"documentIssueDate": "2022-04-20T07:49:52Z",
  "representative": {   
    "name": "Bring AS",                   -->ETNAR 30
    "identificationNumber": "951357482",  -->ETRGR 17
    "status": "2",                        -->ETSTR 3
    "address": {
      "city": "Oslo",                     -->ETPSR 24
      "country": "NO",                    -->ETLKR 2
      "subDivision": "string",
      "streetLine": "Hausemanns gate",    -->ETAD1R 30
      "postcode": "0530",                 -->ETPNR 9
      "streetAdditionalLine": "string",  
      "number": "52F",
      "poBox": "P.B. 0201"
    },
    "communication": [
      {
        "identifier": "en-epost@mail.no", -->ETEMR 50
        "type": "ME"                      -->ETEMRT 2
      }
    ]
  },
  "activeBorderTransportMeans": {
    "identificationNumber": "3535353535353535",  -->ETKMRK 30
    "typeOfIdentification": "40",                -->ETKTYP 2
    "typeOfMeansOfTransport": "4000",            -->ETKTM 4
    "conveyanceReferenceNumber": "stringstringstrin",
    "countryCode": "NO",                         -->ETKLK 2
    "modeOfTransportCode": "4",                  -->ETKTKD 1
    "operator": {
      "name": "Kari Nordmann",        -->ETSJAF 50
      "communication": [
        {
          "identifier": "en-epost@mail.no",   -->ETSJAEMR 50
          "type": "EM"                        -->ETSJAEMRT 2
        }
      ]
    }
  },
  "carrier": {
    "name": "SAS Norge",                    -->ETNAT 30
    "identificationNumber": "961510740",    -->ETRGT 17
    "address": {
      "city": "string",                     -->ETPST 24
      "country": "st",                      -->ETLKT 2
      "subDivision": "string",
      "postcode": "string",                 -->ETPNT 9
      "streetLine": "string",               -->ETAD1T 30
      "streetAdditionalLine": "string",
      "number": "string",
      "poBox": "string"
    },
    "communication": [
      {
        "identifier": "en-epost@mail.no",   -->ETEMT 50
        "type": "EM"                        -->ETEMTT 2
      }
    ]
  },
"customsOfficeOfFirstEntry": {
    "referenceNumber": "NO351001"           -->ETTSD 8
  },
  "estimatedDateAndTimeOfArrival": "2023-02-02T12:00:00Z",    -->ETETAD(8 SONET) + ETETAT (6 SONET)
  "scheduledDateAndTimeOfArrival": "2023-02-02T12:00:00Z",    -->ETSTAD(8 SONET) + ETSTAT (6 SONET)
  "consignmentMasterLevel": [
    {
      "transportDocumentMasterLevel": {
        "documentNumber": "string",         -->ETDKM  50
        "type": "N741"                      -->ETDKMT 4
      }
    }
  ]
}




insert into oscdit (
etavd, etpro, etnar,
etrgr, etstr, etpsr, etlkr, etad1r,  
etpnr, etemr, etemrt,
etkmrk, etktyp, etktm, etklk,
etktkd, etsjaf, etsjaemr, etsjaemrt,
etnat, etrgt, etpst, etlkt,
etad1t, etpnt, etemt, etemtt, ettsd,
etetad, etetat, etstad, etstat,
etdkm, etdkmt
  ) values (
1, 501941, 'VesternGeco AS',
'936809219', '2', 'OSLO', 'NO', 'Skippergt. 8-10',
'0152', '22335760', 'TE',
'AA123456', '10', '31', 'NO',
'4', 'OLA NORMANN', 'ola@normann.com', 'EM',
'CarrierGeco AS', '931234998', 'OSLO', 'NO',
'Dodensvei. 1-2', '0152', 'tarzan@dvei.com', 'EM', 'NO351001',
20231001, 1700, 20231002, 1600,
'TEST-FraktDocNr-20230511', 'N730'
)
*/