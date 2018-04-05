/**
 * 
 */
package no.systema.tvinn.sad.model.external.url;

/**
 * @author oscardelatorre
 * @date Jan 30, 2014
 * 
 */
public final class UrlTvinnSadCountryObject {
	public static final String value = "http://www.skat.dk/SKAT.aspx?oId=1815411";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'SKAT\'); return false";
	public String getValue(){return UrlTvinnSadCountryObject.value; }
	public String getWindowOpenDimensions(){return UrlTvinnSadCountryObject.windowOpenDimensions; }
	
}
