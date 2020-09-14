package no.systema.tvinn.sad.manifest.express.controller.ajax;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.DateTimeManager;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesRecord;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRecord;
import no.systema.tvinn.sad.manifest.express.service.TvinnSadManifestListService;
import no.systema.tvinn.sad.manifest.express.util.manager.ManifestDateManager;
import no.systema.tvinn.sad.manifest.url.store.TvinnSadManifestUrlDataStore;



/**
 * 
 * @author oscardelatorre
 * @date Sep 2020
 * 
 */
@Controller
public class TvinnSadManifestAjaxHandlerController {
	private static final Logger logger = Logger.getLogger(TvinnSadManifestAjaxHandlerController.class.getName());
	
	/**
	 * Gets a specific cargo line
	 * @param applicationUser
	 * @param tur
	 * @param avd
	 * @param opd
	 * @return
	 */
	@RequestMapping(value = "getSpecificCargoLine_TvinnSadManifest.do", method = RequestMethod.GET)
	public @ResponseBody Set<JsonTvinnSadManifestCargoLinesRecord> getSpecificCargoLine
	  						(@RequestParam String applicationUser, @RequestParam String htmlParams ) {
		 logger.warn("Inside: getSpecificCargoLine_TvinnSadManifest.do()");
		 logger.warn(htmlParams);
		 Set result = new HashSet();
		 String[] params = htmlParams.split("@");
		 String pro = params[0].replace("clpro_", "");
		 String tdn = params[1].replace("cltdn_", "");
		 String avd = params[2].replace("clavd_", "");
		 
		 if(StringUtils.isNotEmpty(pro) && StringUtils.isNotEmpty(avd) && StringUtils.isNotEmpty(tdn) ){
		 
			 //prepare the access CGI with RPG back-end
			 String BASE_URL = TvinnSadManifestUrlDataStore.TVINN_SAD_FETCH_MANIFEST_EXPRESS_CARGOLINES_URL;
			 String urlRequestParams = "user=" + applicationUser + "&clpro=" + pro + "&clavd=" + avd + "&cltdn=" + tdn; 
			 logger.warn("URL: " + BASE_URL);
			 logger.warn("URL PARAMS: " + urlRequestParams);
			
			 String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			 if(jsonPayload!=null){
				
				JsonTvinnSadManifestCargoLinesContainer container = this.tvinnSadManifestListService.getListCargolinesContainer(jsonPayload);
				//----------------------------------------------------------------
				//now filter the topic list with the search filter (if applicable)
				//----------------------------------------------------------------
				Collection<JsonTvinnSadManifestCargoLinesRecord> outputList = container.getList();
				for(JsonTvinnSadManifestCargoLinesRecord record : outputList){
					this.adjustFieldsForFetch(record);
					result.add(record);
				}
			 }
		 }else{
			 logger.error("[ERROR] on fields[]...null or incorrect length???...");
		 }
		 
		 return result;
	 }
	
	private void adjustFieldsForUpdate(JsonTvinnSadManifestCargoLinesRecord record){
		record.setCl0068a(new ManifestDateManager().convertToDate_ISO(record.getCl0068a()));
	}
	
	private void adjustFieldsForFetch(JsonTvinnSadManifestCargoLinesRecord record){
		record.setCl0068a(new ManifestDateManager().convertToDate_NO(record.getCl0068a()));
	}

	//SERVICES
	@Autowired
	private TvinnSadManifestListService tvinnSadManifestListService;
	
	@Autowired
	private UrlCgiProxyService urlCgiProxyService;
	
}
