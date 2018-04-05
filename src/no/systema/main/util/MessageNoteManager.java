/**
 * 
 */
package no.systema.main.util;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import org.apache.log4j.Logger;

/**
 * Utility class to manage message notes issues
 * 
 * @author oscardelatorre
 * @date Maj 13, 2015
 * 
 */
public class MessageNoteManager {
	
	private static Logger logger = Logger.getLogger(MessageNoteManager.class.getName());
	
	/**
	 * Get lines separated with CR.
	 * Manage 100 lines.
	 * 
	 * @param message
	 * @return
	 */
	public String[] getChunksOfMessageNote(String message){
		String[] records = new String[100];
		if(message!=null){
			String messageRaw = message;
			if(messageRaw!=null){
				records = messageRaw.split("\\n");
				for (int i = 0; i < records.length; i++){
					records[i] = records[i].replace("\\n", "");
					logger.debug("##:" + records[i]);
				}
			}
		}
		return records;
	}
	/**
	 * 
	 * @param message
	 * @return
	 */
	public List<String> getChunksOfMessageNoteAsList(String message){
		String[] records = this.getChunksOfMessageNote(message);
		List<String> list = new ArrayList<String>();
		
		if(records!=null && records.length>0){
			list= Arrays.asList(records);
		}
		return list;
	}
	

}
