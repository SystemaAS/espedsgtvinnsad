/**
 * 
 */
package no.systema.tvinn.sad.model.external.url;

/**
 * @author oscardelatorre
 * @date Jan 30, 2014
 * 
 */
public final class UrlTvinnSadCurrencyObject {
	public static final String value = "http://www.skat.dk/SKAT.aspx?oId=1658898";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'SKAT\'); return false";
	public String getValue(){return UrlTvinnSadCurrencyObject.value; }
	public String getWindowOpenDimensions(){return UrlTvinnSadCurrencyObject.windowOpenDimensions; }
	
}
