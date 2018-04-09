package no.systema.main.testsuite;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * 
 * @author oscardelatorre
 * Apr 09, 2018
 * 
 */
public class JavaReflectionManager {
	private static JsonSpecialCharactersManager jsonFixMgr = new JsonSpecialCharactersManager();
	private static Logger logger = Logger.getLogger(JavaReflectionManager.class.getName());
	
	private final String GREEN_STATUS = "OK";
	private final String RED_STATUS = "NOK";
	private final int TIMEOUT_LIMIT = 4000;
	private final String ERROR_PREFIX = "ERROR: ";
	
	/**
	 * 
	 * @param objectList
	 * @param urlStoreObj
	 * @param moduleChild
	 */
	public void buildList(List<TestersuiteObject> testersuiteObjectList, Object urlStoreObj){
		try{
			
			//Start with Reflection
			
			Class cl = Class.forName(urlStoreObj.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();
			List<Field> list = Arrays.asList(fields);
			TestersuiteObject testersuiteObject = null;
			//logger.info("before java fields loop: " + cl.getSimpleName());
			for(Field field : list){
				try{
					field.setAccessible(true);
					String value = (String)field.get(urlStoreObj);
					if(value!=null && !"".equals(value)){
						//logger.info(field.getName() + " Value:" + value);
						testersuiteObject = new TestersuiteObject();
						testersuiteObject.setModuleName(value);
						testersuiteObject.setServiceUrl(value);
						
						if(fireFirstSmokeTest(value, testersuiteObject, this.TIMEOUT_LIMIT)){
							testersuiteObject.setStatus(GREEN_STATUS);
							//====================
							//From here on: Note
							//====================
							//THIS 2nd-SMOKE-TEST IS NOT IN USED until further agreement.
							/*
							if(value.contains("syj") && field.getName().contains("UPDATE") ){
								//java services with Update require a valid USER or get a NullPointerException otherwise. 
								//We just leave all CRUD-services as = OK
								testersuiteObject.setStatus(GREEN_STATUS);
							}else{
								
								
								String BASE_URL = value;
								String urlRequestParams = "user=" + appUser.getUser();
								//fire the second smoke test
								String jsonReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
								//logger.info(jsonReturnPayload);
								
								if(jsonReturnPayload!=null && (jsonReturnPayload.contains("user") || jsonReturnPayload.contains("JsonResponseOutputterController") )){
									testersuiteObject.setStatus(GREEN_STATUS);
								}else{
									testersuiteObject.setStatus(RED_STATUS);
									testersuiteObject.setErrMsg(ERROR_PREFIX + "json-payload [null]: second smoke test failure [http: code 500]...");
								}
							
							}
							*/
						}else{
							testersuiteObject.setStatus(RED_STATUS);
							
						}
						
						testersuiteObjectList.add(testersuiteObject);
					}
				}catch(Exception e){
					continue;
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	/**
	 * 
	 * @param url
	 * @param testersuiteObject
	 * @param timeout
	 * @return
	 */
	private boolean fireFirstSmokeTest(String url, TestersuiteObject testersuiteObject, int timeout){
		boolean retval = false;
		try{
			URL u = new URL ( url);
			HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
			huc.setConnectTimeout(TIMEOUT_LIMIT); // x seconds connectTimeout
			huc.setReadTimeout(timeout);
			huc.connect () ; 
			int code = huc.getResponseCode() ;
			//logger.info("CODE:" + code);
			
			retval = true;
		}catch(Exception e){
			testersuiteObject.setErrMsg(ERROR_PREFIX + e.toString());
			logger.info(e.toString());	
		}
		return retval;
	}
}
