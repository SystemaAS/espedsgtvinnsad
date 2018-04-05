/**
 * 
 */
package no.systema.main.service;

import org.springframework.core.io.Resource;

/**
 * 
 * This interface lends a cgi request to the back-end usually returning a Payload String (JSON or other list structure)
 * 
 * @author oscardelatorre
 *
 */
public interface UrlCgiProxyService {
	public Resource getResource(String urlStr);
	public String getJsonContent(String urlStr);
	public String getJsonContent(String urlStr, String urlParams);
	public String getJsonContentFromJsonRawString(String jsonPayloadOriginal);
	
}
