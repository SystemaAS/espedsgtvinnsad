/**
 * 
 */
package no.systema.tvinn.sad.sadexport.service;


//SKAT
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.archive.JsonSadExportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.logging.JsonSadExportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;

import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportTopicCopiedMapper;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicMapper;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicArchiveMapper;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicLoggingMapper;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicLoggingLargeTextMapper;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportTopicCopiedFromTransportUppdragMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportTopicFinansOpplysningerMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerExternalRecord;
import no.systema.tvinn.sad.sadexport.mapper.jsonjackson.SadExportSpecificTopicMapper;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportSpecificTopicFaktTotalContainer;


/**
 * 
 * @author oscardelatorre
 * @date Maj 2, 2014
 * 
 */
public class SadExportSpecificTopicServiceImpl implements SadExportSpecificTopicService{
	/**
	 * 
	 * @param utfPayload
	 * @return
	 *  
	 */
	public JsonSadExportSpecificTopicContainer getSadExportSpecificTopicContainer(String utfPayload) {
		JsonSadExportSpecificTopicContainer container = null;
		try{
			SadExportSpecificTopicMapper mapper = new SadExportSpecificTopicMapper();
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
	public JsonSadExportSpecificTopicAvdDataContainer getSadExportSpecificTopicAvdDataContainer(String utfPayload) {
		JsonSadExportSpecificTopicAvdDataContainer container = null;
		try{
			SadExportSpecificTopicMapper mapper = new SadExportSpecificTopicMapper();
			container = mapper.getAvdDataContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	
	/**
	 * Archive
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportSpecificTopicArchiveContainer getSadExportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSadExportSpecificTopicArchiveContainer container = null;
		try{
			SadExportSpecificTopicArchiveMapper mapper = new SadExportSpecificTopicArchiveMapper();
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
	public JsonSadExportSpecificTopicLoggingContainer getSadExportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSadExportSpecificTopicLoggingContainer container = null;
		try{
			SadExportSpecificTopicLoggingMapper mapper = new SadExportSpecificTopicLoggingMapper();
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
	public JsonSadExportSpecificTopicLoggingLargeTextContainer getSadExportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSadExportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SadExportSpecificTopicLoggingLargeTextMapper mapper = new SadExportSpecificTopicLoggingLargeTextMapper();
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
	public JsonSadExportTopicCopiedContainer getSadExportTopicCopiedContainer(String utfPayload){
		JsonSadExportTopicCopiedContainer container = null;
		try{
			SadExportTopicCopiedMapper mapper = new SadExportTopicCopiedMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * @param utfPayload
	 * @return
	 * 
	 */
	public JsonSadExportTopicCopiedFromTransportUppdragContainer getSadExportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonSadExportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			SadExportTopicCopiedFromTransportUppdragMapper mapper = new SadExportTopicCopiedFromTransportUppdragMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
	}
	
	/**
	 * Gets a list of financial items
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportTopicFinansOpplysningerContainer getSadExportTopicFinansOpplysningerContainer (String utfPayload){
		JsonSadExportTopicFinansOpplysningerContainer container = null;
		try{
			SadExportTopicFinansOpplysningerMapper mapper = new SadExportTopicFinansOpplysningerMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
		
	}
	/**
	 * Gets a specific financial item
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportTopicFinansOpplysningerContainer getSadExportTopicFinansOpplysningerContainerOneInvoice (String utfPayload){
		JsonSadExportTopicFinansOpplysningerContainer container = null;
		try{
			SadExportTopicFinansOpplysningerMapper mapper = new SadExportTopicFinansOpplysningerMapper();
			container = mapper.getContainerOneInvoice(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
		
	}
	
	//External invoices
	/**
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportTopicFinansOpplysningerExternalContainer getSadExportTopicFinansOpplysningerContainerContainerExternal (String utfPayload){
		JsonSadExportTopicFinansOpplysningerExternalContainer container = null;
		try{
			SadExportTopicFinansOpplysningerMapper mapper = new SadExportTopicFinansOpplysningerMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonSadExportTopicFinansOpplysningerExternalRecord record : container.getListexternfakt()){
				//DEBUG
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportTopicFinansOpplysningerExternalContainer getSadExportTopicFinansOpplysningerContainerOneInvoiceExternal (String utfPayload){
		JsonSadExportTopicFinansOpplysningerExternalContainer container = null;
		try{
			SadExportTopicFinansOpplysningerMapper mapper = new SadExportTopicFinansOpplysningerMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonSadExportTopicFinansOpplysningerExternalRecord record : container.getGetexternfakt()){
				//DEBUG
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	/**
	 * @param utfPayload
	 * @return
	 */
	public JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer getSadExportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate (String utfPayload){
		JsonSadExportTopicFinansOpplysningerExternalForUpdateContainer container = null;
		try{
			SadExportTopicFinansOpplysningerMapper mapper = new SadExportTopicFinansOpplysningerMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternalForUpdate(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadExportSpecificTopicFaktTotalContainer getSadExportSpecificTopicFaktTotalContainer (String utfPayload){
		JsonSadExportSpecificTopicFaktTotalContainer container = null;
		try{
			SadExportSpecificTopicMapper mapper = new SadExportSpecificTopicMapper();
			container = mapper.getFaktTotalContainer(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	
	
}

	
