package no.systema.tvinn.sad.manifest.express.controller.ajax;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder(alphabetic = false)
public class DtoNodeGoogleCloudApiFileTest {
	//@JsonProperty("Companyid")
	String companyid = "a12";
	
	//@JsonProperty("Filename")
	String filename = "si201300170000658WtGAj87xw0.pdf";
}
