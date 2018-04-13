package no.systema.main.controller;


import java.net.URLEncoder;
import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.WebDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.validator.UserValidator;
import no.systema.tvinn.sad.sadimport.model.jsonjackson.topic.JsonSadImportTopicListContainer;
import no.systema.tvinn.sad.sadimport.service.SadImportTopicListService;
import no.systema.tvinn.sad.sadimport.url.store.SadImportUrlDataStore;
import no.systema.tvinn.sad.sadexport.model.jsonjackson.topic.JsonSadExportTopicListContainer;
import no.systema.tvinn.sad.sadexport.service.SadExportTopicListService;
import no.systema.tvinn.sad.sadexport.url.store.SadExportUrlDataStore;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.model.jsonjackson.JsonSystemaUserContainer;
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;
import no.systema.main.model.jsonjackson.JsonSystemaUserExtensionsMultiUserSwitchRecord;
import no.systema.main.model.jsonjackson.JsonFirmLoginContainer;
import no.systema.main.model.jsonjackson.JsonFirmLoginRecord;


import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.login.SystemaWebLoginService;
import no.systema.main.service.FirmLoginService;

import no.systema.main.url.store.MainUrlDataStore;
import no.systema.main.util.AppConstants;
import no.systema.main.util.StringManager;
import no.systema.jservices.common.util.AesEncryptionDecryptionManager;




/**
 * Login for the external module: espedsgtror
 * 
 * To accomplish direct link into whatever tvinnsad - view (JSP). 
 * A separate logon MUST take place.
 * 
 * 
 * @author oscardelatorre
 * Apr 11, 2018
 * 
 *
 */

@Controller
@Scope("session")
public class LoginFromEspedsgTrorController {
	private static final Logger logger = Logger.getLogger(LoginFromEspedsgTrorController.class.getName());
	private final String COMPANY_CODE_REQUIRED_FLAG_VALUE = "1";
	private AesEncryptionDecryptionManager aesManager = new AesEncryptionDecryptionManager();
	private StringManager strMgr = new StringManager();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }
	
	
	/**
	 * This method is the point of entry for the whole Systema Web esped domain.
	 * 
	 * IMPORTANT! 
	 * Note the use of ModelAttribute annotation in order to have the validation errors out back in the JSP
	 * The name of the command object MUST be specified as in the method signature otherwise the <spring:hasBindErrors name="user"> in the login.jsp
	 * won't show the errors!
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="logonTrorList_toSad.do", method= { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView logon(@ModelAttribute (AppConstants.SYSTEMA_WEB_USER_KEY) SystemaWebUser appUser, BindingResult bindingResult, HttpSession session, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttr){
		logger.info("Inside logon");
		ModelAndView successView = this.getSuccessView(appUser, request);
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			UserValidator validator = new UserValidator();
			
		    validator.validate(appUser, bindingResult);
		    if(bindingResult.hasErrors()){
		    	logger.info("[ERROR Fatal] User not valid (user/password ?)");
		    	//
		    	SystemaWebUser userForCssPurposes = new SystemaWebUser();
		    	userForCssPurposes.setCssEspedsg(AppConstants.CSS_ESPEDSG);
				model.put(AppConstants.SYSTEMA_WEB_USER_KEY, userForCssPurposes);
				this.loginView.addObject("model",model);
				
		    	//this.loginView.addObject("model", null);
		    	return loginView;
	
		    }else{
		    	//Decrypt password to be able to work with it. 
		    	//All sub-modules will be passed an encrypted password (from the dashboard). ALWAYS!
		    	appUser.setEncryptedPassword(appUser.getPassword());
		    	appUser.setPassword(this.aesManager.decrypt(appUser.getPassword()));
		    	//logger.info("DECRYPT...:" + appUser.getPassword());
		    	
		    	//get the company code for the comming user
		    	//this routine was triggered by Totens upgrade (Jan-2017 V12). Ref. JOVOs requirement
		    	String companyCode = null;
		    	if(COMPANY_CODE_REQUIRED_FLAG_VALUE.equals(AppConstants.LOGIN_FIRMA_CODE_REQUIRED)){
		    		companyCode = this.getCompanyCodeForLogin();
		    	}
		    	
		    	//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_URL;
				
				//url params
				String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, companyCode);
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL);
			    	//don't show the pwd
			    	//int pwd = urlRequestParamsKeys.indexOf("&pwd");
			    	//String credentailsPwd = urlRequestParamsKeys.substring(pwd + 5);
			    	//logger.info("URL PARAMS: " + urlRequestParamsKeys.substring(0,pwd)+"&md5");
			    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
			    	
			    	//--------------------------------------
			    	//EXECUTE the FETCH (RPG program) here
			    	//--------------------------------------
			    	try{
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				    	//Debug --> 
				    	//System.out.println(jsonPayload);
				    	logger.info(jsonPayload);
				    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp " + new StringBuilder(credentailsPwd).reverse().toString() + "carebum");
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp ");
				    	if(jsonPayload!=null){ 
				    		JsonSystemaUserContainer jsonSystemaUserContainer = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
				    		//check for errors
				    		if(jsonSystemaUserContainer!=null){
				    			if(jsonSystemaUserContainer.getErrMsg()!=null && !"".equals(jsonSystemaUserContainer.getErrMsg())){
				    				logger.info("[ERROR Fatal] User not valid (user/password) Check your credentials...");
				    				this.setFatalAS400LoginError(model, jsonSystemaUserContainer.getErrMsg());
				    				this.loginView.addObject("model", model);
				    				return this.loginView;
				    			}else{
				    				this.setDashboardMenuObjectsInSession(session, jsonSystemaUserContainer);
				    				//hand-over to appUser from JsonUser
				    				this.doHandOverToSystemaWebUser(request, appUser, jsonSystemaUserContainer, companyCode);
				    			}
				    		}
				    	}else{
				    		String msg = "NO CONTENT on jsonPayload";
				    		logger.info("[ERROR Fatal] " + msg);
				    		this.setFatalAS400LoginError(model, msg);
	    					this.loginView.addObject("model", model);
						
	    					return loginView;
				    	}
				    	
				    	session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
				    	
			    	}catch(Exception e){
			    		String msg = "NO CONNECTION:" + e.toString();
			    		logger.info("[ERROR Fatal] NO CONNECTION ");
			    		this.setFatalAS400LoginError(model, msg);
    					this.loginView.addObject("model", model);
					
    					return loginView;
			    		
			    	}
			    	
			    	//------------------------------------------------------------------------------------------------------
			    	//If this tomcat port exists then the user will be redirect to the correct subsidiary-company tomcat.
			    	//Note: To allow for a correct Company Tomcat from a Holding Company Web Portal.
			    	//TOTEN AS triggered this requirement
			    	//------------------------------------------------------------------------------------------------------
			    	/*
			    	if(appUser.getTomcatPort()!=null && !"".equals(appUser.getTomcatPort())){
				    	String urlRedirectTomcatToSubsidiaryCompany = this.getTomcatServerRedirectionUrl(appUser, request);
				    	RedirectView rw = new RedirectView();
				    	logger.info("Redirecting to:" + urlRedirectTomcatToSubsidiaryCompany);
				    	rw.setUrl(urlRedirectTomcatToSubsidiaryCompany);
				    	successView = new ModelAndView(rw);
			    	}
			    	*/
			    	return successView;
		    }
		}
	}
	/**
	 * 
	 * @param appUser
	 * @param request
	 * @return
	 */
	private ModelAndView getSuccessView (SystemaWebUser appUser, HttpServletRequest request){
		
		//get all other params
		String sadExport = request.getParameter("sade");
		String avd = request.getParameter("avd");
		String opd = request.getParameter("opd");
		String signatur = request.getParameter("sysg");
		String password = request.getParameter("dp");//map the password (encrypted) since we must send it as parameter= dp instead of texted: password
		//map the Tvinn authorization attributes as well
		appUser.setPassword(password);
		appUser.setAuthorizedTvinnSadUserAS400("Y");
		appUser.setTvinnSadSign(signatur);
		//debug
		logger.info("USER:" + appUser.getUser());
		//logger.info("PASS:" + appUser.getPassword());
		logger.info("EXPORT:" + sadExport);
		
		//================================================================
		//Start building the final redirection to
		//(1) Export or Import
 		//(2) Existing or a New One (having a Transportuppdrag as origin)
		//================================================================
		String successViewStr = "";
		if(strMgr.isNotNull(sadExport) && "Y".equals(sadExport)){
			//SAD EXPORT
			if(this.sadExportExists(appUser, avd, opd)){
				logger.info("SAD Export EXISTS !");
				successViewStr = "redirect:tvinnsadexport_edit.do?action=doFetch" + "&avd=" + avd + "&opd=" + opd + "&sysg=" + signatur;
			}else{
				successViewStr = "redirect:tvinnsadexport_doFetchTopicFromTransportUppdrag.do?actionGS=doUpdate" + "&selectedAvd=" + avd + "&selectedOpd=" + opd ;
			}
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_EXPORT);
		}else{
			//SAD IMPORT
			if(this.sadImportExists(appUser, avd, opd)){
				logger.info("SAD Import EXISTS !");
				successViewStr = "redirect:tvinnsadimport_edit.do?action=doFetch" + "&avd=" + avd + "&opd=" + opd + "&sysg=" + signatur;
			}else{
				successViewStr = "redirect:tvinnsadimport_doFetchTopicFromTransportUppdrag.do?actionGS=doUpdate" + "&selectedAvd=" + avd + "&selectedOpd=" + opd ;
			}
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_TVINN_SAD_IMPORT);
		}
		
		ModelAndView successView = new ModelAndView(successViewStr);
		return successView;
	}
	
	/**
	 * This method is user ONLY when the normal logonDashboard is required to redirect to another Tomcat Server (same IP but different port)
	 * Toten being the trigger company of this functionality (to allow for automatic logon into a subsidiary firm.
	 * 
	 * @param appUser
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="logonWRedTrorList_toSad.do", method= { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView logonRedirected(RedirectAttributes redirectAttrs, Model modelX, @ModelAttribute (AppConstants.SYSTEMA_WEB_USER_KEY) SystemaWebUser appUser, HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside logon");
		ModelAndView successView = this.getSuccessView(appUser, request);
		Map model = new HashMap();
		
		String pwd = request.getParameter("dp");
		//set attributes since the method call do not uses those fields' names
		appUser.setEncryptedPassword(pwd);
		appUser.setPassword(this.aesManager.decrypt(pwd));
		
		if(appUser==null){
			return this.loginView;
		
		}else{
			
	    	//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = MainUrlDataStore.SYSTEMA_WEB_LOGIN_URL;
			
			//url params
			String firmaCode = null; //always null in this method (because of multi-firm redirection)
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(appUser, firmaCode);
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	//don't show the pwd - not in use right now 
		    	//int pwd = urlRequestParamsKeys.indexOf("&pwd");
		    	//String credentailsPwd = urlRequestParamsKeys.substring(pwd + 5);
		    	//logger.info("URL PARAMS: " + urlRequestParamsKeys.substring(0,pwd)+"&md5");
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	try{
			    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			    	//Debug --> 
			    	//System.out.println(jsonPayload);
			    	logger.info(jsonPayload);
			    	//logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp " + new StringBuilder(credentailsPwd).reverse().toString() + "carebum");
			    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp ");
			    	if(jsonPayload!=null){ 
			    		JsonSystemaUserContainer jsonSystemaUserContainer = this.systemaWebLoginService.getSystemaUserContainer(jsonPayload);
			    		//check for errors
			    		if(jsonSystemaUserContainer!=null){
			    			if(jsonSystemaUserContainer.getErrMsg()!=null && !"".equals(jsonSystemaUserContainer.getErrMsg())){
			    				logger.info("[ERROR Fatal] User not valid (user/password) Check your credentials...");
			    				this.setFatalAS400LoginError(model, jsonSystemaUserContainer.getErrMsg());
			    				this.loginView.addObject("model", model);
			    				return this.loginView;
			    			}else{
			    				this.setDashboardMenuObjectsInSession(session, jsonSystemaUserContainer);
			    				//hand-over to appUser from JsonUser
			    				this.doHandOverToSystemaWebUser(request, appUser, jsonSystemaUserContainer, null);
			    			}
			    		}
			    	}else{
			    		String msg = "NO CONTENT on jsonPayload";
			    		logger.info("[ERROR Fatal] " + msg);
			    		this.setFatalAS400LoginError(model, msg);
						this.loginView.addObject("model", model);
					
						return loginView;
			    	}
			    	//Encrypt the password so late as possible
			    	//appUser.setEncryptedPassword(this.aesManager.encrypt(appUser.getPassword()));
			    	session.setAttribute(AppConstants.SYSTEMA_WEB_USER_KEY, appUser);
			    	
		    	}catch(Exception e){
		    		String msg = "NO CONNECTION:" + e.toString();
		    		logger.info("[ERROR Fatal] NO CONNECTION ");
		    		this.setFatalAS400LoginError(model, msg);
					this.loginView.addObject("model", model);
				
				return loginView;
		    		
		    	}
		    
		    	return successView;
			   
			}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @return
	 */
	private boolean sadImportExists(SystemaWebUser appUser, String avd, String opd ){
		boolean retval = false;
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadImportUrlDataStore.SAD_IMPORT_BASE_TOPICLIST_URL;
		
		//-------------------
		//add URL-parameter 
		//-------------------
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer(); 
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd + "&opd=" + opd);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

		//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadImportTopicListContainer container = this.sadImportTopicListService.getSadImportTopicListContainer(jsonPayload);
    		if(container!=null){
    			if(container.getOrderList()!=null && container.getOrderList().size()>0){
    				retval = true;	
    			}
    		}
    	}
    	return retval;
	}
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 * @return
	 */
	private boolean sadExportExists(SystemaWebUser appUser, String avd, String opd ){
		boolean retval = false;
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SadExportUrlDataStore.SAD_EXPORT_BASE_TOPICLIST_URL; 
		
		//-------------------
		//add URL-parameter 
		//-------------------
		//add URL-parameters
		StringBuffer urlRequestParams = new StringBuffer(); 
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&avd=" + avd + "&opd=" + opd);
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());

		//Debug --> 
    	logger.debug(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSadExportTopicListContainer container = this.sadExportTopicListService.getSadExportTopicListContainer(jsonPayload);
    		if(container!=null){
    			if(container.getOrderList()!=null && container.getOrderList().size()>0){
    				retval = true;	
    			}
    		}
    	}
    	return retval;
	}
	/**
	 * Gets the java services host name from application.properties.
	 * Needed in order to use the host name WITHOUT the port number.
	 * 
	 * @return
	 */
	private String getTomcatServerRedirectionUrl(SystemaWebUser appUser, HttpServletRequest request){
		String retval = null;
		String HTTP_PREFIX = "http://";
		String HTTPS_PREFIX = "https://";
		
		//get host from url
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String hostRaw = url.substring(0, url.indexOf(uri));
		String protocol = HTTP_PREFIX;
		//update Tomcat port if it exists from DB-login return (when a user must be redirected to the same http but different port (TOTEN-case. Multi-firm)
		if(appUser.getTomcatPort()!=null){
			Integer indexHttps = hostRaw.indexOf(HTTPS_PREFIX);
			Integer indexHttp = hostRaw.indexOf(HTTP_PREFIX);
			//default
			if(indexHttp >= 0){
				hostRaw = hostRaw.replace(HTTP_PREFIX, "");
				logger.info("HTTP hostRaw: " + hostRaw);
			}else if(indexHttps >= 0){
				//https (if applicable)
				hostRaw = hostRaw.replace(HTTPS_PREFIX, "");
				protocol = HTTPS_PREFIX;
				logger.info("HTTPs hostRaw: " + hostRaw);
			}
			
			//now to the issue
			Integer indx = hostRaw.indexOf(":");
			if(indx >= 0){
				String host = hostRaw.substring(0, indx + 1);
				//default
				String port = appUser.getTomcatPort();
				if(protocol.contains(HTTPS_PREFIX)){
					//take the port from the url and not from the json-return on ...symn0J.pgm
					Integer indxPort = hostRaw.lastIndexOf(":");
					String portTmp = hostRaw.substring(indxPort + 1);
					port = portTmp;
				}
				hostRaw = protocol + host + port;
				logger.info("HTTP host with protocol and port: " + hostRaw);
			}
		}
		
		//We must user GET until we get Spring 4 (in order to send params on POST)
		try{
			StringBuffer params = new StringBuffer();
			
			params.append("/logonWRedTrorList_toSad.do?user=" + appUser.getUser());
			params.append("&sade=" + request.getParameter("sade"));
			params.append("&avd=" + request.getParameter("avd"));
			params.append("&opd=" + request.getParameter("opd"));
			params.append("&sysg=" + request.getParameter("sysg"));
			params.append("&dp=" + URLEncoder.encode(appUser.getEncryptedPassword(), "UTF-8"));
			//
			retval = hostRaw + request.getContextPath() + params.toString();
			
		}catch(Exception e){
			//logger.info("XXXXX:" + request.getContextPath());
		}
		return retval;
	}
	/**
	 * This method must be used as long as we use AS400 login service
	 * When migrating to JAVA services the method and procedure will be obsolete...
	 * 
	 * @return
	 */
	private String getCompanyCodeForLogin(){
		String companyCode = null;
		
		String FIRM_URL = MainUrlDataStore.SYSTEMA_WEB_FIRMLOGIN_URL;
    	String jsonFirmPayload = this.urlCgiProxyService.getJsonContent(FIRM_URL);
    	logger.info(FIRM_URL);
    	logger.info(jsonFirmPayload);
    	JsonFirmLoginContainer firmContainer = this.firmLoginService.getContainer(jsonFirmPayload);
    	for(JsonFirmLoginRecord record : firmContainer.getList()){
    		companyCode = record.getFifirm();
    	}
    	logger.info(companyCode);
	
    	return companyCode;
	}
	/**
	 * This is the only place in which the jsonUserContainer lends over its values to the global SystemWebUser object
	 * @param request
	 * @param appUser
	 * @param jsonSystemaUserContainer
	 * @param companyCode
	 * 
	 */
	private void doHandOverToSystemaWebUser(HttpServletRequest request, SystemaWebUser appUser, JsonSystemaUserContainer jsonSystemaUserContainer, String companyCode){
		
		//user values
		appUser.setOs(System.getProperty("os.name").toLowerCase());
		//logger.info("OS:" + appUser.getOs());
		
		appUser.setUser(jsonSystemaUserContainer.getUser().toUpperCase());
		appUser.setUserName(jsonSystemaUserContainer.getUserName());
		appUser.setCompanyCode(companyCode);//fifirm in firm
		appUser.setFallbackCompanyCode(this.getCompanyCodeForLogin()); //as a fallback needed in espedsg use cases
		appUser.setUsrLang(jsonSystemaUserContainer.getUsrLang());
		appUser.setUserAS400(jsonSystemaUserContainer.getUsrAS400());
		appUser.setIntern(jsonSystemaUserContainer.getIntern());
		//customer values
		appUser.setCustNr(jsonSystemaUserContainer.getCustNr());
		appUser.setCustName(jsonSystemaUserContainer.getCustName());
		//tomcat specific port
		appUser.setTomcatPort(jsonSystemaUserContainer.getTcPort());
		
		//logo and banner
		appUser.setLogo(jsonSystemaUserContainer.getLogo());
		appUser.setBanner(jsonSystemaUserContainer.getBanner());
		appUser.setSystemaLogo(jsonSystemaUserContainer.getSystemaLogo());
		appUser.setSignatur(jsonSystemaUserContainer.getSignatur());
		appUser.setFiland(jsonSystemaUserContainer.getFiland());
		appUser.setDftdg(jsonSystemaUserContainer.getDftdg());
		appUser.setAsavd(jsonSystemaUserContainer.getAsavd());
		
		//DEBUG
		/*logger.info("[INFO] user logo:" + appUser.getLogo() );
		logger.info("[INFO] user banner:" + appUser.getBanner() );
		logger.info("[INFO] user sign:" + appUser.getTdsSign() );
		logger.info("[INFO] user Systema logo:" + appUser.getSystemaLogo() );
		//end user - login url
		logger.info("[INFO] servlet host (on Login):" + appUser.getServletHost() );
		*/
		
		//User extensions
		if(jsonSystemaUserContainer.getArkivKodOpdList()!=null){
			appUser.setArkivKodOpdList(jsonSystemaUserContainer.getArkivKodOpdList());
		}
		if(jsonSystemaUserContainer.getArkivKodTurList()!=null){
			appUser.setArkivKodTurList(jsonSystemaUserContainer.getArkivKodTurList());
		}
		if(jsonSystemaUserContainer.getMultiUser()!=null){
			appUser.setMultiUser(jsonSystemaUserContainer.getMultiUser());
		}
		
		//This host parameter below is used for reaching external resources since images or other static resources
		//might be available either for internal ip-addresses or external but not both.
		//If the user reaches the login-page then he/she will reach static resources on this ip-address
		String host = this.getServletHostWithNoPort(request.getHeader("Host"));
		appUser.setServletHostWithoutHttpPrefix(host);
		appUser.setServletHost("http://" + host);
		
		//get Cgi root in case we must have direct access to the AS400 services directly from a JSP (e.g. Use Case for print in eBooking)
		appUser.setHttpCgiRoot(AppConstants.HTTP_ROOT_CGI);
		//get host root for docs (PDF,etc) where the call is done from outside the AS400 env. Usually from JQuery.
		//this variable will be usually exactly the same as httpCgiRoot unless Systema's customer does have own customers outside the range of their internal IP
		appUser.setHttpJQueryDocRoot(AppConstants.HTTP_ROOT_JQUERY_DOCS_ROOT);
		
		//CSS
		appUser.setCssEspedsg(AppConstants.CSS_ESPEDSG);
		appUser.setCssEspedsgMaintenance(AppConstants.CSS_ESPEDSG_MAINTENANCE);
		
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private String getServletHostWithNoPort(String rawValue){
		String retval = rawValue;
		if(rawValue!=null){
			if(rawValue.contains(":")){
				int end = rawValue.indexOf(":");
				retval = rawValue.substring(0,end);
			}
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param companyCode
	 * 
	 * @return
	 */
	private String getRequestUrlKeyParameters(SystemaWebUser appUser, String companyCode){
		StringBuffer urlRequestParamsKeys = null;
		
		if(appUser!=null){
			if( (appUser.getUser()!=null && !"".equals(appUser.getUser())) && (appUser.getPassword()!=null && !"".equals(appUser.getPassword()))){
				urlRequestParamsKeys = new StringBuffer();
				urlRequestParamsKeys.append("user=" + appUser.getUser());
				urlRequestParamsKeys.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "pwd=" + appUser.getPassword());
				if(companyCode!=null){
					urlRequestParamsKeys.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "firma=" + companyCode);
				}
			}
		}
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Sets the domain objects (menu of program lists) in session. 
	 * Must be in session since different applications must get back to the dashboard.
	 *  
	 * @param session
	 * @param model
	 * @param jsonSystemaUserContainer
	 */
	private void setDashboardMenuObjectsInSession(HttpSession session, JsonSystemaUserContainer jsonSystemaUserContainer){
		//SET HEADER RECORDS  (from RPG)
		List list = new ArrayList();
		for (JsonSystemaUserRecord record : jsonSystemaUserContainer.getMenuList()){
			list.add(record);
			/*logger.info("PrTxt:" + record.getPrTxt());
			logger.info("Prog:" + record.getProg());
			logger.info("Veiledning:" + record.getVeiledning());
			logger.info("infoImg:" + record.getInfoImg());
			*/
		}
		//model.put(Constants.DOMAIN_LIST, list);
		session.setAttribute(AppConstants.DOMAIN_LIST, list);

	}
	
	/**
	 * 
	 * @param model
	 * @param errorMessage
	 */
	private void setFatalAS400LoginError(Map model, String errorMessage){
		String errorAS400Suffix = " [AS400 error]";
		model.put(AppConstants.ASPECT_ERROR_MESSAGE, errorMessage + errorAS400Suffix);
	}
	
	
	
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("systemaWebLoginService")
	private SystemaWebLoginService systemaWebLoginService;
	@Autowired
	@Required
	public void setSystemaWebLoginService (SystemaWebLoginService value){ this.systemaWebLoginService = value; }
	public SystemaWebLoginService getSystemaWebLoginService(){ return this.systemaWebLoginService; }
	
	
	@Qualifier ("firmLoginService")
	private FirmLoginService firmLoginService;
	@Autowired
	@Required
	public void setFirmLoginService (FirmLoginService value){ this.firmLoginService = value; }
	public FirmLoginService getFirmLoginService(){ return this.firmLoginService; }
	
	
	@Qualifier ("sadImportTopicListService")
	private SadImportTopicListService sadImportTopicListService;
	@Autowired
	@Required
	public void setSadImportTopicListService (SadImportTopicListService value){ this.sadImportTopicListService = value; }
	public SadImportTopicListService getSadImportTopicListService(){ return this.sadImportTopicListService; }
	
	@Qualifier ("sadExportTopicListService")
	private SadExportTopicListService sadExportTopicListService;
	@Autowired
	@Required
	public void setSadExportTopicListService (SadExportTopicListService value){ this.sadExportTopicListService = value; }
	public SadExportTopicListService getSadExportTopicListService(){ return this.sadExportTopicListService; }
	
	
		
}

	