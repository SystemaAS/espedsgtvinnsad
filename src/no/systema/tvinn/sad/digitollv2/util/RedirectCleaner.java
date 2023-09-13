package no.systema.tvinn.sad.digitollv2.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.systema.tvinn.sad.digitollv2.controller.TvinnSadDigitollv2HouseController;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;


public class RedirectCleaner {
	private static final Logger logger = LoggerFactory.getLogger(RedirectCleaner.class.getName());
	
	/**
	 * 
	 * @param apiDtoResponse
	 */
	public void doIt(ApiGenericDtoResponse apiDtoResponse) {
		logger.error("dtoResponse:" + apiDtoResponse.toString());
		//model.put("errorMessage", apiDtoResponse.getErrMsg());
		logger.error("##dto-errMsg:" + apiDtoResponse.getErrMsg());
		
		//the errMsg could come in a variaty of flavours. We shoud clean much of the reserved chars for URL ({,},=, etc)
		String output = apiDtoResponse.getErrMsg();
		if(StringUtils.isNotEmpty(output) && output.length()>250) {
			//it should be enough...
			output = output.substring(0,249);
		}
		apiDtoResponse.setErrMsgClean(this.clean(output));
		logger.info("##dto-errMsgClean:" + apiDtoResponse.getErrMsgClean());
		//in order to catch it after the redirect as a parameter...if applicable

	}
	
	/**
	 * 
	 * @param origValue
	 * @return
	 */
	private String clean (String origValue) {
		
		String retval = "";
		try {
			String tmp = origValue.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("=", "-");
			logger.info("tmp:" + tmp);
			String out = tmp.replaceAll("\\[", "(").replaceAll("\\]", ")");
			retval = out;
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		
		return retval;
		
	}
}
