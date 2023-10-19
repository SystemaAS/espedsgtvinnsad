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
public class SadTurRecord extends JsonAbstractGrandFatherRecord  {
	
	private String tust  = ""; //       turstatus                                1    1     1         a  
	private String tuavd = ""; //       avdeling                                 2    5     4   4   0 s  
	private String tupro = ""; //       turnummer                                6   13     8   8   0 s  
	private String tudt = ""; //        dato fra                                14   21     8   8   0 s  
	private String tukna = ""; //       agentnummer                             22   29     8   8   0 s  
	private String tuknt = ""; //       transportørsnr.                         30   37     8   8   0 s  
	private String tunat = ""; //       transportørnavn                         38   67    30         a  
	private String tuad1t = ""; //      transportør adr1                        68   97    30         a  
	private String tuad2t = ""; //      transportør adr2                        98  127    30         a  
	private String tuad3t = ""; //      transportør adr3                       128  157    30         a  
	private String tubiln = ""; //      bilnummer                              158  167    10         a  
	private String tutarf = ""; //      tariffør                               168  182    15         a  
	private String tulk  = ""; //       landkode fra                           183  184     2         a  
	private String tuxxx1 = ""; //      ledig                                  185  185     1         a  
	private String tusdf = ""; //       sted fra 20 lang                       186  205    20         a
	private String tusdt = ""; //       sted til 20 lang                       206  225    20         a
	private String tuao  = ""; //       antall oppdrag                         226  230     5   5   0 s
	private String tuts  = ""; //       antall stykk                           231  234     4   7   0 p
	private String tutvkt = ""; //      total vekt                             235  239     5   9   0 p
	private String tutlm  = ""; //      total lm faktura                       240  243     4   4   2 s
	private String tutm3  = ""; //      total kubikk                           244  250     7   7   3 s
	private String tulast = ""; //      laste                                  251  251     1   1   0 s
	private String tubusj = ""; //      budsjett                               252  256     5   5   0 s
	private String tuxxx2 = ""; //      ledige                                 257  260     4         a
	private String tutst1 = ""; //      staprivate String tus 1                               261  261     1         a
	private String tutst2 = ""; //      staprivate String tus 2                               262  262     1         a
	private String tutst3 = ""; //      staprivate String tus 3                               263  263     1         a
	private String tutst4 = ""; //      staprivate String tus 4                               264  264     1         a
	private String tutst5 = ""; //      staprivate String tus 5                               265  265     1         a
	private String tuavre = ""; //      avregnet                               266  266     1         a
	private String tuavnr = ""; //      avregningsnr                           267  270     4   7   0 p
	private String turund  = ""; //     rundprivate String tur                                271  278     8   8   0 s
	private String turacc = ""; //      regnskaps.århundre                     279  280     2   2   0 s
	private String turaar = ""; //      regnskaps.år                           281  282     2   2   0 s
	private String turmnd  = ""; //     regnskaps.mnd                          283  284     2   2   0 s
	private String tuopdt = ""; //      opd.type                               285  286     2         a
	private String tutrma = ""; //      tr.måte                                287  288     2   2   0 s
	private String tuheng = ""; //      henger                                 289  298    10         a
	private String tulkh  = ""; //      land henger                            299  300     2         a
	private String tucon1 = ""; //      containernr 1                          301  317    17         a
	private String tulkc1 = ""; //      land cont. 1                           318  319     2         a
	private String tucon2 = ""; //      containernr 2                          320  336    17         a
	private String tulkc2  = ""; //     land cont. 2                           337  338     2         a
	private String tutara  = ""; //     tara                                   339  343     5   5   0 s
	private String tubilk = ""; //      bilkode                                344  346     3         a
	private String tuknt2 = ""; //      transportørsnr.2                       347  354     8   8   0 s
	private String tusja1 = ""; //      sjåførnr 1                             355  362     8   8   0 s
	private String tusjn1 = ""; //      sjåfør 1 navn                          363  392    30         a
	private String tusja2 = ""; //      sjåførnr 2                             393  400     8   8   0 s
	private String tusjn2 = ""; //      sjåfør 2 navn                          401  430    30         a
	private String tustef = ""; //      stedskode fra                          431  435     5         a
	private String tusonf = ""; //      sone fra                               436  440     5         a
	private String tudtt = ""; //       dato til                               441  448     8   8   0 s  
	private String tutm  = ""; //       klokkeslett fra                        449  452     4   4   0 s  
	private String tutmt = ""; //       klokkeslett til                        453  456     4   4   0 s  
	private String tustet = ""; //      stedskode til                          457  461     5         a  
	private String tusont = ""; //      sone til                               462  466     5         a  
	private String tukvkt = ""; //      kapasitet kg                           467  471     5   9   0 p  
	private String tukam3 = ""; //      kapasitet m3                           472  478     7   7   3 s  
	private String tukalm = ""; //      kapasitet lm                           479  482     4   4   2 s  
	private String tuto1a = ""; //      tollsted 1 a                           483  490     8         a  
	private String tuto1b = ""; //      tollsted 1 b                           491  492     2         a  
	private String tutval = ""; //      valutakode                             493  495     3         a  
	private String tutako = ""; //      kode frakt                             496  496     1         a  
	private String tutbel = ""; //      beløp                                  497  502     6  11   2 p  
	private String tutref = ""; //      refnr budsjett                         503  506     4   7   0 p  
	private String tuhøyb  = ""; //     inv.høyde bil                          507  510     4   4   2 s
	private String tuhøyh = ""; //      inv.høyde henger                       511  514     4   4   2 s
	private String tusg = ""; //        signatur                           515  517     3         a
	private String tuant1  = ""; //     antall av enh 1                        518  520     3   5   0 p
	private String tuenh1 = ""; //      enhet 1                                521  523     3         a
	private String tuant2 = ""; //      antall av enh 2                        524  526     3   5   2 p
	private String tuenh2 = ""; //      enhet 2                                527  529     3         a
	private String tukm = ""; //        antall kilometer                       530  532     3   5   0 p
	private String tublnr = ""; //      bl-nr                                  533  549    17         a
	private String tubkd = ""; //       båtkode                                550  554     5         a
	private String tubnv = ""; //       båtnavn                                555  584    30         a
	private String tuetd = ""; //       etd                                    585  592     8   8   0 s
	private String tueta = ""; //       eta                                    593  600     8   8   0 s
	private String tuatd = ""; //       atd                                    601  608     8   8   0 s
	private String tuata = ""; //       ata                                    609  616     8   8   0 s
	private String tuckd1 = ""; //      container kode 1                       617  618     2         a
	private String tuckd2 = ""; //      container kode 2                       619  620     2         a
	private String tupoen = ""; //      adr-poeng                              621  625     5   5   0 s
	private String tutlm2 = ""; //      total lm lasting                       626  630     5   5   2 s
	private String tustn1 = ""; //      status ny 1                            631  631     1         a
	private String tustn2 = ""; //      status ny 2                            632  632     1         a
	private String tustn3 = ""; //      status ny 3                            633  633     1         a
	private String tustn4 = ""; //      status ny 4                            634  634     1         a
	private String tustn5 = ""; //      status ny 5                            635  635     1         a
	private String tustn6 = ""; //      status ny 6                            636  636     1         a
	private String tustn7 = ""; //      status ny 7                            637  637     1         a
	private String tustn8 = ""; //      status ny 8                            638  638     1         a
	private String tustn9 = ""; //      status ny 9                            639  639     1         a
	//
	private String own_ErrMsg = ""; // in order to catch the error at Container-level for Ajax call 
	
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



/* File description
 
TUST       TURSTATUS                                1    1     1         A  
TUAVD      AVDELING                                 2    5     4   4   0 S  
TUPRO      TURNUMMER                                6   13     8   8   0 S  
TUDT       DATO FRA                                14   21     8   8   0 S  
TUKNA      AGENTNUMMER                             22   29     8   8   0 S  
TUKNT      TRANSPORTØRSNR.                         30   37     8   8   0 S  
TUNAT      TRANSPORTØRNAVN                         38   67    30         A  
TUAD1T     TRANSPORTØR ADR1                        68   97    30         A  
TUAD2T     TRANSPORTØR ADR2                        98  127    30         A  
TUAD3T     TRANSPORTØR ADR3                       128  157    30         A  
TUBILN     BILNUMMER                              158  167    10         A  
TUTARF     TARIFFØR                               168  182    15         A  
TULK       LANDKODE FRA                           183  184     2         A  
TUXXX1     LEDIG                                  185  185     1         A  
TUSDF      STED FRA 20 LANG                       186  205    20         A
TUSDT      STED TIL 20 LANG                       206  225    20         A
TUAO       ANTALL OPPDRAG                         226  230     5   5   0 S
TUTS       ANTALL STYKK                           231  234     4   7   0 P
TUTVKT     TOTAL VEKT                             235  239     5   9   0 P
TUTLM      TOTAL LM FAKTURA                       240  243     4   4   2 S
TUTM3      TOTAL KUBIKK                           244  250     7   7   3 S
TULAST     LASTE                                  251  251     1   1   0 S
TUBUSJ     BUDSJETT                               252  256     5   5   0 S
TUXXX2     LEDIGE                                 257  260     4         A
TUTST1     STATUS 1                               261  261     1         A
TUTST2     STATUS 2                               262  262     1         A
TUTST3     STATUS 3                               263  263     1         A
TUTST4     STATUS 4                               264  264     1         A
TUTST5     STATUS 5                               265  265     1         A
TUAVRE     AVREGNET                               266  266     1         A
TUAVNR     AVREGNINGSNR                           267  270     4   7   0 P
TURUND     RUNDTUR                                271  278     8   8   0 S
TURACC     REGNSKAPS.ÅRHUNDRE                     279  280     2   2   0 S
TURAAR     REGNSKAPS.ÅR                           281  282     2   2   0 S
TURMND     REGNSKAPS.MND                          283  284     2   2   0 S
TUOPDT     OPD.TYPE                               285  286     2         A
TUTRMA     TR.MÅTE                                287  288     2   2   0 S
TUHENG     HENGER                                 289  298    10         A
TULKH      LAND HENGER                            299  300     2         A
TUCON1     CONTAINERNR 1                          301  317    17         A
TULKC1     LAND CONT. 1                           318  319     2         A
TUCON2     CONTAINERNR 2                          320  336    17         A
TULKC2     LAND CONT. 2                           337  338     2         A
TUTARA     TARA                                   339  343     5   5   0 S
TUBILK     BILKODE                                344  346     3         A
TUKNT2     TRANSPORTØRSNR.2                       347  354     8   8   0 S
TUSJA1     SJÅFØRNR 1                             355  362     8   8   0 S
TUSJN1     SJÅFØR 1 NAVN                          363  392    30         A
TUSJA2     SJÅFØRNR 2                             393  400     8   8   0 S
TUSJN2     SJÅFØR 2 NAVN                          401  430    30         A
TUSTEF     STEDSKODE FRA                          431  435     5         A
TUSONF     SONE FRA                               436  440     5         A
TUDTT      DATO TIL                               441  448     8   8   0 S  
TUTM       KLOKKESLETT FRA                        449  452     4   4   0 S  
TUTMT      KLOKKESLETT TIL                        453  456     4   4   0 S  
TUSTET     STEDSKODE TIL                          457  461     5         A  
TUSONT     SONE TIL                               462  466     5         A  
TUKVKT     KAPASITET KG                           467  471     5   9   0 P  
TUKAM3     KAPASITET M3                           472  478     7   7   3 S  
TUKALM     KAPASITET LM                           479  482     4   4   2 S  
TUTO1A     TOLLSTED 1 A                           483  490     8         A  
TUTO1B     TOLLSTED 1 B                           491  492     2         A  
TUTVAL     VALUTAKODE                             493  495     3         A  
TUTAKO     KODE FRAKT                             496  496     1         A  
TUTBEL     BELØP                                  497  502     6  11   2 P  
TUTREF     REFNR BUDSJETT                         503  506     4   7   0 P  
 TUHØYB     INV.HØYDE BIL                          507  510     4   4   2 S
TUHØYH     INV.HØYDE HENGER                       511  514     4   4   2 S
TUSG       SIGNATUR TUR                           515  517     3         A
TUANT1     ANTALL AV ENH 1                        518  520     3   5   0 P
TUENH1     ENHET 1                                521  523     3         A
TUANT2     ANTALL AV ENH 2                        524  526     3   5   2 P
TUENH2     ENHET 2                                527  529     3         A
TUKM       ANTALL KILOMETER                       530  532     3   5   0 P
TUBLNR     BL-NR                                  533  549    17         A
TUBKD      BÅTKODE                                550  554     5         A
TUBNV      BÅTNAVN                                555  584    30         A
TUETD      ETD                                    585  592     8   8   0 S
TUETA      ETA                                    593  600     8   8   0 S
TUATD      ATD                                    601  608     8   8   0 S
TUATA      ATA                                    609  616     8   8   0 S
TUCKD1     CONTAINER KODE 1                       617  618     2         A
TUCKD2     CONTAINER KODE 2                       619  620     2         A
TUPOEN     ADR-POENG                              621  625     5   5   0 S
TUTLM2     TOTAL LM LASTING                       626  630     5   5   2 S
TUSTN1     STATUS NY 1                            631  631     1         A
TUSTN2     STATUS NY 2                            632  632     1         A
TUSTN3     STATUS NY 3                            633  633     1         A
TUSTN4     STATUS NY 4                            634  634     1         A
TUSTN5     STATUS NY 5                            635  635     1         A
TUSTN6     STATUS NY 6                            636  636     1         A
TUSTN7     STATUS NY 7                            637  637     1         A
TUSTN8     STATUS NY 8                            638  638     1         A
TUSTN9     STATUS NY 9                            639  639     1         A

 
 
 */



