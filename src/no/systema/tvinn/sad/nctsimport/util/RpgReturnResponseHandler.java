/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.util;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	//header
	private String titdn = null;
	public void setTitdn(String value){ this.titdn=value;  }
	public String getTitdn(){ return this.titdn;  }
	
	private String tiavd = null;
	public void setTiavd(String value){ this.tiavd=value;  }
	public String getTiavd(){ return this.tiavd;  }
	
	private String tign = null;
	public void setTign(String value){ this.tign=value;  }
	public String getTign(){ return this.tign;  }
	
	
	//Items (Händelser)
	private String tvli = null;
	public void setTvli(String value){ this.tvli=value;  }
	public String getTvli(){ return this.tvli;  }
	
	private String tvtdn = null;
	public void setTvtdn(String value){ this.tvtdn=value;  }
	public String getTvtdn(){ return this.tvtdn;  }
		
	
	
	/**
	 * Sets the error message code after an RPG-call been made by an HTML-POST request on a topic (arende) update
	 * 
	 * @param rpgRawReturnPayload
	 * @return
	 */
	public void evaluateRpgResponseOnTopicUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[0] + ":" + keyValuePair[1] + ",";
						//logger.info(field);
						
					}else if(keyValuePair[0].trim().equalsIgnoreCase("titdn")){
						this.titdn = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						//logger.info(field);
						
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tiavd")){
						this.tiavd = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						//logger.info(field);
						
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tign")){
						this.tign = keyValuePair[1];
						if(this.tign!=null){
							this.tign = this.tign.trim();
						}
						//logger.info("TIGNNNNN=" + this.tign);
						
					}
					
				}
			}
		}
		
		
	}
	
	/**
	 * Gets a seed (counter) required for an INSERT
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void getNewSeedsOpdAndTuidRequiredForCreateNewTopic(String rpgRawResponsePayload){
		Map map = new HashMap(); 
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("titdn")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.titdn = keyValuePair[1];
								logger.info(field);
								
							}
						}
					}
					
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}
				}
			}
		}	
	}
	
	/**
	 * Evaluates the creation of a topic item line (add)
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnTopicItemCreateOrUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("errMsg")){
						if(keyValuePair.length>1){
							String errorCode = keyValuePair[1];
							if(errorCode!=null && !"".equals(errorCode.trim())){
								this.errorMessage = errorCode ;
								logger.info(this.errorMessage);
							}
						}
					}else if(keyValuePair[0].trim().equalsIgnoreCase("user")){
						this.user = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tvavd")){
						this.tiavd = keyValuePair[1];
						logger.info(field);
						
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tvtdn")){
						this.tvtdn = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
						logger.info(field);
						
					}else if(keyValuePair[0].trim().equalsIgnoreCase("tvli")){
						this.tvli = keyValuePair[1];
						if(this.tvli!=null){
							this.tvli = this.tvli.trim();
						}
						logger.info("tvli=" + this.tvli);
					}
				}
			}
		}
		
		
	}
}
