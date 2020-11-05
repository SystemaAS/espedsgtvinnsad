/**
 * 
 */
package no.systema.tvinn.sad.manifest.express.service;

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
public interface TvinnSadManifestListService {
	public JsonTvinnSadManifestContainer getListContainer(String utfPayload);
	public JsonTvinnSadManifestCargoLinesContainer getListCargolinesContainer(String utfPayload);
	public JsonTvinnSadManifestContainer getListContainerDefaultValues(String utfPayload);
	public JsonTvinnSadManifestRpgContainer getContainerRpgResult(String utfPayload);
	public JsonTvinnSadManifestArchivedDocsContainer getArchiveDocsContainer(String utfPayload);
	public JsonTvinnSadManifestLoggingContainer getLoggingContainer(String utfPayload);
	
}
