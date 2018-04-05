package no.systema.main.model;

import java.util.Collection;

import no.systema.main.model.jsonjackson.JsonSystemaUserExtensionsArchiveRecord;
import no.systema.main.model.jsonjackson.JsonSystemaUserExtensionsMultiUserSwitchRecord;
import no.systema.main.util.AppConstants;

/**
 * 
 * @author oscardelatorre
 *
 */
public class SystemaWebUser {
	//TDS
	public static final String ACTIVE_MENU_TDS_EXPORT = "TDS_EXPORT";
	public static final String ACTIVE_MENU_NCTS_EXPORT = "NCTS_EXPORT";
	public static final String ACTIVE_MENU_TDS_IMPORT = "TDS_IMPORT";
	public static final String ACTIVE_MENU_NCTS_IMPORT = "NCTS_IMPORT";
	public static final String ACTIVE_MENU_TDS_SIGN_PKI = "SIGN_PKI";
	public static final String ACTIVE_MENU_TDS_CHANGE_PASSWORD = "CHANGE_PASSWORD";
	public static final String ACTIVE_MENU_TDS_ADMIN = "TDS_ADMIN";
	//TDS MAINTENANCE 
	public static final String ACTIVE_MENU_TDS_MAINTENANCE_EXPORT = "TDS_MAINTENANCE_EXPORT";
	public static final String ACTIVE_MENU_TDS_MAINTENANCE_NCTS_EXPORT = "TDS_MAINTENANCE_NCTS_EXPORT";
	public static final String ACTIVE_MENU_TDS_MAINTENANCE_IMPORT = "TDS_MAINTENANCE_IMPORT";
	public static final String ACTIVE_MENU_TDS_MAINTENANCE_NCTS_IMPORT = "TDS_MAINTENANCE_NCTS_IMPORT";
	public static final String ACTIVE_MENU_TDS_MAINTENANCE_FELLES = "TDS_MAINTENANCE_FELLES";
	//SKAT
	public static final String ACTIVE_MENU_SKAT_EXPORT = "SKAT_EXPORT";
	public static final String ACTIVE_MENU_SKAT_NCTS_EXPORT = "SKAT_NCTS_EXPORT";
	public static final String ACTIVE_MENU_SKAT_IMPORT = "SKAT_IMPORT";
	public static final String ACTIVE_MENU_SKAT_NCTS_IMPORT = "SKAT_NCTS_IMPORT";
	public static final String ACTIVE_MENU_SKAT_ADMIN = "SKAT_ADMIN";
	//SKAT MAINTENANCE 
	public static final String ACTIVE_MENU_SKAT_MAINTENANCE_EXPORT = "SKAT_MAINTENANCE_EXPORT";
	public static final String ACTIVE_MENU_SKAT_MAINTENANCE_NCTS_EXPORT = "SKAT_MAINTENANCE_NCTS_EXPORT";
	public static final String ACTIVE_MENU_SKAT_MAINTENANCE_IMPORT = "SKAT_MAINTENANCE_IMPORT";
	public static final String ACTIVE_MENU_SKAT_MAINTENANCE_NCTS_IMPORT = "SKAT_MAINTENANCE_NCTS_IMPORT";
	public static final String ACTIVE_MENU_SKAT_MAINTENANCE_FELLES = "SKAT_MAINTENANCE_FELLES";
	//TVINN (SAD)
	public static final String ACTIVE_MENU_TVINN_SAD_EXPORT = "TVINN_SAD_EXPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_NCTS_EXPORT = "TVINN_SAD_NCTS_EXPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_IMPORT = "TVINN_SAD_IMPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_NCTS_IMPORT = "TVINN_SAD_NCTS_IMPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_ADMIN = "TVINN_SAD_ADMIN";
	public static final String ACTIVE_MENU_TVINN_SAD_AVGGRUNNLAG_EXTERNAL = "TVINN_SAD_AVGGRUNNLAG_EXTERNAL";
	
	//TVINN MAINTENANCE (SAD)
	public static final String ACTIVE_MENU_TVINN_SAD_MAINTENANCE_EXPORT = "TVINN_SAD_MAINTENANCE_EXPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_MAINTENANCE_NCTS_EXPORT = "TVINN_SAD_MAINTENANCE_NCTS_EXPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_MAINTENANCE_IMPORT = "TVINN_SAD_MAINTENANCE_IMPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_MAINTENANCE_NCTS_IMPORT = "TVINN_SAD_MAINTENANCE_NCTS_IMPORT";
	public static final String ACTIVE_MENU_TVINN_SAD_MAINTENANCE_FELLES = "TVINN_SAD_MAINTENANCE_FELLES";
	
	//MAIN MAINTENANCE
	public static final String ACTIVE_MENU_MAIN_MAINTENANCE = "MAIN_MAINTENANCE";
	
	//TEST SUITES
	public static final String ACTIVE_MENU_TEST_SUITES = "TEST_SUITES";
		
	//TRANSPORTDISP
	public static final String ACTIVE_MENU_TRANSPORT_DISP = "TRANSPORT_DISP";
	//SPØRRING PÅ OPPDRAG
	public static final String ACTIVE_MENU_SPORRING_OPPDRAG = "SPORRING_OPPDRAG";
	//FRAKTKALKULATOR
	public static final String ACTIVE_MENU_FRAKTKALKULATOR = "FRAKTKALKULATOR";
	//EFAKTURA
	public static final String ACTIVE_MENU_EFAKTURA = "EFAKTURA";
	//EBOOKING
	public static final String ACTIVE_MENU_EBOOKING = "EBOOKING";
	//OPPDRAGSREGISTRERING - TROR (TransportOppdrag)
	public static final String ACTIVE_MENU_TROR = "TROR";
		
	//----------------------------------------------------------------------
	//In order to get the login host (request.getHost()) (when applicable)
	//----------------------------------------------------------------------
	private String servletHost = null;
	public void setServletHost(String value) {  this.servletHost = value; }
	public String getServletHost() { return this.servletHost;}
	
	private String servletHostWithoutHttpPrefix = null;
	public void setServletHostWithoutHttpPrefix(String value) {  this.servletHostWithoutHttpPrefix = value; }
	public String getServletHostWithoutHttpPrefix() { return this.servletHostWithoutHttpPrefix;}
	
	//In order to get the application.properties: http.as400.root.cgi
	private String httpCgiRoot = null;
	public void setHttpCgiRoot(String value) {  this.httpCgiRoot = value; }
	public String getHttpCgiRoot() { return this.httpCgiRoot;}
	
	//In order to get the application.properties: http.as400.root.jquery.docs.host
	private String httpJQueryDocRoot = null;
	public void setHttpJQueryDocRoot(String value) {  this.httpJQueryDocRoot = value; }
	public String getHttpJQueryDocRoot() { return this.httpJQueryDocRoot;}
	
	private String os = null; 
	public void setOs(String value) {  this.os = value; }
	public String getOs() { return this.os;}
	
	private String user = null; 
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String userName = null; 
	public void setUserName(String value) {  this.userName = value; }
	public String getUserName() { return this.userName;}
	
	private String password = null;
	public void setPassword(String value) {  this.password = value; }
	public String getPassword() { return this.password;}
	
	private String encryptedPassword = null;
	public void setEncryptedPassword(String value) {  this.encryptedPassword = value; }
	public String getEncryptedPassword() { return this.encryptedPassword;}
	
	private String companyCode = null; 
	public void setCompanyCode(String value) {  this.companyCode = value; }
	public String getCompanyCode() { return this.companyCode;}
	
	private String fallbackCompanyCode = null; 
	public void setFallbackCompanyCode(String value) {  this.fallbackCompanyCode = value; }
	public String getFallbackCompanyCode() { return this.fallbackCompanyCode;}
	
	private String cssEspedsg = "espedsg.css"; 
	public void setCssEspedsg(String value) {  this.cssEspedsg = value; }
	public String getCssEspedsg() { return this.cssEspedsg;}
	
	private String cssEspedsgMaintenance = "espedsgmaintenance.css"; 
	public void setCssEspedsgMaintenance(String value) {  this.cssEspedsgMaintenance = value; }
	public String getCssEspedsgMaintenance() { return this.cssEspedsgMaintenance;}
	
	private String cssEspedsgTestersuite = "espedsgmaintenance.css"; 
	public void setCssEspedsgTestersuite(String value) {  this.cssEspedsgTestersuite = value; }
	public String getCssEspedsgTestersuite() { return this.cssEspedsgTestersuite;}
	
	private String tomcatPort = null; 
	public void setTomcatPort(String value) {  this.tomcatPort = value; }
	public String getTomcatPort() { return this.tomcatPort;}
	
	private String espedsgLoginTitle = "Systema AS - eSpedsg Application"; 
	public void setEspedsgLoginTitle(String value) {  this.espedsgLoginTitle = value; }
	public String getEspedsgLoginTitle() { return this.espedsgLoginTitle;}
	
	
	//---------------------------------------------
	//Return parameters after valid login in AS400
	//---------------------------------------------
	private String httpProtocol = AppConstants.HTTP_PROTOCOL;
	public String getHttpProtocol() { return this.httpProtocol;}
	
	private String versionEspedsg = AppConstants.VERSION_ESPSEDSG;
	public String getVersionEspedsg() { return this.versionEspedsg;}
	
	private String versionSpring = AppConstants.VERSION_SPRING;
	public String getVersionSpring() { return this.versionSpring;}
	

	private String userAS400 = null;
	public void setUserAS400(String value) {  this.userAS400 = value; }
	public String getUserAS400() { return this.userAS400;}

	private String pwAS400 = null;
	public void setPwAS400(String value) {  this.pwAS400 = value; }
	public String getPwAS400() { return this.pwAS400;}

	//This field is used when the user has been authorized for TDS ("Y") 
	private String authorizedTdsUserAS400 = null;
	public void setAuthorizedTdsUserAS400(String value) {  this.authorizedTdsUserAS400 = value; }
	public String getAuthorizedTdsUserAS400() { return this.authorizedTdsUserAS400;}
	
	//This field is used when the user has been authorized for TDS - signature PKI ("Y") 
	private String authorizedTdsSignPkiUserAS400 = null;
	public void setAuthorizedTdsSignPkiUserAS400(String value) {  this.authorizedTdsSignPkiUserAS400 = value; }
	public String getAuthorizedTdsSignPkiUserAS400() { return this.authorizedTdsSignPkiUserAS400;}
	
	//This field is used when the user has been authorized for TDS - signature PKI for second time 
	private String authenticatedTdsSignPkiUserAS400 = null;
	public void setAuthenticatedTdsSignPkiUserAS400(String value) {  this.authenticatedTdsSignPkiUserAS400 = value; }
	public String getAuthenticatedTdsSignPkiUserAS400() { return this.authenticatedTdsSignPkiUserAS400;}
	
	//This field is used when the user has been authenticated for TDS - signature PKI by typing the valid SMS-one-time code. 
	private String authenticatedTdsSignPkiSmsCode = null;
	public void setAuthenticatedTdsSignPkiSmsCode(String value) {  this.authenticatedTdsSignPkiSmsCode = value; }
	public String getAuthenticatedTdsSignPkiSmsCode() { return this.authenticatedTdsSignPkiSmsCode;}
	
	//This field is used when the user has been authorized for SKAT ("Y") 
	private String authorizedSkatUserAS400 = null;
	public void setAuthorizedSkatUserAS400(String value) {  this.authorizedSkatUserAS400 = value; }
	public String getAuthorizedSkatUserAS400() { return this.authorizedSkatUserAS400;}
	
	//This field is used when the user has been authorized for TVINN-SAD ("Y") 
	private String authorizedTvinnSadUserAS400 = null;
	public void setAuthorizedTvinnSadUserAS400(String value) {  this.authorizedTvinnSadUserAS400 = value; }
	public String getAuthorizedTvinnSadUserAS400() { return this.authorizedTvinnSadUserAS400;}
		
	
	private String usrLang = null;
	public void setUsrLang(String value) {  this.usrLang = value; }
	public String getUsrLang() { return this.usrLang;}

	private String custNr = null;
	public void setCustNr(String value) {  this.custNr = value; }
	public String getCustNr() { return this.custNr;}

	private String custName = null;
	public void setCustName(String value) {  this.custName = value; }
	public String getCustName() { return this.custName;}
	
	private String logo = null;
	public void setLogo(String value) {  this.logo = value; }
	public String getLogo() { return this.logo;}
	
	private String banner = null;
	public void setBanner(String value) {  this.banner = value; }
	public String getBanner() { return this.banner;}
	
	private String systemaLogo = null;
	public void setSystemaLogo(String value) {  this.systemaLogo = value; }
	public String getSystemaLogo() { return this.systemaLogo;}
	
	private String usrSpcName = null;
	public void setUsrSpcName(String value) {  this.usrSpcName = value; }
	public String getUsrSpcName() { return this.usrSpcName;}
	
	private String intern = null;
	public void setIntern(String value) {  this.intern = value; }
	public String getIntern() { return this.intern;}
	

	//TDS sign
	private String tdsSign = null;
	public void setTdsSign(String value) {  this.tdsSign = value; }
	public String getTdsSign() { return this.tdsSign;}

	//SKAT sign
	private String skatSign = null;
	public void setSkatSign(String value) {  this.skatSign = value; }
	public String getSkatSign() { return this.skatSign;}
	
	//TVINN sign
	private String tvinnSadSign = null;
	public void setTvinnSadSign(String value) {  this.tvinnSadSign = value; }
	public String getTvinnSadSign() { return this.tvinnSadSign;}

	//Work with trips or other external module
	private String signatur = null;
	public void setSignatur(String value) {  this.signatur = value; }
	public String getSignatur() { return this.signatur;}

	private String filand = null;
	public void setFiland(String value) {  this.filand = value; }
	public String getFiland() { return this.filand;}
	
	private String dftdg = null;
	public void setDftdg(String value) {  this.dftdg = value; }
	public String getDftdg() { return this.dftdg;}
	
	private String asavd = null;
	public void setAsavd(String value) {  this.asavd = value; }
	public String getAsavd() { return this.asavd;}
	
	private String errMsg = null;
	public void setErrMsg(String value){ this.errMsg=value;  }
	public String getErrMsg(){ return this.errMsg;  }
	
	//UrlStore properties (for each module). In order to be able to see the UrlStore-component (Reflection) for each module.
	//Started with Lastetorg
	private String urlStoreProps = null;
	public void setUrlStoreProps(String value){ this.urlStoreProps=value;  }
	public String getUrlStoreProps(){ return this.urlStoreProps;  }
	
	/**
	 * Check if user is under a Norwegian firma.
	 * 
	 * @return, true, if filand = NO, else false
	 */
	public boolean isNorwegianFirma() {
		if("NO".equals(filand)) {
			return true;
		} else {
			return false;
		}
	}
	
	//---------
	//Aspects
	//---------
	//Active menu only in dashboard (to highlight the item)
	private String activeMenuInDashboard = null;
	public void setActiveMenuInDashboard(String value) {  this.activeMenuInDashboard = value; }
	public String getActiveMenuInDashboard() { return this.activeMenuInDashboard;}
	
	//Active menu in specific module (to highlight the menu button or alike)
	private String activeMenu = null;
	public void setActiveMenu(String value) {  this.activeMenu = value; }
	public String getActiveMenu() { return this.activeMenu;}
	
	private Collection<JsonSystemaUserExtensionsArchiveRecord> arkivKodOpdList;
	public void setArkivKodOpdList(Collection<JsonSystemaUserExtensionsArchiveRecord> value){ this.arkivKodOpdList = value; }
	public Collection<JsonSystemaUserExtensionsArchiveRecord> getArkivKodOpdList(){ return arkivKodOpdList; }
	
	private Collection<JsonSystemaUserExtensionsArchiveRecord> arkivKodTurList;
	public void setArkivKodTurList(Collection<JsonSystemaUserExtensionsArchiveRecord> value){ this.arkivKodTurList = value; }
	public Collection<JsonSystemaUserExtensionsArchiveRecord> getArkivKodTurList(){ return arkivKodTurList; }
	
	private Collection<JsonSystemaUserExtensionsMultiUserSwitchRecord> multiUser;
	public void setMultiUser(Collection<JsonSystemaUserExtensionsMultiUserSwitchRecord> value){ this.multiUser = value; }
	public Collection<JsonSystemaUserExtensionsMultiUserSwitchRecord> getMultiUser(){ return multiUser; }
	
	

}
