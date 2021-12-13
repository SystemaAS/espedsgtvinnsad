package no.systema.tvinn.sad.manifest.express.controller;

import java.net.URI;
import java.util.List;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import no.systema.jservices.common.dto.expressfortolling.ManifestTransportationCompanyDto;
import no.systema.main.util.AppConstants;

/**
 * Just to testing, remove when appropriate.
 * 
 * Full use of Spring integragted Jacksonmapper
 * 
 * @author Fredrik Möller
 * @date Sep 01 , 2019
 * 
 */
@Controller
public class CodeSandboxController {
	private static Logger logger = LogManager.getLogger(CodeSandboxController.class.getName());
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value="sandboxTransportationCompany.do")
	public String doSandboxTransportationCompany(@RequestParam(value = "id", required = false) String id) {
		logger.info("sandboxTransportationCompany.do...");
		
		try {

			List<ManifestTransportationCompanyDto> dtoList =  getTransportationCompany(id);
			
			logger.info("dtoList="+dtoList);

		} catch (Exception e) {
			logger.error("ERROR:", e);
		}
		
		return "Kilroy was here...";
		
	}	

//	@PostMapping(value="createManifest.do")
//	public ModelAndView createManifest( @ModelAttribute ("record") KostaDto record, 
//								@RequestParam(value = "action", required = true) Integer action,
//								BindingResult bindingResult, HttpSession session, HttpServletRequest request){
//
//		return null;
//		
//	}
	
	/**
	 * Usage: e.g. data for populating dropdowns.
	 * 
	 * @param id
	 * @return
	 */
	
	private List<ManifestTransportationCompanyDto> getTransportationCompany(String id) {
		String BASE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicestn-expft/transportationCompany.do";
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
		        .queryParam("user", "SYSTEMA")
		        .queryParam("id", id);
		URI uri = builder.buildAndExpand().toUri();
		logger.info("uri="+uri);
		
		
		ResponseEntity<List<ManifestTransportationCompanyDto>> response = restTemplate.exchange(uri,HttpMethod.GET, null, new ParameterizedTypeReference<List<ManifestTransportationCompanyDto>>() {});
		return response.getBody();		
	}	
	
}

