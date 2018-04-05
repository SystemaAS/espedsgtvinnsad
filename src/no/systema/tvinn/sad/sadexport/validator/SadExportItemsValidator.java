	package no.systema.tvinn.sad.sadexport.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;

/**
 * 
 * @author oscardelatorre
 * Nov 4, 2014
 * 
 */
public class SadExportItemsValidator implements Validator {
	
	
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSadExportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * 
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		
		JsonSadExportSpecificTopicItemRecord record = (JsonSadExportSpecificTopicItemRecord)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svfyl", "systema.tvinn.sad.export.header.error.null.item.fylkeskod.svfyl");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvnt", "systema.tvinn.sad.export.header.error.null.item.varenr.svvnt");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvktb", "systema.tvinn.sad.export.header.error.null.item.grossweight.svvktb");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "svvktn", "systema.tvinn.sad.export.header.error.null.item.netweight.svvktn");
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Invalid number Tolltariff
				/*String ITEM_NR_SUFFIX_CHARACTERS_INVALID = "?";
				if(record.getSvvnt().contains(ITEM_NR_SUFFIX_CHARACTERS_INVALID)){
					errors.rejectValue("svvnt", "systema.tvinn.sad.export.header.error.null.item.varenr.svvnt");
				}*/
				if(!record.isSvvntValid()){
					errors.rejectValue("svvnt", "systema.tvinn.sad.export.header.error.null.item.varenr.svvnt");
				}
				
				//fiskavgift
				if(record.isMandatoryFiskAvgift()){
					if(!this.fiskAvgiftExists(record)){
						errors.rejectValue("svavt", "systema.tvinn.sad.export.header.error.rule.item.svavt.fiskeavgift.mustExist");
					}
				}
				
				//---------------------
				//Gross and Net weight
				//---------------------
				if(record.getSvvktb()!=null && !"".equals(record.getSvvktb())){
					if(record.getSvvktn()!=null && !"".equals(record.getSvvktn())){
						try{
							String grossFormatTmp = record.getSvvktb().replace(".", "");
							double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
							String netFormatTmp = record.getSvvktn().replace(".", "");
							double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
							
							//Net can not be > than Gross
							if (netWeight>grossWeight){
								errors.rejectValue("svvktn", "systema.tvinn.sad.export.header.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a phantom hit here 
						}
					}
				}
				//Validate extra - mengde is mandatory in some combinations and should not exist in other combinations.
				if("Y".equals(record.getExtraMangdEnhet())){
					if(record.getSvntm()!=null && !"".equals(record.getSvntm())){
						//valid
					}else{
						errors.rejectValue("svntm", "systema.tvinn.sad.export.header.error.rule.item.svntm.extraMangd.mustExist");
					}
				}else{
					if(record.getSvntm()!=null && !"".equals(record.getSvntm())){
						errors.rejectValue("svntm", "systema.tvinn.sad.export.header.error.rule.item.svntm.extraMangd.mustNotExist");
					}
				}
				//Landkod Oppr. must not exist in some combinations
				if("91".equals(record.getSvfyl())){
					if(!"".equals(record.getSvlk())){
						errors.rejectValue("svlk", "systema.tvinn.sad.export.header.error.rule.item.svlk.landkodOppr.mustNotExist");
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param record
	 * @return
	 */
	private boolean fiskAvgiftExists(JsonSadExportSpecificTopicItemRecord record){
		boolean retval = false;
		if(  (record.getSvavt()!=null && !"".equals(record.getSvavt())) &&
			 (record.getSvavtp()!=null && !"".equals(record.getSvavtp())) &&	
			 (record.getSvavts()!=null && !"".equals(record.getSvavts())) ){
			retval = true;
		}
		return retval;
	}
	
}
