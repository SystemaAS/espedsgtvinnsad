/**
 * 
 */
package no.systema.tvinn.sad.nctsimport.model.jsonjackson.topic.archive;

import java.lang.reflect.Field;
import java.util.*;


/**
 * @author oscardelatorre
 * @date Sep 5, 2014
 *
 */
public class JsonSadNctsImportSpecificTopicArchiveRecord {
	
	
	private String url = null;
	public void setUrl(String value) {  this.url = value; }
	public String getUrl() {return this.url;}
	
	public String documentName = null;
	public String getDocumentName() {
		if(this.url!=null){
			int x = this.url.lastIndexOf("/");
			this.documentName = url.substring(x+1);
		}
		return this.documentName;
	}
	
	private String docType = null;
	public void setDocType(String value) {  this.docType = value; }
	public String getDocType() {return this.docType;}
	
	private String subject = null;
	public void setSubject(String value) {  this.subject = value; }
	public String getSubject() {return this.subject;}
	
	private String additionalInfo = null;
	public void setAdditionalInfo(String value) {  this.additionalInfo = value; }
	public String getAdditionalInfo() {return this.additionalInfo;}
	
	private String createUser = null;
	public void setCreateUser(String value) {  this.createUser = value; }
	public String getCreateUser() {return this.createUser;}
	
	private String createDate = null;
	public void setCreateDate(String value) {  this.createDate = value; }
	public String getCreateDate() {return this.createDate;}
	
	private String createTime = null;
	public void setCreateTime(String value) {  this.createTime = value; }
	public String getCreateTime() {return this.createTime;}
	
	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
