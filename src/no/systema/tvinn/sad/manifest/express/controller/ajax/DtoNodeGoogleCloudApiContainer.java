package no.systema.tvinn.sad.manifest.express.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"localwritepath", "Files" })
public class DtoNodeGoogleCloudApiContainer {

	//@JsonProperty("LocalWritePath")
	String localwritepath;
	
	//@JsonProperty("Files")
	List<DtoNodeGoogleCloudApiFile> files = new ArrayList<DtoNodeGoogleCloudApiFile>();
}
