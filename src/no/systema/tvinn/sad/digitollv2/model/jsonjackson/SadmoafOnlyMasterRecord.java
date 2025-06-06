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
public class SadmoafOnlyMasterRecord extends JsonAbstractGrandFatherRecord  {
	
	private String emst =""; //        status                                 876  876     1         a  
	private String emuuid =""; //      lrn                                    877  912    36         a  
	private String emmid  =""; //      mrn                                    913  930    18         a  
	private String emmid_own =""; //   mrn backup                             931  948    18         a  
	private Integer emavd = 0; //      avdeling                               949  952     4   4   0 s  
	private Integer empro = 0; //      turnummer                              953  960     8   8   0 s  
	private Integer emlnrt = 0; //     løpenummer                             961  967     7   7   0 s  
	private Integer emlnrm = 0; //     m-lnr innen transp                     968  971     4   4   0 s  
	private Integer emdtr = 0; //      registreringsdato                      972  979     8   8   0 s  
	private String emdtrStr = "";
	public String getEmdtrStr() {
		if(this.emdtr!=null && this.emdtr > 0 ){
			String tmp = String.valueOf(this.emdtr);
			if (tmp.length()< 6) {
				this.emdtrStr = "0" + tmp;
			}else {
				this.emdtrStr = tmp;
			}
		}
		return this.emdtrStr;
	}
	private String emsg =""; //        signatur                               980  982     3         a  
	private String emst2 =""; //       status om manifest                     983  983     1         a  
	private String emst3 =""; //       status om innpass.                     984  984     1         a  
	private Integer emdtin = 0; //     innsendingsdato                        985  992     8   8   0 s
	private Integer ematdd = 0; //     date ymd                               993 1000     8   8   0 s
	private String emrcem1  =""; //    releasedconf. epost.                  1001 1050    50         a
	private String emrcem2 =""; //     releasedconf. epost.                  1051 1100    50         a
	private String emrcem3 =""; //     releasedconf. epost.                  1101 1150    50         a
	private Integer emcn = 0; //       container 1/0                         1151 1151     1   1   0 s
	private Integer emvkb = 0; //      bruttovekt                            1152 1160     9   9   0 s
	private Integer emknt = 0; //      transportør                           1161 1168     8   8   0 s
	private String emrgt =""; //       org.nr transportør                    1169 1185    17         a
	private Integer emknm = 0; //      mottaker                              1186 1193     8   8   0 s
	private String emrgm =""; //       org.nr mottaker                       1194 1210    17         a
	private Integer emtppm = 0; //     type of person mott                   1211 1211     1   1   0 s
	private String emnam =""; //       navn mottaker                         1212 1241    30         a
	private String emna2m =""; //      subdivvision mottak.                  1242 1271    30         a
	private String emad1m =""; //      gateadr. mottaker                     1272 1301    30         a
	private String emnrm =""; //       husnr mottaker                        1302 1316    15         a
	private String empnm =""; //       postnr mottaker                       1317 1325     9         a
	private String empsm =""; //       p.sted mottaker                       1326 1349    24         a
	private String emlkm =""; //       l.kode mottaker                       1350 1351     2         a
	private String empbm =""; //       postbox mottaker                      1352 1366    15         a
	private String ememm =""; //       ep.adr/tlf mottaker                   1367 1416    50         a
	private String ememmt =""; //      kodetype mottaker                     1417 1418     2         a
	private String own_ememm_telephone = ""; // 
	private String own_ememm_email = ""; // 
	
	private Integer emkns = 0; //      avsender                              1419 1426     8   8   0 s
	private String emrgs =""; //       org.nr avsender                       1427 1443    17         a
	private Integer emtpps = 0; //     type of person avs.                   1444 1444     1   1   0 s
	private String emnas  =""; //      navn avsender                         1445 1474    30         a
	private String emna2s =""; //      subdivvision avsend.                  1475 1504    30         a
	private String emad1s =""; //      gateadr. avsender                     1505 1534    30         a
	private String emnrs =""; //       husnr avsender                        1535 1549    15         a
	private String empns =""; //       postnr avsender                       1550 1558     9         a
	private String empss =""; //       p.sted avsender                       1559 1582    24         a
	private String emlks =""; //       l.kode avsender                       1583 1584     2         a
	private String empbs =""; //       postbox avsender                      1585 1599    15         a
	private String emems =""; //       epost/tlf avsender                    1600 1649    50         a
	private String ememst =""; //      kodetype avsender                     1650 1651     2         a
	private String own_emems_telephone = ""; // 
	private String own_emems_email = ""; // 
	
	private String emdkm =""; //       master dokumentnr                     1652 1701    50         a
	private String emdkmt =""; //      master dokumenttype                   1702 1705     4         a
	private String emc1ty =""; //      cont1.sizetype                        1706 1707     2         a
	private String emc1ps =""; //      cont1.packsts                         1708 1708     1         a
	private String emc1ss =""; //      cont1.supptype                        1709 1709     1         a
	private String emc1id =""; //      cont1.idnumber                        1710 1726    17         a
	private String emc2ty =""; //      cont2.sizetype                        1727 1728     2         a
	private String emc2ps =""; //      cont2.packsts                         1729 1729     1         a
	private String emc2ss =""; //      cont2.supptype                        1730 1730     1         a
	private String emc2id =""; //      cont2.idnumber                        1731 1747    17         a
	private String emc3ty =""; //      cont3.sizetype                        1748 1749     2         a
	private String emc3ps =""; //      cont3.packsts                         1750 1750     1         a
	private String emc3ss =""; //      cont3.supptype                        1751 1751     1         a
	private String emc3id =""; //      cont3.idnumber                        1752 1768    17         a
	private String emlkl =""; //       land of loading                       1769 1770     2         a
	private String emsdl =""; //       place of load code                    1771 1775     5         a
	private String emsdlt =""; //      place of load text                    1776 1805    30         a
	private String emlku  =""; //      land of unloading                     1806 1807     2         a
	private String emsdu =""; //       place of unloa code                   1808 1812     5         a
	private String emsdut =""; //      place of unloa text                   1813 1842    30         a
	private String emlkd =""; //       land of delivery                      1843 1844     2         a
	private String emsdd =""; //       place of deliv code                   1845 1849     5         a
	private String emsddt =""; //      place of deliv text                   1850 1879    30         a
	private String emerr =""; //       feilmelding ved snd                   1880 1929    50         a

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
