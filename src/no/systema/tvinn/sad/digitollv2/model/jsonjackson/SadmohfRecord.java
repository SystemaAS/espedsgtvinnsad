package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
public class SadmohfRecord extends JsonAbstractGrandFatherRecord   {
	
	private String ehst = ""; //       tegn            1       1         1        begge    status      
	private String ehst2 = ""; //      tegn            1       1         2        begge    status 2    
	private String ehst3 = ""; //      tegn            1       1         3        begge    status 3    
	private String ehuuid = ""; //     tegn           36      36         4        begge    lrn  
	private String ehuuid_own = ""; //     tegn           36      36         4        begge    lrn  
	private String ehmid = ""; //      tegn           18      18        40        begge    mrn  
	private String ehmid_own = ""; //      tegn           18      18        40        begge    mrn back up
	private Integer ehdts = 0; //      sonet        8  0       8        58        begge    sendingsdato  
	private String ehdtsStr = "";
	public String getEhdtsStr() {
		if(this.ehdts!=null && this.ehdts > 0 ){
			String tmp = String.valueOf(this.ehdts);
			if (tmp.length()< 6) {
				this.ehdtsStr = "0" + tmp;
			}else {
				this.ehdtsStr = tmp;
			}
		}
		return this.ehdtsStr;
	}
	
	private Integer ehtms = 0; //      sonet        6  0       6        66        begge    sendingstid   
	private Integer ehpro = 0; //      sonet        8  0       8        72        begge    turnummer     
	private Integer ehavd = 0; //      sonet        4  0       4        80        begge    avdeling      
	private Integer ehtdn = 0; //      sonet        7  0       7        84        begge    oppdragsnr     
	private Integer ehlnrt = 0; //     sonet        7  0       7        91        begge    løpenummer     
	private Integer ehlnrm = 0; //     sonet        4  0       4        98        begge    m-lnr innen transp  
	private Integer ehlnrh = 0; //     sonet        4  0       4       102        begge    h-lnr innen master 
	private String ehrecid = ""; //    tegn           35						  begge    ReceptacleId_No
	private Integer ehcnin = 0; //     sonet        1  0       1       106        begge    container indikator 
	private String ehvkb = ""; //      sonet       13  3      13       107        begge    total vekt 
	private String ehextref = ""; //   tegn       	  35      35              	  begge    ext.ref
	private Integer ehntk = 0; //      sonet        7  0       7       120        begge    total kolli         
	private String ehvt = ""; //       tegn           50      50       127        begge    varebeskrivelse     
	private String ehdkh = ""; //      tegn           50      50       177        begge    house dokumentnr    
	private String ehdkht = ""; //     tegn            4       4       227        begge    house dokumenttype  
	private String ehpr = ""; //       tegn            2       2       231        begge    prosedyre            
	private String ehprt = ""; //      tegn           30      30       233        begge    prosedyre tekst      
	private String ehupr = ""; //      tegn            5       5       263        begge    outgoingprosedyre    
	private String ehuprt = ""; //     tegn           30      30       268        begge    outgoingpros. tekst  
	private String ehrg = ""; //       tegn           11      11       298        begge    org.nr               
	private Integer eh0068a = 0; //    sonet        8  0       8       309        begge    sendingsdato
	private String eh0068aStr = "";
	public String getEh0068aStr() {
		if(this.eh0068a!= null && this.eh0068a > 0 ){
			String tmp = String.valueOf(this.eh0068a);
			if (tmp.length()< 6) {
				this.eh0068aStr = "0" + tmp;
			}else {
				this.eh0068aStr = tmp;
			}
		}
		return this.eh0068aStr;
	}
	private Integer eh0068b = 0; //    sonet        6  0       6       317        begge    sendingssekv         
	
	private String ehtrnr = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty = ""; //     tegn            4       4       341        begge    ref.type cude
	//flera transiteringar
	private String ehtrnr2 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty2 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr3 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty3 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr4 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty4 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr5 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty5 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr6 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty6 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr7 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty7 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr8 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty8 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr9 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty9 = ""; //     tegn            4       4       341        begge    ref.type cude
	private String ehtrnr10 = ""; //     tegn           18      18       323        begge    mrn-nr          
	private String ehtrty10 = ""; //     tegn            4       4       341        begge    ref.type cude
	
	
	private String ehetyp = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid = ""; //      tegn           18      18       377        begge    eksport id  
	
	private String ehetyp2 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt2 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid2 = ""; //      tegn           18      18       377        begge    eksport id  
	
	private String ehetyp3 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt3 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid3 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp4 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt4 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid4 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp5 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt5 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid5 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp6 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt6 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid6 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp7 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt7 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid7 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp8 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt8 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid8 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp9 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt9 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid9 = ""; //      tegn           18      18       377        begge    eksport id  
	private String ehetyp10 = ""; //     tegn            2       2       345        begge    type            
	private String ehetypt10 = ""; //    tegn           30      30       347        begge    type tekst      
	private String eheid10 = ""; //      tegn           18      18       377        begge    eksport id  
	
	private Integer ehkns = 0; //      sonet        8  0       8       395        begge    avsender        
	private String ehrgs = ""; //      tegn           17      17       403        begge    org.nr avsender
	private Integer ehtpps = 0;   //sonet           1   Type of person
	private String ehnas = ""; //      tegn           30      30       420        begge    navn avsender       
	private String ehna2s = ""; //     tegn           30      30       450        begge    subdivvision avsend.
	private String ehad1s = ""; //     tegn           30      30       480        begge    gateadr. avsender   
	private String ehnrs = ""; //      tegn           15      15       510        begge    husnr avsender      
	private String ehpns = ""; //      tegn            9       9       525        begge    postnr avsender     
	private String ehpss = ""; //      tegn           24      24       534        begge    p.sted avsender     
	private String ehlks = ""; //      tegn            2       2       558        begge    l.kode avsender    
	private String ehpbs = ""; //      tegn           15      15       560        begge    postbox avsender   
	private String ehems = ""; //      tegn           50      50       575        begge    epost/tlf avsender 
	private String ehemst = ""; //     tegn            2       2       625        begge    kodetype mottaker  
	private String own_ehems_telephone = ""; // 
	private String own_ehems_email = ""; // 
	
	private Integer ehknm = 0; //      sonet        8  0       8       627        begge    mottaker           
	private String ehrgm = ""; //      tegn           17      17       635        begge    org.nr mottaker
	private Integer ehtppm = 0;   //sonet           1   Type of person
	private String ehnam = ""; //      tegn           30      30       652        begge    navn mottaker      
	private String ehna2m = ""; //     tegn           30      30       682        begge    subdivvision mottak.
	private String ehad1m = ""; //     tegn           30      30       712        begge    gateadr. mottaker   
	private String ehnrm = ""; //      tegn           15      15       742        begge    husnr mottaker      
	private String ehpnm = ""; //      tegn            9       9       757        begge    postnr mottaker     
	private String ehpsm = ""; //      tegn           24      24       766        begge    p.sted mottaker     
	private String ehlkm = ""; //      tegn            2       2       790        begge    l.kode mottaker     
	private String ehpbm = ""; //      tegn           15      15       792        begge    postbox mottaker    
	private String ehemm = ""; //      tegn           50      50       807        begge    ep.adr/tlf mottaker 
	private String ehemmt = ""; //     tegn            2       2       857        begge    kodetype mottaker 
	private String own_ehemm_telephone = ""; // 
	private String own_ehemm_email = ""; // 
	
	private String ehlka = ""; //      tegn            2       2       859        begge    land of acceptance  
	private String ehsda = ""; //      tegn            5       5       861        begge    place of acceptance 
	private String ehsdat = ""; //     tegn           30      30       866        begge    place of delivery   
	private String ehlkd = ""; //      tegn            2       2       896        begge    land of delivery     
	private String ehsdd = ""; //      tegn            5       5       898        begge    place of delivery    
	private String ehsddt = ""; //     tegn           30      30       903        begge    place of delivery
	//
	private String incltdoc = ""; //   tegn           70
	
	private SadmotfRecord transportDto; //Dto for transport to get som fields
	private SadmomfRecord masterDto; //Dto for master to get som fields
	

	private Collection<SadmoifRecord> listItemLines = null;
	public void setListItemLines (Collection<SadmoifRecord> value){ this.listItemLines = value;}
	public Collection<SadmoifRecord> getListItemLines(){ return this.listItemLines; }
	
	
	//private String own_emdkmUnique = "";  
	//private Boolean own_okToDelete = true;
	//private Boolean own_invalidHousesExist = false;
	private String own_resultAjaxText = "";
	
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
