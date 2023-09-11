package no.systema.tvinn.sad.digitollv2.controller;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmoifRecord;
import no.systema.tvinn.sad.digitollv2.service.SadmoifListService;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.sadimport.controller.ajax.SadImportAjaxHandlerController;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerContainer;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicFinansOpplysningerRecord;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;

@RestController
public class TvinnSadDigitollAjaxController {
	private static final Logger logger = LoggerFactory.getLogger(TvinnSadDigitollAjaxController.class.getName());
	
	/**
	 * 
	 * @param applicationUser
	 * @param elementValue
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificGoodsItemVoec_Digitoll.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonSadImportTopicFinansOpplysningerRecord> getSpecificGoodsItemVoec_Digitoll
	  						(@RequestParam String applicationUser, @RequestParam String eili, 
	  						 @RequestParam String eilnrt, @RequestParam String eilnrm , @RequestParam String eilnrh) {
		 
		 final String METHOD = "[DEBUG] getSpecificGoodsItemVoec_Digitoll ";
		 logger.info(METHOD + "Inside");
		 Set result = new HashSet();
		 //prepare the access CGI with RPG back-end
		 final String BASE_URL = SadDigitollUrlDataStore.SAD_FETCH_DIGITOLL_ITEMLINES_URL;
		 String urlRequestParams = "user=" + applicationUser + "&eili=" + eili + "&eilnrt=" + eilnrt + "&eilnrm=" + eilnrm + "&eilnrh=" + eilnrh;
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + BASE_URL);
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
	
	    	//Debug --> 
	    	logger.debug(jsonPayload);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		SadmoifContainer jsonContainer = this.sadmoifListService.getListContainer(jsonPayload);
	    		List<SadmoifRecord> list = (List)jsonContainer.getList();
	    		for(SadmoifRecord record : list) {
	    			result.add(record);
	    		}
	    		
	    	}
		 
		 return result;
	 }
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	private SadmoifListService sadmoifListService;
}
