/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Oct, 2023
 */
@Data
public class SadAvdSignContainer {
	
	private String user = null;
	private String errMsg = null;
	
	private Collection<SadAvdSignRecord> avdelningar = null;
	public void setAvdelningar(Collection<SadAvdSignRecord> value){ this.avdelningar = value;}
	public Collection<SadAvdSignRecord> getAvdelningar(){ return this.avdelningar; }
	
	private Collection<SadAvdSignRecord> signaturer = null;
	public void setSignaturer(Collection<SadAvdSignRecord> value){ this.signaturer = value;}
	public Collection<SadAvdSignRecord> getSignaturer(){ return this.signaturer; }
	
	
}
