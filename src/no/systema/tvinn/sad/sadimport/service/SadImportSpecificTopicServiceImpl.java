/**
 * 
 */
package no.systema.tvinn.sad.sadimport.service;
//Containers

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalRecord;
import no.systema.tvinn.sad.sadimport.controller.SadImportHeaderController;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicRecord;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicSendParametersContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicFaktTotalContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedFromTransportUppdragContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicCopiedContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.logging.JsonSadImportSpecificTopicLoggingLargeTextContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.archive.JsonSadImportSpecificTopicArchiveContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportSpecificTopicAvdDataContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.validator.JsonSadImportTopicIncotermsAttributesContainer;

//Mappers
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportSpecificTopicMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportSpecificTopicLoggingMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportSpecificTopicLoggingLargeTextMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportSpecificTopicArchiveMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTopicFinansOpplysningerMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTopicCopiedMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTopicCopiedFromTransportUppdragMapper;
import no.systema.tvinn.sad.sadimport.mapper.jsonjackson.SadImportTopicIncotermsAttributesMapper;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Jul 30, 2014
 * 
 */
public class SadImportSpecificTopicServiceImpl implements SadImportSpecificTopicService{
	private static final Logger logger = Logger.getLogger(SadImportHeaderController.class.getName());

	/**
	 * 
	 * @param utfPayload
	 * @return
	 *  
	 */
	public JsonSadImportSpecificTopicContainer getSadImportSpecificTopicContainer(String utfPayload) {
		JsonSadImportSpecificTopicContainer container = null;
		try{
			SadImportSpecificTopicMapper mapper = new SadImportSpecificTopicMapper();
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
	
	public JsonSadImportSpecificTopicAvdDataContainer getSadImportSpecificTopicAvdDataContainer(String utfPayload) {
		JsonSadImportSpecificTopicAvdDataContainer container = null;
		try{
			SadImportSpecificTopicMapper mapper = new SadImportSpecificTopicMapper();
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
	public JsonSadImportSpecificTopicArchiveContainer getSadImportSpecificTopicArchiveContainer(String utfPayload) {
		JsonSadImportSpecificTopicArchiveContainer container = null;
		try{
			SadImportSpecificTopicArchiveMapper mapper = new SadImportSpecificTopicArchiveMapper();
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
	public JsonSadImportSpecificTopicLoggingContainer getSadImportSpecificTopicLoggingContainer(String utfPayload) {
		JsonSadImportSpecificTopicLoggingContainer container = null;
		try{
			SadImportSpecificTopicLoggingMapper mapper = new SadImportSpecificTopicLoggingMapper();
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
	public JsonSadImportSpecificTopicLoggingLargeTextContainer getSadImportSpecificTopicLoggingLargeTextContainer(String utfPayload){
		JsonSadImportSpecificTopicLoggingLargeTextContainer container = null;
		try{
			SadImportSpecificTopicLoggingLargeTextMapper mapper = new SadImportSpecificTopicLoggingLargeTextMapper();
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
	public JsonSadImportTopicCopiedContainer getSadImportTopicCopiedContainer(String utfPayload){
		JsonSadImportTopicCopiedContainer container = null;
		try{
			SadImportTopicCopiedMapper mapper = new SadImportTopicCopiedMapper();
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
	public JsonSadImportTopicCopiedFromTransportUppdragContainer getSadImportTopicCopiedFromTransportUppdragContainer(String utfPayload){
		JsonSadImportTopicCopiedFromTransportUppdragContainer container = null;
		try{
			SadImportTopicCopiedFromTransportUppdragMapper mapper = new SadImportTopicCopiedFromTransportUppdragMapper();
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
	public JsonSadImportTopicFinansOpplysningerContainer getSadImportTopicFinansOpplysningerContainer (String utfPayload){
		JsonSadImportTopicFinansOpplysningerContainer container = null;
		try{
			SadImportTopicFinansOpplysningerMapper mapper = new SadImportTopicFinansOpplysningerMapper();
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
	public JsonSadImportTopicFinansOpplysningerContainer getSadImportTopicFinansOpplysningerContainerOneInvoice (String utfPayload){
		JsonSadImportTopicFinansOpplysningerContainer container = null;
		try{
			SadImportTopicFinansOpplysningerMapper mapper = new SadImportTopicFinansOpplysningerMapper();
			container = mapper.getContainerOneInvoice(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;	
		
	}
	
	/**
	 * 
	 * Adjusts the financial data if needed
	 * @param jsonSadImportSpecificTopicContainer 
	 * 
	 */
	public void updateFinansInformationIfApplicable(JsonSadImportSpecificTopicContainer jsonSadImportSpecificTopicContainer){
		if(jsonSadImportSpecificTopicContainer!=null){
			for(JsonSadImportSpecificTopicRecord record : jsonSadImportSpecificTopicContainer.getOneorder()){
				record.setFinansOpplysningarExist(true);
			}
		}
	}
	
	/**
	 * Incoterms validation service
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonSadImportTopicIncotermsAttributesContainer getSadImportTopicIncotermsAttributesContainer (String utfPayload){
		JsonSadImportTopicIncotermsAttributesContainer container = null;
		try{
			SadImportTopicIncotermsAttributesMapper mapper = new SadImportTopicIncotermsAttributesMapper();
			container = mapper.getContainer(utfPayload);
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
	public JsonSadImportTopicFinansOpplysningerExternalContainer getSadImportTopicFinansOpplysningerContainerContainerExternal (String utfPayload){
		JsonSadImportTopicFinansOpplysningerExternalContainer container = null;
		try{
			SadImportTopicFinansOpplysningerMapper mapper = new SadImportTopicFinansOpplysningerMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonSadImportTopicFinansOpplysningerExternalRecord record : container.getListexternfakt()){
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
	public JsonSadImportTopicFinansOpplysningerExternalContainer getSadImportTopicFinansOpplysningerContainerOneInvoiceExternal (String utfPayload){
		JsonSadImportTopicFinansOpplysningerExternalContainer container = null;
		try{
			SadImportTopicFinansOpplysningerMapper mapper = new SadImportTopicFinansOpplysningerMapper();
			container = mapper.getContainerInvoiceExternal(utfPayload);
			for(JsonSadImportTopicFinansOpplysningerExternalRecord record : container.getGetexternfakt()){
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
	public JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer getSadImportTopicFinansOpplysningerContainerOneInvoiceExternalForUpdate (String utfPayload){
		JsonSadImportTopicFinansOpplysningerExternalForUpdateContainer container = null;
		try{
			SadImportTopicFinansOpplysningerMapper mapper = new SadImportTopicFinansOpplysningerMapper();
			container = mapper.getContainerOneInvoiceInvoiceExternalForUpdate(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
	/**
	 * 
	 */
	public JsonSadImportSpecificTopicFaktTotalContainer getSadImportSpecificTopicFaktTotalContainer (String utfPayload){
		JsonSadImportSpecificTopicFaktTotalContainer container = null;
		try{
			SadImportSpecificTopicMapper mapper = new SadImportSpecificTopicMapper();
			container = mapper.getFaktTotalContainer(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
		
	}
	/**
	 * 
	 */
	public JsonSadImportSpecificTopicSendParametersContainer getSadImportSpecificTopicSendParametersContainer (String utfPayload){
		JsonSadImportSpecificTopicSendParametersContainer container = null;
		try{
			SadImportSpecificTopicMapper mapper = new SadImportSpecificTopicMapper();
			container = mapper.getSendParametersContainer(utfPayload);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return container;
	}
	
}
