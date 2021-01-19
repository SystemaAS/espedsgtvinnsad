/**
 * 
 */
package no.systema.tvinn.sad.model.jsonjackson.codes;

import java.util.Comparator;
/**
 * @author oscardelatorre
 * @date Jun 13, 2014
 * 
 */
public class JsonTvinnSadCodeRecord  {
	
	private String zkod = null;
	public void setZkod(String value){ this.zkod = value;}
	public String getZkod(){ return this.zkod; }
	
	private String ztxt = null;
	public void setZtxt(String value){ this.ztxt = value;}
	public String getZtxt(){ return this.ztxt; }

	private String zskv = null;
	public void setZskv(String value){ this.zskv = value;}
	public String getZskv(){ return this.zskv; }

	//alternative
	private String tkkode = null;
	public void setTkkode(String value){ this.tkkode = value;}
	public String getTkkode(){ return this.tkkode; }
	
	private String tktxtn = null;
	public void setTktxtn(String value){ this.tktxtn = value;}
	public String getTktxtn(){ return this.tktxtn; }
	
	//extra string in some cases
	private String ztxt2 = null;
	public void setZtxt2(String value){ this.ztxt2 = value;}
	public String getZtxt2(){ return this.ztxt2; }
	
	private String[] ztxt2Array = null;
	public String[] getZtxt2Array(){
		if(this.ztxt2 !=null || !"".equals(this.ztxt2)){
			//this regex will split the string every 2 characters: example "12465788" will be splitted into the array:[12 46 57 88]
			ztxt2Array = this.getZtxt2().split("(?<=\\G.{2})");
		}
		return ztxt2Array;
	}
	

	
}
