package no.systema.tvinn.sad.manifest.express.controller.ajax;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = false)
public class DtoNodeGoogleCloudApiFile {
	//@JsonProperty("Companyid")
	String companyid;
	
	//@JsonProperty("Filename")
	String filename;
}
