/**
 * 
 */
package no.systema.skat.nctsexport.service;

import no.systema.skat.nctsexport.mapper.SkatNctsExportSpecificTopicMapper;
import no.systema.skat.nctsexport.mapper.SkatNctsExportSpecificTopicGuaranteeValidatorMapper;
import no.systema.skat.nctsexport.mapper.SkatNctsExportSpecificTopicArchiveMapper;
import no.systema.skat.nctsexport.mapper.SkatNctsExportSpecificTopicLoggingLargeTextMapper;
import no.systema.skat.nctsexport.mapper.SkatNctsExportSpecificTopicLoggingMapper;
import no.systema.skat.nctsexport.mapper.SkatNctsExportTopicCopiedMapper;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;
import no.systema.skat.nctsexport.model.JsonSkatNctsExportTopicCopiedContainer;
import no.systema.skat.nctsexport.mapper.SkatNctsExportTopicCopiedFromTransportUppdragMapper;

/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 * 
 */
public class SkatNctsExportSpecificTopicServiceImpl implements SkatNctsExportSpecificTopicService{
	public JsonSkatNctsExportSpecificTopicContainer getNctsExportSpecificTopicContainer(String utfPayload) {
		JsonSkatNctsExportSpecificTopicContainer jsonNctsExportSpecificTopicContainer = null;
		try{
			SkatNctsExportSpecificTopicMapper nctsExportSpecificTopicMapper = new SkatNctsExportSpecificTopicMapper();
			jsonNctsExportSpecificTopicContainer = nctsExportSpecificTopicMapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsExportSpecificTopicContainer;
		
	}
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatNctsExportSpecificTopicArchiveContainer getNctsExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSkatNctsExportSpecificTopicArchiveContainer container = null;
		try{
			SkatNctsExportSpecificTopicArchiveMapper mapper = new SkatNctsExportSpecificTopicArchiveMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

	/**
	 * Logging
	 * @param utfPayload
	 * @return
	 */
	public JsonSkatNctsExportSpecificTopicLoggingContainer getNctsExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSkatNctsExportSpecificTopicLoggingContainer container = null;
		try{
			SkatNctsExportSpecificTopicLoggingMapper mapper = new SkatNctsExportSpecificTopicLoggingMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Show large text
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer getNctsExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSkatNctsExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SkatNctsExportSpecificTopicLoggingLargeTextMapper mapper = new SkatNctsExportSpecificTopicLoggingLargeTextMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	
	public JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer getNctsExportSpecificTopicGuaranteeValidatorContainer(String utfPayload){
		JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer container = null;
		try{
			SkatNctsExportSpecificTopicGuaranteeValidatorMapper mapper = new SkatNctsExportSpecificTopicGuaranteeValidatorMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	
	/**
	 * Cloned record
	 * 
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSkatNctsExportTopicCopiedContainer getNctsExportTopicCopiedContainer(String utfPayload){
		JsonSkatNctsExportTopicCopiedContainer container = null;
		try{
			SkatNctsExportTopicCopiedMapper mapper = new SkatNctsExportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer getSkatNctsExportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonSkatNctsExportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			SkatNctsExportTopicCopiedFromTransportUppdragMapper mapper = new SkatNctsExportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
		
	}
	

	
}
