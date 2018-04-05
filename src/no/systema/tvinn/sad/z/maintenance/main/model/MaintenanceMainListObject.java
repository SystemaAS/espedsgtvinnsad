/**
 * 
 */
package no.systema.tvinn.sad.z.maintenance.main.model;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Mar 27, 2016
 * 
 */
public class MaintenanceMainListObject  {
	
	private String id = null; 
	public void setId(String value) {  this.id = value; }
	public String getId() { return this.id;}
	
	private Integer idInt = 0; 
	public void setIdInt(Integer value) {  this.idInt = value; }
	public Integer getIdInt() { return idInt;}
	
	private String code = null; 
	public void setCode(String value) {  this.code = value; }
	public String getCode() { return this.code;}
	
	private String subject = null; 
	public void setSubject(String value) {  this.subject = value; }
	public String getSubject() { return this.subject;}
	
	private String text = null; 
	public void setText(String value) {  this.text = value; }
	public String getText() { return this.text;}
	
	private String status = null; 
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String description = null; 
	public void setDescription(String value) {  this.description = value; }
	public String getDescription() { return this.description;}
	
	private String dbTable = null; 
	public void setDbTable(String value) {  this.dbTable = value; }
	public String getDbTable() { return this.dbTable;}
	
	private String pgm = null; 
	public void setPgm(String value) {  this.pgm = value; }
	public String getPgm() { return this.pgm;}
	
	
}
