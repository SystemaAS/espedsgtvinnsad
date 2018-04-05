package no.systema.main.model.jsonjackson.general;

import java.util.Collection;



/**
 * 
 * 
 * @author oscardelatorre
 * @date May 18, 2016
 * 
 */
public class JsonEdiFtpLogContainer  {
	
	private String user = null;                                
	public void setUser (String value){ this.user = value;   }   
	public String getUser (){ return this.user;   }              

	private String errMsg = null;                                
	public void setErrMsg (String value){ this.errMsg = value;   }   
	public String getErrMsg (){ return this.errMsg;   }              


	private Collection<JsonEdiFtpLogRecord> list;
	public void setList(Collection<JsonEdiFtpLogRecord> value){ this.list = value; }
	public Collection<JsonEdiFtpLogRecord> getList(){ return list; }
	
}
