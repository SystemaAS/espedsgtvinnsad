/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.unloading;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicUnloadingRecord extends JsonAbstractGrandFatherRecord  {
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
	
	private String tiavd = null;
	public void setTiavd(String value) {  this.tiavd = value; }
	public String getTiavd() { return this.tiavd;}
	
	private String titdn = null;
	public void setTitdn(String value) {  this.titdn = value; }
	public String getTitdn() { return this.titdn;}
	
	private String tisg = null;
	public void setTisg(String value) {  this.tisg = value; }
	public String getTisg() { return this.tisg;}
	
	private String tist = null;
	public void setTist(String value) {  this.tist = value; }
	public String getTist() { return this.tist;}
	
	private String tienkl = null;
	public void setTienkl(String value) {  this.tienkl = value; }
	public String getTienkl() { return this.tienkl;}
	
	private String titrnr = null;
	public void setTitrnr(String value) {  this.titrnr = value; }
	public String getTitrnr() { return this.titrnr;}
	
	private String tign = null;
	public void setTign(String value) {  this.tign = value; }
	public String getTign() { return this.tign;}
	
	
	private String tidk = null;
	public void setTidk(String value) {  this.tidk = value; }
	public String getTidk() {return this.tidk;}
	
	private String tikna = null;
	public void setTikna(String value) {  this.tikna = value; }
	public String getTikna() {return this.tikna;}
	
	private String tinaa = null;
	public void setTinaa(String value) {  this.tinaa = value; }
	public String getTinaa() {return this.tinaa;}
	
	private String tiada1 = null;
	public void setTiada1(String value) {  this.tiada1 = value; }
	public String getTiada1() {return this.tiada1;}
	
	private String tipna = null;
	public void setTipna(String value) {  this.tipna = value; }
	public String getTipna() {return this.tipna;}
	
	private String tipsa = null;
	public void setTipsa(String value) {  this.tipsa = value; }
	public String getTipsa() {return this.tipsa;}
	
	private String tilka = null;
	public void setTilka(String value) {  this.tilka = value; }
	public String getTilka() {return this.tilka;}
	
	private String tiska = null;
	public void setTiska(String value) {  this.tiska = value; }
	public String getTiska() {return this.tiska;}
	
	private String titina = null;
	public void setTitina(String value) {  this.titina = value; }
	public String getTitina() {return this.titina;}
	
	private String tikns = null;
	public void setTikns(String value) {  this.tikns = value; }
	public String getTikns() {return this.tikns;}
	
	private String tinas = null;
	public void setTinas(String value) {  this.tinas = value; }
	public String getTinas() {return this.tinas;}
	
	private String tiads1 = null;
	public void setTiads1(String value) {  this.tiads1 = value; }
	public String getTiads1() {return this.tiads1;}
	
	private String tipns = null;
	public void setTipns(String value) {  this.tipns = value; }
	public String getTipns() {return this.tipns;}
	
	private String tipss = null;
	public void setTipss(String value) {  this.tipss = value; }
	public String getTipss() {return this.tipss;}
	
	
	private String tilks = null;
	public void setTilks(String value) {  this.tilks = value; }
	public String getTilks() { return this.tilks;}
	
	private String tisks = null;
	public void setTisks(String value) {  this.tisks = value; }
	public String getTisks() { return this.tisks;}
	
	private String titins = null;
	public void setTitins(String value) {  this.titins = value; }
	public String getTitins() { return this.titins;}
	
	private String tiknk = null;
	public void setTiknk(String value) {  this.tiknk = value; }
	public String getTiknk() { return this.tiknk;}
	
	private String tinak = null;
	public void setTinak(String value) {  this.tinak = value; }
	public String getTinak() { return this.tinak;}
	
	private String tiadk1 = null;
	public void setTiadk1(String value) {  this.tiadk1 = value; }
	public String getTiadk1() { return this.tiadk1;}
	
	private String tipnk = null;
	public void setTipnk(String value) {  this.tipnk = value; }
	public String getTipnk() { return this.tipnk;}
	
	private String tipsk = null;
	public void setTipsk(String value) {  this.tipsk = value; }
	public String getTipsk() { return this.tipsk;}
	
	private String tilkk = null;
	public void setTilkk(String value) {  this.tilkk = value; }
	public String getTilkk() { return this.tilkk;}
	
	private String tiskk = null;
	public void setTiskk(String value) {  this.tiskk = value; }
	public String getTiskk() { return this.tiskk;}
	
	private String titink = null;
	public void setTitink(String value) {  this.titink = value; }
	public String getTitink() { return this.titink;}

	private String tiblk = null;
	public void setTiblk(String value) {  this.tiblk = value; }
	public String getTiblk() { return this.tiblk;}

	private String tialk = null;
	public void setTialk(String value) {  this.tialk = value; }
	public String getTialk() { return this.tialk;}

	private String titaid = null;
	public void setTitaid(String value) {  this.titaid = value; }
	public String getTitaid() { return this.titaid;}

	private String titask = null;
	public void setTitask(String value) {  this.titask = value; }
	public String getTitask() { return this.titask;}

	private String titalk = null;
	public void setTitalk(String value) {  this.titalk = value; }
	public String getTitalk() { return this.titalk;}

	private String tikdc = null;
	public void setTikdc(String value) {  this.tikdc = value; }
	public String getTikdc() { return this.tikdc;}
	
	private String titrdt = null;
	public void setTitrdt(String value) {  this.titrdt = value; }
	public String getTitrdt() { return this.titrdt;}
	
	private String tilstl = null;
	public void setTilstl(String value) {  this.tilstl = value; }
	public String getTilstl() { return this.tilstl;}
	
	private String tivpos = null;
	public void setTivpos(String value) {  this.tivpos = value; }
	public String getTivpos() { return this.tivpos;}
	
	private String tintk = null;
	public void setTintk(String value) {  this.tintk = value; }
	public String getTintk() { return this.tintk;}
	
	private String tivkb = null;
	public void setTivkb(String value) {  this.tivkb = value; }
	public String getTivkb() { return this.tivkb;}
	
	private String ticats = null;
	public void setTicats(String value) {  this.ticats = value; }
	public String getTicats() { return this.ticats;}
	
	private String tictl = null;
	public void setTictl(String value) {  this.tictl = value; }
	public String getTictl() { return this.tictl;}
	
	private String tidant = null;
	public void setTidant(String value) {  this.tidant = value; }
	public String getTidant() { return this.tidant;}
	
	private String tidfkd = null;
	public void setTidfkd(String value) {  this.tidfkd = value; }
	public String getTidfkd() { return this.tidfkd;}
	
	private String tidfsk = null;
	public void setTidfsk(String value) {  this.tidfsk = value; }
	public String getTidfsk() { return this.tidfsk;}
	
	private String nivpos = null;
	public void setNivpos(String value) {  this.nivpos = value; }
	public String getNivpos() { return this.nivpos;}
	
	private String nintk = null;
	public void setNintk(String value) {  this.nintk = value; }
	public String getNintk() { return this.nintk;}
	
	private String nivkb = null;
	public void setNivkb(String value) {  this.nivkb = value; }
	public String getNivkb() { return this.nivkb;}
	
	private String nidfst = null;
	public void setNidfst(String value) {  this.nidfst = value; }
	public String getNidfst() { return this.nidfst;}
	
	private String nidant = null;
	public void setNidant(String value) {  this.nidant = value; }
	public String getNidant() { return this.nidant;}
	
	private String nidfkd = null;
	public void setNidfkd(String value) {  this.nidfkd = value; }
	public String getNidfkd() { return this.nidfkd;}
	
	private String nidfsk = null;
	public void setNidfsk(String value) {  this.nidfsk = value; }
	public String getNidfsk() { return this.nidfsk;}
	
	private String nimn1 = null;
	public void setNimn1(String value) {  this.nimn1 = value; }
	public String getNimn1() { return this.nimn1;}
	
	private String nimn2 = null;
	public void setNimn2(String value) {  this.nimn2 = value; }
	public String getNimn2() { return this.nimn2;}
	
	private String nimnsk = null;
	public void setNimnsk(String value) {  this.nimnsk = value; }
	public String getNimnsk() { return this.nimnsk;}
	
	private String nikonf = null;
	public void setNikonf(String value) {  this.nikonf = value; }
	public String getNikonf() { return this.nikonf;}
	
	private String nifulf = null;
	public void setNifulf(String value) {  this.nifulf = value; }
	public String getNifulf() { return this.nifulf;}
	
	private String nidtl = null;
	public void setNidtl(String value) {  this.nidtl = value; }
	public String getNidtl() { return this.nidtl;}
	
	private String nictb = null;
	public void setNictb(String value) {  this.nictb = value; }
	public String getNictb() { return this.nictb;}
	
	private String nictb2 = null;
	public void setNictb2(String value) {  this.nictb2 = value; }
	public String getNictb2() { return this.nictb2;}
	
	private String nictsk = null;
	public void setNictsk(String value) {  this.nictsk = value; }
	public String getNictsk() { return this.nictsk;}
	
	private String nictp = null;
	public void setNictp(String value) {  this.nictp = value; }
	public String getNictp() { return this.nictp;}
	
	private String nictnv = null;
	public void setNictnv(String value) {  this.nictnv = value; }
	public String getNictnv() { return this.nictnv;}
	
	private String tituid = null;
	public void setTituid(String value) {  this.tituid = value; }
	public String getTituid() { return this.tituid;}
	
	private String tisgdk = null;
	public void setTisgdk(String value) {  this.tisgdk = value; }
	public String getTisgdk() { return this.tisgdk;}
	
	private String tisgme = null;
	public void setTisgme(String value) {  this.tisgme = value; }
	public String getTisgme() { return this.tisgme;}
	
	private String tisgut = null;
	public void setTisgut(String value) {  this.tisgut = value; }
	public String getTisgut() { return this.tisgut;}
	
	private String tisgid = null;
	public void setTisgid(String value) {  this.tisgid = value; }
	public String getTisgid() { return this.tisgid;}
	
	private String tisgdt = null;
	public void setTisgdt(String value) {  this.tisgdt = value; }
	public String getTisgdt() { return this.tisgdt;}
	
	private String tibyte = null;
	public void setTibyte(String value) {  this.tibyte = value; }
	public String getTibyte() { return this.tibyte;}
	

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
