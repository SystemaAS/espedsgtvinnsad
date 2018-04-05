/**
 * 
 */
package no.systema.tvinn.sad.model.external.url;

/**
 * 
 * @author oscardelatorre
 * @date Sep 5, 2014
 * 
 * 
 */
public final class UrlISOLanguageObject {
	public static final String value = "http://www.mathguide.de/info/tools/languagecode.html";
	//public static final String windowOpenDimensions = "window.open(this.href,\'ISO 639 1\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'ISO 639 1\'); return false";
	public String getValue(){return UrlISOLanguageObject.value; }
	public String getWindowOpenDimensions(){return UrlISOLanguageObject.windowOpenDimensions; }
	
}
