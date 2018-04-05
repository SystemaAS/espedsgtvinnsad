package no.systema.tvinn.sad.sadimport.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.items.JsonSadImportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadimport.util.manager.SadImportItemsAutoControlMgr;

/**
 * This validator has been adapted with public methods in order to bypass the Validator.validate method inherent to Spring.
 * This bypass is needed when trying to validate WITHOUT using or polluting the caller with binding errors. 
 * 
 * The result could be digest as follows: 
 * (1) Use the normal validate method when the binding errors are required
 * (2) Use the extra validate method when calling the validation routines from elsewhere
 *  
 * @author oscardelatorre
 * @date Jun 4, 2014
 */
public class SadImportItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SadImportItemsValidator.class.getName());
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadImportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSadImportSpecificTopicItemRecord record = (JsonSadImportSpecificTopicItemRecord)obj;
		
		//logger.info("PVA:" + record.getSvpva());		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvf", "systema.tvinn.sad.import.header.error.null.item.vf.svvf");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svlk", "systema.tvinn.sad.import.header.error.null.item.opprland.svlk");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvnt", "systema.tvinn.sad.import.header.error.null.item.varenr.svvnt");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvktb", "systema.tvinn.sad.import.header.error.null.item.grossweight.svvktb");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvktn", "systema.tvinn.sad.import.header.error.null.item.netweight.svvktn");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svmfr", "systema.tvinn.sad.import.header.error.null.item.momfri.svmfr");
		
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Invalid number Tolltariff
				/*String ITEM_NR_SUFFIX_CHARACTERS_INVALID = "?";
				if(record.getSvvnt().contains(ITEM_NR_SUFFIX_CHARACTERS_INVALID)){
					errors.rejectValue("svvnt", "systema.tvinn.sad.import.header.error.null.item.varenr.svvnt");
				}*/
				if(!record.isSvvntValid()){
					errors.rejectValue("svvnt", "systema.tvinn.sad.import.header.error.null.item.varenr.svvnt");
				}
				//Tilleggsoppl. mandatory if Preferense is ON
				if(!this.isValidRuleForPreferences(record)){
					errors.rejectValue("wf1", "systema.tvinn.sad.import.header.error.null.item.tilleggsoppl.wf1");
				}
				//---------------------
				//Gross and Net weight
				//---------------------
				if(!this.isValidRuleForGrossAndNetWeight(record)){
					errors.rejectValue("svvktn", "systema.tvinn.sad.import.header.error.rule.item.netWeightTooBig");
				}
				//----------------------------------------------------------------------------------------------------------------
				//Validate extra - mengde is mandatory in some combinations whereas must not exist at all with other combinations
				//----------------------------------------------------------------------------------------------------------------
				if(!this.isValidRuleForMengdeMustExist(record)){
					errors.rejectValue("svntm", "systema.tvinn.sad.import.header.error.rule.item.svntm.extraMangd.mustExist");
				}/*else if(!this.isValidRuleForMengdeMustNotExist(record)){
					errors.rejectValue("svntm", "systema.tvinn.sad.import.header.error.rule.item.svntm.extraMangd.mustNotExist");
				}*/
				
				//Avgifter missing (OBSOLETE since it is Optional
				/*
				if(record.isMultipleChoiceAvgifter()){
					errors.rejectValue("wg1", "systema.tvinn.sad.import.header.error.rule.item.wg1.avgifter.mustExist");
				}
				*/
			}
		}
		
		//PVA mandatory must be checked after all rule validations
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svpva", "systema.tvinn.sad.import.header.error.null.item.pva.svpva");
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidRuleForPreferences(JsonSadImportSpecificTopicItemRecord record){
		boolean retval = true;
		//Tilleggsoppl. mandatory if Preferense is ON
		if(record.getSvpre()!=null && !"".equals(record.getSvpre())){
			if(!"N".equals(record.getSvpre()) && !"J".equals(record.getSvpre())){
				if("".equals(record.getWf1()) || "".equals(record.getWe1())){
					retval = false;
				}
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidRuleForGrossAndNetWeight(JsonSadImportSpecificTopicItemRecord record){
		boolean retval = true;
		if(record.getSvvktb()!=null && !"".equals(record.getSvvktb())){
			if(record.getSvvktn()!=null && !"".equals(record.getSvvktn())){
				try{
					String grossFormatTmp = record.getSvvktb().replace(".", "");
					double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
					String netFormatTmp = record.getSvvktn().replace(".", "");
					double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
					
					//Net can not be > than Gross
					if (netWeight>grossWeight){
						retval = false;
					}
				}catch(Exception e){
					//just take a phantom hit here 
				}
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidRuleForMengdeMustExist(JsonSadImportSpecificTopicItemRecord record){
		boolean retval = true;
		if("Y".equals(record.getExtraMangdEnhet())){
			if(record.getSvntm()!=null && !"".equals(record.getSvntm())){
				//valid
			}else{
				retval = false;
			}
		}
		return retval;
	}
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean isValidRuleForMengdeMustNotExist(JsonSadImportSpecificTopicItemRecord record){
		boolean retval = true;
		if(!"Y".equals(record.getExtraMangdEnhet())){
			if(record.getSvntm()!=null && !"".equals(record.getSvntm())){
				retval = false;
			}
		}
		
		return retval;
	}
	
}
