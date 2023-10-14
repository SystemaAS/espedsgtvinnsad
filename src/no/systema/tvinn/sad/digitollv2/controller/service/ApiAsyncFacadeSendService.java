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
	
	/**
	 * Must be exaclty the same params as in the Controller
	 * @param applicationUser
	 * @param etlnrt
	 * @param etmid
	 */
	@Async
	public void sendTransport (String applicationUser, Integer etlnrt, String etmid) {	
		this.apiTransportSendService.send(applicationUser, etlnrt, etmid);
	}
	
	@Async
	public void sendMaster (String applicationUser, Integer emlnrt, Integer emlnrm, String emmid) {	
		this.apiMasterSendService.send(applicationUser, emlnrt, emlnrm, emmid);
	}
	
	@Async
	public void sendHouse (String applicationUser, Integer ehlnrt, Integer ehlnrm, Integer ehlnrh, String ehmid) {	
		this.apiHouseSendService.send(applicationUser, ehlnrt, ehlnrm, ehlnrh, ehmid);
	}
	
	
	
	@Autowired
	private ApiTransportSendService apiTransportSendService;
	@Autowired
	private ApiMasterSendService apiMasterSendService;
	@Autowired
	private ApiHouseSendService apiHouseSendService;
	
}