/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.model.jsonjackson;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Nov 2020
 *
 */
@Data
public class JsonTvinnSadManifestLoggingContainer {
	private String user = null;
	private String pro = null;
	private String typ = null;
	private String errMsg = null;
	
	private Collection<JsonTvinnSadManifestLoggingRecord> logg;
	public void setLogg(Collection<JsonTvinnSadManifestLoggingRecord> value){ this.logg = value; }
	public Collection<JsonTvinnSadManifestLoggingRecord> getLogg(){ return logg; }
	
	
}
