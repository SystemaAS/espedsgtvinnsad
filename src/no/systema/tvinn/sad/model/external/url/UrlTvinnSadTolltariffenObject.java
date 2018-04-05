/**
 * 
 */
package no.systema.tvinn.sad.model.external.url;

/**
 * @author oscardelatorre
 * @date Jan 06, 2015
 * 
 */
public final class UrlTvinnSadTolltariffenObject {
	public static final String value = "http://tolltariffen.toll.no/templates_TAD/Tolltariffen/StartPage.aspx?id=267887&epslanguage=no";
	//public static final String windowOpenDimensions = "window.open(this.href,\'Taric\',\'width=750,height=500,left=5,top=20, scrollbars, resizable'); return false";
	public static final String windowOpenDimensions = "window.open(this.href,\'TAD\'); return false";
	public String getValue(){return UrlTvinnSadTolltariffenObject.value; }
	public String getWindowOpenDimensions(){return UrlTvinnSadTolltariffenObject.windowOpenDimensions; }
	
}
