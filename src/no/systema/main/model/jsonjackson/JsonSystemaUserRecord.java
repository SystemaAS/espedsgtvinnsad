package no.systema.main.model.jsonjackson;

import java.lang.reflect.Field;
import java.util.*;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * Representation of the user applications
 * This records contains the valid applications for a Systema user.
 * The number of valid applications corresponds to the number of menu choices in the dashboard
 *  
 * @author oscardelatorre
 *
 */

public class JsonSystemaUserRecord extends JsonAbstractGrandFatherRecord {
	
	private String line = null; 
	public void setLine(String value) {  this.line = value; }
	public String getLine() { return this.line;}

	private String col = null;
	public void setCol(String value) {  this.col = value; }
	public String getCol() { return this.col;}
	
	private String prog = null;
	public void setProg(String value) {  
		this.prog = value;
		this.setProgChunks();
	}
	public String getProg() { return this.prog;}
	
	private String prTxt = null; 
	public void setPrTxt(String value) {  this.prTxt = value; }
	public String getPrTxt() { return this.prTxt;}
	
	private String progChunks = null;
	private void setProgChunks(){
		StringBuffer sb = new StringBuffer();
		if(prog!=null){
			String tmp = this.prog.replace("#'", "");
			tmp = tmp.replace("OnClick='",""); //window.open(", "'window.open(this.href");
			tmp = tmp.replace("window.open(", "");
			tmp = tmp.replace(")", "");
			
			String[] chunks = tmp.trim().split(",");
			for(int x=0; chunks.length>x; x++){
				if(x==0){
					this.progChunksUrl = chunks[x]; //isolate the url in order to put it independently if applicable (in JSP)
					sb.append("this.href"); //must be replace whith this href (for the window.open javascript on JSP)
				}else{
					sb.append("," + chunks[x]);
				}
			}
			this.progChunks = sb.toString(); //always as the end of javascript for window.open...(JSP)
		}
	}
	public String getProgChunks(){ return this.progChunks;}
	
	
	private String progChunksUrl = null;
	public String getProgChunksUrl (){
		String retval = this.progChunksUrl;
		if(retval!=null){
			retval = this.progChunksUrl.replaceAll("'", "");
		}
		return retval;
	}
	
	private String veiledning = null; 
	public void setVeiledning(String value) {  this.veiledning = value; }
	public String getVeiledning() { return this.veiledning;}
	
	private String infoImg = null; 
	public void setInfoImg(String value) {  this.infoImg = value; }
	public String getInfoImg() { return this.infoImg;}
	
	
	/**
	 * Required for java reflection in other classes
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
