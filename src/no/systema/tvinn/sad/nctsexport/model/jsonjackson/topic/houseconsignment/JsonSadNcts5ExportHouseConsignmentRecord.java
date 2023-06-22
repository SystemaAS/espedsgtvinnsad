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
 * maxOccurs 99 (HouseConsignment)
 */
@Data
public class JsonSadNcts5ExportHouseConsignmentRecord extends JsonAbstractGrandFatherRecord {
	//private TvinnSadDateFormatter dateFormatter = new TvinnSadDateFormatter();
	
	private String avd = null;
	private String opd = null;
	private String sign = null;
	private String datum = null;
	/*
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() {
		if(this.datum != null && !"".equals(this.datum)){
			return dateFormatter.convertToDate_NO(this.datum);
		}else{
			return this.datum;
		}
	}*/
	
	//parter NCTSEC - data file
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
 	//AdditionalSupplyChainActor (0-99)
 	private String tcrole = null;//3 char LEVERANDØR ROLLE /AdditionalSupplyChainActor/role (0-99)
 	private String tcidr = null;//17 char LEVERANDØR ID /AdditionalSupplyChainActor/identificationNumber
 	
 	//DepartureTransportMeans  (0-999)
 	private String tctaty = null;//2 char TYPE IDENTIFIKASJON /DepartureTransportMeans/typeOfIdentification (0-999)
 	private String tctaid = null;//35 char IDENTITET /DepartureTransportMeans/identificationNumber
 	private String tctalk = null;//2 char Landk Tr.middel /DepartureTransportMeans/nationality
 	//Previous Document (0-99)
 	private String tcpdty = null;//4 char DOKUMENT TYPE /PreviousDocument (0-99)
 	private String tcpdrf = null;//70 char DOKUMENT REF. /PreviousDocument
 	private String tcpdin = null;//35 char INFORMASJON /PreviousDocument
 	//Supporting Document (0-99)
 	private String tcsdty = null;//4 char DOKUMENT TYPE /SupportingDocument (0-99)
 	private String tcsdrf = null;//70 char DOKUMENT REF. /SupportingDocument
 	private String tcsdln = null;//5 char LINE ITEM NUMBER /SupportingDocument/documentLineItemNumber
 	private String tcsdin = null;//35 INFORMASJON /SupportingDocument/complementOfInformation
 	//Transport Document (0-99)
 	private String tctdty = null;//4 char DOKUMENT TYPE /TransportDocument/type (0-99)
 	private String tctdrf = null;//70 char DOKUMENT REF. /TransportDocument/referenceNr 
 	//Additional Reference (0-99)
 	private String tcadty = null;//4 char REFERENSE TYPE /AdditionalReference/type (0-99)
 	private String tcadrf = null;//70 char REFERANSE /AdditionalReference/referenceNumber
 	//Additional information (0-99)
 	private String tcaicd = null;//5 INFORMASJON KODE /AdditionalInformation/code (0-99)
 	private String tcaitx = null;//120 char TEKST /AdditionalInformation/text
 	//TransportCharges (1)
 	private String tctrch = null;//1 char BETALINGSMÅTE /TransportCharges/methodOfPayment minOccurs(0)
 	
 	//parter NCTSECAM - data file
 	private String tckns = null;//8 int kundenr selger
 	private String tcnas = null;//30 char navn
 	private String tcad1s = null;//30 char adress
 	private String tcpns = null;//9 char postnr
 	private String tcpss = null;//24 char poststed
 	private String tclks = null;//2 char landkode
 	private String tctins = null;//17 tin-kode
 	private String tccps = null;//30 kontaktperson selger
 	private String tctls = null;//20 telefon
 	private String tcems = null;//50 email
 	
 	private String tcknk = null;//8 int kundenr kjøper
 	private String tcnak = null;//30 char navn
 	private String tcad1k = null;//30 char adress
 	private String tcpnk = null;//9 char postnr
 	private String tcpsk = null;//24 char poststed
 	private String tclkk = null;//2 char landkode
 	private String tctink = null;//17 tin-kode
 	
 	
 	
 	
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
