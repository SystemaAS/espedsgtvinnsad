/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.codes;

/**
 * @author oscardelatorre
 * @date Apr 30, 2014
 */
public class JsonTvinnSadNctsCodeRecord {
	
	private String tkkode = null;
	public void setTkkode(String value){ this.tkkode = value;}
	public String getTkkode(){ return this.tkkode; }
	
	private String tktxtn = null;
	public void setTktxtn(String value){ this.tktxtn = value;}
	public String getTktxtn(){ return this.tktxtn; }
	
	//for compatibility issues towards other codes (general e.g.country code)
	private String zkod = null;
	public void setZkod(String value){ this.zkod = value;}
	public String getZkod(){ return this.zkod; }
	
	private String ztxt = null;
	public void setZtxt(String value){ this.ztxt = value;}
	public String getZtxt(){ return this.ztxt; }
	
	private String zskv = null;
	public void setZskv(String value){ this.zskv = value;}
	public String getZskv(){ return this.zskv; }
	
	/*private String dkkd_kd = null;
	public void setDkkd_kd(String value){ this.dkkd_kd = value;}
	public String getDkkd_kd(){ return this.dkkd_kd; }
	
	private String dkkd_kd2 = null;
	public void setDkkd_kd2(String value){ this.dkkd_kd2 = value;}
	public String getDkkd_kd2(){ return this.dkkd_kd2; }
	
	private String dkkd_kd3 = null;
	public void setDkkd_kd3(String value){ this.dkkd_kd3 = value;}
	public String getDkkd_kd3(){ return this.dkkd_kd3; }
	
	private String dkkf_txt = null;
	public void setDkkf_txt(String value){ this.dkkf_txt = value;}
	public String getDkkf_txt(){ return this.dkkf_txt; }
	*/

	
}
