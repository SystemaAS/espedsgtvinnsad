package no.systema.tvinn.sad.digitollv2.controller.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.tvinn.sad.digitollv2.model.api.ApiGenericDtoResponse;
import no.systema.tvinn.sad.digitollv2.url.store.SadDigitollUrlDataStore;
import no.systema.tvinn.sad.digitollv2.util.RedirectCleaner;
import no.systema.tvinn.sad.digitollv2.util.SadDigitollConstants;

import java.util.Calendar;

import org.slf4j.Logger;


/**
 * This class acts only as a facade in order to deliver the Async mechansim if needed.
 * It is just a wrapper
 * 
 * @author oscardelatorre
 * Oct 2023
 * 
 */
@Service
@EnableAsync
public class ApiAsyncFacadeSendService {
	private static final Logger logger = LoggerFactory.getLogger(ApiAsyncFacadeSendService.class);
	
	@Async
	public void sendTransport (String applicationUser, Integer etlnrt, String etmid) {	
		this.apiTransportSendService.send(applicationUser, etlnrt, etmid);
	}
	
	@Autowired
	private ApiTransportSendService apiTransportSendService;
	
}