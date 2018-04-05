/**
 * 
 */
package no.systema.main.util;


import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @Oct 18, 2013
 * 
 */
public class JsonDebugger {
	private static final Logger logger = Logger.getLogger(JsonDebugger.class.getName());
	int baseLimit = 0;
	/**
	 * 
	 * @param initLimit
	 */
	public JsonDebugger(int initLimit){
		this.baseLimit= initLimit;
	}
	
	public JsonDebugger(){
		
	}
	
	/**
	 * 
	 * @param jsonPayload
	 * @return
	 */
	public final String debugJsonPayloadWithLog4J(String jsonPayload){
		String retval = "";
		if(this.baseLimit==0){
			this.baseLimit = 4000;
		}
		if(jsonPayload!=null){
			if(jsonPayload.length()>this.baseLimit){
				//logger.info(" --> jsonPayload (substr):");
				retval = jsonPayload.substring(0,this.baseLimit);
			}else{
				retval = jsonPayload;
			}
		}else{
			retval=" --> jsonPayload: <null>";
		}
		return retval;
	}
	/**
	 * Default debug (limit=4000 chars)
	 * @param jsonPayload
	 */
	public final void debugJsonPayload(String jsonPayload){
		if(this.baseLimit==0){
			this.baseLimit = 4000;
		}
		if(jsonPayload!=null){
			if(jsonPayload.length()>this.baseLimit){
				logger.info(" --> jsonPayload (substr):" + jsonPayload.substring(0,this.baseLimit));
			}else{
				logger.info(" --> jsonPayload:" + jsonPayload);
			}
		}else{
			logger.info(" --> jsonPayload: <null>");
		}
	}
	
	/**
	 * Dynamic debug (limit=parameter)
	 * @param jsonPayload
	 * @param limit
	 */
	public final void debugJsonPayload(String jsonPayload, int limit){
		//Debug --> 
		if(jsonPayload!=null){
			if(jsonPayload.length()>limit){
				logger.info(" --> jsonPayload (substr):" + jsonPayload.substring(0,limit));
			}else{
				logger.info(" --> jsonPayload:" + jsonPayload);
			}
		}else{
			logger.info(" --> jsonPayload: <null>");
		}
	}
	
	/**
	 * To avoid showing the host name string in any log string
	 * @param rawBASE_URL
	 * @return
	 */
	public final String getBASE_URL_NoHostName(String rawBASE_URL){
		String retval = rawBASE_URL;
		if(rawBASE_URL!=null){
			int index = rawBASE_URL.indexOf("/sycgi");
			//uncomment this if the host name should be hidden. For now, it has been decided not to hide the host name
			//retval = rawBASE_URL.substring(index);
		}
		
		return retval;
	}
	
}
