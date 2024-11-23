/**
 * 
 */
package no.systema.tvinn.sad.digitollv2.service;

import no.systema.tvinn.sad.digitollv2.model.jsonjackson.EoriValidationContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.GeneralUpdateContainer;
import no.systema.tvinn.sad.digitollv2.model.jsonjackson.SadmotfContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestArchivedDocsContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestCargoLinesContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestLoggingContainer;
import no.systema.tvinn.sad.manifest.express.model.jsonjackson.JsonTvinnSadManifestRpgContainer;

/**
 * @author oscardelatorre
 * @date Sep 2020
 *
 */
public interface SadmotfListService {
	public SadmotfContainer getListContainer(String utfPayload);
	public EoriValidationContainer getListContainerEORIValidation(String utfPayload);
	
	/*public SadmomfContainer getListCargolinesContainer(String utfPayload);
	public JsonTvinnSadManifestContainer getListContainerDefaultValues(String utfPayload);
	public JsonTvinnSadManifestRpgContainer getContainerRpgResult(String utfPayload);
	public JsonTvinnSadManifestArchivedDocsContainer getArchiveDocsContainer(String utfPayload);
	public JsonTvinnSadManifestLoggingContainer getLoggingContainer(String utfPayload);
	*/
}
