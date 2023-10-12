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
	
	private String xturtype;
	private String tuavd;
	private String tupro;
	private String tupro2;
	private String tustef;
	private String tustet;
	private String tuetd;
	private String tueta;
	private String tuatd;
	private String tucon1;
	private String tubiln;//": "AA25507",
	private String tudt;  //": "20200528",
	private String tudtt; //": "20200528",
	private String tutm;
	private String tutmt;
	private String tulkf; //": "NO",
	private String tulkt; //": "NO",
	private String tusg; //": "JOV",
	private String turund; //": "1042",
	private String tutst1; //": "",
	private String tust;
	private String tuopdt;
	private String tuao; //": "0",
	private String tuts; //": "0",
	private String tutvkt; // ": "0",
	private String tutm3; //": "0,000",
	private String tutlm2; //": "0,00",
	private String tupoen; //": "",
	private String turclose; //": "close"

	private String tukna; //      agentnummer                             22   29     8   8   0 s  
	private String tuknt; //      transportørsnr.                         30   37     8   8   0 s  
	private String tunat; //      transportørnavn                         38   67    30         a  
	private String tuad1t; //     transportør adr1                        68   97    30         a  
	private String tuad2t; //     transportør adr2                        98  127    30         a  
	private String tuad3t; //     transportør adr3                       128  157    30         a  
	private String tutarf; //     tariffør                               168  182    15         a  
	private String tulk; //       landkode fra                           183  184     2         a  
	private String tusdf; //      sted fra 20 lang                       186  205    20         a
	private String tusdt; //      sted til 20 lang                       206  225    20         a
	private String tutlm; //      total lm faktura                       240  243     4   4   2 s
	private String tulast; //     laste                                  251  251     1   1   0 s
	private String tutrma; //     tr.måte                                287  288     2   2   0 s
	private String tuheng; //     henger                                 289  298    10         a
	private String tulkh; //      land henger                            299  300     2         a
	private String tulkc1; //     land cont. 1                           318  319     2         a
	private String tucon2; //     containernr 2                          320  336    17         a
	private String tulkc2; //     land cont. 2                           337  338     2         a
	private String tutara; //    tara                                   339  343     5   5   0 s
	private String tubilk; //     bilkode                                344  346     3         a
	private String tuknt2; //     transportørsnr.2                       347  354     8   8   0 s
	private String tusja1; //     sjåførnr 1                             355  362     8   8   0 s
	private String tusjn1; //     sjåfør 1 navn                          363  392    30         a
	private String tusja2; //     sjåførnr 2                             393  400     8   8   0 s
	private String tusjn2; //     sjåfør 2 navn                          401  430    30         a
	private String tusonf; //     sone fra                               436  440     5         a
	private String tusont; //     sone til                               462  466     5         a
	private String tukvkt; //     kapasitet kg                           467  471     5   9   0 p
	private String tukam3; //     kapasitet m3                           472  478     7   7   3 s
	private String tukalm; //     kapasitet lm                           479  482     4   4   2 s
	private String tuto1a; //     tollsted 1 a                           483  490     8         a
	private String tuto1b; //     tollsted 1 b                           491  492     2         a
	private String tuant1; //     antall av enh 1                        518  520     3   5   0 p
	private String tuenh1; //     enhet 1                                521  523     3         a
	private String tuant2; //     antall av enh 2                        524  526     3   5   2 p
	private String tuenh2; //     enhet 2                                527  529     3         a
	private String tukm; //       antall kilometer                       530  532     3   5   0 p
	private String tublnr; //     bl-nr                                  533  549    17         a
	private String tubkd; //      båtkode                                550  554     5         a
	private String tubnv; //      båtnavn                                555  584    30         a
	private String tuata; //      ata                                    609  616     8   8   0 s
	private String tuckd1; //     container kode 1                       617  618     2         a
	private String tuckd2; //     container kode 2                       619  620     2         a
	 
	
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



