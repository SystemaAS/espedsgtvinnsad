package no.systema.main.model.jsonjackson;

import java.util.Collection;


/**
 * This class represents the Systema Web eSped general user.
 * It contains the valid user upon the general LOGIN to the Systema Web eSped application
 * 
 * @author oscardelatorre
 *
 */
public class JsonSystemaUserContainer {
	//user name
	private String user = null; 
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}

	private String userName = null; 
	public void setUserName(String value) {  this.userName = value; }
	public String getUserName() { return this.userName;}

	private String intern = null;
	public void setIntern(String value) {  this.intern = value; }
	public String getIntern() { return this.intern;}
	
	private String custNr = null;
	public void setCustNr(String value) {  this.custNr = value; }
	public String getCustNr() { return this.custNr;}
	
	private String custName = null;
	public void setCustName(String value) {  this.custName = value; }
	public String getCustName() { return this.custName;}

	private String usrLang = null;
	public void setUsrLang(String value) {  this.usrLang = value; }
	public String getUsrLang() { return this.usrLang;}
	
	private String usrAS400 = null;
	public void setUsrAS400(String value) {  this.usrAS400 = value; }
	public String getUsrAS400() { return this.usrAS400;}

	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}

	private String usrSpcName = null;
	public void setUsrSpcName(String value) {  this.usrSpcName = value; }
	public String getUsrSpcName() { return this.usrSpcName;}

	private String logo = null;
	public void setLogo(String value) {  this.logo = value; }
	public String getLogo() { return this.logo;}
	
	private String banner = null;
	public void setBanner(String value) {  this.banner = value; }
	public String getBanner() { return this.banner;}
	
	private String systemaLogo = null;
	public void setSystemaLogo(String value) {  this.systemaLogo = value; }
	public String getSystemaLogo() { return this.systemaLogo;}
	
	private String signatur = null;
	public void setSignatur(String value) {  this.signatur = value; }
	public String getSignatur() { return this.signatur;}
	
	private String filand = null;
	public void setFiland(String value) {  this.filand = value; }
	public String getFiland() { return this.filand;}
	
	private String tcPort = null;
	public void setTcPort(String value) {  this.tcPort = value; }
	public String getTcPort() { return this.tcPort;}
	
	private String dftdg = null;
	public void setDftdg(String value) {  this.dftdg = value; }
	public String getDftdg() { return this.dftdg;}
	
	private String asavd = null;
	public void setAsavd(String value) {  this.asavd = value; }
	public String getAsavd() { return this.asavd;}
	
	private Collection<JsonSystemaUserRecord> menuList;
	public void setMenuList(Collection<JsonSystemaUserRecord> value){ this.menuList = value; }
	public Collection<JsonSystemaUserRecord> getMenuList(){ return menuList; }
	
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
