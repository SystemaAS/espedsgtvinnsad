package no.systema.main.model.jsonjackson.general;

import java.util.Collection;



/**
 * 
 * 
 * @author oscardelatorre
 * @date May 19, 2016
 * 
 */
public class JsonEdiFtpLog2Container  {
	
	private String user = null;                                
	public void setUser (String value){ this.user = value;   }   
	public String getUser (){ return this.user;   }              

	private String errMsg = null;                                
	public void setErrMsg (String value){ this.errMsg = value;   }   
	public String getErrMsg (){ return this.errMsg;   }              


	private Collection<JsonEdiFtpLog2Record> list;
	public void setList(Collection<JsonEdiFtpLog2Record> value){ this.list = value; }
	public Collection<JsonEdiFtpLog2Record> getList(){ return list; }
	
}
