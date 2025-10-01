package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import no.systema.jservices.model.dao.entities.IDao;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SadmoafOnlyTransportRecord extends JsonAbstractGrandFatherRecord  {
	
	private String etst =""; //       status                                   1    1     1         a  
	private Integer etavd = -1; //       avdeling                                 2    5     4   4   0 s  
	private Integer etpro = 0; //       turnummer                                6   13     8   8   0 s  
	private Integer etlnrt  = 0; //    løpenummer                              14   20     7   7   0 s  
	private Integer etdtr = 0; //     registreringsdato                       21   28     8   8   0 s  
	private String etsg  =""; //       signatur                                29   31     3         a  
	private String etst2 =""; //       status om manifest                      32   32     1         a  
	private String etuuid =""; //      lrn                                     33   68    36         a  
	private String etmid =""; //       mrn                                     69   86    18         a  
	private String etmid_own =""; //   mrn backup                              87  104    18         a  
	private String etst3 =""; //       status om innpass.                     105  105     1         a  
	private Integer etdtin = 0; //     innsendingsdato                        106  113     8   8   0 s  
	private Integer etetad = 0; //     estimert ank. eta                      114  121     8   8   0 s  
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
	private Integer etetat = 0; //     eta tid                                122  127     6   6   0 s  
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
	private Integer etshed = 0; //     sheduled avg-dt                        128  135     8   8   0 s
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
	private Integer etshet = 0; //     sheduled avg-tid                       136  141     6   6   0 s
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
	private Integer etknr = 0; //      representative                         142  149     8   8   0 s
	private String etrgr =""; //       org.nr representativ                   150  166    17         a
	private String etnar =""; //       navn representative                    167  196    30         a
	private String etna2r =""; //      subdivvision repre.                    197  226    30         a
	private String etad1r =""; //      gateadr. repre.                        227  256    30         a
	private String etnrr =""; //       husnr repre.                           257  271    15         a
	private String etpnr =""; //       postnr representativ                   272  280     9         a
	private String etpsr =""; //       p.sted representativ                   281  304    24         a
	private String etlkr =""; //       l.kode representativ                   305  306     2         a
	private String etpbr =""; //       postbox repre.                         307  321    15         a
	private String etemr =""; //       epostadr/tlf repre.                    322  371    50         a
	private String etemrt =""; //      kodetype repre.                        372  373     2         a
	private String etemrx =""; //       epostadr/tlf repre.                    322  371    50         a
	private String etemrtx =""; //      kodetype repre.                        372  373     2         a
	private String own_etemr_telephone = ""; // 
	private String own_etemr_email = ""; // 
	private String etkmrk =""; //      kjennemerke                            374  403    30         a
	private String etktyp =""; //      kjøretøy type                          404  405     2         a
	private String etktm =""; //       transportmiddel type                   406  409     4         a
	private String etklk =""; //       kjøretøynasjonalitet                   410  411     2         a
	private String etcref =""; //      convay/turref/flight                   412  428    17         a
	private String etktkd =""; //      mode av transportkd                    429  429     1         a
	private String etsjaf =""; //      sjåfør navn                            430  479    50         a
	private String etems =""; //       epostadr/tlf repre.                    480  529    50         a
	private String etemst =""; //      kodetype repre.                        530  531     2         a
	private Integer etknt =0; //     transportør                            532  539     8   8   0 s
	private String etrgt =""; //       org.nr transportør                     540  556    17         a
	private String etnat =""; //       navn transportør                       557  586    30         a
	private String etna2t =""; //      subdivvision trans.                    587  616    30         a
	private String etad1t =""; //      gateadr. trans.                        617  646    30         a
	private String etnrt =""; //       husnr trans.                           647  661    15         a
	private String etpnt =""; //       postnr transportør                     662  670     9         a
	private String etpst =""; //       p.sted transortør                      671  694    24         a
	private String etlkt =""; //       l.kode transportør                     695  696     2         a
	private String etpbt =""; //       postbox trans.                         697  711    15         a
	private String etemt =""; //       epostadr/tlf trans.                    712  761    50         a
	private String etemtt =""; //      kodetype trans.                        762  763     2         a
	private String etemtx =""; //       epostadr/tlf trans.                    712  761    50         a
	private String etemttx =""; //      kodetype trans.                        762  763     2         a
	private String own_etemt_telephone = ""; // 
	private String own_etemt_email = ""; // 
	private String etdkm =""; //       master dokumentnr                      764  813    50         a
	private String etdkmt =""; //      master dokumenttype                    814  817     4         a
	private String ettsd =""; //       passeringstollsted                     818  825     8         a
	private String eterr =""; //       feilmelding ved snd                    826  875    50         a  
	
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
