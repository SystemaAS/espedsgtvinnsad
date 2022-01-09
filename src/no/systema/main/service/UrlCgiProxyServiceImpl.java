/**
 * 
 */
package no.systema.main.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//java net
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import no.systema.main.context.TdsAppContext;
import no.systema.main.util.EncodingTransformer;
/**
 * 
 * @author oscardelatorre
 *
 */
public class UrlCgiProxyServiceImpl implements UrlCgiProxyService{
	private static Logger logger = LoggerFactory.getLogger(UrlCgiProxyServiceImpl.class.getName());
	private static final String ENCODING_JSON_UTF8 = "UTF8";
	private static final String ENCODING_STREAMS_UTF8 = "UTF-8";

	private ApplicationContext context;
	
	/**
	 * @param urlStr
	 * @return 
	 * 
	 */
	public Resource getResource(String urlStr){
		this.context = TdsAppContext.getApplicationContext();
		return this.context.getResource(urlStr);
	}
	
	/**
	 * Returns a content JSON-payload from a http request via Html - GET
	 * 
	 * @param urlStr
	 * @return
	 */
	public String getJsonContent(String urlStr){
		
		this.context = TdsAppContext.getApplicationContext();
		Resource resource = this.context.getResource(urlStr);
		StringBuffer buffer = new StringBuffer();
		String utfPayload = null;
		
		try{
			InputStream is = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
	 
			String line;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
				//logger.info(line);
       	  	} 
			br.close();
			
			//JSON automatic converstion requires UTF8 in order to work. We must convert all responses to UTF8
			EncodingTransformer transformer = new EncodingTransformer();
			utfPayload = transformer.transformToJSONTargetEncoding(buffer.toString(), UrlCgiProxyServiceImpl.ENCODING_JSON_UTF8);
			
			//utfPayload = buffer.toString();
			
			//logger.info(utfPayload);
	 
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.info("Error:", e);
    	}
		
		return utfPayload;
	}
	
	/**
	 * Returns a content JSON-payload from a http request via POST
	 * 
	 * @param urlStr
	 * @param urlParameters
	 * @return
	 */
	public String getJsonContent(String urlStr, String urlParameters){
		StringBuffer buffer = new StringBuffer();
		String utfPayload = null;
		
		try{
			
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			//Open writer
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), ENCODING_STREAMS_UTF8);
			writer.write(urlParameters);
			writer.flush();
			//Open reader
			String line = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING_STREAMS_UTF8));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				//logger.info(line);
			}
			writer.close();
			reader.close(); 
			
			EncodingTransformer transformer = new EncodingTransformer();
			utfPayload = transformer.transformToJSONTargetEncoding(buffer.toString(), UrlCgiProxyServiceImpl.ENCODING_JSON_UTF8);
			//logger.info(utfPayload);
			
		}catch(Exception e){
    		e.printStackTrace();
    		logger.info("Error:", e);
    		
    	}
		return utfPayload;
	}
	
	/**
	 * This method converts any JSON string to a VALID JSON UTF-8 String...
	 * 
	 * @param jsonPayloadOriginal
	 * @return
	 */
	public String getJsonContentFromJsonRawString(String jsonPayloadOriginal){
		String utfPayload = null;
		
		try{
			//JSON automatic converstion requires UTF8 in order to work. We must convert all responses to UTF8
			EncodingTransformer transformer = new EncodingTransformer();
			utfPayload = transformer.transformToJSONTargetEncoding(jsonPayloadOriginal, UrlCgiProxyServiceImpl.ENCODING_JSON_UTF8);
			
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.info("Error:", e);
    	}
		
		return utfPayload;
	}
	
	
}
