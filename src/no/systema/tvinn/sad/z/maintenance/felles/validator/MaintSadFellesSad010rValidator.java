package no.systema.tvinn.sad.z.maintenance.felles.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.tvinn.sad.z.maintenance.felles.model.jsonjackson.dbtable.JsonMaintSadFellesTariRecord;

/**
 * 
 * @author oscardelatorre
 * @date Okt 21, 2016
 * 
 *
 */
public class MaintSadFellesSad010rValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSadFellesSad010rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintSadFellesTariRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintSadFellesTariRecord record = (JsonMaintSadFellesTariRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tatanr", "", "Tariffnr. (TATANR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taalfa", "", "Søkebegrep (TAALFA) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tadtr", "", "Opd.dato (TADTR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tadato", "", "F.o.m dato (TADATO) er obligatorisk"); 
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if( !this.validNumber(record.getTaordb()) ){
					errors.rejectValue("taordb", "", "Ordinær value invalid. The value can not be greater than 99999.99");
				}
				if( !this.validNumber(record.getTaeftb()) ){
					errors.rejectValue("taeftb", "", "EFTA value invalid. The value can not be greater than 99999.99");
				}
				if( !this.validNumber(record.getTaefb()) ){
					errors.rejectValue("taefb", "", "EF value invalid. The value can not be greater than 99999.99");
				}
				//----------
				//PVA rules
				//----------
				if(record.getTaordk()!=null && !"".equals(record.getTaordk())){
					if("F".equals(record.getTaordk())){
						//OK
					}else{
						if(record.getTaordb()!=null && !"".equals(record.getTaordb())){
							//OK
						}else{
							errors.rejectValue("taordk", "", "PVA-code demands an  Ordinaær-value > 0 and <= 99999.99");
						}
					}
				}
				
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintSadFellesTariRecord record = (JsonMaintSadFellesTariRecord)obj;
		logger.info(record.getTatanr());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tatanr", "", "Tariffnr. (TATANR) er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taalfa", "", "Søkebegrep (TAALFA) er obligatorisk"); 
		

	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private boolean validNumber(String value){
		final Double UPPER_LIMIT = 99999.99;
		boolean retval = true;
		if (value!=null && !"".equals(value)){
			String tmp = value.replace(",", ".");
			try{
				Double tmpDbl = Double.parseDouble(tmp);
				if(tmpDbl>UPPER_LIMIT){
					retval = false;
				}
			}catch(Exception e){
				retval = false;
			}
		}
		
		return retval;
	}
}
