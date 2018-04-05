/**
 * 
 */
package no.systema.tvinn.sad.sadexport.util;

import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.items.JsonSadExportSpecificTopicItemRecord;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicFinansOpplysningerRecord;

import java.text.NumberFormat;
import java.math.BigDecimal;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * @author oscardelatorre
 * @date Nov 06, 2014
 * 
 * 
 */
public class SadExportCalculator {
	private static final Logger logger = Logger.getLogger(SadExportCalculator.class.getName());
	
	
	/**
	 * 
	 * @param container
	 * @return
	 */
	public Double getItemLinesTotalAmount(JsonSadExportTopicFinansOpplysningerContainer container){
		Double retval = 0.00D;
		String  NORWAY_CURRENCY_CODE = "NOK";
		if(container!=null){
			for (JsonSadExportTopicFinansOpplysningerRecord record : container.getInvoicList()){
				try{
					String rawValue =  record.getSfbl28();
					if(rawValue==null || "".equals(rawValue)){
						rawValue = "0.00";
					}else{
						NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
					    try {
					        Number parsed = nf.parse(rawValue);
					        BigDecimal bd = new BigDecimal(parsed.toString());
					        //check if there is a valid currency = NOK (meaning implicitly that there is several currencies)
					        if(NORWAY_CURRENCY_CODE.equals(container.getCalculatedValidCurrency())){
					        	    retval += bd.doubleValue() * (record.getSfkr28Dbl() / record.getSfom28Int());
					        }else{
				        			retval += bd.doubleValue();
					        }
					    } catch (Exception e) {
					        e.printStackTrace();
					    }
					}
					//logger.info("################### FINAL CONVERSION SAD-IMPORT FABL(sum of items (sfbl28) calculated: " + retval);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return retval;
		
	}
	/**
	 * 
	 * @param jsonSadExportSpecificTopicItemContainer
	 * @return
	 */
	public Double getItemLinesTotalAmount(JsonSadExportSpecificTopicItemContainer jsonSadExportSpecificTopicItemContainer){
		Double retval = 0.00D;
		if(jsonSadExportSpecificTopicItemContainer!=null){
			for (JsonSadExportSpecificTopicItemRecord record : jsonSadExportSpecificTopicItemContainer.getOrderList()){
				try{
					String rawValue =  record.getSvbelt();
					if(rawValue==null || "".equals(rawValue)){
						rawValue = "0.00";
					}else{
						NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
					    try {
					        Number parsed = nf.parse(rawValue);
					        BigDecimal bd = new BigDecimal(parsed.toString());
					        //logger.info(bd.toString());
					        retval += bd.doubleValue();
					        
					    } catch (Exception e) {
					        e.printStackTrace();
					    }
					}
					//logger.info("################### FINAL CONVERSION SAD-EXPORT FABL(sum of items (svbelt) calculated: " + retval);
					
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return retval;
		
	}
	/**
	 * 
	 * @param invoiceAmountParam
	 * @param calculatedItemLinesTotalAmount
	 * @return
	 */
	public Double getDiffBetweenCalculatedTotalAmountAndTotalAmount(String invoiceAmountParam, Double calculatedItemLinesTotalAmount){
		Double retval = 0.000D;
		Double tmp = 0.000D;
		try{
			String rawValue =  invoiceAmountParam;
			if(rawValue==null || "".equals(rawValue)){
				rawValue = "0.00";
			}else{
				//rawValue = rawValue.replace(",", ".");
				NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
			    try {
			        Number parsed = nf.parse(rawValue);
			        BigDecimal bd = new BigDecimal(parsed.toString());
			        logger.info(bd.toString());
			        tmp = bd.doubleValue();

			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
			Double invoiceAmount = tmp;
			retval = invoiceAmount - calculatedItemLinesTotalAmount;
			//logger.info("################### invoiceAmountParam: " + invoiceAmountParam);
			//logger.info("################### calculatedItemLinesTotalAmount: " + calculatedItemLinesTotalAmount);
			//logger.info("################### FINAL CONVERSION EXPORT FABL (diff): " + retval);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return retval;
	}
	
	/**
	 * 
	 * Gets the final common currency code for all lines
	 * (NOK when there are several currencies)
	 * 
	 * @param container
	 * @return
	 * 
	 */
	public String getFinalCurrency(JsonSadExportTopicFinansOpplysningerContainer container){
		String  NORWAY_CURRENCY_CODE = "NOK";
		String currencyCode = null;
		int counter = 1;
		if(container!=null){
			outer :for (JsonSadExportTopicFinansOpplysningerRecord record : container.getInvoicList()){
				if(counter==1){
					currencyCode = record.getSfvk28();
				}else{
					if(!currencyCode.equals(record.getSfvk28())){
						currencyCode = NORWAY_CURRENCY_CODE;
						break outer;
					}
				}
				counter++;
			}
		}
		return currencyCode;
	}
}
