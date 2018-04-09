/**
 * 
 */
package no.systema.main.testsuite;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 09, 2018
 * 
 */
public class TestersuiteObject {
	
	private String id = null; 
	public void setId(String value) {  this.id = value; }
	public String getId() { return this.id;}
	
	private String moduleName = null; 
	public void setModuleName(String value) {  this.moduleName = value; }
	public String getModuleName() {  return this.moduleName; }
	
	private String text = null; 
	public void setText(String value) {  this.text = value; }
	public String getText() { return this.text;}
	
	private String status = null; 
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String description = null; 
	public void setDescription(String value) {  this.description = value; }
	public String getDescription() { return this.description;}
	//
	private String serviceName = null; 
	public void setServiceName(String value) {  this.serviceName = value; }
	public String getServiceName() { 
		if(serviceUrl!=null){
			String tmp = this.serviceUrl;
			int index = tmp.lastIndexOf("/");
			this.serviceName = tmp.substring(index + 1);
		}
		return this.serviceName;
	}
	
	private String serviceUrl = null; 
	public void setServiceUrl(String value) {  this.serviceUrl = value; }
	public String getServiceUrl() { return this.serviceUrl;}
	
	public String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	
}
