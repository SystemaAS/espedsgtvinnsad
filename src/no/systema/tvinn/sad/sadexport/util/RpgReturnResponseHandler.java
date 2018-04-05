/**
 * 
 */
package no.systema.tvinn.sad.sadexport.util;

import java.util.*;


import org.apache.log4j.Logger;

/**
 * The class evaluates return codes from RPG operations.
 * 
 * @author oscardelatorre
 * @date Sep 4, 2014
 * 
 */
public class RpgReturnResponseHandler {
	private static final Logger logger = Logger.getLogger(RpgReturnResponseHandler.class.getName());
	
	
	private String errorMessage = null;
	public void setErrorMessage(String value){ this.errorMessage=value;  }
	public String getErrorMessage(){ return this.errorMessage;  }
	
	private String user = null;
	public void setUser(String value){ this.user=value;  }
	public String getUser(){ return this.user;  }
	
	private String opd = null;
	public void setOpd(String value){ this.opd=value;  }
	public String getOpd(){ return this.opd;  }
	
	private String setdn = null;
	public void setSetdn(String value){ this.setdn=value;  }
	public String getSetdn(){ return this.setdn;  }
	
	//Item lines key records (line nr, opd, etc)
	private String lin = null;
	public void setLin(String value){ this.lin=value;  }
	public String getLin(){ return this.lin;  }
	
	private String svtdn = null;
	public void setSvtdn(String value){ this.svtdn=value;  }
	public String getSvtdn(){ return this.svtdn;  }
	
	
	
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
				//logger.info(field);
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
					}else if(keyValuePair[0].trim().equalsIgnoreCase("setdn")){
						this.setdn = keyValuePair[0] + ":" + keyValuePair[1]+ ",";
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
				//logger.info(field);
				String[] keyValuePair = field.split(":");
				if(keyValuePair[0]!=null){
					if(keyValuePair[0].trim().equalsIgnoreCase("setdn")){
						if(keyValuePair.length>1){
							if(keyValuePair[1]!=null && !"".equals(keyValuePair[1])){
								this.setdn = keyValuePair[1];
								
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
				//logger.info(field);
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
					}else if(keyValuePair[0].trim().equalsIgnoreCase("opd")){
						this.opd = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("lin")){
						this.lin = keyValuePair[1];
					}else if(keyValuePair[0].trim().equalsIgnoreCase("setdn")){
						this.setdn = keyValuePair[1];
					}
				}
			}
		}
		
		
	}
	/**
	 * 
	 * @param rpgRawResponsePayload
	 */
	public void evaluateRpgResponseOnItemKundensVarRegisterUpdate(String rpgRawResponsePayload){
		
		if(rpgRawResponsePayload!=null){
			String tmp = rpgRawResponsePayload.replaceAll("\"", "");
			String tmp2 = tmp.replace("{", "");
			String cleanRawPayload = tmp2.replace("}", "");
			
			String[] record = cleanRawPayload.split(",");
			List <String>list = Arrays.asList(record);
			for(String field: list){
				//logger.info(field);
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
					}
				}
			}
		}
	}
}
